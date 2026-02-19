package com.smartexpense.service.impl;

import com.smartexpense.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartexpense.dto.Register;
import com.smartexpense.repository.UserRepository;
import com.smartexpense.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
                
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
		
	
	@Override
	public String registerUser(Register request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exist.");
		}
		
		User user =User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role("USER")
				.build();
		
		userRepository.save(user);
		
		return "User register successfully.";
	}
}
