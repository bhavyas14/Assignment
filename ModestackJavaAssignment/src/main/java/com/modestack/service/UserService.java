package com.modestack.service;

import com.modestack.model.User;

public interface UserService {
	
	public int registerUser(User user);
	
	public boolean isUserExist(User user);

}
