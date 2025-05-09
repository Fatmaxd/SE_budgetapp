package budgetapp;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the Personal Budgeting desktop application.
 * Launches the JavaFX application and displays the Login Page.
 */
public class PersonalBudgetingApp extends Application {

    static {
        util.Database.initialize(); // Initialize the database when the class is loaded
    }

    /**
     * Starts the JavaFX application by showing the Login Page.
     * @param primaryStage The primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        boundary.LoginPage loginPage = new boundary.LoginPage(primaryStage);
        loginPage.show();
    }

    /**
     * Main method to launch the application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}