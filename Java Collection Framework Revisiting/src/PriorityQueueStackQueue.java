import java.util.PriorityQueue;
import java.util.Collections;

public class PriorityQueueStackQueue {
    public static void main(String[] args) {

        // Queue (FIFO)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("Queue: " + queue);

        // Stack (LIFO)
        PriorityQueue<Integer> stack =
                new PriorityQueue<>(Collections.reverseOrder());
        stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.println("Stack: " + stack);
    }
}
