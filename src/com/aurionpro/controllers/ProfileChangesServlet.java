package com.aurionpro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Customer;
import com.aurionpro.service.Database;

@WebServlet("/ProfileChangesServlet")
public class ProfileChangesServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		
        Customer customer = (Customer)session.getAttribute("customer");
        String email = customer.getEmail();
        String field = request.getParameter("field");
        String newValue = request.getParameter("newValue");
        Database database = new Database();
        database.connect();
        
        boolean isUpdated = database.updateCustomerProfile(email, field, newValue);

        if (isUpdated) {
            session.setAttribute("updateMessage", "Profile updated successfully!");
        } else {
            session.setAttribute("updateMessage", "Failed to update profile.");
        }

        if(field.equals("firstName")) {
        	customer.setFirstName(newValue);
        }
		if(field.equals("lastName")) {
		    customer.setLastName(newValue);	
		}
		if(field.equals("password")) {
			customer.setPassword(newValue);
		}
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("EditProfileController").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	


}
