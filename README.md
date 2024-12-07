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
![393475076-f33688f2-7085-4a33-9f70-750cf375a7d5](https://github.com/user-attachments/assets/65cc8587-15ba-44c0-a2e8-4f47160a7c2a)

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
