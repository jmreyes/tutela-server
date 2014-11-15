/* 
 **
 ** Copyright 2014, Jules White
 **
 ** 
 */
package net.jmreyes.tutelaserver.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.ElementCollection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Patient implements UserDetails {
	public static final String SCOPE_READ = "read";
	public static final String SCOPE_WRITE = "write";
	
	public static final String ROLE_PATIENT = "ROLE_PATIENT";
	public static final String ROLE_DOCTOR = "ROLE_DOCTOR";
	
	@Id
	private String id;

	@JsonIgnore
	private String username;	

	@JsonIgnore
	private String password;

	@JsonIgnore
    @ElementCollection(targetClass=GrantedAuthority.class)
	private Collection<GrantedAuthority> authorities;
	
	
	public static Patient create(String username, String password,
			String...authorities) {
		return new Patient(username, password, authorities);
	}	

	@SuppressWarnings("unchecked")
	private Patient(String username, String password) {
		this(username, password, Collections.EMPTY_LIST);
	}

	public Patient(String username, String password,
			String...authorities) {
		this.username = username;
		this.password = password;
		this.authorities = AuthorityUtils.createAuthorityList(authorities);
	}

	private Patient(String username, String password,
			Collection<GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}	

    public Patient() {
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
