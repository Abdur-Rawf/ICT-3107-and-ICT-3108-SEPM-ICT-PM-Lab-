import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_game";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Change if you have password
    
    private Connection connection;
    
    public Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
    
    // Get 5 random questions from DB
    public List<Question> getRandomQuestions(int limit) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions ORDER BY RAND() LIMIT ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Question q = new Question(
                    rs.getInt("id"),
                    rs.getString("question_text"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_answer").charAt(0)
                );
                questions.add(q);
            }
        } catch (SQLException e) {
            System.out.println("Error getting questions: " + e.getMessage());
        }
        return questions;
    }
    
    // Save player score to DB
    public void saveScore(String playerName, int score) {
        String query = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, playerName);
            stmt.setInt(2, score);
            stmt.executeUpdate();
            System.out.println("Score saved for: " + playerName);
        } catch (SQLException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }
    
    // Get top scores
    public List<PlayerScore> getTopScores(int limit) {
        List<PlayerScore> topScores = new ArrayList<>();
        String query = "SELECT player_name, score, date_played FROM scores ORDER BY score DESC LIMIT ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                PlayerScore ps = new PlayerScore(
                    rs.getString("player_name"),
                    rs.getInt("score"),
                    rs.getTimestamp("date_played")
                );
                topScores.add(ps);
            }
        } catch (SQLException e) {
            System.out.println("Error getting scores: " + e.getMessage());
        }
        return topScores;
    }
    
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
