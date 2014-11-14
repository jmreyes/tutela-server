package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Patient;
import net.jmreyes.tutelaserver.model.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private VideoRepository videos;	
	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private DoctorRepository doctors;
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("Looking for user "+username+" in CustomUserDetailsService");
		
		Patient u = patients.findByUsername(username);		
		if (u != null) return u;
		
		Doctor d = doctors.findByUsername(username);		
		if (d != null) return d;
		
		return u;		
	}
}
