package birthdaymanager;

import java.time.LocalDate;

public class Birthday {
    private int id;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String notes;
    
    // Constructors
    public Birthday() {}
    
    public Birthday(String name, LocalDate birthDate, String phone, String email, String notes) {
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    // Helper methods
    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
    
    public String getMonth() {
        String[] months = {"জানুয়ারি", "ফেব্রুয়ারি", "মার্চ", "এপ্রিল", "মে", "জুন",
                          "জুলাই", "আগস্ট", "সেপ্টেম্বর", "অক্টোবর", "নভেম্বর", "ডিসেম্বর"};
        return months[birthDate.getMonthValue() - 1];
    }
    
    @Override
    public String toString() {
        return name + " - " + birthDate.toString();
    }
}
