
package service;

import model.DrivingSession;
import model.User;
import java.sql.*;

public class DrivingSessionService {
    private Connection connection;

    public DrivingSessionService(Connection connection) {
        this.connection = connection;
    }

    public void startSession(int userId, String startTime) throws SQLException {
        String query = "INSERT INTO driving_sessions (user_id, startTime, performanceScore) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, startTime);
            pstmt.setDouble(3, 0.0);
            pstmt.executeUpdate();
            System.out.println("Driving session started successfully.");
        }
    }

    public void endSession(int sessionId, String endTime, double performanceScore) throws SQLException {
        String query = "UPDATE driving_sessions SET endTime = ?, performanceScore = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, endTime);
            pstmt.setDouble(2, performanceScore);
            pstmt.setInt(3, sessionId);
            pstmt.executeUpdate();
            System.out.println("Driving session ended successfully.");
        }
    }
}
