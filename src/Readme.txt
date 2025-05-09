Personal Budgeting Application
CS251 Winter 2025 Homework 2
Team: [Your Names Here]
IDs: 20230308, 20240832, 20230759

=== Files Included ===
- budgetapp/PersonalBudgetingApp.java: Main application and all classes (entity, control, boundary).
- schema.sql: SQL script to create SQLite database tables.
- budget.db: SQLite database file (created automatically when running the app).
- Readme.txt: This file.

=== Tools Used ===
- Java 17: Programming language.
- JavaFX 17: For the graphical user interface.
- SQLite: For data storage.
- JDBC: For database connectivity.
- IntelliJ IDEA: IDE for development.
- GitHub: For version control.

=== How to Run ===
1. Ensure Java 17 and JavaFX 17 are installed.
2. Add the SQLite JDBC driver (sqlite-jdbc-3.36.0.3.jar) to your project.
3. Place all files in a project directory.
4. Run schema.sql to create the database tables (or let the app create them automatically).
5. Compile and run PersonalBudgetingApp.java:
   - In IntelliJ: Open the project, configure JavaFX, and run the main class.
   - Command line: 
     javac --module-path [path-to-javafx-lib] --add-modules javafx.controls budgetapp/PersonalBudgetingApp.java
     java --module-path [path-to-javafx-lib] --add-modules javafx.controls budgetapp.PersonalBudgetingApp
6. The app starts with a login page. Click "Sign Up" to create an account, then log in to access the dashboard.

=== Notes ===
- The app implements six user stories: Sign Up, Login, Tracking Income, Budgeting & Analysis, Reminders, Expense Tracking.
- Passwords are stored in plain text for simplicity (in production, they should be hashed).
- Use the dashboard to navigate to different features.
- GitHub repository: [Your private repo URL, accessible to TA].