package com.aurionpro.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Account;
import com.aurionpro.entities.Transaction;
import com.aurionpro.service.Database;


@WebServlet("/ViewTransactionsController")
public class ViewTransactionsController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("name") == null) {

            response.sendRedirect("login.html");
            return;
        }
		Database database = new Database();
		database.connect();
		List<Transaction> transactions = database.getAllTransactions();
		String choice = request.getParameter("choice");
		String filter = request.getParameter("filter");
		List<Transaction> filteredTransactions = new ArrayList<Transaction>();
		
		if(choice == null) {
			request.setAttribute("transactions", transactions);
		}else {
			if(choice.equals("credit")) {
				filteredTransactions = transactions.stream()
		                .filter(t -> t.getTransactionType().equals("Credit"))
		                .collect(Collectors.toList());
			}
			else if(choice.equals("debit")) {
				filteredTransactions = transactions.stream()
		                .filter(t -> t.getTransactionType().equals("Debit"))
		                .collect(Collectors.toList());
			}
			else if(choice.equals("transfer")){
				filteredTransactions = transactions.stream()
		                .filter(t -> t.getTransactionType().equals("Transfer"))
		                .collect(Collectors.toList());
			}else {
				
				if(filter.equals("amount")) {
					
					filteredTransactions = transactions.stream()
			                .filter(t -> t.getAmount() == Integer.parseInt(choice))
			                .collect(Collectors.toList());
				}
				else if(filter.equals("accountNumber")) {
					filteredTransactions = transactions.stream()
			                .filter(t -> t.getSenderAccountNumber() == Integer.parseInt(choice))
			                .collect(Collectors.toList());
				}
				else if(filter.equals("date")) {
					filteredTransactions = transactions.stream()
			                .filter(t -> t.getDate().equals(Date.valueOf(choice)))
			                .collect(Collectors.toList());
				}
			}
			request.setAttribute("transactions", filteredTransactions);
		}
		
		
		request.getRequestDispatcher("viewTransactions.jsp").forward(request, response);
		database.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
