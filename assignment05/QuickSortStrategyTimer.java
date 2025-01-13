//package assignment05;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class QuickSortStrategyTimer {
//    public static void main(String args[]) {
//        //Fields
//    	ArrayListSorter als = new ArrayListSorter();
//    	
//        // Do 1000 merge sorts and use the average running time
//        int timesToLoop = 1000;
//        // For each problem size n . . .
//        for (int n = 0; n <= 20_000; n += 1000) {
//            //Create a permuted ArrayList of size N
//            ArrayList arr = als.generatePermuted(n);
//            ArrayList temp = new ArrayList<>();
//            temp.addAll(arr);
//            
//
//            long startTime, midpointTime, stopTime;
//            // First, spin computing stuff until one second has gone by
//            // This allows this thread to stabilize
//            startTime = System.nanoTime();
//            while (System.nanoTime() - startTime < 1000000000) { // empty block
//            }
//            // Now, run the test
//            startTime = System.nanoTime();
//            for (int i = 0; i < timesToLoop; i++) {
//            	//mergesort
//            	als.quicksort(arr,1);
//            	//new permutedList
//            	for(int j = 0; j < arr.size(); j++)
//            		arr.set(j, temp.get(j));
//            }
//                
//            midpointTime = System.nanoTime();
//            // Run a loop to capture the cost of running the "timesToLoop" loop
//            for (int i = 0; i < timesToLoop; i++) {
//            	for(int j = 0; j < arr.size(); j++)
//            		arr.set(j, temp.get(j));
//            }
//            stopTime = System.nanoTime();
//// Compute the time, subtract the cost of running the loop
//// from the cost of running the loop and doing the lookups
//// Average it over the number of runs
//            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
////            System.out.println(n + "\t" + averageTime + "\t" + "O(1)" +averageTime/1 + "\t" + "O(NlgN)" +averageTime/(n * Math.log(n)) + "\t" + "O(lgN)" +averageTime/Math.log(n) + "\t" + "O(N^2)" +averageTime/(n*n));
//            System.out.print("Random: " + n + "\t" + averageTime);
//
//            
//            
//            
//            
//            
//            
//            
//            
//         // Now, run the test
//            startTime = System.nanoTime();
//            for (int i = 0; i < timesToLoop; i++) {
//            	//mergesort
//            	als.quicksort(arr,2);
//            	//new permutedList
//            	for(int j = 0; j < arr.size(); j++)
//            		arr.set(j, temp.get(j));
//            }
//                
//            midpointTime = System.nanoTime();
//            // Run a loop to capture the cost of running the "timesToLoop" loop
//            for (int i = 0; i < timesToLoop; i++) {
//            	for(int j = 0; j < arr.size(); j++)
//            		arr.set(j, temp.get(j));
//            }
//            stopTime = System.nanoTime();
//// Compute the time, subtract the cost of running the loop
//// from the cost of running the loop and doing the lookups
//// Average it over the number of runs
//            double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
////            System.out.println(n + "\t" + averageTime + "\t" + "O(1)" +averageTime/1 + "\t" + "O(NlgN)" +averageTime/(n * Math.log(n)) + "\t" + "O(lgN)" +averageTime/Math.log(n) + "\t" + "O(N^2)" +averageTime/(n*n));
//            System.out.print("\tMedianOf3: " + n + "\t" + averageTime2);
//
//            
//            
//            
//            
//            
//            
//         // Now, run the test
//            startTime = System.nanoTime();
//            for (int i = 0; i < timesToLoop; i++) {
//            	//mergesort
//            	als.quicksort(arr,3);
//            	//new permutedList
//            	for(int j = 0; j < arr.size(); j++)
//            		arr.set(j, temp.get(j));
//            }
//                
//            midpointTime = System.nanoTime();
//            // Run a loop to capture the cost of running the "timesToLoop" loop
//            for (int i = 0; i < timesToLoop; i++) {
//            	for(int j = 0; j < arr.size(); j++)
//            		arr.set(j, temp.get(j));
//            }
//            stopTime = System.nanoTime();
//// Compute the time, subtract the cost of running the loop
//// from the cost of running the loop and doing the lookups
//// Average it over the number of runs
//            double averageTime3 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
////            System.out.println(n + "\t" + averageTime + "\t" + "O(1)" +averageTime/1 + "\t" + "O(NlgN)" +averageTime/(n * Math.log(n)) + "\t" + "O(lgN)" +averageTime/Math.log(n) + "\t" + "O(N^2)" +averageTime/(n*n));
//            System.out.println("\tMiddle: " + n + "\t" + averageTime3);
//
//        }
//    }
//}
