package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.model.User;
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
	private UserRepository users;
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("Looking for user "+username+" in CustomUserDetailsService");
		
		User u = users.findByUsername(username);		
		
		return u;		
	}
}
