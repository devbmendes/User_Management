package com.devb.usermanagement.entity.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAppDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private UserApp userApp;

	public UserAppDetails(UserApp userApp) {
		this.userApp = userApp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(userApp.getRole().name()));
	}

	@Override
	public String getPassword() {
		return userApp.getPassw();
	}

	@Override
	public String getUsername() {

		return userApp.getEmail();
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

	public UserApp getUserApp() {
		return userApp;
	}

	public void setUserApp(UserApp userApp) {
		this.userApp = userApp;
	}

}
