package com.aurionpro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entities.Admin;
import com.aurionpro.service.Database;


@WebServlet("/AddNewCustomerController")
public class AddNewCustomerController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("name") == null) {

            response.sendRedirect("login.html");
            return;
        }
		Database database = new Database();
		database.connect();
        Admin admin = (Admin)session.getAttribute("admin");
        
        request.setAttribute("admin", admin);

        
        request.getRequestDispatcher("addNewCustomer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
