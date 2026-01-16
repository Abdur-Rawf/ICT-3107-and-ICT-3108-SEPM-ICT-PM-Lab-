/**
 * Represents an Order with unique ID and status tracking
 */
public class Order {
    private static int orderCounter = 0;
    private final int orderId;
    private final String customerName;
    private final String items;
    private String status;
    private final long creationTime;
    private long deliveryTime;
    
    public Order(String customerName, String items) {
        this.orderId = ++orderCounter;
        this.customerName = customerName;
        this.items = items;
        this.status = "PLACED";
        this.creationTime = System.currentTimeMillis();
    }
    
    // Getter methods
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getItems() { return items; }
    public String getStatus() { return status; }
    public long getCreationTime() { return creationTime; }
    public long getDeliveryTime() { return deliveryTime; }
    
    // Set delivery status
    public void setDelivered(String agentName) {
        this.status = "DELIVERED by " + agentName;
        this.deliveryTime = System.currentTimeMillis();
    }
    
    public void setProcessing(String agentName) {
        this.status = "PROCESSING by " + agentName;
    }
    
    public long getProcessingTime() {
        if (deliveryTime > 0) {
            return deliveryTime - creationTime;
        }
        return System.currentTimeMillis() - creationTime;
    }
    
    @Override
    public String toString() {
        return String.format("Order #%03d | Customer: %-15s | Items: %-20s | Status: %s",
                orderId, customerName, items, status);
    }
}
