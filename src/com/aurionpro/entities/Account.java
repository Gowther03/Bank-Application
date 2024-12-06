package com.aurionpro.entities;

import java.util.ArrayList;
import java.util.List;

public class Account {
	public Account() {
		
	}
	public Account(int accountNumber, String customerId) {
		this.accountNumber = accountNumber;
		this.customerId = customerId;
	}
	private int accountNumber;
	private double balance;
	private String customerId;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double amount) {
		this.balance = amount;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Transaction transaction) {
		transactions.add(transaction);
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
