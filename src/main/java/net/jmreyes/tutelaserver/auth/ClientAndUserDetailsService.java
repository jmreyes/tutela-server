/* 
 **
 ** Copyright 2014, Jules White
 **
 ** 
 */
package net.jmreyes.tutelaserver.auth;

import net.jmreyes.tutelaserver.repository.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;

/**
 * A class that combines a UserDetailsService and ClientDetailsService
 * into a single object.
 * 
 * @author jules
 *
 */
public class ClientAndUserDetailsService implements UserDetailsService,
		ClientDetailsService {

	private final ClientDetailsService clients_;

	@Autowired
	private CustomUserDetailsService users_;
	
	private final ClientDetailsUserDetailsService clientDetailsWrapper_;

	public ClientAndUserDetailsService() throws Exception {
		super();

		clients_ = new InMemoryClientDetailsServiceBuilder()
				// Create a client that has "read" and "write" access to the
				// video service
				.withClient("mobile")
				.authorizedGrantTypes("password")
				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
				.scopes("read", "write")
				.resourceIds("video")
				.and()
				// Create a second client that only has "read" access to the
				// video service
				.withClient("mobileReader").authorizedGrantTypes("password")
				.authorities("ROLE_CLIENT").scopes("read").resourceIds("video")
				.accessTokenValiditySeconds(3600).and().build();

		clientDetailsWrapper_ = new ClientDetailsUserDetailsService(clients_);
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		return clients_.loadClientByClientId(clientId);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = null;
		try{
			user = users_.loadUserByUsername(username);
		}catch(UsernameNotFoundException e){
			user = clientDetailsWrapper_.loadUserByUsername(username);
		}
		return user;
	}

}
