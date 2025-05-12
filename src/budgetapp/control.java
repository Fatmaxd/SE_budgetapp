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
 * Contains controller classes that handle business logic for the Personal Budgeting application.
 */
public class control {

    /**
     * Handles user authentication including sign-up and login functionality.
     */
    public static class AuthController {

        /**
         * Registers a new user in the database.
         *
         * @param email    the user's email address
         * @param username the chosen username
         * @param password the chosen password
         * @param phone    the user's phone number
         * @return {@code true} if sign-up was successful; {@code false} otherwise
         */
        public boolean signupUser(String email, String username, String password, String phone) {
            String sql = "INSERT INTO users (email, username, password, phone) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

        /**
         * Attempts to log in a user with the provided credentials.
         *
         * @param username the username
         * @param password the password
         * @return a {@link entity.User} object if authentication is successful, otherwise {@code null}
         */
        public entity.User loginUser(String username, String password) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new entity.User(rs.getInt("id"), rs.getString("email"),
                            rs.getString("username"), rs.getString("password"), rs.getString("phone"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Handles operations related to user income.
     */
    public static class IncomeController {

        /**
         * Adds a new income entry for a user.
         *
         * @param userId the ID of the user
         * @param source the income source description
         * @param amount the amount of income
         * @param date   the date of the income
         * @return {@code true} if income was successfully added; {@code false} otherwise
         */
        public boolean addIncome(int userId, String source, double amount, String date) {
            String sql = "INSERT INTO incomes (user_id, source, amount, date) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
     * Handles operations related to user expenses.
     */
    public static class ExpenseController {

        /**
         * Adds a new expense entry for a user.
         *
         * @param userId   the ID of the user
         * @param amount   the amount spent
         * @param category the expense category
         * @param date     the date of the expense
         * @return {@code true} if expense was successfully added; {@code false} otherwise
         */
        public boolean addExpense(int userId, double amount, String category, String date) {
            String sql = "INSERT INTO expenses (user_id, amount, category, date) VALUES (?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
     * Handles operations related to budgeting.
     */
    public static class BudgetController {

        /**
         * Creates a new budget for a specific category.
         *
         * @param userId   the ID of the user
         * @param category the budget category
         * @param amount   the budgeted amount
         * @return {@code true} if the budget was successfully created; {@code false} otherwise
         */
        public boolean createBudget(int userId, String category, double amount) {
            String sql = "INSERT INTO budgets (user_id, category, amount) VALUES (?, ?, ?)";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

        /**
         * Tracks how much has been spent against a given budget category.
         *
         * @param userId   the ID of the user
         * @param category the budget category
         * @return a string summarizing budget, spending, and remaining amount
         */
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
     * Handles operations related to reminders.
     */
    public static class ReminderController {

        /**
         * Sets a new reminder for a user.
         *
         * @param userId the ID of the user
         * @param title  the reminder title
         * @param date   the reminder date (format: yyyy-MM-dd)
         * @param time   the reminder time (format: HH:mm)
         * @return {@code true} if the reminder was successfully set; {@code false} otherwise
         */
        public boolean setReminder(int userId, String title, String date, String time) {
            String sql = "INSERT INTO reminders (user_id, title, date, time, completed) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

        /**
         * Checks for due reminders and displays alerts to the user.
         *
         * @param userId the ID of the user
         */
        public void checkReminders(int userId) {
            String sql = "SELECT id, title, date, time FROM reminders " +
                    "WHERE user_id = ? AND date || ' ' || time <= ? AND completed = FALSE";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

        /**
         * Marks a reminder as completed in the database.
         *
         * @param userId     the ID of the user
         * @param reminderId the ID of the reminder
         */
        private void markReminderCompleted(int userId, int reminderId) {
            String sql = "UPDATE reminders SET completed = TRUE WHERE user_id = ? AND id = ?";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, reminderId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Deletes a specific reminder.
         *
         * @param userId the ID of the user
         * @param title  the title of the reminder
         * @param date   the date of the reminder
         * @param time   the time of the reminder
         */
        private void deleteReminder(int userId, String title, String date, String time) {
            String sql = "DELETE FROM reminders WHERE user_id = ? AND title = ? AND date = ? AND time = ?";
            try (Connection conn = util.Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
}
