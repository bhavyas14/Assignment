package com.modestack.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modestack.dao.UserDao;
import com.modestack.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	private static final AtomicInteger counter = new AtomicInteger();
	
	
	public int registerUser(User user) {
		user.setUserId(counter.incrementAndGet());
		int count = userDao.registerUser(user);
		return count;
	}
	
	public boolean isUserExist(User user) {
		boolean isUser = userDao.isUserExist(user);
		return isUser;
	}
	

}
