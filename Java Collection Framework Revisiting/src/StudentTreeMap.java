import java.util.TreeMap;

public class StudentTreeMap {
    public static void main(String[] args) {

        TreeMap<Integer, String> students = new TreeMap<>();
        students.put(102, "Rahim");
        students.put(101, "Karim");
        students.put(103, "Sakib");

        System.out.println(students);
    }
}
