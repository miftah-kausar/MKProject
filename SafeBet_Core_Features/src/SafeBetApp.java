
import model.User;
import service.UserService;
import service.ChallengeService;
import service.DrivingSessionService;
import util.DatabaseConnection;
import util.DatabaseInitializer;

import java.sql.Connection;

public class SafeBetApp {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {

        	DatabaseInitializer.initializeDatabase();

            // Your other application logic here
            System.out.println("Database initialized!");
        	
            UserService userService = new UserService(connection);
            ChallengeService challengeService = new ChallengeService(connection);
            DrivingSessionService sessionService = new DrivingSessionService(connection);

            // Example flow
            System.out.println("SafeBet Application Started!");

            // User Registration
            System.out.println("Registering User...");
            userService.registerUser("John Doe", "john.doe@example.com", "password123");

            // User Login
            System.out.println("Logging in User...");
            User user = userService.loginUser("john.doe@example.com", "password123");
            if (user != null) {
                System.out.println("Login Successful! Welcome, " + user.getName());

                // Create a Challenge
                System.out.println("Creating Challenge...");
                challengeService.createChallenge("Drive below speed limit for 7 days", 50.0);

                // List Active Challenges
                System.out.println("Available Challenges:");
                challengeService.getActiveChallenges().forEach(challenge ->
                    System.out.println("Challenge: " + challenge.getDescription() + ", Bet: " + challenge.getBetAmount())
                );

                // Join a Challenge
                System.out.println("Joining Challenge...");
                challengeService.joinChallenge(1, user);

                // Start a Driving Session
                System.out.println("Starting Driving Session...");
                sessionService.startSession(user.getId(), "2024-11-26T10:00:00");

                // End Driving Session
                System.out.println("Ending Driving Session...");
                sessionService.endSession(1, "2024-11-26T11:00:00", 85.5);
            } else {
                System.out.println("Login Failed. Please check your credentials.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
