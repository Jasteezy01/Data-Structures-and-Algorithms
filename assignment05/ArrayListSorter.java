package assignment05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents an ArrayList sorter. It sorts ArrayLists using either a combination
 * of merge sort and insertion sort or quicksort.
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version June 14, 2023
 */
public class ArrayListSorter {
	
	//private static int threshold;
	
	/**
	 * This generic driver method calls a method that performs a merge sort on the generic ArrayList given as input.
	 * 
	 * This implementation switches over to insertion sort when the size of the 
	 * sublist to be sorted meets a certain threshold (i.e., becomes small enough).
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - the ArrayList to be sorted
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		//threshold = num;
		ArrayList<T> temp = new ArrayList<>();
		for(int i = 0; i < arr.size(); i++) {
			temp.add(null);
		}
		mergeSortHelper(arr, temp, 0, arr.size() - 1);
	}

	/**
	 * This generic driver method calls a method that performs a quicksort on the generic ArrayList given as input.
	 * 
	 * There are three different strategies for determining the pivot.
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - the ArrayList to be sorted
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
//		strategy = num;
		quickSortHelper(arr, 0, arr.size()-1);
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in ascending order.
	 * 
	 * @param size - the size of the ArrayList to be returned
	 * @return ret  - An ascending integer ArrayList.
	 */
	public static ArrayList<Integer> generateAscending(int size){
		ArrayList<Integer> ret = new ArrayList<>();
		for(int i = 0; i < size; i++)
			ret.add(i+1);
		return ret;

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in permuted order
	 * (i,e., randomly ordered).
	 * 
	 * @param size - the size of the ArrayList to be returned
	 * @return ret  - A random integer ArrayList.
	 */
	public static ArrayList<Integer> generatePermuted(int size){
		ArrayList<Integer> ret = generateAscending(size);
		Collections.shuffle(ret);
		return ret;

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in descending order.
	 * 
	 * @param size - the size of the ArrayList to be returned
	 * @return ret  - An descending integer ArrayList.
	 */
	public static ArrayList<Integer> generateDescending(int size){
		ArrayList<Integer> ret = new ArrayList<>();
		for(int i = size; i > 0; i--)
			ret.add(i);
		return ret;

	}
	/**
	 * This generic method performs the merge sort recursively and calls the merge method.
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - The ArrayList to be sorted
	 * @param temp - A temporary holder ArrayList
	 * @param start - The starting index of the section to be sorted
	 * @param end - The last index of the section to be sorted
	 */
	private static <T extends Comparable<? super T>> void mergeSortHelper(ArrayList<T> arr, ArrayList<T> temp, int start, int end) {

		int length = end - start;
		if (length <= 1) return; //base case
		
		if(length <= 10) {
			insertionSort(arr, start, end);
			return;
		}
		

		int middle = (length / 2) + start;
		
		mergeSortHelper(arr, temp, start, middle);
		mergeSortHelper(arr, temp, middle + 1, end);
		merge(arr, temp, start, middle + 1, end);
	}

	/**
	 * This generic method performs insertion sort.
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - The ArrayList to be sorted
	 */
	private static  <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int start, int end) {
		for(int i = start; i <= end; i++) {
			T val = arr.get(i);
			int j;
			for(j = i-1; j >= start && arr.get(j).compareTo(val)>0; j--)
				arr.set(j+1, arr.get(j));
			arr.set(j+1, val);
		}
	}
	
	/**
	 * This generic method merges the values in order from the temporary ArrayList into the 
	 * original ArrayList
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - The ArrayList to be sorted
	 * @param temp - A temporary holder ArrayList
	 * @param start - The starting index of the section to be sorted
	 * @param middle - The middle index of the section to be sorted
	 * @param end - The last index of the section to be sorted
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int start, int middle, int end) {

		int i = start, l = start, r = middle; //indices

		//check the conditions for merging
		while(l < middle && r < end + 1) {
			if(arr.get(l).compareTo(arr.get(r)) < 0) {
				temp.set(i, arr.get(l));
				i++;
				l++;
			}
			else {
				temp.set(i, arr.get(r));
				i++;
				r++;
			}
		}
		while(l < middle) {
			temp.set(i, arr.get(l));
			i++;
			l++;
		}
		while(r < end + 1) {
			temp.set(i, arr.get(r));
			i++;
			r++;
		}		
		
		for(int j = start; j < end + 1; j++) {
			arr.set(j, temp.get(j));
		}
	}

	/**
	 * This generic method performs the quick sort
	 * 
	 * @param <T> - extends Comparable.
	 * @param arr - The ArrayList to be sorted
	 * @param start - The starting index of the section to be sorted
	 * @param end - The last index of the section to be sorted
	 */
	private static <T extends Comparable<? super T>> void quickSortHelper(ArrayList<T> arr, int start, int end) {
		
		if(end <= start) return; //base case

		int pivot = partition(arr, start, end);
		quickSortHelper(arr, start, pivot-1);
		quickSortHelper(arr, pivot + 1, end);
	}

	/**
	 * This generic performs the parition for merge sort
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - The ArrayList to be sorted
	 * @param start - The starting index of the section to be sorted
	 * @param end - The last index of the section to be sorted
	 * @return i - The index of the pivot. 
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int start, int end) {
//		int pivot = start + ((int)(Math.random()*(end - start)));
		int pivot = medianOfThree(arr, start, end);
//		int pivot = start + (end-start)/2;
		
		T temp = arr.get(end);
		arr.set(end, arr.get(pivot));
		arr.set(pivot, temp);
		int i = start;
		int j = end-1;
		while(i < j){
			while(arr.get(i).compareTo(arr.get(end))<0 && i < end)
				i++;
			while(arr.get(j).compareTo(arr.get(end))>=0 && j > start)
				j--;
			if(i < j){
				temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);
			}
		}
		if(arr.get(i).compareTo(arr.get(end))>0) {
		temp = arr.get(i);
		arr.set(i, arr.get(end));
		arr.set(end, temp);
		}
		return i;
	}

	/**
	 * This generic method finds the median of three values 
	 * 
	 * @param <T> - extends Comparable
	 * @param arr - The ArrayList to be sorted
	 * @param start - The starting index of the section to be sorted
	 * @param end - The last index of the section to be sorted
	 * @return The index of the median value.
	 */
	private static <T extends Comparable<? super T>> int medianOfThree(ArrayList<T> arr, int start, int end) {
		T a = arr.get(start);
		T b = arr.get((end - start) / 2);
		T c = arr.get(end);

		// Checking for b
		if(((a.compareTo(b) < 0) && (b.compareTo(c) < 0)) || (c.compareTo(b) < 0) && (b.compareTo(a) < 0))
			return end/2;

		// Checking for a
		else if (((b.compareTo(a) < 0) && (a.compareTo(c) < 0)) || (c.compareTo(a) < 0) && (a.compareTo(b) < 0))
			return start;

		else
			return end;
	}
}


