package encapsulation;

/*
 Main class to test encapsulation.
 Data is accessed using getter and setter methods.
*/

public class Main {
    public static void main(String[] args) {

        Student s = new Student();

        // Setting values using setter methods
        s.setName("Abdur Rauf");
        s.setAge(22);
        s.setCgpa(3.75);

        // Getting values using getter methods
        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());
        System.out.println("CGPA: " + s.getCgpa());
    }
}
