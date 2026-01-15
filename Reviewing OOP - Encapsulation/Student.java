package encapsulation;

/*
 This class demonstrates Encapsulation.
 Variables are kept private and accessed
 using public getter and setter methods.
*/

public class Student {

    // private data members (data hiding)
    private String name;
    private int age;
    private double cgpa;

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for age with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    // Setter method for cgpa
    public void setCgpa(double cgpa) {
        if (cgpa >= 0 && cgpa <= 4.0) {
            this.cgpa = cgpa;
        }
    }

    // Getter method for cgpa
    public double getCgpa() {
        return cgpa;
    }
}
