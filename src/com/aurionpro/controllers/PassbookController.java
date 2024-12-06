package com.aurionpro.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Customer;
import com.aurionpro.entities.Transaction;
import com.aurionpro.service.Database;


@WebServlet("/PassbookController")
public class PassbookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		 if (session == null) {
	            
	            response.sendRedirect("login.html");
	            return;
	     }else {
	    	 Customer customer = (Customer)session.getAttribute("customer");
	         String customerEmail = customer.getEmail();
	         
	         Database database = new Database();
	         database.connect();
	         List<Transaction> transactionList = database.getCustomerTransactions(customerEmail);
	         request.setAttribute("customer", customer);
	         request.setAttribute("transactionList", transactionList);
	         
	         request.getRequestDispatcher("passbookPage.jsp").forward(request, response);
	     }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
