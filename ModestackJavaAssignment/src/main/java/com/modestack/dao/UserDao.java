package com.modestack.dao;

import com.modestack.model.User;

public interface UserDao {
	
	public int registerUser(User user);
	
	public boolean isUserExist(User user);

}
