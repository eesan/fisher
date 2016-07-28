package com.user.services.impl;

import java.util.HashMap;

import com.user.form.User;
import com.user.services.UserService;

public class UserServiceImpl implements UserService {
	
	private HashMap<String, User> userMap = new HashMap<String, User>();
	
	@Override
	public boolean isUserSignatureExists(User user) {
		if (userMap.containsKey(user)){
			return true;
		}
		createUser(user);
		return false;
				
	}
	
	public void createUser(User user){
		userMap.put(user.getSignature(), user);
	}

}
