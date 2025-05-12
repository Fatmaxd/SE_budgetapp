package budgetapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Contains utility classes for the Personal Budgeting application.
 */
public class util {

    /**
     * Provides methods to connect to and initialize the SQLite database used
     * by the Personal Budgeting application. This class ensures that required
     * tables exist and are properly structured.
     */
    public static class Database {

        /** JDBC URL for the SQLite database file. */
        private static final String URL = "jdbc:sqlite:budget.db";

        /**
         * Establishes and returns a connection to the SQLite database.
         *
         * @return a {@link Connection} object to the SQLite database
         * @throws SQLException if a database access error occurs
         */
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL);
        }

        /**
         * Initializes the database by creating necessary tables if they do not exist.
         * This includes tables for users, incomes, expenses, budgets, and reminders.
         * It also ensures that the `completed` column exists in the reminders table.
         */
        public static void initialize() {
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {

                // Create 'users' table
                stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "email TEXT NOT NULL, " +
                        "username TEXT NOT NULL, " +
                        "password TEXT NOT NULL, " +
                        "phone TEXT NOT NULL)");

                // Create 'incomes' table
                stmt.execute("CREATE TABLE IF NOT EXISTS incomes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "source TEXT, " +
                        "amount REAL, " +
                        "date TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");

                // Create 'expenses' table
                stmt.execute("CREATE TABLE IF NOT EXISTS expenses (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "amount REAL, " +
                        "category TEXT, " +
                        "date TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");

                // Create 'budgets' table
                stmt.execute("CREATE TABLE IF NOT EXISTS budgets (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "category TEXT, " +
                        "amount REAL, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");

                // Create 'reminders' table
                stmt.execute("CREATE TABLE IF NOT EXISTS reminders (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "title TEXT, " +
                        "date TEXT, " +
                        "time TEXT, " +
                        "completed BOOLEAN DEFAULT FALSE, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");

                // Ensure 'completed' column exists in 'reminders' table
                try (ResultSet rs = conn.getMetaData().getColumns(null, null, "reminders", "completed")) {
                    if (!rs.next()) {
                        stmt.execute("ALTER TABLE reminders ADD COLUMN completed BOOLEAN DEFAULT FALSE");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Consider logging in production applications
            }
        }
    }
}
