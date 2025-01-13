package comprehensive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * This class generates a phrase based on the rules of the 
 * provided grammar
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version August 2, 2023
 */
public class GrammerOrganizer {

	private ArrayList<ArrayList<String>> list;
	private HashMap<String,Integer> map;
	private File file;
	private int count;
	private int numOfPhrases;
	private boolean isBracket;
	private boolean b;
	private char punctuation;
	private Random random;

	/**
	 * This constructor takes a filename as a String and the number of phrases
	 * as an int.
	 * @param fileName - The name of the file.
	 * @param numOfPhrases - The number of random phrases to be generated.
	 * @throws IOException 
	 */
	public GrammerOrganizer(String fileName, int numOfPhrases) throws IOException {
		list = new ArrayList<ArrayList<String>>();
		map  = new HashMap<String ,Integer>();
		this.file = new File(fileName);
		this.count = 0;
		this.numOfPhrases = numOfPhrases;
		this.isBracket = false;
		readFile();
	}

	/**
	 * This method reads the provided grammar file and sets up the backing ArrayList 
	 * and Map using the contents of the file and it calls the generatePhrases() 
	 * method after setup is complete.
	 * @throws IOException 
	 */
	private void readFile() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(br == null) {
			return;
		}

		String currentLine = null;
		while((currentLine = br.readLine()) != null) {
			
			//String s = br.readLine()();
			if(currentLine == null) {
				continue;
			}

			if(currentLine.isEmpty()) {
				continue;
			}
			if(currentLine.charAt(0) == '{') {
				isBracket = true;
				String key = br.readLine();
				list.add(new ArrayList<String>());
				map.put(key,count);
				continue;
			}

			else if(currentLine.charAt(0) == '}') {
				isBracket = false;
				count++;
				continue;
			}
			if(isBracket) {
				list.get(count).add(currentLine);

			}
		}
		generatePhrases();
	}

	/**
	 * This method generates random phrases based on the rules of the 
	 * grammar file.
	 * @throws IOException 
	 */
	private void generatePhrases() throws IOException {

		random = new Random(); //New Random Object
		StringBuilder sb = new StringBuilder(); // New StringBuilder
		String str = "";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// Generate Phrases
		for(int i = 0; i < numOfPhrases; i++) {
			String phrase = list.get(0).get(random.nextInt(list.get(0).size())); //Select the starting grammer
			sb = new StringBuilder();
			for(int j = 0; j < phrase.length(); j++) {
				char temp = phrase.charAt(j);
				str += temp;
				if(str.charAt(0) == '<' && str.charAt(str.length() - 1) == '>') {
					str = replace(str);
					sb.append(str);
					str = "";
					
				}
				else if(temp == ' ') {
					sb.append(str);
					str = "";
					continue;
				}
			}
			sb.append(str);
			String strRet = sb.toString();
			str = "";
			
			//bw.write(strRet + "\n");
			bw.flush();
			
		}
		//bw.close();
	}

	/**
	 * This method replaces non-terminals with one of its productions
	 * @param str - A non-terminal that needs to be replaced.
	 * @return - The production of the non-terminal.
	 */
	private String replace(String str) {
		int index = map.get(str);
		str = list.get(index).get(random.nextInt(list.get(index).size()));
		String ret = "";
		StringBuilder sb = new StringBuilder();

		for(int j = 0; j < str.length(); j++) {
			char temp = str.charAt(j);
			if(temp == '<') {
				sb.append(ret);
				ret = "";
			}
			ret += temp;
			if(ret.charAt(0) == '<' && ret.charAt(ret.length() - 1) == '>') {
				ret = replace(ret);
				continue;
			}
			else if(temp == ' ') {
				sb.append(ret);
				ret = "";
			}
		}
		sb.append(ret);

		return sb.toString();
	}



}
