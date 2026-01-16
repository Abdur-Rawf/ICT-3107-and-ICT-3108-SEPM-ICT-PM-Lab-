import java.util.LinkedList;

public class LinkedListEquality {
    public static void main(String[] args) {

        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        list1.add(1);
        list1.add(2);

        list2.add(1);
        list2.add(2);

        System.out.println("Lists are equal: " + list1.equals(list2));
    }
}
