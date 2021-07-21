package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entity.CustomUserDetails;
import com.app.entity.User;
import com.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		try {
			User user = userRepository.findByusername(username);
			
//			return user.map(CustomUserDetails::new).get();
			return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
		}catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("Invalid Username and Password");
		}
	}

}
