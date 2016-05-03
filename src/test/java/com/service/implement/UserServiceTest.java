package com.service.implement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.User;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testLoginCheck() {
		User user = new User();
		user.setName("manager2");
		user.setPassword("123");
		if (null != userService.loginCheck(user))
			System.out.println("------OK!!-----");
		else
			System.out.println("------Sorry!!-----");
	}

	@Test
	public void testRegister() {
		User user = new User();
		user.setName("manager");
		user.setPassword("123");
		System.out.println(userService.register(user));
	}

}