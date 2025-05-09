package budgetapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Contains all boundary classes for the Personal Budgeting application.
 */
public class boundary {
    // Common styles for the app
    private static final String APP_STYLE = "-fx-background-color: linear-gradient(to bottom, #2E2E2E, #1C2526);";
    private static final String LABEL_STYLE = "-fx-text-fill: #E0E0E0; -fx-font-size: 16px; -fx-font-family: 'Arial';";
    private static final String BUTTON_STYLE = "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;";
    private static final String BUTTON_HOVER_STYLE = "-fx-background-color: #45A049;";
    private static final String TEXT_FIELD_STYLE = "-fx-background-color: #424242; -fx-text-fill: white; -fx-prompt-text-fill: #B0B0B0; -fx-border-color: #616161; -fx-border-radius: 5;";
    private static final String ERROR_STYLE = "-fx-border-color: #F44336; -fx-border-width: 2;";

    /**
     * Displays the login page for user authentication.
     */
    public static class LoginPage {
        private Stage stage;
        private control.AuthController authController = new control.AuthController();

        public LoginPage(Stage stage) {
            this.stage = stage;
        }

        public void show() {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(15);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Personal Budgeting - Login");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, javafx.geometry.HPos.CENTER);

            Label usernameLabel = new Label("Username:");
            usernameLabel.setStyle(LABEL_STYLE);
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(usernameField, new Tooltip("Enter your username"));

            Label passwordLabel = new Label("Password:");
            passwordLabel.setStyle(LABEL_STYLE);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(passwordField, new Tooltip("Enter your password"));

            Button loginButton = new Button("Login");
            loginButton.setStyle(BUTTON_STYLE);
            loginButton.setOnMouseEntered(e -> loginButton.setStyle(BUTTON_HOVER_STYLE));
            loginButton.setOnMouseExited(e -> loginButton.setStyle(BUTTON_STYLE));

            Button signupButton = new Button("Sign Up");
            signupButton.setStyle(BUTTON_STYLE);
            signupButton.setOnMouseEntered(e -> signupButton.setStyle(BUTTON_HOVER_STYLE));
            signupButton.setOnMouseExited(e -> signupButton.setStyle(BUTTON_STYLE));

            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                boolean hasError = false;
                if (username.isEmpty()) {
                    usernameField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    usernameField.setStyle(TEXT_FIELD_STYLE);
                }
                if (password.isEmpty()) {
                    passwordField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    passwordField.setStyle(TEXT_FIELD_STYLE);
                }
                if (!hasError) {
                    entity.User user = authController.loginUser(username, password);
                    if (user != null) {
                        new Dashboard(stage, user).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid credentials").showAndWait();
                    }
                }
            });

            signupButton.setOnAction(e -> new SignUpPage(stage).show());

            // Add elements to GridPane
            root.add(titleLabel, 0, 0, 2, 1);
            root.add(usernameLabel, 0, 1);
            root.add(usernameField, 1, 1);
            root.add(passwordLabel, 0, 2);
            root.add(passwordField, 1, 2);
            root.add(loginButton, 0, 3);
            root.add(signupButton, 1, 3);

