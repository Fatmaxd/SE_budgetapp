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
     * Manages SQLite database connections and queries.
     */
    public static class Database {
        private static final String URL = "jdbc:sqlite:budget.db";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL);
        }

        public static void initialize() {
            try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
                // Create tables if they don't exist
                stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "email TEXT NOT NULL, " +
                        "username TEXT NOT NULL, " +
                        "password TEXT NOT NULL, " +
                        "phone TEXT NOT NULL)");
                stmt.execute("CREATE TABLE IF NOT EXISTS incomes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "source TEXT, " +
                        "amount REAL, " +
                        "date TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");
                stmt.execute("CREATE TABLE IF NOT EXISTS expenses (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "amount REAL, " +
                        "category TEXT, " +
                        "date TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");
                stmt.execute("CREATE TABLE IF NOT EXISTS budgets (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "category TEXT, " +
                        "amount REAL, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");
                stmt.execute("CREATE TABLE IF NOT EXISTS reminders (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "title TEXT, " +
                        "date TEXT, " +
                        "time TEXT, " +
                        "completed BOOLEAN DEFAULT FALSE, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))");

                // Check if completed column exists in reminders table and add if missing
                try (ResultSet rs = conn.getMetaData().getColumns(null, null, "reminders", "completed")) {
                    if (!rs.next()) {
                        stmt.execute("ALTER TABLE reminders ADD COLUMN completed BOOLEAN DEFAULT FALSE");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}