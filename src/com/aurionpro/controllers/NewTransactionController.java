package com.aurionpro.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Account;
import com.aurionpro.entities.Customer;
import com.aurionpro.service.Database;

@WebServlet("/NewTransactionController")
public class NewTransactionController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		System.out.println(session);
		if(session == null) {
			response.sendRedirect("login.html");
			return;
		}
		Customer customer = null;
		if(session.getAttribute("customer") == null) {
			response.sendRedirect("login.html");
		}else {
			customer = (Customer)session.getAttribute("customer");
		}
		Database database = new Database();
		database.connect();
		List<Account> accounts = database.getAllAccounts();
		request.setAttribute("accounts", accounts);
        request.setAttribute("customer", customer);
        
        request.getRequestDispatcher("newTransactionPage.jsp").forward(request, response);
        

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
