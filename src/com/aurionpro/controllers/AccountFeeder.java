package com.aurionpro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.Database;


@WebServlet("/AccountFeeder")
public class AccountFeeder extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		String customer_id = request.getParameter("customerId");
		
		
		Database database = new Database();
		database.connect();
		database.putAccountData(customer_id);

		response.sendRedirect("adminHomePage.jsp");
		database.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
