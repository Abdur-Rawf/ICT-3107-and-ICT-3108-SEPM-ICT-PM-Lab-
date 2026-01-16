import java.util.LinkedList;
import java.util.Queue;

/**
 * Thread-safe Order Queue using synchronized methods
 */
public class OrderQueue {
    private final Queue<Order> orders = new LinkedList<>();
    private boolean isAcceptingOrders = true;
    private int totalOrdersProcessed = 0;
    
    /**
     * Add order to queue (synchronized)
     */
    public synchronized void addOrder(Order order) {
        if (!isAcceptingOrders) {
            System.out.println("[QUEUE] Not accepting new orders. Order #" + order.getOrderId() + " rejected.");
            return;
        }
        orders.add(order);
        System.out.println("[QUEUE] Order #" + order.getOrderId() + " added. Queue size: " + orders.size());
        notifyAll(); // Notify waiting delivery agents
    }
    
    /**
     * Get order from queue (synchronized)
     */
    public synchronized Order getOrder() throws InterruptedException {
        while (orders.isEmpty() && isAcceptingOrders) {
            System.out.println("[QUEUE] No orders available. Delivery agent waiting...");
            wait(); // Wait for orders
        }
        
        if (!orders.isEmpty()) {
            Order order = orders.poll();
            totalOrdersProcessed++;
            return order;
        }
        return null; // When queue is closed and empty
    }
    
    /**
     * Close queue for new orders
     */
    public synchronized void closeQueue() {
        isAcceptingOrders = false;
        notifyAll(); // Wake up all waiting threads
    }
    
    /**
     * Check if queue is accepting orders
     */
    public synchronized boolean isAcceptingOrders() {
        return isAcceptingOrders;
    }
    
    /**
     * Get queue size
     */
    public synchronized int size() {
        return orders.size();
    }
    
    /**
     * Get total orders processed
     */
    public synchronized int getTotalOrdersProcessed() {
        return totalOrdersProcessed;
    }
}
