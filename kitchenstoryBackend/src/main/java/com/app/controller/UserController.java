package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.UserService;

@RestController
@CrossOrigin()
public class UserController{

	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public User registerUser( @RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping("/login")
	public List<User> loginUser(@RequestParam String username, @RequestParam String password) {
		return userService.loginUser(username, password);
	}

	@PutMapping("/updatePassword")
	public User changePassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestBody User user) {
		return userService.changePassword(oldPassword, newPassword, user);
	}

}
