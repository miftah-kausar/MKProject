
package service;

import model.Challenge;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeService {
    private Connection connection;

    public ChallengeService(Connection connection) {
        this.connection = connection;
    }

    public void createChallenge(String description, double betAmount) throws SQLException {
        String query = "INSERT INTO challenges (description, betAmount, status) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, description);
            pstmt.setDouble(2, betAmount);
            pstmt.setString(3, "Active");
            pstmt.executeUpdate();
            System.out.println("Challenge created successfully.");
        }
    }

    public List<Challenge> getActiveChallenges() throws SQLException {
        String query = "SELECT * FROM challenges WHERE status = 'Active'";
        List<Challenge> challenges = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Challenge challenge = new Challenge(rs.getInt("id"), rs.getString("description"), rs.getDouble("betAmount"));
                challenges.add(challenge);
            }
        }
        return challenges;
    }

    public void joinChallenge(int challengeId, User user) throws SQLException {
        String query = "INSERT INTO challenge_participants (challenge_id, user_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, challengeId);
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
            System.out.println("User joined the challenge successfully.");
        }
    }
}
