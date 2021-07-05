package com.app.service;

import java.util.List;

import com.app.model.User;

public interface UserService {
	public User registerUser(User user);
	public List<User> loginUser(String username, String password);
	public User changePassword(String oldPassword, String newPassword, User user);
}
