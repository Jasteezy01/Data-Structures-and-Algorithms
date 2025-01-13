package assignment09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JavaHash {
	public static void main(String args[]) throws FileNotFoundException {
		//list of names
		String[] names = nameList();

		//HashTable Object
		HashTable<StudentBadHash, Integer> badStudentHash = new HashTable<>();
		HashTable<StudentMediumHash, Integer> mediumStudentHash = new HashTable<>();
		HashTable<StudentGoodHash, Integer> goodStudentHash = new HashTable<>();

		//Random object
		Random random = new Random();


		//Loop through tests 10000 times
		int timesToLoop = 100;
		// For each problem size n . . .
		for (int n = 1_000; n <= 10_000; n += 500) {





			long startTime, midpointTime, stopTime;


			// First, spin computing stuff until two seconds have gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) { // empty block
			}
			// Now, run the test
			startTime = System.nanoTime();
			for(int j = 0; j < timesToLoop; j++) {
				for (int i = 0; i < n; i++) {
					//Add students
					String firstName = names[(int) (Math.random() * names.length)];
					String lastName = random.toString();
					badStudentHash.put(new StudentBadHash(random.nextInt(n), firstName, lastName), n);
				}
				badStudentHash = new HashTable<>();
			}
			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the N and times to loop loop
			for(int j = 0; j < timesToLoop; j++) {
				for (int i = 0; i < n; i++) {
					//Add students
					String firstName = names[(int) (Math.random() * names.length)];
					String lastName = random.toString();
				}
				badStudentHash = new HashTable<>();
			}
			stopTime = System.nanoTime();
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
			System.out.print("StudentBadHash: " + n + "\t" + averageTime + "\t");









			// Now, run the test
			startTime = System.nanoTime();
			for(int j = 0; j < timesToLoop; j++) {
				for (int i = 0; i < n; i++) {
					//Add students
					String firstName = names[(int) (Math.random() * names.length)];
					String lastName = random.toString();
					mediumStudentHash.put(new StudentMediumHash(random.nextInt(n), firstName, lastName), n);
				}
				mediumStudentHash = new HashTable<>();
			}
			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the N and times to loop loop
			for(int j = 0; j < timesToLoop; j++) {
				for (int i = 0; i < n; i++) {
					//Add students
					String firstName = names[(int) (Math.random() * names.length)];
					String lastName = random.toString();
				}
				mediumStudentHash = new HashTable<>();
			}
			stopTime = System.nanoTime();
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime2 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
			System.out.print("StudentMediumHash: " + n + "\t" + averageTime2 + "\t");









			// Now, run the test
			startTime = System.nanoTime();
			for(int j = 0; j < timesToLoop; j++) {
				for (int i = 0; i < n; i++) {
					//Add students
					String firstName = names[(int) (Math.random() * names.length)];
					String lastName = random.toString();
					goodStudentHash.put(new StudentGoodHash(random.nextInt(n), firstName, lastName), n);
				}
				goodStudentHash = new HashTable<>();
			}
			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the N and times to loop loop
			for(int j = 0; j < timesToLoop; j++) {
				for (int i = 0; i < n; i++) {
					//Add students
					String firstName = names[(int) (Math.random() * names.length)];
					String lastName = random.toString();
				}
				goodStudentHash = new HashTable<>();
			}
			stopTime = System.nanoTime();
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime3 = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop * n);
			System.out.println("StudentGoodHash: " + n + "\t" + averageTime3 + "\t");

		}
	}


	private static String[] nameList() throws FileNotFoundException {
		Scanner in = new Scanner(new File("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment09/names.txt"));
		String[] ret = new String[21985];
		int count = 0;
		while(count < 21985) {
			ret[count] = in.next();
			count++;
		}

		return ret;
	}
}