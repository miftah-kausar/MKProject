package com.safebet;

public class PerformanceMetrics {
    private double averageSpeed;
    private double maxSpeed;
    private double harshAccelerations;
    private double harshBrakings;

    public void updateMetrics(double speed, double acceleration, double braking) {
        this.averageSpeed = calculateMovingAverage(averageSpeed, speed);
        this.maxSpeed = Math.max(maxSpeed, speed);
        this.harshAccelerations += acceleration > 3.0 ? 1 : 0;
        this.harshBrakings += braking > 2.0 ? 1 : 0;
    }

    private double calculateMovingAverage(double current, double newValue) {
        // Simple moving average calculation
        return (current + newValue) / 2;
    }

    public boolean compareWithChallengeGoals(PerformanceMetrics challengeGoals) {
        // Implement goal comparison logic
        return averageSpeed < challengeGoals.maxSpeed &&
               harshAccelerations < challengeGoals.harshAccelerations &&
               harshBrakings < challengeGoals.harshBrakings;
    }
}

