package com.safebet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Challenge Class
public class Challenge {
 private String challengeId;
 private String description;
 private BigDecimal betAmount;
 private LocalDateTime startTime;
 private LocalDateTime endTime;
 private ChallengeStatus status;
 private List<User> participants;
 private List<DrivingSession> drivingSessions;
 private PerformanceMetrics challengeGoals;

 public Challenge(String description, BigDecimal betAmount, 
                  LocalDateTime startTime, LocalDateTime endTime) {
     this.challengeId = UUID.randomUUID().toString();
     this.description = description;
     this.betAmount = betAmount;
     this.startTime = startTime;
     this.endTime = endTime;
     this.status = ChallengeStatus.ACTIVE;
     this.participants = new ArrayList<>();
     this.drivingSessions = new ArrayList<>();
     this.challengeGoals = new PerformanceMetrics();
 }

 public boolean canJoin(User user) {
     return status == ChallengeStatus.ACTIVE && 
            participants.size() < 100 && 
            !participants.contains(user);
 }

 public void addParticipant(User user) {
     if (canJoin(user)) {
         participants.add(user);
     }
 }

 public void addDrivingSession(DrivingSession session) {
     drivingSessions.add(session);
 }

 public void evaluateChallengeResults() {
     List<User> winners = new ArrayList<>();
     for (DrivingSession session : drivingSessions) {
         if (session.meetsChallengeCriteria()) {
             winners.add(session.getUser());
         }
     }

     // Distribute pot among winners
     if (!winners.isEmpty()) {
         BigDecimal potAmount = betAmount.multiply(BigDecimal.valueOf(participants.size()));
         BigDecimal winnerShare = potAmount.divide(BigDecimal.valueOf(winners.size()));

         for (User winner : winners) {
             winner.updateAccountBalance(winnerShare);
         }
     }

     status = ChallengeStatus.COMPLETED;
 }

 public List<User> getParticipants() {
     return participants;
 }
	public String getDescription() {
        return description;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public LocalDateTime getStart() {
        return getStart();
    }

    public LocalDateTime getEnd() {
        return getEnd();
    }

    public List<DrivingSession> getDrivingSessions() {
        return drivingSessions;
    }

}