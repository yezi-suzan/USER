
package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/loginView")
	public String loginView() {
		return "login";
	}

	@RequestMapping("/registerView")
	public String registerView() {
		return "register";
	}
	@RequestMapping("/users")
	public String users() {
		return "users";
	}
	@RequestMapping("/login")
	public ModelAndView login(User user,HttpSession session) {
		// System.out.println("进入login控制了");
		// user.print();
		ModelAndView mav = new ModelAndView();
		User u = userService.loginCheck(user);
		if (null == u) {
			
			mav.setViewName("login");
			mav.addObject("errorMsg", "wrong");
			return mav;
		} else {
			List<User> lUsers = userService.allUser();
			session.setAttribute("user",u);
			mav.setViewName("users");
			mav.addObject("user", u);
			mav.addObject("users", lUsers);
			return mav;
		}
	}

	@RequestMapping("/register")
	public ModelAndView register(User user) {
		ModelAndView mav = new ModelAndView();
		if (userService.register(user)) {
			List<User> lUsers = userService.allUser();
			mav.setViewName("users");
			mav.addObject("users", lUsers);
			return mav;
		} else {
			mav.setViewName("register");
			mav.addObject("errorMsg", "我是");
			return mav;
		}
	}
	
//	@RequestMapping("/Users.do")
//	public  List<User> Users() {
//		List<User> lUsers = userService.allUser();
//		return lUsers;
//	}

	@RequestMapping("/delete")
	public ModelAndView delete(String id) {
		userService.delete(id);
		ModelAndView mav = new ModelAndView();
		List<User> lUsers = userService.allUser();
		mav.setViewName("users");
		mav.addObject("users", lUsers);
		return mav;
	}
}