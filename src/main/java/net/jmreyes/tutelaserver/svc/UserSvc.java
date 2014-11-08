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
import java.util.List;

import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Patient;
import net.jmreyes.tutelaserver.repository.DoctorRepository;
import net.jmreyes.tutelaserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserSvc {
	

	@Autowired
	private UserRepository users;
	
	@Autowired
	private DoctorRepository doctors;
	
	@RequestMapping(value = "/users" + "/{username}", method = RequestMethod.GET)
	public @ResponseBody Patient findById(@PathVariable("username") String username) {
		return users.findByUsername(username);
	}
	
	@RequestMapping(value = "/user/me/role", method = RequestMethod.GET)
	public @ResponseBody String getMyRole() {
		String role = null;
		Collection<GrantedAuthority> authorities = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof Patient) {
			Patient activeUser = (Patient) principal;
			authorities = activeUser.getAuthorities();        
		} else if (principal instanceof Doctor) {
			Doctor activeUser = (Doctor) principal;
			authorities = activeUser.getAuthorities();        
		}
		
        for (GrantedAuthority auth : authorities) {
        	role = auth.getAuthority();
        }
        
        return role;        
	}
}
