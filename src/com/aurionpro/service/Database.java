package com.aurionpro.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurionpro.entities.Account;
import com.aurionpro.entities.Customer;
import com.aurionpro.entities.RandomNumberGenerator;
import com.aurionpro.entities.Transaction;


public class Database {
	
	private Connection connection;
	private PreparedStatement preStatement;
	public void connect() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

	public void putAdminData(String name,  String username, String password) {
		try {
			preStatement = connection.prepareStatement("INSERT INTO admins VALUES(?,?,?)");
			preStatement.setString(1, name);
			preStatement.setString(2, username);
			preStatement.setString(3, password);
			preStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAdminName(String username) {
		try {
			preStatement = connection.prepareStatement("select name from admins where admin_id = ?");
			preStatement.setString(1, username);
			ResultSet result = preStatement.executeQuery();
			if (result.next()) {
                return result.getString("name");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void putCustomerData(int customerId, String firstname, String lastname, String email, String password) {
		try {
			preStatement = connection.prepareStatement("INSERT INTO customers VALUES(?,?,?,?,?)");
			preStatement.setInt(1, customerId);
			preStatement.setString(2, firstname);
			preStatement.setString(3, lastname);
			preStatement.setString(4, email);
			preStatement.setString(5, password);
			preStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Customer> getAllCustomers() {
		try {
			preStatement = connection.prepareStatement("select * from customers");
			ResultSet result = preStatement.executeQuery();
			CustomerService service = new CustomerService();
			List<Customer> customers = new ArrayList<Customer>();
			int customerID = 0;
			String firstname = null;
			String lastname = null;
			String email = null;
			String password = null;
			while(result.next()) {
				customerID = result.getInt("customer_id");
                firstname = result.getString("first_name");
                lastname = result.getString("last_name");
                email = result.getString("email");
                password = result.getString("password");
               
                customers.add(service.createCustomer(customerID, firstname, lastname, email, password));
            }
			return customers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Account getAccountFromAccNo(int accountNumber) {
		
		try {
			preStatement = connection.prepareStatement("select * from accounts where account_number = ?");
			preStatement.setInt(1, accountNumber);
			ResultSet result = preStatement.executeQuery();
			AccountService service = new AccountService();
			String customerID = null;
			double balance = 0;
			while(result.next()) {
				customerID = result.getString("email");
	            balance = result.getDouble("balance");
	        }
			Account account = service.createAccount(accountNumber,customerID);
			account.setBalance(balance);
			return account;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Customer getCustomer(String username) {
		try {
			preStatement = connection.prepareStatement("select * from customers where email = ?");
			preStatement.setString(1, username);
			ResultSet result = preStatement.executeQuery();
			CustomerService service = new CustomerService();
			if (result.next()) {
				int customerID = result.getInt("customer_id");
                String firstname = result.getString("first_name");
                String lastname = result.getString("last_name");
                String email = result.getString("email");
                String password = result.getString("password");
               
                return service.createCustomer(customerID, firstname, lastname, email, password);
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void updateAccountBalance(int accountNumber, double balance) {
		try {
			preStatement = connection.prepareStatement("update accounts set balance = ? where account_number = ?");
			preStatement.setDouble(1, balance);;
			preStatement.setInt(2, accountNumber);;
			preStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public List<Account> getAccountsOfCustomer(String email){
		try {
			preStatement = connection.prepareStatement("select * from accounts where email = ?");
			preStatement.setString(1, email);
			ResultSet result = preStatement.executeQuery();
			AccountService service = new AccountService();
			List<Account> accounts = new ArrayList<Account>();
			int accountNumber = 0;
			String customerID = null;
			double balance = 0;
			while(result.next()) {
				customerID = result.getString("email");
                accountNumber = result.getInt("account_number");
                balance = result.getDouble("balance");
                Account account = service.createAccount(accountNumber,customerID);
                account.setBalance(balance);
                accounts.add(account);
            }
			return accounts;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public List<Customer> getCustomersWithAccounts() throws SQLException {
        
        String query = "SELECT c.email AS email, c.first_name AS firstName, c.last_name AS lastName, c.customer_id AS customerID, c.password AS password, " +
                       "a.account_number AS account, a.balance as balance " +
                       "FROM customers c " +
                       "LEFT JOIN accounts a ON c.email = a.email";

        preStatement = connection.prepareStatement(query);
        ResultSet resultSet = preStatement.executeQuery();

       
        Map<String, Customer> customerMap = new HashMap<>();
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            Customer customer = customerMap.getOrDefault(email, new Customer());
            if (!customerMap.containsKey(email)) {
                customer.setCustomerId(resultSet.getInt("customerID"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setPassword(resultSet.getString("password"));
                customer.setEmail(email);
                customerMap.put(email, customer);
            }

            int accountNumber = resultSet.getInt("account");
            if (resultSet.wasNull()) {
                accountNumber = -1;
            }
            if (accountNumber != -1) {
                Account account = new Account();
                account.setAccountNumber(resultSet.getInt("account"));
                account.setBalance(resultSet.getDouble("balance"));
                customer.getAccounts().add(account);
            }
        }
        return new ArrayList<>(customerMap.values());
    }
	
	
	public void putAccountData(String customerId) {
		try {
			preStatement = connection.prepareStatement("INSERT INTO accounts VALUES(?,?,?)");
			AccountService service = new AccountService();
			Account account = service.createAccount(RandomNumberGenerator.generateNumber(), customerId);
			
			preStatement.setInt(1, account.getAccountNumber());
			preStatement.setDouble(2, account.getBalance());
			preStatement.setString(3, account.getCustomerId());
			
			preStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void putTransactionData(int tarnsactionId, Date date, String transactionType, double amount, int senderAccountNumber, int receiverAccountNumber, int accountNumber) {
		try {
			preStatement = connection.prepareStatement("INSERT INTO transactions VALUES(?,?,?,?,?,?,?)");
			preStatement.setInt(1, tarnsactionId);
			preStatement.setString(2, transactionType);
			preStatement.setDate(3, date);;
			preStatement.setDouble(4, amount);
			preStatement.setInt(5, senderAccountNumber);
			preStatement.setInt(6, receiverAccountNumber);
			preStatement.setInt(7, accountNumber);
			
			preStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkLogin(String username, String password, String role) {
		
		try {
			String tableName;
			switch (role) {
		    case "admins":
		        tableName = "admins";
		        break;
		    case "customers":
		        tableName = "customers";
		        break;
		    default:
		        throw new IllegalArgumentException("Invalid table name");
			}

			String query = "SELECT * FROM " + tableName;
			preStatement = connection.prepareStatement(query);
	
			ResultSet result = preStatement.executeQuery();
			if(role.equals("admins")) {
				while(result.next()) {
	            	if(username.equals(result.getString(2)) & password.equals(result.getString(3))){
	            		return true;
	            	}
				}
			}
			if(role.equals("customers")) {
				while(result.next()) {
	            	if(username.equals(result.getString(4)) & password.equals(result.getString(5))){
	            		return true;
	            	}
				}
			}
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the connection.");
            e.printStackTrace();
        }
    }

	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		try {
			preStatement = connection.prepareStatement("select * from accounts");
			ResultSet result = preStatement.executeQuery();
			AccountService service = new AccountService();
			List<Account> accounts = new ArrayList<Account>();
			int accountNumber = 0;
			String customerID = null;
			double balance = 0;
			while(result.next()) {
				customerID = result.getString("email");
                accountNumber = result.getInt("account_number");
                balance = result.getDouble("balance");
                
                Account account = service.createAccount(accountNumber,customerID);
                account.setBalance(balance);
                accounts.add(account);
            }
			return accounts;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Transaction> getCustomerTransactions(String customerEmail){
		
		try {
			String query = "SELECT t.*, a.email FROM transactions t LEFT JOIN accounts a ON t.account_number = a.account_number";

			 preStatement = connection.prepareStatement(query);
			 ResultSet resultSet = preStatement.executeQuery();
		
			 TransactionService transactionService = new TransactionService();
			 List<Transaction> transactions = new ArrayList<>();
			 int transaction_id = 0;
			 String transactionType = null;
			 Date date = null;
			 double amount = 0;
			 int senderAccount = 0;
			 int receiverAccount =0;
			 
			 while (resultSet.next()) {
			     String email = resultSet.getString("email");
			     if (email.equals(customerEmail)) {
			         transaction_id = resultSet.getInt("transaction_id");
			         transactionType = resultSet.getString("transaction_type");
			         date = resultSet.getDate("date");
			         amount = resultSet.getDouble("amount");
			         senderAccount = resultSet.getInt("sender_account");
			         receiverAccount = resultSet.getInt("receiver_acccount");
			         transactions.add(transactionService.transfer(transaction_id, date, transactionType, amount, senderAccount, receiverAccount));
			     }
			 }
			 return transactions;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return null;
	}
	
	
	public boolean updateCustomerProfile(String email, String field, String newValue) {
		
		try {
			if(field.equals("firstName")) {
				preStatement = connection.prepareStatement("update customers set first_name = ? where email = ?");
				preStatement.setString(1, newValue);
				preStatement.setString(2, email);
			}
			if(field.equals("lastName")) {
				preStatement = connection.prepareStatement("update customers set last_name = ? where email = ?");
				preStatement.setString(1, newValue);
				preStatement.setString(2, email);
			}
			if(field.equals("password")) {
				preStatement = connection.prepareStatement("update customers set password = ? where email = ?");
				preStatement.setString(1, newValue);
				preStatement.setString(2, email);
			}
			preStatement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Transaction> getAllTransactions() {
		try {
			preStatement = connection.prepareStatement("select * from transactions");
			ResultSet result = preStatement.executeQuery();
			TransactionService service = new TransactionService();
			List<Transaction> transactions = new ArrayList<Transaction>();
			int transactionID = 0;
			String transactionType = null;
			Date date = null;
			double amount = 0;
			int receiverAccount = 0;
			int senderAccount = 0;
			int accountNumber = 0;
			while(result.next()) {
				transactionID = result.getInt("transaction_id");
				transactionType = result.getString("transaction_type");
				amount = result.getDouble("amount");
				receiverAccount = result.getInt("receiver_acccount");
				senderAccount = result.getInt("sender_account");
				date = result.getDate("date");
				accountNumber = result.getInt("account_number");
                transactions.add(service.transfer(transactionID, date, transactionType, amount, senderAccount, receiverAccount));
            }
			return transactions;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}

