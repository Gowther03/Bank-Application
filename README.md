🏦 Bank Application

🚀 Overview A dynamic and responsive bank management system built using
JSP, Servlets, and Bootstrap 5. The application provides separate
dashboards for admins and customers to manage accounts, transactions,
and profiles efficiently.

✨ Features

🔑 Admin Dashboard 👤 Add New Customers. 💼 Create New Bank Accounts. 📝
View Customer Information. 💳 Monitor Transactions. 🔒 Secure Logout. 👥
Customer Dashboard 📖 View Passbook and Transaction History. 💸 Make
Deposits and Withdrawals. ✏️ Update Profile Details. 🔍 View Account
Balances.

🔐 Login
https://private-user-images.githubusercontent.com/92570822/393475089-a46a1a1d-4c5d-4122-8851-8a97f226a641.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzM1NTAxNzQsIm5iZiI6MTczMzU0OTg3NCwicGF0aCI6Ii85MjU3MDgyMi8zOTM0NzUwODktYTQ2YTFhMWQtNGM1ZC00MTIyLTg4NTEtOGE5N2YyMjZhNjQxLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDEyMDclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQxMjA3VDA1Mzc1NFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWNiZWZmYzEzMzRlMTYxZWUwN2IwZWVjMzg1NGFlODBlNDQxMTQwM2M1YWI4NDU1ZmU0OGUzZDllNTYwZDQ4NmQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.qJOi-jYa4svehbmpPxr7i9CdxGZ_Z4tPdlgJ62x-4dE

Admin Login: Access admin management features. Customer Login: Access
customer profile and account details.

📚 Tech Stack

Frontend: HTML, CSS, JSP, Bootstrap 5. Backend: Java (Servlets, JSTL),
JDBC. Database: MySQL (for storing users, accounts, and transactions).
Build Tool: Maven.


🔗 Flow Diagram

Login: Authenticates users based on role (Admin or Customer). Admin
Dashboard: Manages customers, accounts, and transactions. Customer
Dashboard: Handles account interactions and profile updates.


📸 Screenshots 

Admin Dashboard

Customer Dashboard

Login Page



🛠️ Installation & Setup

Clone the Repository:

git clone https://github.com/Gowther03/Bank-Application.git cd


Bank-Application Setup Database:

Import the provided .sql file into your MySQL database. Update database
configurations in src/main/resources/application.properties. Run the
Application:

mvn clean install mvn tomcat7:run Access in Browser:

Admin: http://localhost:8080/adminHomePage.jsp Customer:
http://localhost:8080/customerHomePage.jsp


🛡️ Security Considerations

Passwords should be hashed and salted (e.g., using BCrypt). Secure
sensitive data with HTTPS. Implement proper session handling and
timeouts.

🙌 Contributors Aashish Mishra.
