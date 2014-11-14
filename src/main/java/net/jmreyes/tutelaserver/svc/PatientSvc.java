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

import java.util.Collection;

import net.jmreyes.tutelaserver.api.PatientSvcApi;
import net.jmreyes.tutelaserver.model.Patient;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.repository.PatientRepository;
import net.jmreyes.tutelaserver.repository.TreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
public class PatientSvc {	

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private TreatmentRepository treatmentRepo;	
	
	@RequestMapping(value = PatientSvcApi.PATIENT_TREATMENTS, method = RequestMethod.GET)
	public @ResponseBody Collection<Treatment> getTreatmentList() {
		System.out.println("HOLA");
		
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println(patient.getId());
		
		return Lists.newArrayList(treatmentRepo.findByPatientId(patient.getId()));
	}	
}
