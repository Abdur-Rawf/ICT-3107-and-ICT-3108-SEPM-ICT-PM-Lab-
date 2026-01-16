import java.util.Arrays;

/**
 * Demonstration class showing how to use CustomRandom
 */
public class TestCustomRandom {
    public static void main(String[] args) {
        System.out.println("=== Custom Random Number Generator Demo ===\n");
        
        // 1. Generate different types
        System.out.println("1. Single Values:");
        System.out.println("Random int: " + CustomRandom.nextInt());
        System.out.println("Random int (0-99): " + CustomRandom.nextInt(100));
        System.out.println("Random int (10-50): " + CustomRandom.nextInt(10, 51));
        System.out.println("Random double: " + CustomRandom.nextDouble());
        System.out.println("Random double (5.0-10.0): " + CustomRandom.nextDouble(5.0, 10.0));
        System.out.println("Random float: " + CustomRandom.nextFloat());
        System.out.println("Random boolean: " + CustomRandom.nextBoolean());
        System.out.println("Random long: " + CustomRandom.nextLong());
        
        // 2. Generate array (like in assignment hint)
        System.out.println("\n2. Array Generation (like assignment example):");
        double[] myList = new double[5];
        System.out.print("Generated 5 random doubles: ");
        for (int i = 0; i < myList.length; i++) {
            myList[i] = CustomRandom.nextDouble(0.0, 100.0);
            System.out.printf("%.2f ", myList[i]);
        }
        System.out.println();
        
        // 3. Mixed type array
        System.out.println("\n3. Mixed Type Array:");
        Object[] mixed = CustomRandom.nextMixedArray(8);
        System.out.println("Mixed array: " + Arrays.toString(mixed));
        
        // 4. Seed control
        System.out.println("\n4. Seed Control:");
        CustomRandom.setSeed(12345L);
        System.out.println("With seed 12345 - First int: " + CustomRandom.nextInt());
        System.out.println("With seed 12345 - Second int: " + CustomRandom.nextInt());
        
        // Reset to time-based
        CustomRandom.setSeed(System.currentTimeMillis());
        System.out.println("Reset to time-based seed");
        
        // 5. Statistical test (simple)
        System.out.println("\n5. Simple Distribution Test (1000 numbers 0-9):");
        int[] counts = new int[10];
        for (int i = 0; i < 1000; i++) {
            counts[CustomRandom.nextInt(10)]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.printf("%d: %d times\n", i, counts[i]);
        }
    }
}
