import java.util.Vector;

public class VectorCapacity {

    public static void main(String[] args) {

        // Initial capacity = 10
        Vector<String> vector = new Vector<>(10);

        // Adding 10 elements
        for (int i = 1; i <= 10; i++) {
            vector.add("IT2300" + i);
        }

        System.out.println("Vector Initial Capacity: " + vector.capacity());
        System.out.println("Size before exceeding: " + vector.size());

        // Adding 11th element (exceed initial capacity)
        vector.add("IT23011");

        System.out.println("Size after exceeding: " + vector.size());
        System.out.println("Capacity after exceeding: " + vector.capacity());
    }
}
