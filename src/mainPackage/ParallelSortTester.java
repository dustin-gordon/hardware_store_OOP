package assign6package;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * The ParrallelSortTester class calls the ParallelMergeSorter class to test the merge sort algorithm 
 * on several arrays of integers, using progressively more threads to demonstrate the benefit of 
 * multiple threads on parrallel optimized computational tasks.
 * 
 * @author Dustin Gordon
 */
public class ParallelSortTester extends Thread{
	/**
     * Main class uses Loop to test parallel merge sorter on random arrays ranging in length from 
     * 1,000 to 16,384,000. Starts with 1 thread then doubles, up to max threads, and 
     * benchmarks time for each one.
     */
    public static void main(String[] args) throws Exception {
    	int totalThreads = Runtime.getRuntime().availableProcessors(); // Get # of threads from host.
    	int counter = 1;
    	
    	System.out.println("There are " +totalThreads+ " threads available on this system.");
    	
        while (counter <= totalThreads) {
        	System.out.println("\nSorting with " +counter+ " thread(s):");
        	for (int length = 1000; length <= 16384000;) {
        		runSortTester(counter, length);
        		length = length * 2;
        	}
        	counter = counter * 2;
        }
    }

    /**
     * Calls merge sorter given an array length and number of threads.
     * 
     * @param threads The number of threads granted to run sort.
     * @param length The length of random array to be generated.
     */
    public static void runSortTester (int threads, int length) {
        int[] randomArray = createRandomArray(length);
        
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer d1, Integer d2) {
                return d1.compareTo(d2);
            }
        };
        
        // Capture start time of sort:
        long startTime = System.currentTimeMillis();
        // Run merge sort:
        ParallelMergeSorter.sort(randomArray, threads, comp);
       // Capture end time:
        long endTime = System.currentTimeMillis();

        if (!isSorted(randomArray, comp)) {
            throw new RuntimeException("Not sorted afterward: " + Arrays.toString(randomArray));
        }
        System.out.printf("%10d elements  =>  %6d ms \n", length, endTime - startTime);
    }

    /**
     * Returns true if the given array is in sorted ascending order.
     *
     * @param a the array to examine
     * @param comp the comparator to compare array elements
     * @return true if the given array is sorted, false otherwise
     */
    public static boolean isSorted(int[] array, Comparator<Integer> comp) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comp.compare(array[i], array[i + 1]) > 0) {
                System.out.println(array[i] + " > " + array[i + 1]);
                return false;
            }
        }
        //System.out.println("Array is sorted properly.");		// For debugging.
        return true;
    }

    // Randomly rearranges the elements of the given array.
    public static <E> void shuffle(E[] array) {
        for (int i = 0; i < array.length; i++) {
            // move element i to a random index in [i .. length-1]
            int randomIndex = (int) (Math.random() * array.length - i);
            swap(array, i, i + randomIndex);
        }
    }

    // Swaps the values at the two given indexes in the given array.
    public static final <E> void swap(E[] array, int indexA, int indexB) {
        if (indexA != indexB) {
            E temp = array[indexA];
            array[indexA] = array[indexB];
            array[indexB] = temp;
        }
    }

    /** 
     * Creates an array of the given length, fills it with random non-negative integers, and returns said array.
     *
     * @param length Max size of array to generate
     * @return Randomized array of specified length
     */
    public static int[] createRandomArray(int length) {
        int[] newArray = new int[length];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < newArray.length; i++) {
        	newArray[i] = rand.nextInt(1000000);
        }
        return newArray;
    }
}