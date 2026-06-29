# 🏦 Banking System

A professional desktop banking application developed using **Java Swing, JDBC, and PostgreSQL**. The system provides secure role-based access for **Admin, Customer, and Bank**, enabling efficient banking operations such as account management, money transfer, loan processing, and transaction tracking.

---

## 📌 Features

### 👨‍💼 Admin
- Secure Login & Registration
- Manage Users (Add, Update, Delete)
- Manage Banks (Add, Update, Delete)
- View All Accounts
- View Loan Applications

### 👤 Customer
- Secure Login & Registration
- Create Bank Account
- Deposit Money
- Transfer Money
- Apply for Loan
- View Loan Status
- View Transaction History

### 🏦 Bank
- Secure Login & Registration
- Manage Bank Details
- View Customer Accounts
- View Transactions
- Approve or Reject Loan Requests

---

# 🛠 Tech Stack

- Java
- Java Swing (GUI)
- JDBC
- PostgreSQL
- IntelliJ IDEA
- Git & GitHub

---
# 🗄 Database

### Tables

- users
- banks
- accounts
- transactions
- loan_options
- loan

---
# 🚀 How to Run

### 1 Clone Repository

```bash
git clone https://github.com/shrutikagadekar898-tech/Banking-System
```

### 2 Open Project

Open the project in **IntelliJ IDEA**.

### 3 Create Database

Create a PostgreSQL database and execute the SQL script.

### 4 Configure Database

Update your database credentials in:

```
DBConnection.java
```

Example:

```java
url = "jdbc:postgresql://localhost:5432/bankdb";
username = "postgres";
password = "your_password";
```

### 5 Add PostgreSQL JDBC Driver

Download and add the PostgreSQL JDBC driver to the project libraries.

### 6 Run

Run:

```
Main.java
```
# 📂 Project Structure

```
src
 ├── dao
 ├── model
 ├── ui
 ├── util
 └── Main.java
```

---

# 🔐 User Roles

| Role | Access |
|------|--------|
| Admin | Manage Users, Banks, Accounts, View Loans |
| Customer | Account Creation, Deposit, Transfer, Loan, Transactions |
| Bank | Manage Banks, View Accounts, Transactions, Loan Requests |

---

# ✨ Highlights

- Role-Based Authentication
- Modern Java Swing UI
- CRUD Operations
- PostgreSQL Integration
- JDBC Connectivity
- Money Transfer Module
- Loan Application Workflow
- Transaction History
- Clean MVC Structure

---

# 🔮 Future Enhancements

- Email Notifications
- OTP Verification
- Interest Calculation Automation
- Loan EMI Calculator
- Account Statement PDF Export
- Dashboard Analytics
- Search & Filter Features
- Password Encryption (BCrypt)
- Responsive Dark Theme

---

# 👩‍💻 Developer

**Shrutika Gadekar**

Github: https://github.com/shrutikagadekar898-tech

LinkedIn: https://www.linkedin.com/in/shrutikagadekar

---

⭐ If you found this project useful, don't forget to star the repository!
