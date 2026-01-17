public class RegistrarParking {

    private static int counter = 1;
    private int parkingId;

    public RegistrarParking() {
        this.parkingId = counter++;
    }

    public int getParkingId() {
        return parkingId;
    }
}
