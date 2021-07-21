package com.app.dto;

public class LoginResponseVO {
	private String key;
	private long expiresIn;
	
	public LoginResponseVO(String key, long expiresIn) {
		this.key = key;
		this.expiresIn = expiresIn;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
