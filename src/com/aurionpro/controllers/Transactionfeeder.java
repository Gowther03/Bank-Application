package com.aurionpro.controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.Database;


@WebServlet("/Transactionfeeder")
public class Transactionfeeder extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		String id = request.getParameter("transaction_id");
		String date = request.getParameter("transactionDate");
		String type = request.getParameter("transactionType");
		String amount = request.getParameter("amount");
		String receiver = request.getParameter("receiverAccount");
		String sender = request.getParameter("senderAccount");
		String accountNumber = request.getParameter("accountNumber");
		
		
		Database database = new Database();
		database.connect();
		database.putTransactionData(Integer.parseInt(id), Date.valueOf(date), type, Double.parseDouble(amount), Integer.parseInt(sender), Integer.parseInt(receiver), Integer.parseInt(accountNumber));

		response.sendRedirect("login.html");
		database.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
