package assignment05;


import java.util.ArrayList;
import java.util.List;


public class MergeVsQuickTimer {
    public static void main(String args[]) {
        //Fields
    	ArrayListSorter als = new ArrayListSorter();
    	
        // Do 1000 merge sorts and use the average running time
        int timesToLoop = 1000;
        // For each problem size n . . .
        for (int n = 0; n <= 10_000; n += 500) {
            //Create a permuted ArrayList of size N
            ArrayList arr = als.generateDescending(n);
            ArrayList temp = new ArrayList<>();
            temp.addAll(arr);


            long startTime, midpointTime, stopTime;
            // First, spin computing stuff until one second has gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
            	//mergesort
            	als.mergesort(arr);
            	//new permutedList
            	for(int j = 0; j < arr.size(); j++)
            		arr.set(j, temp.get(j));
            }
                
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {
            	for(int j = 0; j < arr.size(); j++)
            		arr.set(j, temp.get(j));
            }
            stopTime = System.nanoTime();
// Compute the time, subtract the cost of running the loop
// from the cost of running the loop and doing the lookups
// Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
//            System.out.println(n + "\t" + averageTime + "\t" + "O(1)" +averageTime/1 + "\t" + "O(NlgN)" +averageTime/(n * Math.log(n)) + "\t" + "O(lgN)" +averageTime/Math.log(n) + "\t" + "O(N^2)" +averageTime/(n*n));
            System.out.print(n + "\tMergeSort: " + "\t" + averageTime);
            
            
            
            
            
            
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
            	//mergesort
            	als.quicksort(arr);
            	//new permutedList
            	for(int j = 0; j < arr.size(); j++)
            		arr.set(j, temp.get(j));
            }
                
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {
            	for(int j = 0; j < arr.size(); j++)
            		arr.set(j, temp.get(j));
            }
            stopTime = System.nanoTime();
// Compute the time, subtract the cost of running the loop
// from the cost of running the loop and doing the lookups
// Average it over the number of runs
            double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
//            System.out.println(n + "\t" + averageTime + "\t" + "O(1)" +averageTime/1 + "\t" + "O(NlgN)" +averageTime/(n * Math.log(n)) + "\t" + "O(lgN)" +averageTime/Math.log(n) + "\t" + "O(N^2)" +averageTime/(n*n));
            System.out.println("\tQuicksort: " + "\t" + averageTime2);

        }
    }
}
