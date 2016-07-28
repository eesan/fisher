package com.user.form;

public class User {

	public User() {

	}

	public User(String name, String emailId, String address, String signature) {
		this.name = name;
		this.emailId = emailId;
		this.address = address;
		this.signature = signature;
	}

	private String name;
	private String address;
	private String emailId;
	private String signature;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
