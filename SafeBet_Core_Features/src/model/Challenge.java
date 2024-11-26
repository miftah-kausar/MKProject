
package model;

import java.util.ArrayList;
import java.util.List;

public class Challenge {
    private int id;
    private String description;
    private double betAmount;
    private List<User> participants;
    private String status; // Active, Completed, etc.

    public Challenge(int id, String description, double betAmount) {
        this.id = id;
        this.description = description;
        this.betAmount = betAmount;
        this.participants = new ArrayList<>();
        this.status = "Active";
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getBetAmount() { return betAmount; }
    public void setBetAmount(double betAmount) { this.betAmount = betAmount; }

    public List<User> getParticipants() { return participants; }
    public void addParticipant(User user) { this.participants.add(user); }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
