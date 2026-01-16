/**
 * CustomPRNG - A novel pseudo-random number generator using
 * a modified Linear Congruential Generator (LCG) with bitwise operations
 * 
 * Mathematical Core:
 * f(x, n) = (x * (n/x - 1) <= n) ? x : x << n  (with modifications)
 * 
 * Implemented as: seed = (a * seed + c) ^ (seed >> shift)
 */
public class CustomRandom {
    
    // Core parameters for the generator
    private static long seed;
    private static final long a = 1664525L;    // Multiplier
    private static final long c = 1013904223L; // Increment
    private static final long m = (1L << 48);  // Modulus 2^48
    private static final int SHIFT = 21;       // Bit shift value
    
    // Static initializer - initialize with current time
    static {
        seed = System.nanoTime();
    }
    
    /**
     * Core random generation algorithm
     * Modified LCG with XOR shift for better distribution
     */
    private static long next() {
        // Basic LCG: (a * seed + c) mod m
        long newSeed = (a * seed + c) % m;
        
        // Apply custom transformation from hint:
        // f(x, |n|) = {x . (n/x - 1) <= n, x, x<<n, n E Z}
        // We implement as: XOR with right shift for better mixing
        newSeed ^= (newSeed >> SHIFT);
        
        // Additional transformation: if condition matches hint formula
        if (newSeed != 0 && (newSeed * (SHIFT/newSeed - 1) <= SHIFT)) {
            newSeed = newSeed << (int)(newSeed % 16);
        }
        
        seed = newSeed;
        return Math.abs(newSeed);
    }
    
    // ========== OVERLOADED METHODS ==========
    
    /**
     * Generate random integer (full range)
     */
    public static int nextInt() {
        return (int)(next() & 0x7FFFFFFF); // Ensure positive
    }
    
    /**
     * Generate random integer between 0 (inclusive) and max (exclusive)
     */
    public static int nextInt(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max must be positive");
        }
        return nextInt() % max;
    }
    
    /**
     * Generate random integer between min (inclusive) and max (exclusive)
     */
    public static int nextInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return min + nextInt(max - min);
    }
    
    /**
     * Generate random double between 0.0 (inclusive) and 1.0 (exclusive)
     */
    public static double nextDouble() {
        return (next() % 0xFFFFFL) / (double)0xFFFFFL;
    }
    
    /**
     * Generate random double between min (inclusive) and max (exclusive)
     */
    public static double nextDouble(double min, double max) {
        return min + (nextDouble() * (max - min));
    }
    
    /**
     * Generate random float between 0.0f (inclusive) and 1.0f (exclusive)
     */
    public static float nextFloat() {
        return (float)nextDouble();
    }
    
    /**
     * Generate random float between min (inclusive) and max (exclusive)
     */
    public static float nextFloat(float min, float max) {
        return min + (nextFloat() * (max - min));
    }
    
    /**
     * Generate random boolean
     */
    public static boolean nextBoolean() {
        return nextInt() % 2 == 0;
    }
    
    /**
     * Generate random long
     */
    public static long nextLong() {
        return (next() << 32) | next();
    }
    
    /**
     * Generate mixed type array
     */
    public static Object[] nextMixedArray(int size) {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            switch (nextInt(4)) {
                case 0: arr[i] = nextInt(); break;
                case 1: arr[i] = nextDouble(); break;
                case 2: arr[i] = nextFloat(); break;
                case 3: arr[i] = nextBoolean(); break;
            }
        }
        return arr;
    }
    
    /**
     * Set custom seed for reproducible sequences
     */
    public static void setSeed(long newSeed) {
        if (newSeed == 0) {
            newSeed = System.nanoTime();
        }
        seed = newSeed;
    }
    
    /**
     * Get current seed
     */
    public static long getSeed() {
        return seed;
    }
}
