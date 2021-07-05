package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> loginUser(String username, String password) {		
		return userRepository.findByEmailAndPassword(username, password);
	}

	@Override
	public User changePassword(String oldPassword, String newPassword, User user) {
		User updatedUser = userRepository.findById(user.getUserid()).get();
		if(updatedUser.getPassword().equals(oldPassword)){
			updatedUser.setPassword(newPassword);
			return userRepository.save(updatedUser);
		}else {
			return null;
		}	
	}

}
