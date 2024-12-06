package com.aurionpro.entities;

import java.util.ArrayList;
import java.util.List;

public class Admin {

	public Admin(String name, String adminId, String password) {
		this.name = name;
		this.adminId = adminId;
		this.password = password;
		
	}
	private String name;
	private String adminId;
	private String password;
	private List<Customer> customers = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Customer customer) {
		customers.add(customer);
	}
	
	
}
