package assign6package;

import java.util.*;

/**
 * This class carries out the merge sort algorithm via multi-threading.
 */
public class ParallelMergeSorter extends Thread {
	// Determine # of threads on host:
	static int threadsAvailable = Runtime.getRuntime().availableProcessors();

    /**
     * Sorts an array, using the merge sort algorithm, called by SortTester.
     *
     * @param array the array to sort
     * @param threads Number of threads granted to run on
     * @param comp the comparator to compare array elements
     */
    public static void sort(int[] array, int threads, Comparator<Integer> comp) {
    	parallelMergeSort(array, threads, comp);
    }

    /**
     * Sorts a range of an array, using the merge sort algorithm, using only a single thread.
     *
     * @param array the array to sort
     * @param firstIndex the first index of the range to sort
     * @param lastIndex the last index of the range to sort
     * @param comp the comparator to compare array elements
     */
    private static void mergeSort(int[] array, int firstIndex, int lastIndex, Comparator<Integer> comp) {
    	if (firstIndex == lastIndex) {
            return;
        }
    	
        //Find split point of array:
        int middleIndex = (firstIndex + lastIndex) / 2;
        
        // Sort the first half:
        mergeSort(array, firstIndex, middleIndex, comp);
        
        // Sort the second half:
        mergeSort(array, middleIndex + 1, lastIndex, comp);
        
        // Combine sorted arrays:
        merge(array, firstIndex, middleIndex, lastIndex, comp);
    }
    
    public static void parallelMergeSort(int[] array, Comparator<Integer> comp) {
		int threads = Runtime.getRuntime().availableProcessors();
		parallelMergeSort(array, threads, comp);
	}
    
    /**
     * Sorts a range of an array, with merge sort algorithm, using multiple threads.
     * 
     * @param array Array to be sorted
     * @param threads Number of threads to run on
     * @param comp Comparator for integers
     */
    public static int[] parallelMergeSort(int[] array, int threads, Comparator<Integer> comp) {
    	if (array.length < 1) {
            return array;
        }
    	if (threadsAvailable <= 1) {
			 mergeSort(array, 0, array.length - 1, comp);
			 return array;
    	}
    	//System.out.println("Running in multi-threaded mode...");
    	//System.out.println("Threads available = " +threadsAvailable);
    	
    	// Split array into two subarrays:
    	int[] left  = Arrays.copyOfRange(array, 0, array.length / 2);
		int[] right = Arrays.copyOfRange(array, array.length / 2, array.length);
        
        // Sort the first half in a new thread:
        Thread threadA= new Thread(new Runnable() {
        	public void run() {
        		threadsAvailable = threadsAvailable / 2;
        		parallelMergeSort(left, threadsAvailable / 2, comp);
        	}	
     	});
     	//System.out.println("Thread A created."); 		// For debugging.
        
        // Sort the second half in a new thread:
       Thread threadB = new Thread(new Runnable() {
     		public void run() {
     			try {
					threadA.join(); // Wait for left sort to finish.
					threadsAvailable = threadsAvailable / 2;
					parallelMergeSort(right, threadsAvailable / 2, comp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
     		}
     	});
     	//System.out.println("Thread B created.");		// For debugging.
     		
     	// Start threads:
        threadA.start();
        //System.out.println("Thread A started.");		// For debugging.
        threadB.start();
        //System.out.println("Thread B started.");		// For debugging.
        	
        try {
			threadA.join();
			threadB.join();
		} 
        catch (InterruptedException ie) {}
        merge(left, right, array);
		return array;
    }

    /**
     * Merges two adjacent subranges of an array
     *
     * @param array the array with entries to be merged
     * @param firstElement the index of the first element of the first range
     * @param middleElement the index of the last element of the first range
     * @param lastElement the index of the last element of the second range
     * @param comp the comparator to compare array elements
     */
    private static void merge(int[] array, int firstElement, int middleElement, int lastElement, Comparator<Integer> comp) {
    	 // Size of the range to be merged:
        int range = lastElement - firstElement + 1;
       
        // Merge both halves into a temporary array:
        Object[] tempArray = new Object[range];
        
        int indexA = firstElement; 				// Next element to consider in the first range
        int indexB = middleElement + 1;  	// Next element to consider in the second range
        int indexTemp = 0; 							// Next open position in tempArray

        // As long as neither indexA nor indexB are past the end, move the smaller element into tempArray:
        while (indexA <= middleElement && indexB <= lastElement) {
            if (comp.compare(array[indexA], array[indexB]) < 0) {
            	tempArray[indexTemp] = array[indexA];
            	indexA++;
            } else {
            	tempArray[indexTemp] = array[indexB];
            	indexB++;
            }
            indexTemp++;
        }

        // (Note that only one of the two while loops below is executed)
        // Copy any remaining entries of the first half
        while (indexA <= middleElement) {
        	tempArray[indexTemp] = array[indexA];
        	indexA++;
        	indexTemp++;
        }
        // Copy any remaining entries of the second half
        while (indexB <= lastElement) {
        	tempArray[indexTemp] = array[indexB];
        	indexB++;
            indexTemp++;
        }

        // Copy back from the temporary array
        for (indexTemp = 0; indexTemp < range; indexTemp++) {
        	array[firstElement + indexTemp] = (int) tempArray[indexTemp];
        }
    }

    /**
     * Merges two subarrays into one array.
     * 
     * @param left First half of array
     * @param right Second half of array
     * @param a Array to merge halves into
     */
    public static void merge(int[] left, int[] right, int[] a) {
		int indexA = 0;
		int i2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (i2 >= right.length || (indexA < left.length && left[indexA] < right[i2])) {
				a[i] = left[indexA];
				indexA++;
			} else {
				a[i] = right[i2];
				i2++;
			}
		}
	}
    
}