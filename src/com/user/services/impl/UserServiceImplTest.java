package com.user.services.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.user.form.User;

public class UserServiceImplTest {

	@Test
	public void isUserSignatureExistsTest() {
		User user = new User("Kamal", "kamla@yah.com", "mount eden" , "kamla@yah.comKmapp1");
		assertFalse(new UserServiceImpl().isUserSignatureExists(user));
	}

}
