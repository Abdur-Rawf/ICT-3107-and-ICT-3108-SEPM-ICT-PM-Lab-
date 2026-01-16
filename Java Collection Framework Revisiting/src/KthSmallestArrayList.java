import java.util.ArrayList;
import java.util.Collections;

public class KthSmallestArrayList {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(40);
        list.add(10);
        list.add(30);
        list.add(20);

        int k = 2;

        Collections.sort(list);
        System.out.println("Kth smallest element: " + list.get(k - 1));
    }
}
