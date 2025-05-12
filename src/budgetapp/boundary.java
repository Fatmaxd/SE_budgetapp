package budgetapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;


/**
 * Contains all boundary classes for the Personal Budgeting application.
 */
public class boundary {
    // Modified color scheme for black-and-white theme
    private static final String PRIMARY_COLOR = "#1C2526"; // Dark charcoal
    private static final String SECONDARY_COLOR = "#4A4A4A"; // Medium gray
    private static final String ACCENT_COLOR = "#FFFFFF"; // White for accents
    private static final String TEXT_COLOR = "#E0E0E0"; // Light gray for text
    
    // Updated styles for professional dark theme
    private static final String APP_STYLE = String.format(
        "-fx-background-color: linear-gradient(to bottom, %s, #2E3557); " +
        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 12, 0, 0, 3);",
        PRIMARY_COLOR
    );
    private static final String LABEL_STYLE = String.format(
        "-fx-text-fill: %s; -fx-font-size: 16px; " +
        "-fx-font-family: 'Inter', -apple-system, BlinkMacSystemFont, Roboto, sans-serif; " +
        "-fx-font-weight: 700;",
        TEXT_COLOR
    );
    private static final String BUTTON_STYLE = String.format(
        "-fx-background-color: %s; -fx-text-fill: %s; -fx-font-size: 14px; " +
        "-fx-padding: 14 28; -fx-background-radius: 12; -fx-border-radius: 12; " +
        "-fx-font-family: 'Inter', -apple-system, BlinkMacSystemFont, Roboto, sans-serif; " +
        "-fx-font-weight: 600; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 6, 0, 0, 2);",
        ACCENT_COLOR, PRIMARY_COLOR
    );
    private static final String BUTTON_HOVER_STYLE = String.format(
        "-fx-background-color: #E0E0E0; " +
        "-fx-scale-x: 1.03; -fx-scale-y: 1.03;"
    );
    private static final String TEXT_FIELD_STYLE = String.format(
        "-fx-background-color: #2E3537; -fx-text-fill: %s; " +
        "-fx-prompt-text-fill: #6B7280; -fx-border-color: transparent; " +
        "-fx-background-radius: 10; -fx-padding: 12; " +
        "-fx-font-family: 'Inter', -apple-system, BlinkMacSystemFont, Roboto, sans-serif;",
        TEXT_COLOR
    );
    private static final String ERROR_STYLE = "-fx-border-color: #FF5555; -fx-border-width: 2; -fx-border-radius: 10;";

    private static void styleButton(Button button) {
        button.setStyle(BUTTON_STYLE);
        
        // Add scale animation on hover
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), button);
        scaleIn.setToX(1.03);
        scaleIn.setToY(1.03);
        
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), button);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);
        
        // Hover effects
        button.setOnMouseEntered(e -> {
            button.setStyle(BUTTON_STYLE + BUTTON_HOVER_STYLE);
            scaleIn.play();
        });
        button.setOnMouseExited(e -> {
            button.setStyle(BUTTON_STYLE);
            scaleOut.play();
        });
        
        // Click animation
        ScaleTransition clickEffect = new ScaleTransition(Duration.millis(80), button);
        clickEffect.setToX(0.97);
        clickEffect.setToY(0.97);
        clickEffect.setAutoReverse(true);
        clickEffect.setCycleCount(2);
        
        button.setOnMousePressed(e -> clickEffect.play());
        button.setOnMouseReleased(e -> button.setStyle(BUTTON_STYLE));
    }

    public static class LoginPage {
        private Stage stage;
        private control.AuthController authController = new control.AuthController();

        public LoginPage(Stage stage) {
            this.stage = stage;
        }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Header
            Label titleLabel = new Label("Personal Budgeting");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");
            
            // Form container
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");
            
            Label usernameLabel = new Label("Username");
            usernameLabel.setStyle(LABEL_STYLE);
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(usernameField, new Tooltip("Enter your username"));

            Label passwordLabel = new Label("Password");
            passwordLabel.setStyle(LABEL_STYLE);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(passwordField, new Tooltip("Enter your password"));

            // Button container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);
            
            Button loginButton = new Button("Login");
            styleButton(loginButton);
            loginButton.setMinWidth(100);
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

            Button signupButton = new Button("Sign Up");
            styleButton(signupButton);
            signupButton.setMinWidth(100);
            signupButton.setOnAction(e -> new SignUpPage(stage).show());

            formBox.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField
            );
            buttonBox.getChildren().addAll(loginButton, signupButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        }
    }

    public static class SignUpPage {
        private Stage stage;
        private control.AuthController authController = new control.AuthController();

        public SignUpPage(Stage stage) {
            this.stage = stage;
        }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            Label titleLabel = new Label("Create Account");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            Label emailLabel = new Label("Email");
            emailLabel.setStyle(LABEL_STYLE);
            TextField emailField = new TextField();
            emailField.setPromptText("Enter email");
            emailField.setStyle(TEXT_FIELD_STYLE);

            Label usernameLabel = new Label("Username");
            usernameLabel.setStyle(LABEL_STYLE);
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(TEXT_FIELD_STYLE);

            Label passwordLabel = new Label("Password");
            passwordLabel.setStyle(LABEL_STYLE);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(TEXT_FIELD_STYLE);

            Label phoneLabel = new Label("Phone");
            phoneLabel.setStyle(LABEL_STYLE);
            TextField phoneField = new TextField();
            phoneField.setPromptText("Enter phone number");
            phoneField.setStyle(TEXT_FIELD_STYLE);

            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            Button signupButton = new Button("Sign Up");
            styleButton(signupButton);
            signupButton.setMinWidth(100);
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

            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new LoginPage(stage).show());

            formBox.getChildren().addAll(
                emailLabel, emailField,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                phoneLabel, phoneField
            );
            buttonBox.getChildren().addAll(signupButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        }
    }

    public static class Dashboard {
        private static entity.User currentUser; // Track logged-in user
        private Stage stage;
        private entity.User user;

    public Dashboard(Stage stage, entity.User user) {
        this.stage = stage;
        this.user = user;
        currentUser = user; // Set current user
    }
    
    public static entity.User getCurrentUser() {
        return currentUser;
    }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            Label titleLabel = new Label("Welcome, " + user.getUsername());
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            VBox buttonBox = new VBox(20);
            buttonBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            Button incomeButton = new Button("Track Income");
            styleButton(incomeButton);
            incomeButton.setMaxWidth(Double.MAX_VALUE);
            incomeButton.setOnAction(e -> new IncomeTrackingPage(stage, user).show());

            Button expenseButton = new Button("Track Expenses");
            styleButton(expenseButton);
            expenseButton.setMaxWidth(Double.MAX_VALUE);
            expenseButton.setOnAction(e -> new ExpenseTrackingPage(stage, user).show());

            Button budgetButton = new Button("Manage Budget");
            styleButton(budgetButton);
            budgetButton.setMaxWidth(Double.MAX_VALUE);
            budgetButton.setOnAction(e -> new BudgetPage(stage, user).show());

            Button reminderButton = new Button("Set Reminders");
            styleButton(reminderButton);
            reminderButton.setMaxWidth(Double.MAX_VALUE);
            reminderButton.setOnAction(e -> new ReminderPage(stage, user).show());

            Button logoutButton = new Button("Logout");
            styleButton(logoutButton);
            logoutButton.setMaxWidth(Double.MAX_VALUE);
            logoutButton.setOnAction(e -> new LoginPage(stage).show());

            buttonBox.getChildren().addAll(incomeButton, expenseButton, budgetButton, reminderButton, logoutButton);
            root.getChildren().addAll(titleLabel, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        }
    }

    public static class IncomeTrackingPage {
        private Stage stage;
        private entity.User user;
        private control.IncomeController incomeController = new control.IncomeController();

        public IncomeTrackingPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            Label titleLabel = new Label("Track Income");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            Label sourceLabel = new Label("Source");
            sourceLabel.setStyle(LABEL_STYLE);
            TextField sourceField = new TextField();
            sourceField.setPromptText("e.g., Salary");
            sourceField.setStyle(TEXT_FIELD_STYLE);

            Label amountLabel = new Label("Amount");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Enter amount");
            amountField.setStyle(TEXT_FIELD_STYLE);

            Label dateLabel = new Label("Date");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);

            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            Button addButton = new Button("Add Income");
            styleButton(addButton);
            addButton.setMinWidth(100);
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

            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            formBox.getChildren().addAll(
                sourceLabel, sourceField,
                amountLabel, amountField,
                dateLabel, dateField
            );
            buttonBox.getChildren().addAll(addButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Track Income");
            stage.show();
        }
    }

    public static class ExpenseTrackingPage {
        private Stage stage;
        private entity.User user;
        private control.ExpenseController expenseController = new control.ExpenseController();

        public ExpenseTrackingPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            Label titleLabel = new Label("Track Expenses");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            Label amountLabel = new Label("Amount");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Enter amount");
            amountField.setStyle(TEXT_FIELD_STYLE);

            Label categoryLabel = new Label("Category");
            categoryLabel.setStyle(LABEL_STYLE);
            TextField categoryField = new TextField();
            categoryField.setPromptText("e.g., Food");
            categoryField.setStyle(TEXT_FIELD_STYLE);

            Label dateLabel = new Label("Date");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);

            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            Button addButton = new Button("Add Expense");
            styleButton(addButton);
            addButton.setMinWidth(100);
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

            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            formBox.getChildren().addAll(
                amountLabel, amountField,
                categoryLabel, categoryField,
                dateLabel, dateField
            );
            buttonBox.getChildren().addAll(addButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Track Expenses");
            stage.show();
        }
    }

    public static class BudgetPage {
        private Stage stage;
        private entity.User user;
        private control.BudgetController budgetController = new control.BudgetController();

        public BudgetPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            Label titleLabel = new Label("Manage Budget");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            Label categoryLabel = new Label("Category");
            categoryLabel.setStyle(LABEL_STYLE);
            TextField categoryField = new TextField();
            categoryField.setPromptText("e.g., Food");
            categoryField.setStyle(TEXT_FIELD_STYLE);

            Label amountLabel = new Label("Amount");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Budget amount");
            amountField.setStyle(TEXT_FIELD_STYLE);

            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            Button setButton = new Button("Set Budget");
            styleButton(setButton);
            setButton.setMinWidth(100);
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

            Button trackButton = new Button("Track Budget");
            styleButton(trackButton);
            trackButton.setMinWidth(100);
            trackButton.setOnAction(e -> {
                String category = categoryField.getText();
                if (category.isEmpty()) {
                    categoryField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                } else {
                    categoryField.setStyle(TEXT_FIELD_STYLE);
                    Label resultLabel = new Label(budgetController.trackBudget(user.getId(), category));
                    resultLabel.setStyle(LABEL_STYLE + " -fx-padding: 10; -fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 8;");
                    resultLabel.setWrapText(true);
                    VBox.setMargin(resultLabel, new Insets(10, 0, 0, 0));
                    root.getChildren().add(resultLabel);
                }
            });

            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            formBox.getChildren().addAll(
                categoryLabel, categoryField,
                amountLabel, amountField
            );
            buttonBox.getChildren().addAll(setButton, trackButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Manage Budget");
            stage.show();
        }
    }

    public static class ReminderPage {
        private Stage stage;
        private entity.User user;
        private control.ReminderController reminderController = new control.ReminderController();

        public ReminderPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        public void show() {
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            Label titleLabel = new Label("Set Reminder");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            Label titleFieldLabel = new Label("Title");
            titleFieldLabel.setStyle(LABEL_STYLE);
            TextField titleField = new TextField();
            titleField.setPromptText("e.g., Pay Rent");
            titleField.setStyle(TEXT_FIELD_STYLE);

            Label dateLabel = new Label("Date");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);

            Label timeLabel = new Label("Time");
            timeLabel.setStyle(LABEL_STYLE);
            TextField timeField = new TextField();
            timeField.setPromptText("HH:MM");
            timeField.setStyle(TEXT_FIELD_STYLE);

            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            Button setButton = new Button("Set Reminder");
            styleButton(setButton);
            setButton.setMinWidth(100);
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

            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            formBox.getChildren().addAll(
                titleFieldLabel, titleField,
                dateLabel, dateField,
                timeLabel, timeField
            );
            buttonBox.getChildren().addAll(setButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Set Reminder");
            stage.show();
        }
    }
}