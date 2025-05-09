# SE_budgetapp

## Personal Budgeting Application  
**CS251 Winter 2025 – Homework 2**  
**Team Members:** Mazen, Fatma, Farah
**IDs:** 20230308, 20240832, 20230759

---

## 📁 Files Included

- `budgetapp/PersonalBudgetingApp.java`: Main application and package with all classes (entity, control, boundary)
- `schema.sql`: SQL script to create SQLite database tables

---

## 🛠 Tools Used

- **Java 17**: Core programming language
- **JavaFX 17**: GUI framework
- **SQLite**: Database engine
- **JDBC**: Java database connectivity
- **IntelliJ IDEA**: Development environment
- **GitHub**: Version control and collaboration

---

## ▶️ How to Run

1. Ensure Java 17 and JavaFX 17 are installed.
2. Add the SQLite JDBC driver (`sqlite-jdbc-3.49.1.0.jar`) to your project.
3. Place all files in your working project directory.
4. Run `schema.sql` to create tables (optional, the app will auto-generate them if missing).
5. Compile and run `PersonalBudgetingApp.java`:

   - **Using Command Line**:
     ```
     javac --module-path [path-to-javafx-lib] --add-modules javafx.controls budgetapp/PersonalBudgetingApp.java
     java --module-path [path-to-javafx-lib] --add-modules javafx.controls budgetapp.PersonalBudgetingApp
     ```

---

## 🔎 Notes

- Features implemented: Sign Up, Login, Expense Tracking, Income Tracking, Budgeting & Analysis, Reminders
