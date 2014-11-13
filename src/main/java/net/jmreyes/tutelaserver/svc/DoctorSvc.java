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

import javax.servlet.http.HttpServletResponse;

import net.jmreyes.tutelaserver.api.VideoSvcApi;
import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Medication;
import net.jmreyes.tutelaserver.model.Video;
import net.jmreyes.tutelaserver.repository.DoctorRepository;
import net.jmreyes.tutelaserver.repository.MedicationRepository;
import net.jmreyes.tutelaserver.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
public class DoctorSvc {	

	@Autowired
	private DoctorRepository doctors;	
	
	// Receives GET requests to /video and returns the current
	// list of videos in memory. Spring automatically converts
	// the list of videos to JSON because of the @ResponseBody
	// annotation.
	@RequestMapping(value = "/me/doctors", method = RequestMethod.GET)
	public @ResponseBody Collection<Doctor> getDoctorList() {
		return Lists.newArrayList(doctors.findAll());
	}
	
	
}
