package com.aurionpro.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.aurionpro.entities.Account;
import com.aurionpro.entities.Admin;
import com.aurionpro.entities.Customer;
import com.aurionpro.entities.Transaction;
import com.aurionpro.service.AdminService;
import com.aurionpro.service.Database;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
	
		
		RequestDispatcher dispatcher = null;
		Database database = new Database();
		database.connect();
		List<Customer> customers = database.getAllCustomers();
		if(role.equals("Admin Login")) {
			role = "admins";
			boolean validity = database.checkLogin(username, password, role);
			String name = database.getAdminName(username);
			if (validity) {
				AdminService adminService = new AdminService();
				Admin admin = adminService.createAdmin(name, username, password);
				request.setAttribute("admin", admin);
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				session.setAttribute("admin", admin);
				session.setAttribute("customersList", customers);
				request.getRequestDispatcher("AdminHomeController").forward(request, response);
			} else {
			    writer.print("<h3>Invalid Credentials! Please try again.</h3>");
			    writer.print("<a href='login.html'>Back to Login</a>");
			}
		}
		
		if(role.equals("Customer Login")){
			role = "customers";
			boolean validity = database.checkLogin(username, password, role);
			if (validity) {
				Customer customer = database.getCustomer(username);
				String email = customer.getEmail();
				List<Account> accounts = database.getAccountsOfCustomer(email);
				customer.setAccounts(accounts);
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);
				request.getRequestDispatcher("CustomerHomeController").forward(request, response);
			} else {
			    writer.print("<h3>Invalid Credentials! Please try again.</h3>");
			    writer.print("<a href='login.html'>Back to Login</a>");
			}
		}
		database.close();
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
