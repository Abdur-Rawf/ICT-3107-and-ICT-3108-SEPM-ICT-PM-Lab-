import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListCapacity {

    // Method to get actual capacity of ArrayList using reflection
    public static int getCapacity(ArrayList<?> list) {
        try {
            Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] elementData = (Object[]) field.get(list);
            return elementData.length;
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {

        // Initial capacity = 10
        ArrayList<String> list = new ArrayList<>(10);

        // Adding 10 elements
        for (int i = 1; i <= 10; i++) {
            list.add("IT2300" + i);
        }

        System.out.println("ArrayList Initial Capacity: " + getCapacity(list));
        System.out.println("Size before exceeding: " + list.size());

        // Adding 11th element (exceed initial capacity)
        list.add("IT23011");

        System.out.println("Size after exceeding: " + list.size());
        System.out.println("Capacity after exceeding: " + getCapacity(list));
    }
}
