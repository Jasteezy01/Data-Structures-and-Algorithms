package comprehensive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class numOfPhrasesTimer {
    public static void main(String args[]) throws IOException {
        //HashTable Object

        //Loop through tests 1000 times
        int timesToLoop = 10;
        // For each problem size n . . .
        for (int n = 1000; n <= 20_000; n += 1000) {
        	
        	generateGrammarFile(n);
        	
        	
            long startTime, midpointTime, stopTime;


            // First, spin computing stuff until two seconds have gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
            	GrammerOrganizer go = new GrammerOrganizer("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/comprehensive/poetic_sentence.g", n);
            }

            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the N and times to loop loop
            for (int i = 0; i < timesToLoop; i++) {
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) (timesToLoop);
            System.out.println("Grammar Organizer: " + n + "\t" + averageTime + "\t");

        }
    }

	private static void generateGrammarFile(int n) throws IOException {
		FileWriter writer = new FileWriter(new File("timer.txt"));
		
		for(int i = 0; i < n; i++) {
			writer.write(i + "+");
		}
		writer.write("\n");
		writer.write("\n");
		writer.write("{\n");
		writer.write("<start>\n");
		for(int i = 0; i < n; i++) {
			writer.write("<" + i + ">");
		}
		writer.write("\n}\n");
		for(int i = 0; i < n; i++) {
			writer.write("\n");
			writer.write("{\n");
			writer.write("<" + i + ">");
			writer.write("\n" + i);
			writer.write("\n" + "<" + i + ">" + i);
			writer.write("\n}");
			writer.write("\n");
		}
		writer.flush();
		//writer.close();
	}
}