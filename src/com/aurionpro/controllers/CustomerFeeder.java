package com.aurionpro.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Customer;
import com.aurionpro.entities.RandomNumberGenerator;
import com.aurionpro.service.CustomerService;
import com.aurionpro.service.Database;

@WebServlet("/CustomerFeeder")
public class CustomerFeeder extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("name") == null) {

            response.sendRedirect("login.html");
            return;
        }
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int customerId = RandomNumberGenerator.generateNumber();		
		
		Database database = new Database();
		database.connect();

		database.putCustomerData(customerId, firstname, lastname, email, password);

		List<Customer> customers = database.getAllCustomers();
		request.setAttribute("customers", customers);;
		
		request.getRequestDispatcher("adminHomePage.jsp").forward(request, response);
		database.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
