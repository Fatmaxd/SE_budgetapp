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

        /**
         * Constructs a new User object.
         *
         * @param id       The ID of the user.
         * @param email    The email of the user.
         * @param username The username of the user.
         * @param password The password of the user.
         * @param phone    The phone number of the user.
         */
        public User(int id, String email, String username, String password, String phone) {
            this.id = id;
            this.email = email;
            this.username = username;
            this.password = password;
            this.phone = phone;
        }

        /**
         * Returns the ID of the user.
         *
         * @return The user ID.
         */
        public int getId() { return id; }

        /**
         * Returns the email of the user.
         *
         * @return The user's email.
         */
        public String getEmail() { return email; }

        /**
         * Returns the username of the user.
         *
         * @return The user's username.
         */
        public String getUsername() { return username; }

        /**
         * Returns the password of the user.
         *
         * @return The user's password.
         */
        public String getPassword() { return password; }

        /**
         * Returns the phone number of the user.
         *
         * @return The user's phone number.
         */
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

        /**
         * Constructs a new Income object.
         *
         * @param id      The ID of the income entry.
         * @param userId  The ID of the user.
         * @param source  The source of the income.
         * @param amount  The amount of the income.
         * @param date    The date the income was recorded.
         */
        public Income(int id, int userId, String source, double amount, String date) {
            this.id = id;
            this.userId = userId;
            this.source = source;
            this.amount = amount;
            this.date = date;
        }

        /**
         * Returns the ID of the income entry.
         *
         * @return The income entry ID.
         */
        public int getId() { return id; }

        /**
         * Returns the user ID associated with the income entry.
         *
         * @return The user ID.
         */
        public int getUserId() { return userId; }

        /**
         * Returns the source of the income.
         *
         * @return The income source.
         */
        public String getSource() { return source; }

        /**
         * Returns the amount of the income.
         *
         * @return The income amount.
         */
        public double getAmount() { return amount; }

        /**
         * Returns the date the income was recorded.
         *
         * @return The income date.
         */
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

        /**
         * Constructs a new Expense object.
         *
         * @param id       The ID of the expense entry.
         * @param userId   The ID of the user.
         * @param amount   The amount of the expense.
         * @param category The category of the expense.
         * @param date     The date the expense was recorded.
         */
        public Expense(int id, int userId, double amount, String category, String date) {
            this.id = id;
            this.userId = userId;
            this.amount = amount;
            this.category = category;
            this.date = date;
        }

        /**
         * Returns the ID of the expense entry.
         *
         * @return The expense entry ID.
         */
        public int getId() { return id; }

        /**
         * Returns the user ID associated with the expense entry.
         *
         * @return The user ID.
         */
        public int getUserId() { return userId; }

        /**
         * Returns the amount of the expense.
         *
         * @return The expense amount.
         */
        public double getAmount() { return amount; }

        /**
         * Returns the category of the expense.
         *
         * @return The expense category.
         */
        public String getCategory() { return category; }

        /**
         * Returns the date the expense was recorded.
         *
         * @return The expense date.
         */
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

        /**
         * Constructs a new Budget object.
         *
         * @param id       The ID of the budget entry.
         * @param userId   The ID of the user.
         * @param category The category of the budget.
         * @param amount   The amount allocated for the budget.
         */
        public Budget(int id, int userId, String category, double amount) {
            this.id = id;
            this.userId = userId;
            this.category = category;
            this.amount = amount;
        }

        /**
         * Returns the ID of the budget entry.
         *
         * @return The budget entry ID.
         */
        public int getId() { return id; }

        /**
         * Returns the user ID associated with the budget entry.
         *
         * @return The user ID.
         */
        public int getUserId() { return userId; }

        /**
         * Returns the category of the budget.
         *
         * @return The budget category.
         */
        public String getCategory() { return category; }

        /**
         * Returns the amount allocated for the budget.
         *
         * @return The budget amount.
         */
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

        /**
         * Constructs a new Reminder object.
         *
         * @param id     The ID of the reminder.
         * @param userId The ID of the user.
         * @param title  The title of the reminder.
         * @param date   The date the reminder is due.
         * @param time   The time the reminder is due.
         */
        public Reminder(int id, int userId, String title, String date, String time) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.date = date;
            this.time = time;
        }

        /**
         * Returns the ID of the reminder.
         *
         * @return The reminder ID.
         */
        public int getId() { return id; }

        /**
         * Returns the user ID associated with the reminder.
         *
         * @return The user ID.
         */
        public int getUserId() { return userId; }

        /**
         * Returns the title of the reminder.
         *
         * @return The reminder title.
         */
        public String getTitle() { return title; }

        /**
         * Returns the date the reminder is due.
         *
         * @return The reminder date.
         */
        public String getDate() { return date; }

        /**
         * Returns the time the reminder is due.
         *
         * @return The reminder time.
         */
        public String getTime() { return time; }
    }
}
