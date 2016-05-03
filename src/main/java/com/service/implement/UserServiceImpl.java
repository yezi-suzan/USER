package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User loginCheck(User user) {
		User u = userDao.findUserByName(user.getName());
		System.out.println("id=" + u.getId() + ",  Name=" + u.getName() + ", password=" + u.getPassword());
		if (user.getPassword().equals(u.getPassword())) {
			return u;
		} else {
			return null;
		}
	}

	public boolean register(User user) {
		User u = userDao.findUserByName(user.getName());
		if (u.getId() == 0) {
			userDao.add(user);
			return true;
		} else {
			System.out.println("id=" + u.getId() + ",  userName=" + u.getName() + ", password=" + u.getPassword());
			return false;
		}
	}

	@Override
	public List<User> allUser() {
		// TODO Auto-generated method stub

		return userDao.getAllUser();
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		
		userDao.delete(id);
	}

}