package com.softtek.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.entity.User;
import com.softtek.repository.UserRepository;
import com.softtek.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addOrUpdate (User user) {
		return userRepository.save(user);
	}
	
	@Override
	public boolean deleteById(int id) {
		userRepository.deleteById(id);
		return true;
	}
	
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
}
