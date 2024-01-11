package com.securty.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class AuthResponse {

	private String accessToken;
	private String refreshToken;
	private String role;
	private String userName;
	
	public AuthResponse() {}

	public AuthResponse(String accessToken, String refreshToken, String role,String userName) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.role = role;
		this.userName=userName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
