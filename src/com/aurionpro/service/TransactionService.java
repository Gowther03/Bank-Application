package com.aurionpro.service;

import java.sql.Date;

import com.aurionpro.entities.Transaction;

public class TransactionService {

	public Transaction transfer(int transaction_id, Date date, String transactionType, double amount, int senderAccountNumber, int receiverAccountNumber) {
		Transaction transaction = new Transaction(transaction_id, date, transactionType, amount, senderAccountNumber, receiverAccountNumber);
		return transaction;
	}
}
