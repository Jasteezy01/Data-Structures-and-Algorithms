package assignment07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSTimer {
    public static void main(String args[]) {
        //Graph Utility Objects
        List<Integer> sources = new ArrayList<>();
        List<Integer> destinations = new ArrayList<>();



        // Do 5000 merge sorts, 1000 per threshold
        int timesToLoop = 1000;
        // For each problem size n . . .
        for (int n = 500; n <= 10_000; n += 500) {
        	sources.clear();
        	destinations.clear();

            for (int i = 0; i < n-1; i++) {
                sources.add(n);
                destinations.add(n+1);
            }
            
//            Collections.shuffle(sources);
//            Collections.shuffle(destinations);

            long startTime, midpointTime, stopTime;


            // First, spin computing stuff until one second has gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                GraphUtility.sort(sources, destinations);
            }
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop and the collections.shuffle
            for (int i = 0; i < timesToLoop; i++) {
        		//Graph graph = new Graph(sources, destinations);//take off the time of creating the graph
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
            System.out.println("Topological Sort: " + n + "\t" + averageTime + "\t");

        }
    }
}