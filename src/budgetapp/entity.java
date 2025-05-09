package budgetapp;

/**
 * Contains all entity classes for the Personal Budgeting application.
 */
public class entity {
    /**
     * Represents a user in the Personal Budgeting system.
     */
    public static class User {
        private int id;
        private String email;
        private String username;
        private String password;
        private String phone;

        public User(int id, String email, String username, String password, String phone) {
            this.id = id;
            this.email = email;
            this.username = username;
            this.password = password;
            this.phone = phone;
        }

        public int getId() { return id; }
        public String getEmail() { return email; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getPhone() { return phone; }
    }

    /**
     * Represents an income entry.
     */
    public static class Income {
        private int id;
        private int userId;
        private String source;
        private double amount;
        private String date;

        public Income(int id, int userId, String source, double amount, String date) {
            this.id = id;
            this.userId = userId;
            this.source = source;
            this.amount = amount;
            this.date = date;
        }

        public int getId() { return id; }
        public int getUserId() { return userId; }
        public String getSource() { return source; }
        public double getAmount() { return amount; }
        public String getDate() { return date; }
    }

    /**
     * Represents an expense entry.
     */
    public static class Expense {
        private int id;
        private int userId;
        private double amount;
        private String category;
        private String date;

        public Expense(int id, int userId, double amount, String category, String date) {
            this.id = id;
            this.userId = userId;
            this.amount = amount;
            this.category = category;
            this.date = date;
        }

        public int getId() { return id; }
        public int getUserId() { return userId; }
        public double getAmount() { return amount; }
        public String getCategory() { return category; }
        public String getDate() { return date; }
    }

    /**
     * Represents a budget for a category.
     */
    public static class Budget {
        private int id;
        private int userId;
        private String category;
        private double amount;

        public Budget(int id, int userId, String category, double amount) {
            this.id = id;
            this.userId = userId;
            this.category = category;
            this.amount = amount;
        }

        public int getId() { return id; }
        public int getUserId() { return userId; }
        public String getCategory() { return category; }
        public double getAmount() { return amount; }
    }

    /**
     * Represents a reminder for a financial task.
     */
    public static class Reminder {
        private int id;
        private int userId;
        private String title;
        private String date;
        private String time;

        public Reminder(int id, int userId, String title, String date, String time) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.date = date;
            this.time = time;
        }

        public int getId() { return id; }
        public int getUserId() { return userId; }
        public String getTitle() { return title; }
        public String getDate() { return date; }
        public String getTime() { return time; }
    }
}