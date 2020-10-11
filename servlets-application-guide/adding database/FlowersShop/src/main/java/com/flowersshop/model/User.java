package com.flowersshop.model;

public class User {
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String contact;
	
	public User() {
		this.firstName = new String();
		this.lastName = new String();
		this.username = new String();
		this.email = new String();
		this.password = new String();
		this.address = new String();
		this.contact = new String();
	}
	
	public User(String username, String password) {
		this.firstName = new String();
		this.lastName = new String();
		this.username = username;
		this.email = new String();
		this.password = password;
		this.address = new String();
		this.contact = new String();
	}
	
	public User(String firstName, String lastName, String username, String email,
			String password, String address, String contact) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contact = contact;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