            Scene scene = new Scene(root, 400, 500);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        }
    }

    /**
     * Displays the sign-up page for creating a new user account.
     */
    public static class SignUpPage {
        private Stage stage;
        private control.AuthController authController = new control.AuthController();

        public SignUpPage(Stage stage) {
            this.stage = stage;
        }

        public void show() {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(15);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Personal Budgeting - Sign Up");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, javafx.geometry.HPos.CENTER);

            Label emailLabel = new Label("Email:");
            emailLabel.setStyle(LABEL_STYLE);
            TextField emailField = new TextField();
            emailField.setPromptText("Enter email");
            emailField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(emailField, new Tooltip("Enter your email address"));

            Label usernameLabel = new Label("Username:");
            usernameLabel.setStyle(LABEL_STYLE);
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(usernameField, new Tooltip("Choose a username"));

            Label passwordLabel = new Label("Password:");
            passwordLabel.setStyle(LABEL_STYLE);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(passwordField, new Tooltip("Choose a password"));

            Label phoneLabel = new Label("Phone:");
            phoneLabel.setStyle(LABEL_STYLE);
            TextField phoneField = new TextField();
            phoneField.setPromptText("Enter phone number");
            phoneField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(phoneField, new Tooltip("Enter your phone number"));

            Button signupButton = new Button("Sign Up");
            signupButton.setStyle(BUTTON_STYLE);
            signupButton.setOnMouseEntered(e -> signupButton.setStyle(BUTTON_HOVER_STYLE));
            signupButton.setOnMouseExited(e -> signupButton.setStyle(BUTTON_STYLE));

            Button backButton = new Button("Back");
            backButton.setStyle(BUTTON_STYLE);
            backButton.setOnMouseEntered(e -> backButton.setStyle(BUTTON_HOVER_STYLE));
            backButton.setOnMouseExited(e -> backButton.setStyle(BUTTON_STYLE));

            signupButton.setOnAction(e -> {
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String phone = phoneField.getText();
                boolean hasError = false;
                if (email.isEmpty()) {
                    emailField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    emailField.setStyle(TEXT_FIELD_STYLE);
                }
                if (username.isEmpty()) {
                    usernameField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    usernameField.setStyle(TEXT_FIELD_STYLE);
                }
                if (password.isEmpty()) {
                    passwordField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    passwordField.setStyle(TEXT_FIELD_STYLE);
                }
                if (phone.isEmpty()) {
                    phoneField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    phoneField.setStyle(TEXT_FIELD_STYLE);
                }
                if (!hasError) {
                    boolean success = authController.signupUser(email, username, password, phone);
                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Sign-up successful! Please log in.").showAndWait();
                        new LoginPage(stage).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Sign-up failed").showAndWait();
                    }
                }
            });

            backButton.setOnAction(e -> new LoginPage(stage).show());

            root.add(titleLabel, 0, 0, 2, 1);
            root.add(emailLabel, 0, 1);
            root.add(emailField, 1, 1);
            root.add(usernameLabel, 0, 2);
            root.add(usernameField, 1, 2);
            root.add(passwordLabel, 0, 3);
            root.add(passwordField, 1, 3);
            root.add(phoneLabel, 0, 4);
            root.add(phoneField, 1, 4);
            root.add(signupButton, 0, 5);
            root.add(backButton, 1, 5);

            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        }
    }

    /**
     * Displays the dashboard with navigation to other features.
     */
    public static class Dashboard {
        private Stage stage;
        private entity.User user;

        public Dashboard(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            VBox root = new VBox(15);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Welcome, " + user.getUsername());
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");

            Button incomeButton = new Button("Track Income");
            incomeButton.setStyle(BUTTON_STYLE);
            incomeButton.setOnMouseEntered(e -> incomeButton.setStyle(BUTTON_HOVER_STYLE));
            incomeButton.setOnMouseExited(e -> incomeButton.setStyle(BUTTON_STYLE));

            Button expenseButton = new Button("Track Expense");
            expenseButton.setStyle(BUTTON_STYLE);
            expenseButton.setOnMouseEntered(e -> expenseButton.setStyle(BUTTON_HOVER_STYLE));
            expenseButton.setOnMouseExited(e -> expenseButton.setStyle(BUTTON_STYLE));

            Button budgetButton = new Button("Set Budget");
            budgetButton.setStyle(BUTTON_STYLE);
            budgetButton.setOnMouseEntered(e -> budgetButton.setStyle(BUTTON_HOVER_STYLE));
            budgetButton.setOnMouseExited(e -> budgetButton.setStyle(BUTTON_STYLE));

            Button reminderButton = new Button("Set Reminder");
            reminderButton.setStyle(BUTTON_STYLE);
            reminderButton.setOnMouseEntered(e -> reminderButton.setStyle(BUTTON_HOVER_STYLE));
            reminderButton.setOnMouseExited(e -> reminderButton.setStyle(BUTTON_STYLE));

            Button logoutButton = new Button("Logout");
            logoutButton.setStyle(BUTTON_STYLE);
            logoutButton.setOnMouseEntered(e -> logoutButton.setStyle(BUTTON_HOVER_STYLE));
            logoutButton.setOnMouseExited(e -> logoutButton.setStyle(BUTTON_STYLE));

            incomeButton.setOnAction(e -> new IncomeTrackingPage(stage, user).show());
            expenseButton.setOnAction(e -> new ExpenseTrackingPage(stage, user).show());
            budgetButton.setOnAction(e -> new BudgetPage(stage, user).show());
            reminderButton.setOnAction(e -> new ReminderPage(stage, user).show());
            logoutButton.setOnAction(e -> new LoginPage(stage).show());

            root.getChildren().addAll(titleLabel, incomeButton, expenseButton, budgetButton, reminderButton, logoutButton);
            Scene scene = new Scene(root, 400, 500);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        }
    }

    /**
     * Displays the page for tracking income.
     */
    public static class IncomeTrackingPage {
        private Stage stage;
        private entity.User user;
        private control.IncomeController incomeController = new control.IncomeController();

        public IncomeTrackingPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(15);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Track Income");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, javafx.geometry.HPos.CENTER);

            Label sourceLabel = new Label("Source:");
            sourceLabel.setStyle(LABEL_STYLE);
            TextField sourceField = new TextField();
            sourceField.setPromptText("e.g., Salary");
            sourceField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(sourceField, new Tooltip("Enter the source of income"));

            Label amountLabel = new Label("Amount:");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Enter amount");
            amountField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(amountField, new Tooltip("Enter the amount"));

            Label dateLabel = new Label("Date:");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(dateField, new Tooltip("Enter the date (YYYY-MM-DD)"));

            Button addButton = new Button("Add Income");
            addButton.setStyle(BUTTON_STYLE);
            addButton.setOnMouseEntered(e -> addButton.setStyle(BUTTON_HOVER_STYLE));
            addButton.setOnMouseExited(e -> addButton.setStyle(BUTTON_STYLE));

            Button backButton = new Button("Back");
            backButton.setStyle(BUTTON_STYLE);
            backButton.setOnMouseEntered(e -> backButton.setStyle(BUTTON_HOVER_STYLE));
            backButton.setOnMouseExited(e -> backButton.setStyle(BUTTON_STYLE));

            addButton.setOnAction(e -> {
                String source = sourceField.getText();
                String amountText = amountField.getText();
                String date = dateField.getText();
                boolean hasError = false;
                if (source.isEmpty()) {
                    sourceField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    sourceField.setStyle(TEXT_FIELD_STYLE);
                }
                double amount = 0;
                try {
                    amount = Double.parseDouble(amountText);
                    amountField.setStyle(TEXT_FIELD_STYLE);
                } catch (NumberFormatException ex) {
                    amountField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                }
                if (date.isEmpty()) {
                    dateField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    dateField.setStyle(TEXT_FIELD_STYLE);
                }
                if (!hasError) {
                    boolean success = incomeController.addIncome(user.getId(), source, amount, date);
                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Income added successfully!").showAndWait();
                        sourceField.clear();
                        amountField.clear();
                        dateField.clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to add income").showAndWait();
                    }
                }
            });

            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            root.add(titleLabel, 0, 0, 2, 1);
            root.add(sourceLabel, 0, 1);
            root.add(sourceField, 1, 1);
            root.add(amountLabel, 0, 2);
            root.add(amountField, 1, 2);
            root.add(dateLabel, 0, 3);
            root.add(dateField, 1, 3);
            root.add(addButton, 0, 4);
            root.add(backButton, 1, 4);

            Scene scene = new Scene(root, 400, 500);
            stage.setScene(scene);
            stage.setTitle("Track Income");
            stage.show();
        }
    }

    /**
     * Displays the page for tracking expenses.
     */
    public static class ExpenseTrackingPage {
        private Stage stage;
        private entity.User user;
        private control.ExpenseController expenseController = new control.ExpenseController();

        public ExpenseTrackingPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(15);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Track Expense");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, javafx.geometry.HPos.CENTER);

            Label amountLabel = new Label("Amount:");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Enter amount");
            amountField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(amountField, new Tooltip("Enter the expense amount"));

            Label categoryLabel = new Label("Category:");
            categoryLabel.setStyle(LABEL_STYLE);
            TextField categoryField = new TextField();
            categoryField.setPromptText("e.g., Food");
            categoryField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(categoryField, new Tooltip("Enter the expense category"));

            Label dateLabel = new Label("Date:");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(dateField, new Tooltip("Enter the date (YYYY-MM-DD)"));

            Button addButton = new Button("Add Expense");
            addButton.setStyle(BUTTON_STYLE);
            addButton.setOnMouseEntered(e -> addButton.setStyle(BUTTON_HOVER_STYLE));
            addButton.setOnMouseExited(e -> addButton.setStyle(BUTTON_STYLE));

            Button backButton = new Button("Back");
            backButton.setStyle(BUTTON_STYLE);
            backButton.setOnMouseEntered(e -> backButton.setStyle(BUTTON_HOVER_STYLE));
            backButton.setOnMouseExited(e -> backButton.setStyle(BUTTON_STYLE));

            addButton.setOnAction(e -> {
                String amountText = amountField.getText();
                String category = categoryField.getText();
                String date = dateField.getText();
                boolean hasError = false;
                double amount = 0;
                try {
                    amount = Double.parseDouble(amountText);
                    amountField.setStyle(TEXT_FIELD_STYLE);
                } catch (NumberFormatException ex) {
                    amountField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                }
                if (category.isEmpty()) {
                    categoryField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    categoryField.setStyle(TEXT_FIELD_STYLE);
                }
                if (date.isEmpty()) {
                    dateField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    dateField.setStyle(TEXT_FIELD_STYLE);
                }
                if (!hasError) {
                    boolean success = expenseController.addExpense(user.getId(), amount, category, date);
                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Expense added successfully!").showAndWait();
                        amountField.clear();
                        categoryField.clear();
                        dateField.clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to add expense").showAndWait();
                    }
                }
            });

            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            root.add(titleLabel, 0, 0, 2, 1);
            root.add(amountLabel, 0, 1);
            root.add(amountField, 1, 1);
            root.add(categoryLabel, 0, 2);
            root.add(categoryField, 1, 2);
            root.add(dateLabel, 0, 3);
            root.add(dateField, 1, 3);
            root.add(addButton, 0, 4);
            root.add(backButton, 1, 4);

            Scene scene = new Scene(root, 400, 500);
            stage.setScene(scene);
            stage.setTitle("Track Expense");
            stage.show();
        }
    }

    /**
     * Displays the page for setting and tracking budgets.
     */
    public static class BudgetPage {
        private Stage stage;
        private entity.User user;
        private control.BudgetController budgetController = new control.BudgetController();

        public BudgetPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(15);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Set Budget");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, javafx.geometry.HPos.CENTER);

            Label categoryLabel = new Label("Category:");
            categoryLabel.setStyle(LABEL_STYLE);
            TextField categoryField = new TextField();
            categoryField.setPromptText("e.g., Food");
            categoryField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(categoryField, new Tooltip("Enter the budget category"));

            Label amountLabel = new Label("Amount:");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Budget amount");
            amountField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(amountField, new Tooltip("Enter the budget amount"));

            Button setButton = new Button("Set Budget");
            setButton.setStyle(BUTTON_STYLE);
            setButton.setOnMouseEntered(e -> setButton.setStyle(BUTTON_HOVER_STYLE));
            setButton.setOnMouseExited(e -> setButton.setStyle(BUTTON_STYLE));

            Button trackButton = new Button("Track Budget");
            trackButton.setStyle(BUTTON_STYLE);
            trackButton.setOnMouseEntered(e -> trackButton.setStyle(BUTTON_HOVER_STYLE));
            trackButton.setOnMouseExited(e -> trackButton.setStyle(BUTTON_STYLE));

            Label resultLabel = new Label();
            resultLabel.setStyle(LABEL_STYLE);
            resultLabel.setWrapText(true);

            Button backButton = new Button("Back");
            backButton.setStyle(BUTTON_STYLE);
            backButton.setOnMouseEntered(e -> backButton.setStyle(BUTTON_HOVER_STYLE));
            backButton.setOnMouseExited(e -> backButton.setStyle(BUTTON_STYLE));

            setButton.setOnAction(e -> {
                String category = categoryField.getText();
                String amountText = amountField.getText();
                boolean hasError = false;
                if (category.isEmpty()) {
                    categoryField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    categoryField.setStyle(TEXT_FIELD_STYLE);
                }
                double amount = 0;
                try {
                    amount = Double.parseDouble(amountText);
                    amountField.setStyle(TEXT_FIELD_STYLE);
                } catch (NumberFormatException ex) {
                    amountField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                }
                if (!hasError) {
                    boolean success = budgetController.createBudget(user.getId(), category, amount);
                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Budget set successfully!").showAndWait();
                        amountField.clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to set budget").showAndWait();
                    }
                }
            });

            trackButton.setOnAction(e -> {
                String category = categoryField.getText();
                if (category.isEmpty()) {
                    categoryField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                } else {
                    categoryField.setStyle(TEXT_FIELD_STYLE);
                    resultLabel.setText(budgetController.trackBudget(user.getId(), category));
                }
            });

            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            root.add(titleLabel, 0, 0, 2, 1);
            root.add(categoryLabel, 0, 1);
            root.add(categoryField, 1, 1);
            root.add(amountLabel, 0, 2);
            root.add(amountField, 1, 2);
            root.add(setButton, 0, 3);
            root.add(trackButton, 1, 3);
            root.add(resultLabel, 0, 4, 2, 1);
            root.add(backButton, 0, 5, 2, 1);

            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
            stage.setTitle("Set Budget");
            stage.show();
        }
    }

    /**
     * Displays the page for setting reminders.
     */
    public static class ReminderPage {
        private Stage stage;
        private entity.User user;
        private control.ReminderController reminderController = new control.ReminderController();

        public ReminderPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(15);
            root.setPadding(new Insets(20));
            root.setStyle(APP_STYLE);

            Label titleLabel = new Label("Set Reminder");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 20px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, javafx.geometry.HPos.CENTER);

            Label titleFieldLabel = new Label("Title:");
            titleFieldLabel.setStyle(LABEL_STYLE);
            TextField titleField = new TextField();
            titleField.setPromptText("e.g., Pay Rent");
            titleField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(titleField, new Tooltip("Enter the reminder title"));

            Label dateLabel = new Label("Date:");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(dateField, new Tooltip("Enter the date (YYYY-MM-DD)"));

            Label timeLabel = new Label("Time:");
            timeLabel.setStyle(LABEL_STYLE);
            TextField timeField = new TextField();
            timeField.setPromptText("HH:MM");
            timeField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(timeField, new Tooltip("Enter the time (HH:MM)"));

            Button setButton = new Button("Set Reminder");
            setButton.setStyle(BUTTON_STYLE);
            setButton.setOnMouseEntered(e -> setButton.setStyle(BUTTON_HOVER_STYLE));
            setButton.setOnMouseExited(e -> setButton.setStyle(BUTTON_STYLE));

            Button backButton = new Button("Back");
            backButton.setStyle(BUTTON_STYLE);
            backButton.setOnMouseEntered(e -> backButton.setStyle(BUTTON_HOVER_STYLE));
            backButton.setOnMouseExited(e -> backButton.setStyle(BUTTON_STYLE));

            setButton.setOnAction(e -> {
                String title = titleField.getText();
                String date = dateField.getText();
                String time = timeField.getText();
                boolean hasError = false;
                if (title.isEmpty()) {
                    titleField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    titleField.setStyle(TEXT_FIELD_STYLE);
                }
                if (date.isEmpty()) {
                    dateField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    dateField.setStyle(TEXT_FIELD_STYLE);
                }
                if (time.isEmpty()) {
                    timeField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                    hasError = true;
                } else {
                    timeField.setStyle(TEXT_FIELD_STYLE);
                }
                if (!hasError) {
                    boolean success = reminderController.setReminder(user.getId(), title, date, time);
                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Reminder set successfully!").showAndWait();
                        titleField.clear();
                        dateField.clear();
                        timeField.clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to set reminder").showAndWait();
                    }
                }
            });

            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            root.add(titleLabel, 0, 0, 2, 1);
            root.add(titleFieldLabel, 0, 1);
            root.add(titleField, 1, 1);
            root.add(dateLabel, 0, 2);
            root.add(dateField, 1, 2);
            root.add(timeLabel, 0, 3);
            root.add(timeField, 1, 3);
            root.add(setButton, 0, 4);
            root.add(backButton, 1, 4);

            Scene scene = new Scene(root, 400, 500);
            stage.setScene(scene);
            stage.setTitle("Set Reminder");
            stage.show();
        }
    }
}