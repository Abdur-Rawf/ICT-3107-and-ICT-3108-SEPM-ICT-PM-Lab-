package birthdaymanager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BirthdayDAO {
    
    // Create (Add)
    public boolean addBirthday(Birthday birthday) {
        String sql = "INSERT INTO birthdays (name, birth_date, phone, email, notes) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, birthday.getName());
            pstmt.setDate(2, Date.valueOf(birthday.getBirthDate()));
            pstmt.setString(3, birthday.getPhone());
            pstmt.setString(4, birthday.getEmail());
            pstmt.setString(5, birthday.getNotes());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("যোগ করতে সমস্যা: " + e.getMessage());
            return false;
        }
    }
    
    // Read (Get All)
    public List<Birthday> getAllBirthdays() {
        List<Birthday> birthdays = new ArrayList<>();
        String sql = "SELECT * FROM birthdays ORDER BY birth_date";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Birthday birthday = new Birthday();
                birthday.setId(rs.getInt("id"));
                birthday.setName(rs.getString("name"));
                birthday.setBirthDate(rs.getDate("birth_date").toLocalDate());
                birthday.setPhone(rs.getString("phone"));
                birthday.setEmail(rs.getString("email"));
                birthday.setNotes(rs.getString("notes"));
                
                birthdays.add(birthday);
            }
        } catch (SQLException e) {
            System.err.println("তালিকা আনতে সমস্যা: " + e.getMessage());
        }
        return birthdays;
    }
    
    // Update
    public boolean updateBirthday(Birthday birthday) {
        String sql = "UPDATE birthdays SET name = ?, birth_date = ?, phone = ?, email = ?, notes = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, birthday.getName());
            pstmt.setDate(2, Date.valueOf(birthday.getBirthDate()));
            pstmt.setString(3, birthday.getPhone());
            pstmt.setString(4, birthday.getEmail());
            pstmt.setString(5, birthday.getNotes());
            pstmt.setInt(6, birthday.getId());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("আপডেট করতে সমস্যা: " + e.getMessage());
            return false;
        }
    }
    
    // Delete
    public boolean deleteBirthday(int id) {
        String sql = "DELETE FROM birthdays WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("ডিলিট করতে সমস্যা: " + e.getMessage());
            return false;
        }
    }
    
    // Search by Name
    public List<Birthday> searchByName(String name) {
        List<Birthday> birthdays = new ArrayList<>();
        String sql = "SELECT * FROM birthdays WHERE name LIKE ? ORDER BY birth_date";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Birthday birthday = new Birthday();
                birthday.setId(rs.getInt("id"));
                birthday.setName(rs.getString("name"));
                birthday.setBirthDate(rs.getDate("birth_date").toLocalDate());
                birthday.setPhone(rs.getString("phone"));
                birthday.setEmail(rs.getString("email"));
                birthday.setNotes(rs.getString("notes"));
                
                birthdays.add(birthday);
            }
        } catch (SQLException e) {
            System.err.println("সার্চ করতে সমস্যা: " + e.getMessage());
        }
        return birthdays;
    }
    
    // Search by Month
    public List<Birthday> searchByMonth(int month) {
        List<Birthday> birthdays = new ArrayList<>();
        String sql = "SELECT * FROM birthdays WHERE MONTH(birth_date) = ? ORDER BY DAY(birth_date)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, month);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Birthday birthday = new Birthday();
                birthday.setId(rs.getInt("id"));
                birthday.setName(rs.getString("name"));
                birthday.setBirthDate(rs.getDate("birth_date").toLocalDate());
                birthday.setPhone(rs.getString("phone"));
                birthday.setEmail(rs.getString("email"));
                birthday.setNotes(rs.getString("notes"));
                
                birthdays.add(birthday);
            }
        } catch (SQLException e) {
            System.err.println("মাস অনুসারে সার্চ করতে সমস্যা: " + e.getMessage());
        }
        return birthdays;
    }
    
    // Get Today's Birthdays
    public List<Birthday> getTodaysBirthdays() {
        List<Birthday> birthdays = new ArrayList<>();
        LocalDate today = LocalDate.now();
        String sql = "SELECT * FROM birthdays WHERE MONTH(birth_date) = ? AND DAY(birth_date) = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, today.getMonthValue());
            pstmt.setInt(2, today.getDayOfMonth());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Birthday birthday = new Birthday();
                birthday.setId(rs.getInt("id"));
                birthday.setName(rs.getString("name"));
                birthday.setBirthDate(rs.getDate("birth_date").toLocalDate());
                birthday.setPhone(rs.getString("phone"));
                birthday.setEmail(rs.getString("email"));
                birthday.setNotes(rs.getString("notes"));
                
                birthdays.add(birthday);
            }
        } catch (SQLException e) {
            System.err.println("আজকের জন্মদিন আনতে সমস্যা: " + e.getMessage());
        }
        return birthdays;
    }
    
    // Get Upcoming Birthdays (next 30 days)
    public List<Birthday> getUpcomingBirthdays() {
        List<Birthday> birthdays = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate next30Days = today.plusDays(30);
        
        String sql = "SELECT * FROM birthdays WHERE " +
                    "DATE_ADD(birth_date, INTERVAL YEAR(CURDATE()) - YEAR(birth_date) YEAR) " +
                    "BETWEEN ? AND ? " +
                    "ORDER BY DATE_ADD(birth_date, INTERVAL YEAR(CURDATE()) - YEAR(birth_date) YEAR)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(today));
            pstmt.setDate(2, Date.valueOf(next30Days));
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Birthday birthday = new Birthday();
                birthday.setId(rs.getInt("id"));
                birthday.setName(rs.getString("name"));
                birthday.setBirthDate(rs.getDate("birth_date").toLocalDate());
                birthday.setPhone(rs.getString("phone"));
                birthday.setEmail(rs.getString("email"));
                birthday.setNotes(rs.getString("notes"));
                
                birthdays.add(birthday);
            }
        } catch (SQLException e) {
            System.err.println("আসন্ন জন্মদিন আনতে সমস্যা: " + e.getMessage());
        }
        return birthdays;
    }
}
