package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private DoctorRepository doctors;
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Patient u = patients.findByUsername(username);		
		if (u != null) return u;
		
		Doctor d = doctors.findByUsername(username);		
		if (d != null) return d;
		
		return u;		
	}
}
