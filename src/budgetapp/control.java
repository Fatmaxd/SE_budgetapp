package budgetapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;


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
        String sql = "INSERT INTO reminders (user_id, title, date, time, completed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, title);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.setBoolean(5, false);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void checkReminders(int userId) {
        String sql = "SELECT id, title, date, time FROM reminders WHERE user_id = ? AND date || ' ' || time <= ? AND completed = FALSE";
        try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            pstmt.setString(2, now.format(formatter));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int reminderId = rs.getInt("id");
                String title = rs.getString("title");
                String date = rs.getString("date");
                String time = rs.getString("time");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reminder");
                    alert.setHeaderText("Reminder: " + title);
                    alert.setContentText("Due: " + date + " " + time);
                    ButtonType markCompleted = new ButtonType("Mark as Completed");
                    ButtonType dismiss = new ButtonType("Dismiss", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(markCompleted, dismiss);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == markCompleted) {
                            markReminderCompleted(userId, reminderId);
                        }
                    });
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void markReminderCompleted(int userId, int reminderId) {
        String sql = "UPDATE reminders SET completed = TRUE WHERE user_id = ? AND id = ?";
        try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, reminderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    private void deleteReminder(int userId, String title, String date, String time) {
        String sql = "DELETE FROM reminders WHERE user_id = ? AND title = ? AND date = ? AND time = ?";
        try (Connection conn = util.Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, title);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }