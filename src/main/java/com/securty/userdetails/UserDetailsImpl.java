package com.securty.userdetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.securty.model.User;

public class UserDetailsImpl implements UserDetails {

	private String name;
	private String password;
	private Set<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		name = user.getName();
		password = user.getPassword();
		authorities =getAuthorities(user);
	}

	private Set<GrantedAuthority> getAuthorities(User user){
		Set<GrantedAuthority> authority=new HashSet<>();
		user.getRoles().forEach(role->authority.add(new SimpleGrantedAuthority("ROLE_"+role.getRole())));
		return authority;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
