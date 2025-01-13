package assignment10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version July 19, 2023
 *
 * @param <E> - A Generic Type.
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private Object[] arr;
	private Comparator cmp;
	private int size;

	/**
	 * This default constructor constructs a BinaryMaxHeap.
	 */
	public BinaryMaxHeap() {
		arr = new Object[6];
		cmp = null;
		size = 0;
	}

	/**
	 * This constructor is takes a Comparator and constructs a BinaryMaxHeap
	 * using the Comparator
	 * @param cmp - The Comparator that is used to order the Heap
	 */
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		arr = new Object[6];
		this.cmp = cmp;
		size = 0;
	}

	/**
	 * This constructor is takes a list and constructs a BinaryMaxHeap
	 * using the List 
	 * @param list - The list that the BinaryMaxHeap is built from
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		arr = new Object[list.size()];
		cmp = null;
		size = list.size();
		buildHeap(list);
	}

	/**
	 * This constructor is takes a list and a Comparator and constructs a BinaryMaxHeap
	 * using the List and the Comparator
	 * @param list - The list that the BinaryMaxHeap is built from
	 * @param cmp - The Comparator that is used to order the Heap
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		arr = new Object[list.size()];
		this.cmp = cmp;
		size = list.size();
		buildHeap(list);
	}

	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 *
	 * @param item - The item that is being added
	 */
	@Override
	public void add(E item) {
		if(size == arr.length - 1) {
			increaseArray();
		}
		
		arr[size] = item;
		percolateUp(size);
		size++;
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 *
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if(size == 0) {
			throw new NoSuchElementException("Heap is empty");
		}

		return (E) arr[0];
	}

	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 *
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if(size == 0) {
			throw new NoSuchElementException("Heap is empty");
		}

		E temp = (E) arr[0];
		arr[0] = arr[size - 1];
		arr[size - 1] = null;
		size--;
		percolateDown(0);
		return temp;
	}

	/**
	 * Returns the number of items in this priority queue.
	 * O(1)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	@Override
	public void clear() {
		arr = new Object[6];
		size = 0;
	}

	/**
	 * Creates and returns an array of the items in this priority queue,
	 * in the same order they appear in the backing array.
	 * O(N)
	 */
	@Override
	public Object[] toArray() {
		Object[] ret = new Object[size];
		for(int i = 0; i < size; i++) {
			ret[i] = arr[i];
		}
		return ret;
	}
	
	/**
	 * This method builds a BinaryMaxHeap from the given List
	 * @param list - The List used to build the BinaryMaxHeap.
	 */
	private void buildHeap(List list) {
		arr = list.toArray();
		int index = size / 2 - 1;
		for(int i = index; i >= 0; i--) {
			percolateDown(i);
		}
	}

	/**
	 * This method increases the size of the backing array.
	 */
	private void increaseArray() {
		Object[] temp = new Object[arr.length * 2];
		for(int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}


	/**
	 * This method compares the objects at 2 different indices.
	 * 
	 * It returns a positive number if the first Object is larger than the second, 
	 * a negative number if the second is larger than the first, and 0 if 
	 * they are the same.
	 * @param o1 - The index of the first Object.
	 * @param o2 - The index of the second Object.
	 * @return The integer that the comparison resulted in.
	 */
	private int innerCmp(int o1, int o2) {
		if(arr[o1] == null && arr[o2] == null)
			return 0;
		if(arr[o1] == null)
			return -1;
		if(arr[o2] == null)
			return 1;
		if(cmp == null)
			return ((Comparable) arr[o1]).compareTo((Comparable) arr[o2]);
		return cmp.compare(arr[o1], arr[o2]);
	}

	/**
	 * This method percolates up from the given index.
	 * @param index - The index that percolation starts at.
	 */
	private void percolateUp(int index){
	    while ((index + 1) / 2 - 1 >= 0 && innerCmp(index, (index + 1) / 2 - 1) > 0) {
	        swap(index, (index + 1) / 2 - 1);
	        index = (index + 1) / 2 - 1;
	    }
	}
	
	/**
	 * This method percolates down from the given index.
	 * @param index - The index that percolation starts at.
	 */

	private void percolateDown(int index) {
	    int leftChild = index * 2 + 1;
	    int rightChild = index * 2 + 2;
	    int largest = index;

	    if (leftChild < size && innerCmp(index, leftChild) < 0) {
	        largest = leftChild;
	    }

	    if (rightChild < size && innerCmp(largest, rightChild) < 0) {
	        largest = rightChild;
	    }

	    if (largest != index) {
	        swap(index, largest);
	        percolateDown(largest);
	    }
	}


	/**
	 * This method swaps two elements at the given indices.
	 * @param index - The first index
	 * @param i - The second index.
	 */
	private void swap(int index, int i) {
		E temp = (E) arr[index];
		arr[index] = arr[i];
		arr[i] = temp;
	}




}
