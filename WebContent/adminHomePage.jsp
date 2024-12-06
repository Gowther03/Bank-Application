<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="adminHomePage.jsp">Admin Dashboard</a>
        </div>
    </nav>

    <!-- Navigation -->
    <div class="bg-light shadow-sm py-3">
        <div class="container">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-primary mx-2" href="AddNewCustomerController">Add New Customer</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-primary mx-2" href="NewAccountPageController">Add Bank Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-primary mx-2" href="ViewCustomersController">View Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-primary mx-2" href="ViewTransactionsController">View Transactions</a>
                </li>
                <li class="nav-item">
				    <a class="nav-link btn btn-danger mx-2" href="LogoutServlet">Logout</a>
				</li>
            </ul>
        </div>
    </div>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                    	<h5 class="card-title">Hello <c:out value="${admin.getName()}"/>!</h6>
                        <h4 class="card-text">Welcome to Admin Dashboard</h5>
                        <p class="card-text">
                            Use the navigation options above to manage customers, accounts, and transactions.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>