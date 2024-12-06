<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Passbook</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .header {
            background-color: #007bff;
            color: white;
            padding: 20px 0;
            text-align: center;
            font-size: 1.8rem;
            font-weight: 500;
        }
        .welcome-msg {
            margin: 20px;
            font-size: 1.2rem;
            color: #6c757d;
        }
        .table-container {
            margin: 20px auto;
            max-width: 90%;
        }
        footer {
            background-color: #007bff;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="customerHomePage.jsp">Passbook</a>
        </div>
    </nav>

    <!-- Welcome Message -->
    <div class="welcome-msg">
        Welcome, <b>${customer.getFirstName()}</b>
    </div>
    

    <!-- Table Section -->
    <div class="container mt-6">
    
	    <form action="AccountPassbookController">
	    	<label for="accountNumber" class="form-label">Select Account</label>
			<select class="form-select" id="accountNumber" name="accountNumber" required onchange="this.form.submit()">
				<option value="" disabled selected>Choose Account...</option>
				<c:forEach var="account" items="${customer.getAccounts()}">
					<option value="${account.getAccountNumber()}" balance="${account.getBalance()}">
					${account.getAccountNumber()}
					</option>
				</c:forEach>		                			            					            
			</select>
	    </form>
		
	</div>										
    <div class="table-container">
        <table class="table table-striped table-bordered">
            <thead class="table-primary">
                <tr>
                    <th>Sender Acc No</th>
                    <th>Receiver Acc No</th>
                    <th>Type of Transaction</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <!-- Dynamic Rows -->
                <c:forEach var="transaction" items="${transactionList}">
                    <tr>
                        <td>${transaction.getSenderAccountNumber()}</td>
                        <td>${transaction.getReceiverAccountNumber()}</td>
                        <td>${transaction.getTransactionType()}</td>
                        <td>${transaction.getAmount()}</td>
                        <td>${transaction.getDate()}</td>
                    </tr>
                </c:forEach>
                <!-- If no transactions are available -->
                <c:if test="${empty transactionList}">
                    <tr>
                        <td colspan="5" class="text-center text-muted">
                            No transactions available.
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>