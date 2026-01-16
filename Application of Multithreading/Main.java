import java.util.Scanner;

/**
 * Main Application - Simulates order placement and delivery system
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=========================================");
        System.out.println("   MULTITHREADED ORDER MANAGEMENT SYSTEM");
        System.out.println("=========================================\n");
        
        // Create shared order queue
        OrderQueue orderQueue = new OrderQueue();
        
        // Create and start delivery agents (threads)
        DeliveryAgent[] agents = {
            new DeliveryAgent("Agent-1", orderQueue),
            new DeliveryAgent("Agent-2", orderQueue),
            new DeliveryAgent("Agent-3", orderQueue)
        };
        
        System.out.println("Starting " + agents.length + " delivery agents...\n");
        for (DeliveryAgent agent : agents) {
            agent.start();
            Thread.sleep(500); // Stagger agent startup
        }
        
        Scanner scanner = new Scanner(System.in);
        int orderCount = 0;
        String[] customerNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry"};
        String[][] menuItems = {
            {"Pizza", "Burger", "Pasta"},
            {"Sushi", "Ramen", "Tempura"},
            {"Biryani", "Kebab", "Naan"},
            {"Tacos", "Burrito", "Quesadilla"},
            {"Salad", "Sandwich", "Soup"},
            {"Steak", "Mashed Potatoes", "Salad"},
            {"Curry", "Rice", "Roti"},
            {"Dim Sum", "Fried Rice", "Spring Roll"}
        };
        
        System.out.println("\n=== ORDER PLACEMENT ===");
        System.out.println("Press Enter to place a new order");
        System.out.println("Type 'exit' to stop placing orders");
        System.out.println("Type 'status' to check current status\n");
        
        // Simulate order creation
        while (true) {
            System.out.print("\nCommand: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            
            if (input.equalsIgnoreCase("status")) {
                System.out.println("\n=== CURRENT STATUS ===");
                System.out.println("Orders in queue: " + orderQueue.size());
                System.out.println("Total orders processed: " + orderQueue.getTotalOrdersProcessed());
                for (DeliveryAgent agent : agents) {
                    System.out.println(agent.getName() + " delivered: " + agent.getOrdersDelivered());
                }
                continue;
            }
            
            // Place a new order
            orderCount++;
            String customer = customerNames[orderCount % customerNames.length];
            String[] items = menuItems[orderCount % menuItems.length];
            String itemList = String.join(", ", items);
            
            Order newOrder = new Order(customer, itemList);
            orderQueue.addOrder(newOrder);
            
            System.out.println("📦 Order #" + newOrder.getOrderId() + " placed for " + 
                             customer + ": " + itemList);
            
            // Simulate random delay between orders
            Thread.sleep(800 + (long)(Math.random() * 1200));
        }
        
        // Close queue and wait for agents to finish
        System.out.println("\n=== SHUTTING DOWN ===");
        orderQueue.closeQueue();
        System.out.println("Queue closed. No new orders accepted.");
        
        // Wait for all agents to finish
        System.out.println("Waiting for delivery agents to finish...\n");
        for (DeliveryAgent agent : agents) {
            agent.join();
        }
        
        // Final statistics
        System.out.println("\n=== FINAL STATISTICS ===");
        System.out.println("Total orders placed: " + orderCount);
        System.out.println("Total orders processed: " + orderQueue.getTotalOrdersProcessed());
        System.out.println("\nAgent Performance:");
        for (DeliveryAgent agent : agents) {
            System.out.printf("%-15s: %d orders delivered\n", 
                            agent.getName(), agent.getOrdersDelivered());
        }
        
        scanner.close();
        System.out.println("\n✅ System shutdown complete.");
    }
}
