package models;

import java.sql.Timestamp;

public class PlayerScore {
    private String playerName;
    private int score;
    private Timestamp datePlayed;
    
    public PlayerScore(String playerName, int score, Timestamp datePlayed) {
        this.playerName = playerName;
        this.score = score;
        this.datePlayed = datePlayed;
    }
    
    // Getters
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public Timestamp getDatePlayed() { return datePlayed; }
}
