import java.util.HashMap;

public class EmployeeHashMap {
    public static void main(String[] args) {

        HashMap<Integer, String> employees = new HashMap<>();
        employees.put(1, "HR");
        employees.put(2, "IT");
        employees.put(3, "Finance");

        System.out.println(employees);
    }
}
