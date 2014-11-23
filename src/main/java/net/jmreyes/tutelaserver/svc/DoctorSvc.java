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
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.jmreyes.tutelaserver.api.DoctorSvcApi;
import net.jmreyes.tutelaserver.model.Alert;
import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Medication;
import net.jmreyes.tutelaserver.model.PatientDetails;
import net.jmreyes.tutelaserver.model.Symptom;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.model.extra.DoctorStatus;
import net.jmreyes.tutelaserver.repository.AlertRepository;
import net.jmreyes.tutelaserver.repository.MedicationRepository;
import net.jmreyes.tutelaserver.repository.PatientDetailsRepository;
import net.jmreyes.tutelaserver.repository.SymptomRepository;
import net.jmreyes.tutelaserver.repository.TreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DoctorSvc {
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	private AlertRepository alertRepo;
	
	@Autowired
	private PatientDetailsRepository patientDetailsRepo;
	
	@Autowired
	private SymptomRepository symptomRepo;
	
	@Autowired
	private MedicationRepository medicationRepo;
	
	@Autowired
	private TreatmentRepository treatmentRepo;
		
	@RequestMapping(value = DoctorSvcApi.DOCTOR_STATUS, method = RequestMethod.GET)
	public @ResponseBody DoctorStatus getStatus() {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println(doctor.getId());

		String username = doctor.getUsername();
		String name = doctor.getFirstName() + " " + doctor.getLastName();
		Collection<Alert> alerts = alertRepo.findByDoctorIdAndSeenFalse(doctor.getId());
		
		return new DoctorStatus(username, name, alerts.size());
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_ALERTS, method = RequestMethod.GET)
	public @ResponseBody Collection<Alert> getAlerts() {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		return alertRepo.findByDoctorId(doctor.getId());
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_ALERTS_MARK_SEEN, method = RequestMethod.POST)
	public @ResponseBody void postAlertsMarkAsSeen() {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("doctorId").is(doctor.getId()));
		
		Update update = new Update();
		update.set("seen", true);
		
		mongoOperations.updateMulti(query, update, Alert.class);
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_PATIENTS, method = RequestMethod.GET)
	public @ResponseBody Collection<PatientDetails> getPatients() {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		return patientDetailsRepo.findByDoctorId(doctor.getId());
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_PATIENTS + "/{id}", method = RequestMethod.GET)
	public @ResponseBody PatientDetails getPatient(@PathVariable("id") String id, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		PatientDetails result = patientDetailsRepo.findByPatientIdAndDoctorId(id, doctor.getId());
		
		if (result == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return result;
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_PATIENTS + "/{id}", method = RequestMethod.PUT)
	public @ResponseBody void updatePatient(@PathVariable("id") String id,
			@RequestBody PatientDetails newPatientDetails, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("doctorId").is(doctor.getId()).andOperator(Criteria.where("patientId").is(id)));
				
		Update update = new Update();
		update.set("treatmentId", newPatientDetails.getTreatmentId());
		update.set("mrn", newPatientDetails.getMrn());
		update.set("firstName", newPatientDetails.getFirstName());
		update.set("lastName", newPatientDetails.getLastName());
		update.set("dateOfBirth", newPatientDetails.getDateOfBirth());

		mongoOperations.findAndModify(query, update, PatientDetails.class);		
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_SYMPTOMS, method = RequestMethod.GET)
	public @ResponseBody Collection<Symptom> getSymptoms() {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return symptomRepo.findByDoctorId(doctor.getId());
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_SYMPTOMS, method = RequestMethod.POST)
	public @ResponseBody void postSymptom(@RequestBody Symptom symptom, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		symptom.setDoctorId(doctor.getId());
		symptomRepo.save(symptom);		
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_SYMPTOMS + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Symptom getSymptom(@PathVariable("id") String id, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Symptom result = symptomRepo.findByIdAndDoctorId(id, doctor.getId());
		
		if (result == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return result;
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_MEDICATION, method = RequestMethod.GET)
	public @ResponseBody Collection<Medication> getMedication() {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return medicationRepo.findByDoctorId(doctor.getId());
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_MEDICATION, method = RequestMethod.POST)
	public @ResponseBody void postMedication(@RequestBody Medication medication, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		medication.setDoctorId(doctor.getId());
		medicationRepo.save(medication);		
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_MEDICATION + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Medication getOneMedication(@PathVariable("id") String id, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Medication result = medicationRepo.findByIdAndDoctorId(id, doctor.getId());
		
		if (result == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return result;
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_TREATMENT + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Treatment getTreatment(@PathVariable("id") String id, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Treatment result = treatmentRepo.findByIdAndDoctorId(id, doctor.getId());
		
		if (result == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return result;
	}
	
	@RequestMapping(value = DoctorSvcApi.DOCTOR_TREATMENT, method = RequestMethod.POST)
	public @ResponseBody void postTreatment(@RequestBody Treatment treatment, HttpServletResponse response) {
		Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		treatment.setDoctorId(doctor.getId());
		treatment.setDoctorName(doctor.getFirstName() + " " + doctor.getLastName());
		
		PatientDetails pd = patientDetailsRepo.findByPatientIdAndDoctorId(treatment.getPatientId(), doctor.getId());		

		if (pd == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		String treatmentIdInPd = pd.getTreatmentId();		
		if (treatmentIdInPd != null) treatment.setId(treatmentIdInPd);
		
		Treatment savedTreatment = treatmentRepo.save(treatment);
		
		// Update reference to Treatment in PatientDetails if necessary
		if (treatmentIdInPd == null) {
			pd.setTreatmentId(savedTreatment.getId());
			patientDetailsRepo.save(pd);
		}
	}
}
