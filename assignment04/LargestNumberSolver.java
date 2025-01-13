package assignment04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a largest number solver. 
 * Which determines the largest number possible from
 * a given Integer array by appending the array's
 * contents.
 * 
 * @author Jadon Olson & Sachin Jampala
 * @version June 7, 2023
 */
public class LargestNumberSolver {

	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param <T>
	 * @param arr
	 * @param cmp
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		for(int i = 1; i < arr.length; i++) {
			T val = arr[i];
			int j;
			for(j = i - 1; j >= 0 && cmp.compare(arr[j], val) > 0; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = val;
		}
	}

	/**
	 * This method returns the largest number that can be formed by arranging the integers of the given array, in any order.
	 * If the array is empty, the largest number that can be formed is 0.
	 * 
	 * This method does not alter the given array and  calls the insertionSort method with a lambda expression.
	 * @param arr - an Integer array
	 * @return Largest number
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		//Change made here***************************************************************************************

		if(arr.length == 0 || arr[0] == null) {
			return BigInteger.ZERO; 
		}

		insertionSort(arr, (a,b) -> { String first = a.toString();
		String second = b.toString();
		return (second + first).compareTo(first + second); });
		StringBuilder bigNumber = new StringBuilder();
		for(Integer i : arr) {
			bigNumber.append(i);
		}
		return new BigInteger(bigNumber.toString());
	}

	/**
	 * This method returns the largest int that can be formed by arranging the integers of the given array, in any order.
	 * If the array is empty, the largest number that can be formed is 0.
	 * 
	 * This method does not alter the given array and calls the findLargestNumber method converting the result to an int.
	 * 
	 * @throws 
	 * @param arr - an Integer array
	 * @return The Largest int
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		//Change made here***************************************************************************************

		BigInteger ret = findLargestNumber(arr);
		BigInteger max = new BigInteger(String.valueOf(Integer.MAX_VALUE));
		if(ret.compareTo(max) > 0)
			throw new OutOfRangeException("int");
		return ret.intValue();
	}

	/**
	 * This method returns the largest long that can be formed by arranging the integers of the given array, in any order.
	 * If the array is empty, the largest number that can be formed is 0.
	 * 
	 * This method does not alter the given array and calls the findLargestNumber method converting the result to an long.
	 * @param arr - an Integer array
	 * @return The Largest long
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		//Change made here***************************************************************************************
		BigInteger ret = findLargestNumber(arr);
		BigInteger max = new BigInteger(String.valueOf(Long.MAX_VALUE));
		if(ret.compareTo(max) > 0)
			throw new OutOfRangeException("long");
		return ret.longValue();
	}

	/**
	 * This method sums the largest numbers that can be formed by each array in the given list.
	 * This method doesn't alter the biggest list
	 * @param list - a list to be added
	 * @return the sum of big numbers
	 */
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger sum = BigInteger.ZERO;
		//Change made here***************************************************************************************
		
		for(int i = 0; i < list.size(); i++) {
			sum = sum.add(findLargestNumber(list.get(i)));
		}
		return sum;

	}

	/**
	 * This method determines the kth largest number that can be formed by each array in the given list. 
	 * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.
	 * 
	 * This method returns the original array that represents the kth largest number, not the kth largest number itself. 
	 * 
	 * An IllegalArgumentException is thrown if k is not a valid position in the list.
	 * 
	 * This method does not alter the given list and must call your insertionSort method with a Comparator
	 *  or lambda expression that you design.
	 * @param list
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
//	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
//		if(k < 0 || k > list.size())
//			throw new IllegalArgumentException("K is not a valid position in the list.");
//
//		Integer[][] arr = new Integer[list.size()][];
//		for(int i = 0; i < list.size(); i++) {
//			arr[i] = list.get(i);
//		}
//
//		insertionSort(arr, (a,b) -> { BigInteger first = findLargestNumber((Integer[]) a);
//		BigInteger second = findLargestNumber((Integer[]) b);
//		return second.compareTo(first); });
//
//
//		return arr[k];
//
//	}
	
	/**
	 * Find kth largest using javas sort method instead
	 * 
	 * @param list
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
		if(k < 0 || k > list.size())
			throw new IllegalArgumentException("K is not a valid position in the list.");

		Integer[][] arr = new Integer[list.size()][];
		for(int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}

		Arrays.sort(arr, (a,b) -> { BigInteger first = findLargestNumber((Integer[]) a);
		BigInteger second = findLargestNumber((Integer[]) b);
		return second.compareTo(first); });


		return arr[k];

	}

	/**
	 * This method generates list of integer arrays from an input file,
	 * such that each line corresponds to one array of integers separated by blank spaces,
	 * and returns an empty list if the file does not exist.
	 * 
	 * @param filename
	 * @return
	 */
	public static List<Integer[]> readFile(String filename){
		File file = new File(filename);
		List<Integer[]> list = new ArrayList<>();

		try {
			Scanner sc = new Scanner(file);
			
			//Change made here***************************************************************************************
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] splitLine = line.split(" ");
                Integer[] arr = new Integer[splitLine.length];
                for(int i = 0; i < arr.length; i++)
                	arr[i] = Integer.parseInt(splitLine[i]);
                list.add(arr);
			}
			sc.close();
			return list;
		} catch (FileNotFoundException e) {
			return list;
		}

	}
}

