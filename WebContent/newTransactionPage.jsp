<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>

<%@ page import="com.aurionpro.entities.Account" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="customerHomePage.jsp">New Transaction</a>
        </div>
    </nav>
    <div class="container">
        <h2 class="text-center mb-4">New Transaction</h2>

        <!-- Form for New Transaction -->
        <form method="POST" action="ProcessTransaction" onsubmit="return validateForm(event)">

            <div class="mb-3">
                <label for="accountNumber" class="form-label">Select Account</label>
                <select class="form-select" id="accountNumber" name="accountNumber" required>
                    <option value="" disabled selected>Choose Account...</option>
                    <c:out value="customer.getAccounts()"></c:out>
                    <c:forEach var="account" items="${customer.getAccounts()}">
                        <option value="${account.getAccountNumber()}">${account.getAccountNumber()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="transactionType" class="form-label">Type of Transaction</label>
                <select class="form-select" id="transactionType" name="transactionType" required>
                    <option value="" disabled selected>Choose Transaction Type...</option>
                    <option value="Credit">Credit</option>
                    <option value="Debit">Debit</option>
                    <option value="Transfer">Transfer</option>
                </select>
            </div>

            <!-- Receiver's Account (Visible only for Transfer) -->
            <div class="mb-3" id="receiverAccountDiv" style="display: none;">
                <label for="receiverAccount" class="form-label">Receiver Account (For Transfer Only)</label>
                <input type="number" class="form-control" id="receiverAccount" name="receiverAccount" placeholder="Enter Receiver's Account Number" min="1">
            </div>

            <!-- Amount -->
            <div class="mb-3">
                <label for="amount" class="form-label">Amount</label>
                <input type="number" class="form-control" id="amount" name="amount" min="1" required>
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-secondary">Cancel</button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    
    <script>
	    const accounts = [];
	    const balances = [];
	    <% 
	       
	        List<Account> accounts = (List<Account>) request.getAttribute("accounts");
	    	
	        if (accounts != null) {
	            for (Account account : accounts) {
	    %>
	    accounts.push("<%= account.getAccountNumber() %>");
	    balances.push({
	        accountNumber: "<%= account.getAccountNumber() %>",
	        balance: <%= account.getBalance() %>,
	      },)
	    <% 
	            }
	        }
	    %>
   	 	const amount = document.getElementById("amount").value.trim();

        const transactionType = document.getElementById("transactionType");
        const receiverAccountDiv = document.getElementById("receiverAccountDiv");

        transactionType.addEventListener("change", function () {
            if (transactionType.value === "Transfer") {
                receiverAccountDiv.style.display = "block";
            } else {
                receiverAccountDiv.style.display = "none";
            }
        });
        
        function validateBalance(event) {
            const senderAccount = document.getElementById("accountNumber").value.trim();
            const amount = parseFloat(document.getElementById("amount").value.trim());

            let sufficientBalance = false;
	
            for (let balance of balances) {
                if (balance.accountNumber === senderAccount) {
                	console.log(balance.balance)
                    if ((balance.balance - amount) >= 0) {
                        sufficientBalance = true;
                    } else {
                        alert("Insufficient balance in account " + senderAccount);
                        event.preventDefault();
                        return false;
                    }
                }
            }

            if (!sufficientBalance) {
                alert("Sender account not found.");
                event.preventDefault();
                return false;
            }

            return true;
        }
        
        function validateAccount(event) {
            const receiverAccount = document.getElementById("receiverAccount").value.trim();

            if (!accounts.includes(receiverAccount)) {
                alert("This Account is not Available");
                event.preventDefault();
                return false;
            }
            return true;
        }
        
        function validateForm(event){
        	 const receiverAccount = document.getElementById("receiverAccount").value.trim();
        	 
        	 if(amount<0){
        		 alert("Amount Cannot be Negative.");
                 event.preventDefault(); 
                 return false;
        	 }
        	 
        	 if(receiverAccount != ""){
        		 if (!validateAccount(event)) {
                     return false;
                 }
        	 }
        	 if (transactionType.value === "Debit" || transactionType.value === "Transfer") {
        		 if (!validateBalance(event)) {
                     return false;
                 }
        	 }
        	 
        	 
        	 showUpdateMessage();
        	 return true;
        }
        function showUpdateMessage() {
            alert("Transaction Succesfull!");
        }
    </script>
</body>
</html>