package com.example.demo.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


//this is where Spring Security will take the UserDetails and Authenticate against it 
public class AdminUserDetails implements UserDetails{

	private String username;
	private String password;
	
	
	public AdminUserDetails() {}
	
	public AdminUserDetails(Admin a) {
		this.username = a.getUsername();
		this.password = a.getPassword();
	}

	public AdminUserDetails(String username) {
		this.username = username;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
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
