package com.aurionpro.service;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entities.Account;

public class AccountService {
	List<Account> accounts = new ArrayList<>();
	public Account createAccount(int accountNumber, String customerId) {
		Account account = new Account(accountNumber, customerId);
		accounts.add(account);
		return account;
	}
	
	public double credit(Account account, double amount) {
		double newBalance = account.getBalance() + amount;
	
		account.setBalance(newBalance);
		return account.getBalance();
		
	}
	
	public double debit(Account account, double amount) {
		account.setBalance(account.getBalance()-amount);
		return account.getBalance();
	}
}
