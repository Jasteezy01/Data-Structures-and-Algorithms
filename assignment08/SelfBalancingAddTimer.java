package assignment08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class SelfBalancingAddTimer {
    public static void main(String args[]) {
        //Binary Search Trees
    	BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    	TreeSet<Integer> selfBalance = new TreeSet<>();
    	
    	//Array Lists
    	ArrayList<Integer> randomList = new ArrayList<Integer>();

        // Loop 1000 times
        int timesToLoop = 100;
        // For each problem size n . . .
        for (int n = 500; n <= 10_000; n += 500) {
        	
        	//Clear both lists and both BSTs
        	selfBalance.clear();
        	randomList.clear();
        	bst.clear();
        	
        	//Add n items to each list
            for (int i = 0; i < n; i++) {
                randomList.add(i);
            }
            
            Collections.shuffle(randomList);
           
            

            long startTime, midpointTime, stopTime;


            // First, spin computing stuff until one second has gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test for the BST
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                for(int j = 0; j < n; j++)
                	bst.add(randomList.get(j));
            }
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop and 0-N loop
            for (int i = 0; i < timesToLoop; i++) {
            	for(int j = 0; j < n; j++) {}
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / ((double) timesToLoop * n);
            System.out.print("BST: " + n + "\t" + averageTime + "\t");

            
            
            //RUN THE 2ND TEST ********************************************************************
            
            
         // Now, run the test for the sorted BST
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                for(int j = 0; j < n; j++)
                	selfBalance.add(randomList.get(j));
            }
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop and 0-N loop
            for (int i = 0; i < timesToLoop; i++) {
            	for(int j = 0; j < n; j++) {}
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / ((double) timesToLoop * n);
            System.out.println("Auto Balance: " + n + "\t" + averageTime2 + "\t");
            
            
        }
    }
}