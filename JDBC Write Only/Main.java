import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        try {
            String url = "jdbc:mysql://localhost:3306/jdbc_test";
            String user = "root";
            String pass = "Rawf@mysql@97";

            Connection con = DriverManager.getConnection(url, user, pass);

            String sql = "INSERT INTO student(name, age) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setInt(2, age);

            pst.executeUpdate();

            System.out.println("Data inserted successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
