package assignment10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FindKthLargestTimer2 {
    public static void main(String[] args) {
        // HashTable Object
        FindKLargest findKLargest = new FindKLargest();

        // Loop through tests 1000 times
        int timesToLoop = 100;
        Random random = new Random();

        // For each problem size n . . .
        for (int n = 10_000; n <= 200_000; n += 10_000) {
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++)
                list.add(i);

            // Warm-up and stabilization
            for (int i = 0; i < 5; i++) {
                List<Integer> randomList = new ArrayList<>(list);
                Collections.shuffle(randomList);
                findKLargest.findKLargestHeap(randomList, random.nextInt(n));
                findKLargest.findKLargestSort(randomList, random.nextInt(n));
            }

            long startTime, midpointTime, stopTime;

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            
            // Binary Heap Approach
            startTime = System.currentTimeMillis();
            for (int i = 0; i < timesToLoop; i++) {
                List<Integer> randomList = new ArrayList<>(list);
                Collections.shuffle(randomList);
                findKLargest.findKLargestHeap(randomList, random.nextInt(n));
            }
            midpointTime = System.currentTimeMillis();
            for(int i = 0; i < timesToLoop; i++) {
            	List<Integer> randomList = new ArrayList<>(list);
                Collections.shuffle(randomList);
            }
            stopTime = System.currentTimeMillis();
            double averageTimeHeap = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop);

            // Java Sort Routine
            startTime = System.currentTimeMillis();
            for (int i = 0; i < timesToLoop; i++) {
                List<Integer> randomList = new ArrayList<>(list);
                Collections.shuffle(randomList);
                findKLargest.findKLargestSort(randomList, random.nextInt(n));
            }
            midpointTime = System.currentTimeMillis();
            for(int i = 0; i < timesToLoop; i++) {
            	List<Integer> randomList = new ArrayList<>(list);
                Collections.shuffle(randomList);
            }
            stopTime = System.currentTimeMillis();
            double averageTimeSort = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop);


            System.out.print("K Largest Heap: " + n + "\t" + averageTimeHeap + "\t");
            System.out.println("Java Sort: " + n + "\t" + averageTimeSort + "\t");
        }
    }
}
