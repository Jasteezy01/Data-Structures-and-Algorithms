package assignment10;


import java.util.ArrayList;
import java.util.List;

public class BasicMethodTimer {
    public static void main(String args[]) {
        //HashTable Object
        BinaryMaxHeap<Integer> maxHeap;


        //Loop through tests 1000 times
        int timesToLoop = 100;
        // For each problem size n . . .
        for (int n = 10_000; n <= 200_000; n += 10_000) {

            List<Integer> list = new ArrayList<>();

            for(int i = 0; i < n; i++)
                list.add(i);

            maxHeap = new BinaryMaxHeap<>();

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
                    //Measure how long it takes to add elements
                    maxHeap.add(i);
                }
                maxHeap = new BinaryMaxHeap<>();
            }

            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the N and times to loop loop
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++);
                maxHeap = new BinaryMaxHeap<>();
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double)(n * timesToLoop);
            System.out.print("Add: " + n + "\t" + averageTime + "\t");

            //Testing for peek ***************************************************************

            maxHeap = new BinaryMaxHeap<>(list);
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++)
                    //Measure how long it takes to peek elements
                    maxHeap.peek();
            }

            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the N and times to loop loop
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++);
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
            System.out.print("Peek: " + n + "\t" + averageTime2 + "\t");

            //Testing extractMax ***********************************************

            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n/2; j++) {
                    //Measure how long it takes to extract elements
                    maxHeap.extractMax();
                }
                maxHeap = new BinaryMaxHeap<>(list);
            }

            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the N and times to loop loop
            for (int i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++);
                maxHeap = new BinaryMaxHeap<>(list);
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime3 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
            System.out.println("ExtractMax: " + n + "\t" + averageTime3 + "\t");
        }
    }
}
