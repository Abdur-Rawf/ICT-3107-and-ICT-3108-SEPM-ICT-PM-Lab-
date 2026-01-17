import java.util.LinkedList;
import java.util.Queue;

public class ParkingPool {

    private Queue<RegistrarParking> queue = new LinkedList<>();

    // Add parking request
    public synchronized void addParking(RegistrarParking parking) {
        queue.add(parking);
        System.out.println("Car added for parking ID: " + parking.getParkingId());
        notifyAll();
    }

    // Get parking request
    public synchronized RegistrarParking getParking() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}
