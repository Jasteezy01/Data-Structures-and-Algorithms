package assignment06;


import java.util.ArrayList;
import java.util.List;


public class PeekComparisonTimer {
	public static void main(String args[]) {
		//Fields
		LinkedListStack<Integer> lls = new LinkedListStack<>();
		ArrayStack<Integer> arrStack = new ArrayStack<>();


		// Do 1000 merge sorts and use the average running time
		int timesToLoop = 1000;
		// For each problem size n . . .
		for (int n = 5000; n <= 200_000; n += 5000) {
			lls.clear();
			arrStack.clear();
			for(int add = 0; add < n; add++) {
				lls.push(add);
				arrStack.push(add);
			}

			long startTime, midpointTime, stopTime;
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}
			// Now, run the test
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
					lls.peek();
			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
				
			}
			stopTime = System.nanoTime();
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop);
			//            System.out.println(n + "\t" + "O(1) " +averageTime/1 + "\t" +  "O(NlgN) " + averageTime/(n) + "\tO(NlgN) " +averageTime/(n * Math.log(n)) + "\t" + "O(lgN) " +averageTime/Math.log(n) + "\t" + "O(N^2) " +averageTime/(n*n));
			System.out.print("LinkedListStack \t" + n + "\t" + averageTime + "\t");



			// Now, run the test
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
					arrStack.peek();
			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
			}
			stopTime = System.nanoTime();
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop);
			//            System.out.println(n + "\t" + "O(1) " +averageTime/1 + "\t" +  "O(NlgN) " + averageTime/(n) + "\tO(NlgN) " +averageTime/(n * Math.log(n)) + "\t" + "O(lgN) " +averageTime/Math.log(n) + "\t" + "O(N^2) " +averageTime/(n*n));
			System.out.println("ArrayStack \t" + n + "\t" + averageTime2 + "\t");


		}
	}
}
