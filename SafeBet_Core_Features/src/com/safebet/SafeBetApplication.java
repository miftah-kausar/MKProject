//package com.safebet;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//// Main Application Controller
//public class SafeBetApplication {
//    private List<User> users;
//    private List<Challenge> challenges;
//
//    public SafeBetApplication() {
//        this.users = new ArrayList<>();
//        this.challenges = new ArrayList<>();
//    }
//
//    // User Registration
//    public User registerUser(String username, String email, String password) {
//        User newUser = new User(username, email, password);
//        users.add(newUser);
//        sendNotification(newUser, "Welcome to SafeBet!");
//        return newUser;
//    }
//
//    // User Login
//    public User loginUser(String email, String password) {
//        for (User user : users) {
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                return user;
//            }
//        }
//        System.out.println("Invalid email or password.");
//        return null;
//    }
//
//    // Challenge Creation
//    public Challenge createChallenge(String description, BigDecimal betAmount,
//                                     LocalDateTime start, LocalDateTime end) {
//        Challenge newChallenge = new Challenge(description, betAmount, start, end);
//        challenges.add(newChallenge);
//        return newChallenge;
//    }
//
//    // Join Challenge
//    public void joinChallenge(User user, Challenge challenge) {
//        if (!challenge.getParticipants().contains(user)) {
//            challenge.getParticipants().add(user);
//            sendNotification(user, "You have joined the challenge: " + challenge.getDescription());
//        } else {
//            System.out.println("User is already in the challenge.");
//        }
//    }
//
//    // Notification Service (Simulated)
//    public void sendNotification(User user, String message) {
//        System.out.println("Notification to " + user.getUsername() + ": " + message);
//    }
//
//    // Main Method to Test Application
//    public static void main(String[] args) {
//        SafeBetApplication app = new SafeBetApplication();
//
//        // User Registration and Login
//        User user = app.registerUser("John Doe", "john@example.com", "password123");
//        User loggedInUser = app.loginUser("john@example.com", "password123");
//
//        if (loggedInUser != null) {
//            // Challenge Creation
//            Challenge challenge = app.createChallenge(
//                "Drive Safely for a Week",
//                BigDecimal.valueOf(50.0),
//                LocalDateTime.now(),
//                LocalDateTime.now().plusDays(7)
//            );
//
//            // Join Challenge
//            app.joinChallenge(loggedInUser, challenge);
//        }
//    }
//    
//}
package com.safebet;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SafeBetApplication {
    private static SafeBetApplication app = new SafeBetApplication();
    private Scanner scanner = new Scanner(System.in);
    private User[] users = new User[10];  // Initialize the array with a size (or use a List<User> for dynamic sizing)
    private Challenge[] challenges = new Challenge[10];  // Initialize as an empty array (or List<Challenge> if preferred)

    public static void main(String[] args) {
        new SafeBetApplication().run();
    }

    public void run() {
        while (true) {
            System.out.println("Welcome to SafeBet!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    User user = loginUser();
                    if (user != null) {
                        userMenu(user);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using SafeBet!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, email, password);
        addUserToSystem(newUser);  // Add the new user to the system
        System.out.println("Registration successful! Welcome, " + newUser.getUsername());
    }

    private void addUserToSystem(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {  // Find the first empty spot
                users[i] = user;
                break;
            }
        }
    }

    private User loginUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user != null && user.getEmail().equals(email) && user.validatePassword(password)) {
                System.out.println("Login successful! Welcome back, " + user.getUsername());
                return user;
            }
        }
        System.out.println("Invalid email or password. Please try again.");
        return null;
    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create Challenge");
            System.out.println("2. Join Challenge");
            System.out.println("3. Start Driving Session");
            System.out.println("4. End Driving Session");
            System.out.println("5. View Account Balance");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createChallenge();
                    break;
                case 2:
                    joinChallenge(user);
                    break;
                case 3:
                    startDrivingSession(user);
                    break;
                case 4:
                    endDrivingSession(user);  // Pass the user object to end the session
                    break;
                case 5:
                    System.out.println("Your account balance: $" + user.getAccountBalance());
                    break;
                case 6:
                    System.out.println("Logging out. Goodbye, " + user.getUsername() + "!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createChallenge() {
        System.out.print("Enter challenge description: ");
        String description = scanner.nextLine();
        System.out.print("Enter bet amount: ");
        BigDecimal betAmount = new BigDecimal(scanner.nextLine());
        System.out.print("Enter challenge start time (1): ");
        LocalDateTime start = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Enter challenge end time (YYYY-MM-DDTHH:MM): ");
        LocalDateTime end = LocalDateTime.parse(scanner.nextLine());

        Challenge challenge = new Challenge(description, betAmount, start, end);
        addChallengeToSystem(challenge);  // Add the new challenge to the system
        System.out.println("Challenge created successfully: " + challenge.getDescription());
    }

    private void addChallengeToSystem(Challenge challenge) {
        for (int i = 0; i < challenges.length; i++) {
            if (challenges[i] == null) {  // Find the first empty spot
                challenges[i] = challenge;
                break;
            }
        }
    }

    private void joinChallenge(User user) {
        System.out.println("Available challenges: ");
        for (int i = 0; i < challenges.length; i++) {
            Challenge challenge = challenges[i];
            if (challenge != null) {  // Check if the challenge is not null
                System.out.println("ID: " + i + " | " + challenge.getDescription());
            }
        }
        System.out.print("Enter the challenge ID to join: ");
        int challengeId = Integer.parseInt(scanner.nextLine());

        if (challengeId >= 0 && challengeId < challenges.length && challenges[challengeId] != null) {
            Challenge challenge = challenges[challengeId];
            user.joinChallenge(challenge);
            System.out.println("You have joined the challenge: " + challenge.getDescription());
        } else {
            System.out.println("Invalid challenge ID. Please try again.");
        }
    }

    
    private void startDrivingSession(User user) {
        // Display available challenges for driving session
        System.out.println("Available challenges for driving sessions: ");
        for (int i = 0; i < app.challenges.length; i++) {  // Use the array length
            Challenge challenge = app.challenges[i];
            if (challenge != null) {  // Null check for the challenge
                System.out.println("ID: " + i + " | " + challenge.getDescription());  // Display the challenge with its index
            } else {
                System.out.println("ID: " + i + " | Challenge not available.");
            }
        }

        System.out.print("Enter the challenge ID to start a session: ");
        int challengeId = Integer.parseInt(scanner.nextLine());

        if (challengeId >= 0 && challengeId < app.challenges.length && app.challenges[challengeId] != null) {  // Check if ID is valid
            Challenge challenge = app.challenges[challengeId];

            // Check if the user already has an ongoing session
            if (user.getActiveSession() != null && user.getActiveSession().getStatus() == DrivingSessionStatus.STARTED) {
                System.out.println("You already have an ongoing driving session. Please end it before starting a new one.");
                return;
            }

            // Start a new driving session for the user
            user.startDrivingSession(challenge);
            System.out.println("Driving session started for challenge: " + challenge.getDescription());
        } else {
            System.out.println("Invalid challenge ID. Please try again.");
        }
    }


    private void endDrivingSession(User user) {
        // Check if the user has an active session
        DrivingSession activeSession = user.getActiveSession();
        if (activeSession != null && activeSession.getStatus() == DrivingSessionStatus.STARTED) {
            // Prompt for performance score
            System.out.print("Enter the performance score (0.0 - 100.0): ");
            double performanceScore = Double.parseDouble(scanner.nextLine());

            // End the session and pass the performance score
            activeSession.endSession(performanceScore);
            System.out.println("Driving session ended. Performance score: " + performanceScore);
        } else {
            System.out.println("No active driving session found.");
        }
    }

}
