/* 
 **
 ** Copyright 2014, Jules White
 **
 ** 
 */
package net.jmreyes.tutelaserver.auth;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
	
	@Id
	private long id;
	
	private String name;

	public static UserDetails create(String username, String password,
			String...authorities) {
		return new User(username, password, authorities);
	}
	

    @ElementCollection(targetClass=GrantedAuthority.class)
	private final Collection<GrantedAuthority> authorities_;
	
	private final String password_;
	private final String username_;

	@SuppressWarnings("unchecked")
	private User(String username, String password) {
		this(username, password, Collections.EMPTY_LIST);
	}

	private User(String username, String password,
			String...authorities) {
		username_ = username;
		password_ = password;
		authorities_ = AuthorityUtils.createAuthorityList(authorities);
	}

	private User(String username, String password,
			Collection<GrantedAuthority> authorities) {
		super();
		username_ = username;
		password_ = password;
		authorities_ = authorities;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities_;
	}

	public String getPassword() {
		return password_;
	}

	public String getUsername() {
		return username_;
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
