// Performance Test: Interface vs Abstract Class Method Invocation
public class PerformanceComparison {
    
    interface WorkerInterface {
        void work();
    }
    
    abstract class AbstractWorker {
        abstract void work();
    }
    
    class ConcreteWorker extends AbstractWorker implements WorkerInterface {
        private int counter = 0;
        
        @Override
        public void work() {
            counter++;
            // Simulate some work
            int result = counter * 10;
        }
    }
    
    public void runPerformanceTest() {
        int loops = 2_000_000;
        ConcreteWorker concrete = new ConcreteWorker();
        
        WorkerInterface interfaceRef = concrete;      // Interface reference
        AbstractWorker abstractRef = concrete;        // Abstract class reference
        ConcreteWorker concreteRef = concrete;        // Concrete class reference
        
        System.out.println("Performance Test: " + loops + " method calls\n");
        
        // Test 1: Interface reference
        long start = System.nanoTime();
        for (int i = 0; i < loops; i++) {
            interfaceRef.work();
        }
        long interfaceTime = System.nanoTime() - start;
        
        // Test 2: Abstract class reference
        start = System.nanoTime();
        for (int i = 0; i < loops; i++) {
            abstractRef.work();
        }
        long abstractTime = System.nanoTime() - start;
        
        // Test 3: Concrete class reference
        start = System.nanoTime();
        for (int i = 0; i < loops; i++) {
            concreteRef.work();
        }
        long concreteTime = System.nanoTime() - start;
        
        // Results
        System.out.println("Results:");
        System.out.println("1. Interface reference:     " + interfaceTime + " ns");
        System.out.println("2. Abstract class reference: " + abstractTime + " ns");
        System.out.println("3. Concrete class reference: " + concreteTime + " ns");
        
        System.out.println("\nPercentage Difference:");
        System.out.println("Interface vs Concrete: " + 
            String.format("%.2f", ((double)(interfaceTime - concreteTime) / concreteTime) * 100) + "% slower");
        System.out.println("Abstract vs Concrete: " + 
            String.format("%.2f", ((double)(abstractTime - concreteTime) / concreteTime) * 100) + "% slower");
        
        System.out.println("\nConclusion:");
        System.out.println("- Interface method calls are slightly slower than concrete calls");
        System.out.println("- Difference is negligible in real applications (nanoseconds)");
        System.out.println("- Design decisions should prioritize architecture over micro-optimizations");
    }
    
    public static void main(String[] args) {
        PerformanceComparison test = new PerformanceComparison();
        test.runPerformanceTest();
    }
}
