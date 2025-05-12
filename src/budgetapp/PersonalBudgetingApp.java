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
 */
public class PersonalBudgetingApp extends Application {
    private ScheduledExecutorService scheduler;

    static {
        util.Database.initialize(); // Initialize the database when the class is loaded
    }

    @Override
    public void start(Stage primaryStage) {
        boundary.LoginPage loginPage = new boundary.LoginPage(primaryStage);
        loginPage.show();

        // Start background thread to check reminders
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            entity.User currentUser = boundary.Dashboard.getCurrentUser(); // Use getter
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

    @Override
    public void stop() {
        // Shutdown scheduler when application closes
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

    public static void main(String[] args) {
        launch(args);
    }
}