package com.softtek.service;

import java.util.List;
import java.util.Optional;

import com.softtek.entity.User;

public interface UserService {

	public boolean deleteById(int id);

	public List<User> findAll();

	public Optional<User> findById(int id);

	public User addOrUpdate(User user);

}
