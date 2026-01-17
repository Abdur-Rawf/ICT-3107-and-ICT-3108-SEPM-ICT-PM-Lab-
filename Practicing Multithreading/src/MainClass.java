import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        ParkingPool pool = new ParkingPool();

        // Create Parking Agents (Threads)
        new ParkingAgent("Agent-1", pool).start();
        new ParkingAgent("Agent-2", pool).start();
        new ParkingAgent("Agent-3", pool).start();

        Scanner scanner = new Scanner(System.in);
        int carCount = 0;

        // Simulate N car parking requests
        while (carCount < 10) {
            System.out.println("Press Enter to add a car for parking...");
            scanner.nextLine();

            RegistrarParking parking = new RegistrarParking();
            pool.addParking(parking);
            carCount++;
        }

        scanner.close();
    }
}
