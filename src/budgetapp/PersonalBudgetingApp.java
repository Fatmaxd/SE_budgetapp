package budgetapp;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main class for the Personal Budgeting desktop application.
 * Launches the JavaFX application and displays the Login Page.
 * It also starts a scheduled background task to check for user reminders.
 */
public class PersonalBudgetingApp extends Application {

    /** Scheduler used to run periodic background tasks. */
    private ScheduledExecutorService scheduler;

    // Static block to initialize the database when the class is loaded
    static {
        util.Database.initialize();
    }

    /**
     * Entry point for the JavaFX application.
     * This method sets up the primary stage and launches the login page UI.
     * It also starts a background scheduler to periodically check reminders for the current user.
     *
     * @param primaryStage The primary window for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        boundary.LoginPage loginPage = new boundary.LoginPage(primaryStage);
        loginPage.show();

        // Start background thread to check reminders every 60 seconds
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            entity.User currentUser = boundary.Dashboard.getCurrentUser(); // Use static getter
            if (currentUser != null) {
                control.ReminderController reminderController = new control.ReminderController();
                try (Connection conn = util.Database.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM users WHERE id = ?")) {
                    pstmt.setInt(1, currentUser.getId());
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        reminderController.checkReminders(currentUser.getId());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 60, TimeUnit.SECONDS); // Check every minute
    }

    /**
     * Called when the application is stopped.
     * This method shuts down the background scheduler.
     */
    @Override
    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
            }
        }
    }

    /**
     * Launches the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
