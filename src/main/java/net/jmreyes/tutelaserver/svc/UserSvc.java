package net.jmreyes.tutelaserver.svc;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.Patient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserSvc {	
	
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
