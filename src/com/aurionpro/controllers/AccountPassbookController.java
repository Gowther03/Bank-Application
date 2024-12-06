package com.aurionpro.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Customer;
import com.aurionpro.entities.Transaction;
import com.aurionpro.service.Database;


@WebServlet("/AccountPassbookController")
public class AccountPassbookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountNumber = request.getParameter("accountNumber");
		int selectedAccountNumber = Integer.parseInt(accountNumber);
		HttpSession session = request.getSession(false);
		Customer customer = (Customer)session.getAttribute("customer");
		String customerEmail = customer.getEmail();
        
        Database database = new Database();
        database.connect();
        List<Transaction> transactionList = database.getCustomerTransactions(customerEmail);
        
        List<Transaction> filteredTransactions = transactionList.stream()
                .filter(t -> t.getSenderAccountNumber() == selectedAccountNumber)
                .collect(Collectors.toList());

        request.setAttribute("customer", customer);
        request.setAttribute("transactionList", filteredTransactions);
        
        request.getRequestDispatcher("passbookPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
