# SE_budgetapp

**CS251 Winter 2025 – Homework 2**  
**Team Members:** Mazen, Fatma, Farah  
**IDs:** 20230308, 20240832, 20230759

---

## 📁 Files Included

- `src/budgetapp/PersonalBudgetingApp.java`: Main application class.
- `src/budgetapp/boundary.java`: UI pages (Login, Sign Up, Dashboard, Income, Expense, Budget, Reminder).
- `src/budgetapp/control.java`: Controllers (Auth, Income, Expense, Budget, Reminder).
- `src/budgetapp/entity.java`: Data models (User, Income, Expense, Budget, Reminder).
- `src/budgetapp/util.java`: SQLite database utility.
- `schema.sql`: Initializes the SQLite schema (auto-creates tables).
- `Readme.md`: Project overview and setup instructions.

---

## 🛠 Tools Used

- **JDK 24.0.1**
- **JavaFX 24.0.1**
- **SQLite + JDBC Driver (sqlite-jdbc-3.49.1.0.jar or 3.46.1)**  
- **Visual Studio Code**
- **GitHub**

---

## ▶️ How to Run

1. **Install Requirements**:
   - JDK 24.0.1
   - JavaFX 24.0.1 SDK
   - SQLite JDBC Driver

2. **Project Setup**:
   - Place files in `src/budgetapp/`
   - Extract JavaFX SDK and note its path

3. **Compile and Run (Command Line)**:
```bash
javac --module-path "[javafx-sdk-path]/lib" --add-modules javafx.controls,javafx.fxml -cp "[jdbc-path]" *.java
java --module-path "[javafx-sdk-path]/lib" --add-modules javafx.controls,javafx.fxml -cp ".;[jdbc-path]" budgetapp.PersonalBudgetingApp
```
   - Replace `[javafx-sdk-path]` and `[jdbc-path]` with actual paths.

4. **Optional: VS Code Configuration**:

`.vscode/settings.json`:
```json
{
  "java.project.referencedLibraries": [
    "[jdbc-path]",
    "[javafx-sdk-path]/lib/*.jar"
  ],
  "java.jdt.ls.vmargs": "--module-path "[javafx-sdk-path]/lib" --add-modules javafx.controls,javafx.fxml"
}
```

`.vscode/launch.json`:
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Launch PersonalBudgetingApp",
      "request": "launch",
      "mainClass": "budgetapp.PersonalBudgetingApp",
      "vmArgs": "--module-path "[javafx-sdk-path]/lib" --add-modules javafx.controls,javafx.fxml -cp "[jdbc-path]""
    }
  ]
}
```

5. **Database Initialization**:
   - App auto-creates `budget.db`
   - Or use `schema.sql` with an SQLite tool

---

## 🔎 Notes

- Features: Sign Up, Login, Expense & Income Tracking, Budgeting & Analysis, Reminders
