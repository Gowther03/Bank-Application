package com.aurionpro.controllers;

import java.io.IOException;
import java.sql.SQLException;
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
import com.aurionpro.entities.Customer;
import com.aurionpro.entities.Transaction;
import com.aurionpro.service.AccountService;
import com.aurionpro.service.Database;


@WebServlet("/ViewCustomersController")
public class ViewCustomersController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("name") == null) {

            response.sendRedirect("login.html");
            return;
        }
		// TODO Auto-generated method stub
		String email = request.getParameter("customerId");
		RequestDispatcher dispatcher = null;
		Database database = new Database();
		database.connect();
		List<Customer> customers = null;;
		try {
			customers = database.getCustomersWithAccounts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(email != null) {
			List<Customer> filteredCustomers = customers.stream()
	                .filter(t -> t.getEmail().equals(email))
	                .collect(Collectors.toList());
			request.setAttribute("filteredCustomers", filteredCustomers);
		}

		request.setAttribute("customers", customers);

		request.getRequestDispatcher("viewCustomer.jsp").forward(request, response);
		database.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
