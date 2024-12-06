<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.entities.Customer" %>
<%@ page import="com.aurionpro.service.Database" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Bank Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
    	function accountMessage(){
    		alert("Account has been Created!")
    	}
    </script>
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <a class="navbar-brand" href="AdminHomeController">Add Bank Account</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card shadow-sm border-0">
                    <div class="card-body p-4">
                        <h3 class="card-title text-center text-primary mb-4">Bank Account Details</h3>
                        <form>
                            <div class="mb-3">
                                <label for="customerId" class="form-label">Search by Customer ID</label>
                                <%
                                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                                %>
                                <select class="form-select" id="customerId" name="customerId" required onchange="this.form.submit()">
                                    <option value="">Select Customer ID</option>
                                    <%
                                        for (Customer customer : customers) {
                                            String customerId = customer.getEmail();
                                    %>
                                        <option value="<%= customerId %>"><%= customerId %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </form>

                        <%
                            String customerId = request.getParameter("customerId");
                            if (customerId != null && !customerId.isEmpty()) {
                                Database database = new Database();
                                database.connect();
                                Customer customer = database.getCustomer(customerId);
                                if (customer != null) {
                        %>
                                    <div class="mb-3">
                                        <label for="customerDetails" class="form-label">Customer Details</label>
                                        <textarea class="form-control" id="customerDetails" rows="3" readonly>
                                            Customer Id: <%= customer.getCustomerId() %>
                                            First Name: <%= customer.getFirstName() %>
                                            Last Name: <%= customer.getLastName() %>
                                            Email: <%= customer.getEmail() %>
                                        </textarea>
                                    </div>
                        <%
                                } else {
                                    out.println("<p class='text-danger'>Customer not found.</p>");
                                }
                                database.close();
                            }
                        %>

                        <form action="AccountFeeder" method="post">
                            <input type="hidden" name="customerId" value="<%= customerId != null ? customerId : "" %>"/>
                            <div class="d-flex justify-content-center mt-3">
                                <button type="submit" name="generate" class="btn btn-success" onclick="accountMessage()">Generate Account Number</button>
                            </div>
                        </form>

                        <%
                            String action = request.getParameter("generate");
                            if (action != null && action.equals("generate")) {
                                String selectedCustomerId = request.getParameter("customerId");
                                if (selectedCustomerId != null && !selectedCustomerId.isEmpty()) {
                                    // Logic for generating account number
                                    out.println("<p>Account number generated for customer with ID: " + selectedCustomerId + "</p>");
                                } else {
                                    out.println("<p class='text-danger'>Please select a customer.</p>");
                                }
                            }
                        %> 
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
