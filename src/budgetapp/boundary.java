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
    /**
     * Primary color used for main backgrounds. A dark charcoal tone.
     */
    private static final String PRIMARY_COLOR = "#1C2526";

    /**
     * Secondary color used for UI highlights. A medium gray tone.
     */
    private static final String SECONDARY_COLOR = "#4A4A4A";

    /**
     * Accent color used for buttons and important highlights. Pure white.
     */
    private static final String ACCENT_COLOR = "#FFFFFF";

    /**
     * Text color used across labels and text fields. A light gray for contrast.
     */
    private static final String TEXT_COLOR = "#E0E0E0";

    // === Style Constants ===

    /**
     * Main application background style with a linear gradient and drop shadow.
     */
    private static final String APP_STYLE = String.format(
            "-fx-background-color: linear-gradient(to bottom, %s, #2E3557); " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 12, 0, 0, 3);",
            PRIMARY_COLOR
    );

    /**
     * Standard style for labels including font, color, and weight settings.
     */
    private static final String LABEL_STYLE = String.format(
            "-fx-text-fill: %s; -fx-font-size: 16px; " +
                    "-fx-font-family: 'Inter', -apple-system, BlinkMacSystemFont, Roboto, sans-serif; " +
                    "-fx-font-weight: 700;",
            TEXT_COLOR
    );

    /**
     * Default button style with background color, padding, font settings, and drop shadow.
     */
    private static final String BUTTON_STYLE = String.format(
            "-fx-background-color: %s; -fx-text-fill: %s; -fx-font-size: 14px; " +
                    "-fx-padding: 14 28; -fx-background-radius: 12; -fx-border-radius: 12; " +
                    "-fx-font-family: 'Inter', -apple-system, BlinkMacSystemFont, Roboto, sans-serif; " +
                    "-fx-font-weight: 600; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 6, 0, 0, 2);",
            ACCENT_COLOR, PRIMARY_COLOR
    );

    /**
     * Hover style applied to buttons, including slight scaling and lighter background.
     */
    private static final String BUTTON_HOVER_STYLE = String.format(
            "-fx-background-color: #E0E0E0; " +
                    "-fx-scale-x: 1.03; -fx-scale-y: 1.03;"
    );

    /**
     * Style for text fields, including prompt text color and rounded corners.
     */
    private static final String TEXT_FIELD_STYLE = String.format(
            "-fx-background-color: #2E3537; -fx-text-fill: %s; " +
                    "-fx-prompt-text-fill: #6B7280; -fx-border-color: transparent; " +
                    "-fx-background-radius: 10; -fx-padding: 12; " +
                    "-fx-font-family: 'Inter', -apple-system, BlinkMacSystemFont, Roboto, sans-serif;",
            TEXT_COLOR
    );

    /**
     * Error style applied to form fields when validation fails.
     */
    private static final String ERROR_STYLE = "-fx-border-color: #FF5555; -fx-border-width: 2; -fx-border-radius: 10;";

    // === Utility Methods ===

    /**
     * Applies the predefined button style and interactive animations (hover, click) to the given button.
     *
     * @param button the JavaFX {@link Button} to style
     */
    private static void styleButton(Button button) {
        button.setStyle(BUTTON_STYLE);

        // Hover scale animations
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

        // Click effect animation
        ScaleTransition clickEffect = new ScaleTransition(Duration.millis(80), button);
        clickEffect.setToX(0.97);
        clickEffect.setToY(0.97);
        clickEffect.setAutoReverse(true);
        clickEffect.setCycleCount(2);

        button.setOnMousePressed(e -> clickEffect.play());
        button.setOnMouseReleased(e -> button.setStyle(BUTTON_STYLE));
    }

    /**
     * Represents the login interface of the Personal Budgeting application.
     * This page allows users to enter their credentials and authenticate into the system.
     * It also provides navigation to the sign-up page for new users.
     */
    public static class LoginPage {

        /**
         * The primary stage on which the login scene is displayed.
         */
        private Stage stage;

        /**
         * Controller responsible for handling authentication logic.
         */
        private control.AuthController authController = new control.AuthController();

        /**
         * Constructs a new LoginPage instance with the specified application stage.
         *
         * @param stage the main application {@link Stage} where the login page will be shown
         */
        public LoginPage(Stage stage) {
            this.stage = stage;
        }

        /**
         * Displays the login interface. Sets up and styles the layout, fields, and buttons,
         * then attaches logic for login validation and navigation to the dashboard or sign-up page.
         */
        public void show() {
            // Root layout container
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Title label
            Label titleLabel = new Label("Personal Budgeting");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Form container for input fields
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Username input field
            Label usernameLabel = new Label("Username");
            usernameLabel.setStyle(LABEL_STYLE);
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(usernameField, new Tooltip("Enter your username"));

            // Password input field
            Label passwordLabel = new Label("Password");
            passwordLabel.setStyle(LABEL_STYLE);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(TEXT_FIELD_STYLE);
            Tooltip.install(passwordField, new Tooltip("Enter your password"));

            // Button container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            // Login button with validation and navigation logic
            Button loginButton = new Button("Login");
            styleButton(loginButton);
            loginButton.setMinWidth(100);
            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                boolean hasError = false;

                // Input validation
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

                // Authentication and navigation
                if (!hasError) {
                    entity.User user = authController.loginUser(username, password);
                    if (user != null) {
                        new Dashboard(stage, user).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid credentials").showAndWait();
                    }
                }
            });

            // Sign-up navigation button
            Button signupButton = new Button("Sign Up");
            styleButton(signupButton);
            signupButton.setMinWidth(100);
            signupButton.setOnAction(e -> new SignUpPage(stage).show());

            // Assemble layout
            formBox.getChildren().addAll(
                    usernameLabel, usernameField,
                    passwordLabel, passwordField
            );
            buttonBox.getChildren().addAll(loginButton, signupButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            // Set scene and show
            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        }
    }

    /**
     * Represents the sign-up interface of the Personal Budgeting application.
     * This page allows new users to create an account by providing required information such as
     * email, username, password, and phone number.
     * It includes validation and error feedback, and navigates back to the login page upon success or user request.
     */
    public static class SignUpPage {

        /**
         * The primary stage on which the sign-up scene is displayed.
         */
        private Stage stage;

        /**
         * Controller responsible for handling authentication and account creation logic.
         */
        private control.AuthController authController = new control.AuthController();

        /**
         * Constructs a new SignUpPage instance with the specified application stage.
         *
         * @param stage the main application {@link Stage} where the sign-up page will be shown
         */
        public SignUpPage(Stage stage) {
            this.stage = stage;
        }

        /**
         * Displays the sign-up interface. Initializes and styles all layout containers and form fields,
         * and binds button actions for account registration and navigation.
         */
        public void show() {
            // Root layout container
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Title
            Label titleLabel = new Label("Create Account");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Form layout
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Email input
            Label emailLabel = new Label("Email");
            emailLabel.setStyle(LABEL_STYLE);
            TextField emailField = new TextField();
            emailField.setPromptText("Enter email");
            emailField.setStyle(TEXT_FIELD_STYLE);

            // Username input
            Label usernameLabel = new Label("Username");
            usernameLabel.setStyle(LABEL_STYLE);
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(TEXT_FIELD_STYLE);

            // Password input
            Label passwordLabel = new Label("Password");
            passwordLabel.setStyle(LABEL_STYLE);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(TEXT_FIELD_STYLE);

            // Phone number input
            Label phoneLabel = new Label("Phone");
            phoneLabel.setStyle(LABEL_STYLE);
            TextField phoneField = new TextField();
            phoneField.setPromptText("Enter phone number");
            phoneField.setStyle(TEXT_FIELD_STYLE);

            // Buttons container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            // Sign-up button logic
            Button signupButton = new Button("Sign Up");
            styleButton(signupButton);
            signupButton.setMinWidth(100);
            signupButton.setOnAction(e -> {
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String phone = phoneField.getText();
                boolean hasError = false;

                // Input validation
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

                // Registration and feedback
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

            // Back to login button
            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new LoginPage(stage).show());

            // Assemble form and scene
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

    /**
     * Represents the main dashboard screen shown after a successful login in the Personal Budgeting application.
     * This dashboard provides navigation to key features like income tracking, expense tracking,
     * budget management, and reminder setting. It also handles user session management.
     */
    public static class Dashboard {

        /**
         * The current logged-in user instance shared across the application.
         */
        private static entity.User currentUser;

        /**
         * The JavaFX stage on which the dashboard scene is displayed.
         */
        private Stage stage;

        /**
         * The user associated with this session (same as currentUser).
         */
        private entity.User user;

        /**
         * Constructs the Dashboard with the specified stage and user.
         * Also sets the static currentUser reference to the provided user.
         *
         * @param stage the main application window
         * @param user  the authenticated {@link entity.User} instance
         */
        public Dashboard(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
            currentUser = user;
        }

        /**
         * Returns the currently logged-in user for global access within the application.
         *
         * @return the current {@link entity.User}
         */
        public static entity.User getCurrentUser() {
            return currentUser;
        }

        /**
         * Displays the dashboard interface with navigation buttons to various budgeting tools.
         * The dashboard includes options for tracking income and expenses, managing budget, setting reminders, and logging out.
         */
        public void show() {
            // Layout setup
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Welcome message
            Label titleLabel = new Label("Welcome, " + user.getUsername());
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Navigation button container
            VBox buttonBox = new VBox(20);
            buttonBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Income tracking navigation
            Button incomeButton = new Button("Track Income");
            styleButton(incomeButton);
            incomeButton.setMaxWidth(Double.MAX_VALUE);
            incomeButton.setOnAction(e -> new IncomeTrackingPage(stage, user).show());

            // Expense tracking navigation
            Button expenseButton = new Button("Track Expenses");
            styleButton(expenseButton);
            expenseButton.setMaxWidth(Double.MAX_VALUE);
            expenseButton.setOnAction(e -> new ExpenseTrackingPage(stage, user).show());

            // Budget management navigation
            Button budgetButton = new Button("Manage Budget");
            styleButton(budgetButton);
            budgetButton.setMaxWidth(Double.MAX_VALUE);
            budgetButton.setOnAction(e -> new BudgetPage(stage, user).show());

            // Reminder setting navigation
            Button reminderButton = new Button("Set Reminders");
            styleButton(reminderButton);
            reminderButton.setMaxWidth(Double.MAX_VALUE);
            reminderButton.setOnAction(e -> new ReminderPage(stage, user).show());

            // Logout button
            Button logoutButton = new Button("Logout");
            styleButton(logoutButton);
            logoutButton.setMaxWidth(Double.MAX_VALUE);
            logoutButton.setOnAction(e -> new LoginPage(stage).show());

            // Add buttons and finalize layout
            buttonBox.getChildren().addAll(incomeButton, expenseButton, budgetButton, reminderButton, logoutButton);
            root.getChildren().addAll(titleLabel, buttonBox);

            // Show scene
            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        }
    }

    /**
     * Represents the page where users can input and track their income in the Personal Budgeting application.
     * This page allows users to enter details such as the income source, amount, and date, and save the data to the system.
     */
    public static class IncomeTrackingPage {

        /**
         * The JavaFX stage on which the income tracking page is displayed.
         */
        private Stage stage;

        /**
         * The authenticated user whose income is being tracked.
         */
        private entity.User user;

        /**
         * The controller responsible for handling income-related operations.
         */
        private control.IncomeController incomeController = new control.IncomeController();

        /**
         * Constructs the IncomeTrackingPage with the specified stage and user.
         *
         * @param stage the main application window
         * @param user  the authenticated {@link entity.User} instance
         */
        public IncomeTrackingPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        /**
         * Displays the income tracking interface, allowing the user to input income details.
         * Includes fields for source, amount, and date, as well as buttons for adding the income or navigating back.
         */
        public void show() {
            // Layout setup
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Page title
            Label titleLabel = new Label("Track Income");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Form container for income details
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Source input field
            Label sourceLabel = new Label("Source");
            sourceLabel.setStyle(LABEL_STYLE);
            TextField sourceField = new TextField();
            sourceField.setPromptText("e.g., Salary");
            sourceField.setStyle(TEXT_FIELD_STYLE);

            // Amount input field
            Label amountLabel = new Label("Amount");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Enter amount");
            amountField.setStyle(TEXT_FIELD_STYLE);

            // Date input field
            Label dateLabel = new Label("Date");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);

            // Button container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            // Add income button
            Button addButton = new Button("Add Income");
            styleButton(addButton);
            addButton.setMinWidth(100);
            addButton.setOnAction(e -> {
                // Validate inputs
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
                    // Add income to the system
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

            // Back button
            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            // Add form fields and buttons to the layout
            formBox.getChildren().addAll(
                    sourceLabel, sourceField,
                    amountLabel, amountField,
                    dateLabel, dateField
            );
            buttonBox.getChildren().addAll(addButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            // Show the scene
            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Track Income");
            stage.show();
        }
    }

    /**
     * Represents the page where users can input and track their expenses in the Personal Budgeting application.
     * This page allows users to enter details such as the expense amount, category, and date, and save the data to the system.
     */
    public static class ExpenseTrackingPage {

        /**
         * The JavaFX stage on which the expense tracking page is displayed.
         */
        private Stage stage;

        /**
         * The authenticated user whose expenses are being tracked.
         */
        private entity.User user;

        /**
         * The controller responsible for handling expense-related operations.
         */
        private control.ExpenseController expenseController = new control.ExpenseController();

        /**
         * Constructs the ExpenseTrackingPage with the specified stage and user.
         *
         * @param stage the main application window
         * @param user  the authenticated {@link entity.User} instance
         */
        public ExpenseTrackingPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        /**
         * Displays the expense tracking interface, allowing the user to input expense details.
         * Includes fields for amount, category, and date, as well as buttons for adding the expense or navigating back.
         */
        public void show() {
            // Layout setup
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Page title
            Label titleLabel = new Label("Track Expenses");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Form container for expense details
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Amount input field
            Label amountLabel = new Label("Amount");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Enter amount");
            amountField.setStyle(TEXT_FIELD_STYLE);

            // Category input field
            Label categoryLabel = new Label("Category");
            categoryLabel.setStyle(LABEL_STYLE);
            TextField categoryField = new TextField();
            categoryField.setPromptText("e.g., Food");
            categoryField.setStyle(TEXT_FIELD_STYLE);

            // Date input field
            Label dateLabel = new Label("Date");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);

            // Button container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            // Add expense button
            Button addButton = new Button("Add Expense");
            styleButton(addButton);
            addButton.setMinWidth(100);
            addButton.setOnAction(e -> {
                // Validate inputs
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
                    // Add expense to the system
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

            // Back button
            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            // Add form fields and buttons to the layout
            formBox.getChildren().addAll(
                    amountLabel, amountField,
                    categoryLabel, categoryField,
                    dateLabel, dateField
            );
            buttonBox.getChildren().addAll(addButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            // Show the scene
            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Track Expenses");
            stage.show();
        }
    }

    /**
     * Represents the page where users can set and track their budgets for various categories in the Personal Budgeting application.
     * Users can set a budget for a specific category and track their progress towards the budget.
     */
    public static class BudgetPage {

        /**
         * The JavaFX stage on which the budget management page is displayed.
         */
        private Stage stage;

        /**
         * The authenticated user whose budgets are being managed.
         */
        private entity.User user;

        /**
         * The controller responsible for handling budget-related operations.
         */
        private control.BudgetController budgetController = new control.BudgetController();

        /**
         * Constructs the BudgetPage with the specified stage and user.
         *
         * @param stage the main application window
         * @param user  the authenticated {@link entity.User} instance
         */
        public BudgetPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        /**
         * Displays the budget management interface, allowing the user to set a budget for a specific category
         * and track the current budget status.
         * The user can either set a new budget or track the current budget for a category.
         */
        public void show() {
            // Layout setup
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Page title
            Label titleLabel = new Label("Manage Budget");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Form container for budget details
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Category input field
            Label categoryLabel = new Label("Category");
            categoryLabel.setStyle(LABEL_STYLE);
            TextField categoryField = new TextField();
            categoryField.setPromptText("e.g., Food");
            categoryField.setStyle(TEXT_FIELD_STYLE);

            // Amount input field
            Label amountLabel = new Label("Amount");
            amountLabel.setStyle(LABEL_STYLE);
            TextField amountField = new TextField();
            amountField.setPromptText("Budget amount");
            amountField.setStyle(TEXT_FIELD_STYLE);

            // Button container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            // Set budget button
            Button setButton = new Button("Set Budget");
            styleButton(setButton);
            setButton.setMinWidth(100);
            setButton.setOnAction(e -> {
                // Validate inputs
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
                    // Set the budget in the system
                    boolean success = budgetController.createBudget(user.getId(), category, amount);
                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Budget set successfully!").showAndWait();
                        amountField.clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to set budget").showAndWait();
                    }
                }
            });

            // Track budget button
            Button trackButton = new Button("Track Budget");
            styleButton(trackButton);
            trackButton.setMinWidth(100);
            trackButton.setOnAction(e -> {
                // Track the current budget for the category
                String category = categoryField.getText();
                if (category.isEmpty()) {
                    categoryField.setStyle(TEXT_FIELD_STYLE + ERROR_STYLE);
                } else {
                    categoryField.setStyle(TEXT_FIELD_STYLE);
                    // Display the tracked budget information
                    Label resultLabel = new Label(budgetController.trackBudget(user.getId(), category));
                    resultLabel.setStyle(LABEL_STYLE + " -fx-padding: 10; -fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 8;");
                    resultLabel.setWrapText(true);
                    VBox.setMargin(resultLabel, new Insets(10, 0, 0, 0));
                    root.getChildren().add(resultLabel);
                }
            });

            // Back button
            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            // Add form fields and buttons to the layout
            formBox.getChildren().addAll(
                    categoryLabel, categoryField,
                    amountLabel, amountField
            );
            buttonBox.getChildren().addAll(setButton, trackButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            // Show the scene
            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Manage Budget");
            stage.show();
        }
    }

    /**
     * Represents the page where users can set reminders in the Personal Budgeting application.
     * Users can create a reminder with a title, date, and time.
     */
    public static class ReminderPage {

        /**
         * The JavaFX stage on which the reminder setting page is displayed.
         */
        private Stage stage;

        /**
         * The authenticated user for whom the reminders are being set.
         */
        private entity.User user;

        /**
         * The controller responsible for handling reminder-related operations.
         */
        private control.ReminderController reminderController = new control.ReminderController();

        /**
         * Constructs the ReminderPage with the specified stage and user.
         *
         * @param stage the main application window
         * @param user  the authenticated {@link entity.User} instance
         */
        public ReminderPage(Stage stage, entity.User user) {
            this.stage = stage;
            this.user = user;
        }

        /**
         * Displays the reminder setting interface, allowing the user to input a title, date, and time for the reminder.
         * The user can set the reminder or go back to the previous screen.
         */
        public void show() {
            // Layout setup
            VBox root = new VBox(25);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle(APP_STYLE);
            root.setMaxWidth(450);
            root.setEffect(new DropShadow(10, Color.web("#000000", 0.4)));

            // Page title
            Label titleLabel = new Label("Set Reminder");
            titleLabel.setStyle(LABEL_STYLE + " -fx-font-size: 32px; -fx-padding: 0 0 25 0; -fx-letter-spacing: 0.5px;");

            // Form container for reminder details
            VBox formBox = new VBox(15);
            formBox.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 15; -fx-padding: 25; -fx-border-color: #4A4A4A; -fx-border-width: 1; -fx-border-radius: 15;");

            // Title input field
            Label titleFieldLabel = new Label("Title");
            titleFieldLabel.setStyle(LABEL_STYLE);
            TextField titleField = new TextField();
            titleField.setPromptText("e.g., Pay Rent");
            titleField.setStyle(TEXT_FIELD_STYLE);

            // Date input field
            Label dateLabel = new Label("Date");
            dateLabel.setStyle(LABEL_STYLE);
            TextField dateField = new TextField();
            dateField.setPromptText("YYYY-MM-DD");
            dateField.setStyle(TEXT_FIELD_STYLE);

            // Time input field
            Label timeLabel = new Label("Time");
            timeLabel.setStyle(LABEL_STYLE);
            TextField timeField = new TextField();
            timeField.setPromptText("HH:MM");
            timeField.setStyle(TEXT_FIELD_STYLE);

            // Button container
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);

            // Set reminder button
            Button setButton = new Button("Set Reminder");
            styleButton(setButton);
            setButton.setMinWidth(100);
            setButton.setOnAction(e -> {
                // Validate inputs
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
                    // Set the reminder in the system
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

            // Back button
            Button backButton = new Button("Back");
            styleButton(backButton);
            backButton.setMinWidth(100);
            backButton.setOnAction(e -> new Dashboard(stage, user).show());

            // Add form fields and buttons to the layout
            formBox.getChildren().addAll(
                    titleFieldLabel, titleField,
                    dateLabel, dateField,
                    timeLabel, timeField
            );
            buttonBox.getChildren().addAll(setButton, backButton);
            root.getChildren().addAll(titleLabel, formBox, buttonBox);

            // Show the scene
            Scene scene = new Scene(root, 550, 650);
            stage.setScene(scene);
            stage.setTitle("Set Reminder");
            stage.show();
        }
    }
}