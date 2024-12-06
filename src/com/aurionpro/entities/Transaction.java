package com.aurionpro.entities;

import java.sql.Date;

public class Transaction {

	public Transaction(int tarnsactionId, Date date, String transactionType, double amount) {
		this.tarnsactionId = tarnsactionId;
		this.date = date;
		this.transactionType = transactionType;
		this.setAmount(amount);
	}
	
	public Transaction(int tarnsactionId, Date date, String transactionType, double amount, int senderAccountNumber, int receiverAccountNumber) {
		this.tarnsactionId = tarnsactionId;
		this.date = date;
		this.transactionType = transactionType;
		this.amount = amount;
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
	}
	private int senderAccountNumber;
	private int receiverAccountNumber;
	private int tarnsactionId;
	private Date date;
	private String transactionType;
	private double amount;
	
	public int getTarnsactionId() {
		return tarnsactionId;
	}
	public void setTarnsactionId(int tarnsactionId) {
		this.tarnsactionId = tarnsactionId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(int senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public int getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(int receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
