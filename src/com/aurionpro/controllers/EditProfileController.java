package com.aurionpro.controllers;

import java.io.IOException;
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


@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("login.html");
			return;
		}

        Customer customer = (Customer)session.getAttribute("customer");
       
        request.setAttribute("customer", customer);
       

        request.getRequestDispatcher("editCustomerProfilePage.jsp").forward(request, response);
        
        
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
