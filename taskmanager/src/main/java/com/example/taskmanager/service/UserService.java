package com.example.taskmanager.service;

import java.util.Optional;

import com.example.taskmanager.entity.User;

public interface UserService {
	  
		User register(User user); // Will hash password before saving
	    Optional<User> findByEmail(String email);
	    User getById(Long id);
	    User updateUser(Long id, String name, String email);

}
