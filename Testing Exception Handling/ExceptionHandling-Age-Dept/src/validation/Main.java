package validation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            Validator.validateAge(age);

            sc.nextLine(); // buffer clear

            System.out.print("Enter Department: ");
            String dept = sc.nextLine();
            Validator.validateDept(dept);

            System.out.println("Validation Successful!");

        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());

        } catch (InvalidDeptException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected Error!");
        }
    }
}
