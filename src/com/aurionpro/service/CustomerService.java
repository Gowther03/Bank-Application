package com.aurionpro.service;

import com.aurionpro.entities.Account;
import com.aurionpro.entities.Customer;

public class CustomerService {

	public Customer createCustomer(int customerId, String firstName, String lastName, String email, String password) {
		Customer customer = new Customer(customerId, firstName, lastName, email, password);
		return customer;
	}
	
	public Account getAccount(Customer customer, int accountNumber) {
		for(Account account: customer.getAccounts()) {
			if(accountNumber == account.getAccountNumber()) {
				return account;
			}
		}
		return null;
	}
}
