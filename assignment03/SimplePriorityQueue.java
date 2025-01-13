package assignment03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class represents a Simple Priority Queue it implements the 
 * Priority Queue interface this class is Generic.
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version May 31, 2023
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	
	private E[] arr;
	private int size;
	private Comparator<? super E> comparator;
	
	/**
	 * This is a null parameter constructor for the Simple Priority Queue.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		arr = (E[]) new Object[10];
		size = 0;
		comparator = null;
	}
	
	/**
	 * This is a constructor for the Simple Priority Queue. It takes a comparator as
	 * a parameter
	 * @param cmp - is a comparator Object.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		arr = (E[]) new Object[10];
		size = 0;
		comparator = cmp;
	}

	/**
	 * This method finds the max element in the Simple Priority Queue. If the Priority 
	 * Queue is empty it throws a NoSuchElementException
	 * 
	 * @throws - NoSuchElementException if the Priority Queue is empty.
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException("Priorty Queue is empty");
		
		return arr[size - 1];
	}

	/**
	 * This method deletes the max element in the Simple Priority Queue. If the Priority 
	 * Queue is empty it throws a NoSuchElementException
	 * 
	 * @throws - NoSuchElementException if the Priority Queue is empty.
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException("Priorty Queue is empty");
		
		E max = arr[size - 1];
		size--;
		return max;
	}
	
	/**
	 * This private method helps the insert and contains method by finding the needed index
	 * using Binary Search.
	 * @param target - target is the target value that the Binary Search is looking for
	 * @param array - array is the array that the Binary Search is searching through.
	 * @return returns an index.
	 */
	private int binarySearch(E target, E[] array) {
		int beg = 0;
		int end = size;
		int mid = (beg + end) / 2;
		while(beg < end) {
			if(compare(array[mid], target) == 0) {
				return mid;
			}
			if(compare(array[mid], target) < 0) {
				beg = mid + 1;
			}
			else {
				end = mid;
			}
			mid = (end + beg) / 2;
		}
		return mid;
	}
	
	/**
	 * This private method compares two Generic Objects either using the comparator or
	 * natural comparison
	 * @param center - center is the first object that is being compared
	 * @param target - target is the second object that is being compared
	 * @return returns and int resulting from the comparison.
	 */
	private int compare(E center, E target) {
		if(comparator == null) {
			return ((Comparable<? super E>)center).compareTo(target);
		}
		else {
			return comparator.compare(target, center);
		}
		
	}
	
	/**
	 * This private helper method is used to shift elements to the right in the Simple
	 * Priority Queue.
	 * @param index - index is the starting value for the shift.
	 */
	private void shiftToRight(int index) {
		for(int i = size; i > index - 1; i--) {
			arr[i + 1] = arr[i];
		}
	}
	
	/**
	 * This method inserts a item into the Simple
	 * Priority Queue.
	 * @param item - item is the Object that is being inserted into the Simple
	 * Priority Queue.
	 */
	@Override
	public void insert(E item) {
		if(size == arr.length - 1) {	
			E[] temp = (E[]) new Object[size * 2];
			for(int i = 0; i < size; i++) {
				temp[i] = arr[i];
			}
			
			arr = temp;
		}
		
		if(size == 0) {
			arr[0] = item;
			size++;
			return;
		}
		
		int index = binarySearch(item, arr);
		
			
		shiftToRight(index);
		arr[index] = item;
		size++;
	}

	/**
	 * This method inserts all items in a collection using the insert method.
	 * @param coll - coll is a Collection of items.
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for(E item : coll) {
			insert(item);
		}
	}

	/**
	 * This method checks if item is in the Simple
	 * Priority Queue.
	 * @param item - item is the Object that is being searched for in the Simple
	 * Priority Queue.
	 * @return returns true if the Simple Priority Queue contains the Object and 
	 * false if it does not.
	 */
	@Override
	public boolean contains(E item) {
		if(isEmpty())
			return false;
		int index = binarySearch(item, arr);
		if(arr[index] != null && arr[index].equals(item)) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns the size of the Simple
	 * Priority Queue.
	 * @return returns an int which is the size 
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This method checks to see if the Simple Priority Queue is empty.
	 * @return returns true if the Simple Priority Queue is empty and false if the Simple
	 * Priority Queue has Objects in it.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * This method clears the Simple Priority Queue.
	 */
	@Override
	public void clear() {
		size = 0;
		
	}
}
