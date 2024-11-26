
package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private double totalWinnings;
    private double totalLosses;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.totalWinnings = 0;
        this.totalLosses = 0;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getTotalWinnings() { return totalWinnings; }
    public void setTotalWinnings(double totalWinnings) { this.totalWinnings = totalWinnings; }

    public double getTotalLosses() { return totalLosses; }
    public void setTotalLosses(double totalLosses) { this.totalLosses = totalLosses; }
}
