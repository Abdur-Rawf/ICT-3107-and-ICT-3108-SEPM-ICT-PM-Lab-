import java.util.*;

public class DSAComparison {

    public static void main(String[] args) {

        // LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.addFirst("C");
        System.out.println("LinkedList: " + linkedList);

        // PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(30);
        pq.add(10);
        pq.add(20);
        System.out.println("PriorityQueue: " + pq);

        // Deque
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Front");
        deque.addLast("Rear");
        System.out.println("Deque: " + deque);

        // HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "C");
        hashMap.put(1, "A");
        hashMap.put(2, "B");
        System.out.println("HashMap: " + hashMap);

        // TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "C");
        treeMap.put(1, "A");
        treeMap.put(2, "B");
        System.out.println("TreeMap: " + treeMap);

        // HashSet
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Java");
        hashSet.add("DSA");
        hashSet.add("Java");
        System.out.println("HashSet: " + hashSet);
    }
}
