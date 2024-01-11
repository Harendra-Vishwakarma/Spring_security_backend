package com.securty.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
	@NotBlank(message = "UserName is mendatory")
	private String userName;
	@NotBlank(message = "Password is mendatory")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
