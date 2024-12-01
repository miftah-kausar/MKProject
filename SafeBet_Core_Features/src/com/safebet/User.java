package com.safebet;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Enums for Status and Types
enum ChallengeStatus {
    ACTIVE, COMPLETED, CANCELLED
}

enum DrivingSessionStatus {
    STARTED, ENDED, FAILED
}
// User Class
public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private BigDecimal accountBalance;
    private List<Challenge> participatedChallenges;
    private PrivacySettings privacySettings;
    private DrivingSession activeSession;  // Store the active session here

    // Constructor
    public User(String username, String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = hashPassword(password);
        this.accountBalance = BigDecimal.ZERO;
        this.participatedChallenges = new ArrayList<>();
        this.privacySettings = new PrivacySettings();
        this.activeSession = null;  // No active session at the start
    }

    // Method to start driving session
    public void startDrivingSession(Challenge challenge) {
        if (this.activeSession == null || this.activeSession.getStatus() == DrivingSessionStatus.ENDED) {
            this.activeSession = new DrivingSession(this, challenge);
            System.out.println("Started a new driving session for challenge: " + challenge.getDescription());
        } else {
            System.out.println("You already have an active driving session.");
        }
    }

    // Getter for active session
    public DrivingSession getActiveSession() {
        return activeSession;
    }

    // Authentication Methods
    private String hashPassword(String password) {
        // Implement secure password hashing (e.g., using BCrypt)
        return password; // Placeholder
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(hashPassword(inputPassword));
    }

    // Challenge Participation Methods
    public void joinChallenge(Challenge challenge) {
        if (challenge.canJoin(this)) {
            participatedChallenges.add(challenge);
            challenge.addParticipant(this);
        }
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public BigDecimal getAccountBalance() { return accountBalance; }
    public void updateAccountBalance(BigDecimal amount) { 
        this.accountBalance = this.accountBalance.add(amount); 
    }

	public Object getPrivacySettings() {
		// TODO Auto-generated method stub
		return null;
		
	}

	public String getEmail() {
	    return email;
	}

	public String getPassword() {
	    return password;
	}
}