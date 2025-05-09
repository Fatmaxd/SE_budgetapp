package budgetapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains all control classes for the Personal Budgeting application.
 */
public class control {
    /**
     * Handles authentication-related operations (Sign Up, Login).
     */
    public static class AuthController {
        public boolean signupUser(String email, String username, String password, String phone) {
            String sql = "INSERT INTO users (email, username, password, phone) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, username);
                pstmt.setString(3, password);
                pstmt.setString(4, phone);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public entity.User loginUser(String username, String password) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new entity.User(rs.getInt("id"), rs.getString("email"), rs.getString("username"),
                            rs.getString("password"), rs.getString("phone"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Manages income-related operations.
     */
    public static class IncomeController {
        public boolean addIncome(int userId, String source, double amount, String date) {
            String sql = "INSERT INTO incomes (user_id, source, amount, date) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, source);
                pstmt.setDouble(3, amount);
                pstmt.setString(4, date);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Manages expense-related operations.
     */
    public static class ExpenseController {
        public boolean addExpense(int userId, double amount, String category, String date) {
            String sql = "INSERT INTO expenses (user_id, amount, category, date) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setDouble(2, amount);
                pstmt.setString(3, category);
                pstmt.setString(4, date);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Manages budget-related operations.
     */
    public static class BudgetController {
        public boolean createBudget(int userId, String category, double amount) {
            String sql = "INSERT INTO budgets (user_id, category, amount) VALUES (?, ?, ?)";
            try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, category);
                pstmt.setDouble(3, amount);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public String trackBudget(int userId, String category) {
            String sqlBudget = "SELECT amount FROM budgets WHERE user_id = ? AND category = ?";
            String sqlExpenses = "SELECT SUM(amount) AS total FROM expenses WHERE user_id = ? AND category = ?";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmtBudget = conn.prepareStatement(sqlBudget);
                 PreparedStatement pstmtExpenses = conn.prepareStatement(sqlExpenses)) {
                pstmtBudget.setInt(1, userId);
                pstmtBudget.setString(2, category);
                ResultSet rsBudget = pstmtBudget.executeQuery();
                double budgetAmount = rsBudget.next() ? rsBudget.getDouble("amount") : 0;

                pstmtExpenses.setInt(1, userId);
                pstmtExpenses.setString(2, category);
                ResultSet rsExpenses = pstmtExpenses.executeQuery();
                double totalExpenses = rsExpenses.next() ? rsExpenses.getDouble("total") : 0;

                return String.format("Budget: %.2f, Spent: %.2f, Remaining: %.2f",
                        budgetAmount, totalExpenses, budgetAmount - totalExpenses);
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error tracking budget";
            }
        }
    }

    /**
     * Manages reminder-related operations.
     */
    public static class ReminderController {
        public boolean setReminder(int userId, String title, String date, String time) {
            String sql = "INSERT INTO reminders (user_id, title, date, time) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, title);
                pstmt.setString(3, date);
                pstmt.setString(4, time);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}