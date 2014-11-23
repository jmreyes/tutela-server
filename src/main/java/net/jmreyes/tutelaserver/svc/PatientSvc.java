/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package net.jmreyes.tutelaserver.svc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.jmreyes.tutelaserver.api.PatientSvcApi;
import net.jmreyes.tutelaserver.model.Alert;
import net.jmreyes.tutelaserver.model.CheckIn;
import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Patient;
import net.jmreyes.tutelaserver.model.PatientDetails;
import net.jmreyes.tutelaserver.model.Symptom;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.model.Treatment.EmbeddedSymptom;
import net.jmreyes.tutelaserver.model.extra.Answer;
import net.jmreyes.tutelaserver.model.extra.CheckInProposal;
import net.jmreyes.tutelaserver.model.extra.MyDoctor;
import net.jmreyes.tutelaserver.model.extra.MyMedication;
import net.jmreyes.tutelaserver.repository.AlertRepository;
import net.jmreyes.tutelaserver.repository.CheckInRepository;
import net.jmreyes.tutelaserver.repository.DoctorRepository;
import net.jmreyes.tutelaserver.repository.PatientDetailsRepository;
import net.jmreyes.tutelaserver.repository.SymptomRepository;
import net.jmreyes.tutelaserver.repository.TreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
public class PatientSvc {
	
	@Autowired
	private TreatmentRepository treatmentRepo;
	
	@Autowired
	private PatientDetailsRepository patientDetailsRepo;
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private SymptomRepository symptomRepo;
	
	@Autowired
	private CheckInRepository checkInRepo;
	
	@Autowired
	private AlertRepository alertRepo;
	
	
	@RequestMapping(value = PatientSvcApi.PATIENT_MEDICATION, method = RequestMethod.GET)
	public @ResponseBody Collection<MyMedication> getMyMedicationList() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<Treatment> treatmentList = treatmentRepo.findByPatientId(patient.getId());
		
		return MyMedication.createListFromTreatments(treatmentList);
	}
	
	@RequestMapping(value = PatientSvcApi.PATIENT_MEDICATION + "/{treatmentId}/{medicationId}", method = RequestMethod.GET)
	public @ResponseBody MyMedication getOneMedication(@PathVariable("treatmentId") String treatmentId,
			@PathVariable("medicationId") String medicationId, HttpServletResponse response) {		
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Treatment treatment = treatmentRepo.findByIdAndPatientId(treatmentId, patient.getId());

		if (treatment == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return MyMedication.createFromTreatment(treatment, medicationId);
	}
	
	@RequestMapping(value = PatientSvcApi.PATIENT_DOCTORS, method = RequestMethod.GET)
	public @ResponseBody Collection<MyDoctor> getDoctors() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<PatientDetails> patientDetails = patientDetailsRepo.findByPatientId(patient.getId());
		
		return MyDoctor.createListFromPatientDetails(patientDetails);
	}
	
	@RequestMapping(value = PatientSvcApi.PATIENT_DOCTORS + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Doctor getDoctor(@PathVariable("id") String id, HttpServletResponse response) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Doctor doctor = null;
		PatientDetails pd = patientDetailsRepo.findByPatientIdAndDoctorId(patient.getId(), id);
		
		if (pd != null) {
			doctor = doctorRepo.findById(id);
		}

		if (doctor == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return doctor;
	}
	
	@RequestMapping(value = PatientSvcApi.PATIENT_CHECKIN, method = RequestMethod.GET)
	public @ResponseBody Collection<CheckInProposal> getCheckInProposals() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<Treatment> treatments = treatmentRepo.findByPatientId(patient.getId());
				
		ArrayList<String> symptomIds = new ArrayList<String>();
		for (Treatment t : treatments) {
			for (EmbeddedSymptom es : t.getSymptoms()) {
				symptomIds.add(es.getSymptomId());
			}
		}
		
		Collection<Symptom> symptoms = symptomRepo.findByIdIn(symptomIds);		

		HashMap<String, Symptom> symptomsMap = new HashMap<String, Symptom>();
		for (Symptom s : symptoms) {
			symptomsMap.put(s.getId(), s);
		}		

		Collection<CheckInProposal> cp = CheckInProposal.createFromTreatments(treatments, symptomsMap);
				
		return cp;		
	}
	
	@RequestMapping(value = PatientSvcApi.PATIENT_CHECKIN, method = RequestMethod.POST)
	public @ResponseBody void postCheckIn(@RequestBody Collection<CheckIn> checkIns) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// TODO: validation
		
		for (CheckIn c : checkIns) {
			c.setPatientId(c.getPatientId());
			c.setDate(new Date());
			checkInRepo.save(c);
			
			createAlertIfNeeded(c);
		}
	}
	

	
	private void createAlertIfNeeded(CheckIn checkIn) {
		Collection<CheckIn> checkInsForTreatment = checkInRepo.findByTreatmentId(checkIn.getTreatmentId());
		
		for (CheckIn.EmbeddedSymptom es : checkIn.getSymptoms()) {
			Symptom s = symptomRepo.findById(es.getSymptomId());
			
			if (s == null) continue;
			
			Collection<Symptom.EmbeddedAlert> eas = s.getAlerts();			
			
			for (Symptom.EmbeddedAlert ea : eas) {
				int ansIndex = ea.getAnsIndex();
				int timeMs = ea.getHours() * 60*60*1000;
				long initialTime = checkIn.getDate().getTime();
				boolean sendAlert = false;
				
				checkInLoop:
				for (CheckIn c : checkInsForTreatment) {
					for (CheckIn.EmbeddedSymptom es2 : c.getSymptoms()) {
						if (es2.getSymptomId().equals(es.getSymptomId())) {
							if (es2.getAnsIndex() < ansIndex) {
								break checkInLoop;
							} else if (initialTime - c.getDate().getTime() > timeMs) {
								sendAlert = true;
								break checkInLoop;
							}
						}
					}
				}
				
				if (sendAlert) {
					PatientDetails pd = patientDetailsRepo.findByPatientIdAndDoctorId(checkIn.getPatientId(), s.getDoctorId());
					Collection<Answer> answers = s.getAnswers();
					String ansText = null;
					for (Answer a : answers) {
						if (a.getAnsIndex() == ansIndex) {
							ansText = a.getAnsText();
						}
					}
					Alert alert = new Alert(null, s.getDoctorId(), checkIn.getPatientId(), pd.getFirstName() + " " + pd.getLastName(),
							checkIn.getTreatmentId(), false, ea.getHours(), ansText, new Date());
					alertRepo.save(alert);
				}
			}
		}		
	}
}
