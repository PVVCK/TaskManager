package com.example.taskmanager.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	 @Override
    public User register(User user) {
		 if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	            throw new RuntimeException("Email already registered");
	        }
	        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, String name, String email) {
        User user = getById(id);
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

}
