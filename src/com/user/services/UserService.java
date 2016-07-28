package com.user.services;

import com.user.form.User;

public interface UserService {
	
	public boolean isUserSignatureExists(User user);
	public void createUser(User user);

}
