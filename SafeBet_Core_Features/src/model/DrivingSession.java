
package model;

public class DrivingSession {
    private int id;
    private String startTime;
    private String endTime;
    private double performanceScore;
    private User user;

    public DrivingSession(int id, String startTime, User user) {
        this.id = id;
        this.startTime = startTime;
        this.user = user;
        this.performanceScore = 0.0;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public double getPerformanceScore() { return performanceScore; }
    public void setPerformanceScore(double performanceScore) { this.performanceScore = performanceScore; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
