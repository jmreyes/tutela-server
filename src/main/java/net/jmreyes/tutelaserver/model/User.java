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

@Document
public class User implements UserDetails {
	public static final String SCOPE_READ = "read";
	public static final String SCOPE_WRITE = "write";
	
	public static final String ROLE_PATIENT = "ROLE_PATIENT";
	public static final String ROLE_DOCTOR = "ROLE_DOCTOR";
	
	@Id
	private String id;
	
	public User() {
	}
	
	public static User create(String username, String password,
			String...authorities) {
		return new User(username, password, authorities);
	}	

    @ElementCollection(targetClass=GrantedAuthority.class)
	private Collection<GrantedAuthority> authorities_;
	
	private String password;
	private String username;

	@SuppressWarnings("unchecked")
	private User(String username, String password) {
		this(username, password, Collections.EMPTY_LIST);
	}

	private User(String username, String password,
			String...authorities) {
		this.username = username;
		this.password = password;
		authorities_ = AuthorityUtils.createAuthorityList(authorities);
	}

	private User(String username, String password,
			Collection<GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		authorities_ = authorities;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities_;
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
