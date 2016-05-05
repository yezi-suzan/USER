package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {
	public void add(User user);

	public User findUserByName(final String name);

	public List<User> getAllUser();

	public void delete(String id); 
}