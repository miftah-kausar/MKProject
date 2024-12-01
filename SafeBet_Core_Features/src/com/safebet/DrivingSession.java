package com.safebet;

import java.time.LocalDateTime;
import java.util.UUID;

public class DrivingSession {
    private String sessionId;
    private User user;
    private Challenge challenge;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DrivingSessionStatus status;
    private PerformanceMetrics sessionMetrics;
    private double performanceScore;

    public DrivingSession(User user, Challenge challenge) {
        this.sessionId = UUID.randomUUID().toString();
        this.user = user;
        this.challenge = challenge;
        this.startTime = LocalDateTime.now();
        this.status = DrivingSessionStatus.STARTED;
        this.sessionMetrics = new PerformanceMetrics();
    }

    // End session and store performance score
    public void endSession(double performanceScore) {
        this.performanceScore = performanceScore;
        this.endTime = LocalDateTime.now();
        this.status = DrivingSessionStatus.ENDED;
        // Collect and process driving metrics
        collectDrivingData();
    }

    private void collectDrivingData() {
        // Simulated method to collect driving data
        sessionMetrics.updateMetrics(
            generateRandomSpeed(), 
            generateRandomAcceleration(), 
            generateRandomBraking()
        );
    }

    public DrivingSessionStatus getStatus() {
        return status;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    // Utility methods for simulation
    private double generateRandomSpeed() { return Math.random() * 100; }
    private double generateRandomAcceleration() { return Math.random() * 5; }
    private double generateRandomBraking() { return Math.random() * 3; }

	public boolean meetsChallengeCriteria() {
		// TODO Auto-generated method stub
		return false;
	}

	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
