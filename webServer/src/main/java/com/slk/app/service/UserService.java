package com.slk.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slk.app.entity.User;
import com.slk.app.repository.UserProfileRepository;

@Service
public class UserService {

	@Autowired
	UserProfileRepository userRepository;
	public Optional<User> getUserByLoginId(String loginId){
		return userRepository.findByloginId(loginId);
	}
	
}
