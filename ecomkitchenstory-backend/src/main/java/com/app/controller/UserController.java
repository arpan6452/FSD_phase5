package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.LoginResponseVO;
import com.app.dto.RegistrationRequest;
import com.app.dto.RegistrationResponseVO;
import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins= {"http://localhost:8080"})
@CrossOrigin(origins= {"http://kstory-simplilearn.s3-website.us-east-2.amazonaws.com/"})
public class UserController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/all")
	public String welcomeApp() {
		return "Welcome to the Application";
	}
	
	@PostMapping("/login")
	public LoginResponseVO login(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManger.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
			String data = jwtUtil.generateToken(authRequest.getUsername());
			
			return new LoginResponseVO(data, 2000);
		}catch (Exception e) {
			throw new Exception("Invalid Username and Password");
		}
	}
	
	@PostMapping("/registration")
	public RegistrationResponseVO registration(@RequestBody RegistrationRequest registrationRequest) {
		if (userRepository.existsByUsername(registrationRequest.getUsername())) {
			return new RegistrationResponseVO(registrationRequest.getUsername(), "EmailId already exist");
		}
		
//		if (userRepository.existsByEmail(registrationRequest.getEmail())) {
//			return "Email already exist";
//		}
		
		User user = new User( registrationRequest.getUsername(), passwordEncoder.encode(registrationRequest.getPassword()), 
				registrationRequest.getAddress1(), registrationRequest.getAddress2(), registrationRequest.getCity(), 
				registrationRequest.getState(), registrationRequest.getPincode());
		userRepository.save(user);
		return new RegistrationResponseVO(registrationRequest.getUsername(), "User Registered successfully");

	}
	

}
