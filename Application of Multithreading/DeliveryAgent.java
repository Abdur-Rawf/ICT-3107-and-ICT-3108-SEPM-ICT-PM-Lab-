/**
 * Delivery Agent Thread - Processes orders from queue
 */
public class DeliveryAgent extends Thread {
    private final String agentName;
    private final OrderQueue orderQueue;
    private int ordersDelivered = 0;
    private boolean isActive = true;
    
    public DeliveryAgent(String name, OrderQueue queue) {
        this.agentName = name;
        this.orderQueue = queue;
        this.setName(name + "-Thread");
    }
    
    @Override
    public void run() {
        System.out.println("[" + agentName + "] Started and ready for deliveries.");
        
        try {
            while (isActive) {
                // Get order from queue (this will wait if queue is empty)
                Order order = orderQueue.getOrder();
                
                if (order == null) {
                    // No more orders and queue is closed
                    System.out.println("[" + agentName + "] No more orders. Shutting down.");
                    break;
                }
                
                // Process the order
                processOrder(order);
                
                // Small delay to simulate delivery time
                Thread.sleep(1500 + (long)(Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            System.out.println("[" + agentName + "] Interrupted.");
            Thread.currentThread().interrupt();
        }
        
        System.out.println("[" + agentName + "] Delivered " + ordersDelivered + " orders. Finished.");
    }
    
    private void processOrder(Order order) {
        System.out.println("[" + agentName + "] Picked up Order #" + order.getOrderId());
        order.setProcessing(agentName);
        
        // Simulate delivery process
        System.out.println("[" + agentName + "] Delivering Order #" + order.getOrderId() + " to " + 
                          order.getCustomerName());
        
        order.setDelivered(agentName);
        ordersDelivered++;
        
        System.out.println("[" + agentName + "] ✅ Order #" + order.getOrderId() + 
                          " delivered! (" + ordersDelivered + " total)");
        System.out.println("    " + order);
    }
    
    public void stopAgent() {
        this.isActive = false;
        this.interrupt();
    }
    
    public int getOrdersDelivered() {
        return ordersDelivered;
    }
}
