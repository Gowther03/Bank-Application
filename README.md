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
![393475089-a46a1a1d-4c5d-4122-8851-8a97f226a641](https://github.com/user-attachments/assets/0b46722c-bfd4-4b05-ac94-35b1d69bb8c8)

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
![393475079-ad20067c-5784-4b61-951e-5b8f2dc4a332](https://github.com/user-attachments/assets/2dcf5b4b-53d4-425c-92e9-ba225c8859cf)

Add New Customer
![393475077-977cf9b6-05ea-46bc-bdcf-6a8b24b433ae](https://github.com/user-attachments/assets/48926533-767a-41e2-aef3-7414eaf399b2)

Add New Bank Account
![393475076-f33688f2-7085-4a33-9f70-750cf375a7d5](https://github.com/user-attachments/assets/eb8828b1-3e8d-4a62-b07b-d76188ded898)

View Customers
![393475090-2a3e9ceb-fa60-4e12-bc3f-9ca70a096a79](https://github.com/user-attachments/assets/8b09b8eb-754e-495f-867c-8abca653b7e3)

View Transactions
![393475091-03b64b04-120b-4721-9c9a-4add64ce9e2e](https://github.com/user-attachments/assets/c5e56528-b28e-4e6a-9157-b043fb92beeb)

Customer Dashboard
![393475081-bb798903-5d68-41ec-a9aa-758677cb3f81](https://github.com/user-attachments/assets/ebd8df1e-ef48-4ea0-9177-bb3ba4195eb8)

Passbook
![393475085-2c5e3463-8bb8-4d08-a280-98a9087d7036](https://github.com/user-attachments/assets/825a49e0-b73e-4525-b7ef-70ccb09d5b15)

New Transaction
![393475083-c705bc33-059c-47e0-a057-fbcccc0811b3](https://github.com/user-attachments/assets/c54edf7d-46ff-4bdf-9804-354adecea031)

Edit Profile
![393475082-ed345687-f2dc-4e60-8a87-135b193c8afd](https://github.com/user-attachments/assets/39b68d83-161c-4aa4-b329-3b65e9ab2286)



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
