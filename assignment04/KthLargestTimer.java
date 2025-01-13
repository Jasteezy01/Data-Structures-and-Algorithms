package assignment04;

import assignment04.LargestNumberSolver;

import java.util.ArrayList;
import java.util.List;


public class KthLargestTimer {
    public static void main(String args[]) {
        //Fields
        LargestNumberSolver lns = new LargestNumberSolver();

        // Do 1000 find k's and use the average running time
        int timesToLoop = 1000;
        // For each problem size n . . .
        for (int n = 0; n <= 10_000; n += 500) {
            //Create a list and fill it with N arrays of size 10.
            List<Integer[]> list = new ArrayList<>();
            for (int i = 0; i <= n; i++){
                Integer[] arr = new Integer[10];
                for(int j = 0; j < arr.length; j++)
                    arr[j] = (int)Math.random()*100;
                list.add(arr);
            }

            //Initialize a random K
            int k = (int)Math.random()*n;

            long startTime, midpointTime, stopTime;
            // First, spin computing stuff until one second has gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++)
                //Find kth largest number
                lns.findKthLargest(list, k);
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) { //empty block
            }
            stopTime = System.nanoTime();
// Compute the time, subtract the cost of running the loop
// from the cost of running the loop and doing the lookups
// Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
            System.out.println(n + "\t" + averageTime + "\t" + "O(1)" +averageTime/1 + "\t" + "O(N)" +averageTime/n + "\t" + "O(lgN)" +averageTime/Math.log(n) + "\t" + "O(N^2)" +averageTime/(n*n));
//            System.out.println(n + "\t" + averageTime);

        }
    }
}
