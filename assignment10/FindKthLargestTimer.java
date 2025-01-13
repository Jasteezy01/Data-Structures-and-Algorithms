package assignment10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindKthLargestTimer {
    public static void main(String args[]) {
        //HashTable Object
        FindKLargest findKLargest = new FindKLargest();


        //Loop through tests 1000 times
        int timesToLoop = 10;
        // For each problem size n . . .
        for (int n = 100; n <= 2000; n += 100) {

            List<Integer> list = new ArrayList<>();

            int rando = (int)(Math.random() * n);

            for (int i = 0; i < n; i++)
                list.add(i);



            long startTime, midpointTime, stopTime;


            // First, spin computing stuff until two seconds have gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++) {
                    findKLargest.findKLargestHeap(list, rando);
                }
            }

            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the N and times to loop loop
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++) ;
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
            System.out.print("K Largest Heap: " + n + "\t" + averageTime + "\t");

            // Java Sort Routine ******************************************

            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++) {
                    findKLargest.findKLargestSort(list, j);
                }
            }

            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the N and times to loop loop
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++) ;
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
            System.out.println("Java Sort: " + n + "\t" + averageTime2 + "\t");
        }
    }
}