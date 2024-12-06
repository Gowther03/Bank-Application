<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Transactions</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <!-- Header -->
    <nav class="navbar navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <a class="navbar-brand" href="AdminHomeController">View Transactions</a>
        </div>
    </nav>
    
    <div class="container mt-9">
    
		    <form action="ViewTransactionsController">
		    	<div class="mb-3">
	                <label for="filter" class="form-label">Select Filter</label>
	                <select class="form-select" id="filter" name="filter" required>
	                    <option value="" disabled selected>Choose...</option>
	                    <option value="transactionType">Transaction Type</option>    
	                    <option value="date">Date</option>     
	                    <option value="accountNumber">Sender Account Number</option>   
	                    <option value="amount">Amount</option>                                     
	                </select>
            	</div>

	            <div class="mb-3" id="choice-container">
	                <label for="choice" class="form-label">Select/Enter Value</label>
	                <!-- Placeholder for dynamic options -->
	                <select class="form-select" id="choice" name="choice" required>
	                    <option value="" disabled selected>Choose...</option>
	                </select>
	            </div>

            	<button type="submit" class="btn btn-primary">Filter Transactions</button>
		    </form>
		
	</div>

    <!-- Table Container -->
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h3 class="card-title text-center text-primary mb-4">Transactions List</h3>
                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <thead class="bg-primary text-white">
                                <tr>
                                    <th>Sender Acc No.</th>
                                    <th>Receiver Acc No.</th>
                                    <th>Type Of Transaction</th>
                                    <th>Amount</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="transaction" items="${transactions}">
                                    <tr>
                                        <td>${transaction.getSenderAccountNumber()}</td>
                                        <td>${transaction.getReceiverAccountNumber()}</td>
                                        <td>${transaction.getTransactionType()}</td>
                                        <td>${transaction.getAmount()}</td>
                                        <td>${transaction.getDate()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!-- No Transactions Found -->
                        <c:if test="${empty transactions}">
                            <div class="text-center text-danger mt-3">
                                No Transactions found.
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById("filter").addEventListener("change", function () {
            const filter = this.value;
            const choiceContainer = document.getElementById("choice-container");
            const choice = document.getElementById("choice");

            choice.innerHTML = "";

            if (filter === "transactionType") {
                
                choice.innerHTML = `<option value="" disabled selected>Choose...</option><option value="credit">Credit</option><option value="debit">Debit</option><option value="transfer">Transfer</option>`;
                choiceContainer.innerHTML = `<label for="choice" class="form-label">Select Transaction Type</label>`;
                choiceContainer.appendChild(choice);
            } else {
                
                choiceContainer.innerHTML = `
                    <label for="choice" class="form-label">Enter</label>
                    <input type="text" class="form-control" id="choice" name="choice" required placeholder="Enter ${filter}">
                `;
            }
        });
    </script>
</body>
</html>