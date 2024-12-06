package com.aurionpro.controllers;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.Database;

@WebServlet("/AdminFeeder")
public class AdminFeeder extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		Database database = new Database();
		database.connect();
		database.putAdminData(name, username, password);

		response.sendRedirect("login.html");
		database.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
