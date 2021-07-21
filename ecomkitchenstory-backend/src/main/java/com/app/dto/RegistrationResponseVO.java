package com.app.dto;

public class RegistrationResponseVO {
	private String username;
	private String status;
	
	public RegistrationResponseVO(String username, String status) {
		super();
		this.username = username;
		this.status = status;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
