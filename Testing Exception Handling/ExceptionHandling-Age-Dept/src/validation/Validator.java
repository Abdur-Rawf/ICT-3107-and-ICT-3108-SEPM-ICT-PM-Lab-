package validation;

public class Validator {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18 || age > 60) {
            throw new InvalidAgeException("Invalid Age! Age must be between 18 and 60.");
        }
    }

    public static void validateDept(String dept) throws InvalidDeptException {
        if (!(dept.equalsIgnoreCase("CSE") ||
              dept.equalsIgnoreCase("EEE") ||
              dept.equalsIgnoreCase("BBA"))) {

            throw new InvalidDeptException("Invalid Department! Allowed: CSE, EEE, BBA");
        }
    }
}
