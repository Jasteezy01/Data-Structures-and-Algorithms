package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a Singly Linked List and implements a List interface
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version June 21, 2023
 * 
 * @param <E> - A Generic Type
 */
public class SinglyLinkedList<E> implements List<E> {

	private Node<E> head;
	private int size;

	/**
	 * The default constructor for a Singly Linked List
	 */
	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * This method inserts the element at the head of the Singly Linked List.
	 * @param element - element to be inserted
	 */
	@Override
	public void insertFirst(E element) {
		Node<E> newNode = new Node<>(element);
		newNode.next = head;
		head = newNode;
		size++;
	}

	/**
	 * This method inserts the element at the provided index of the Singly Linked List.
	 * @param element - element to be inserted
	 * @param index - the index that the element is being inserted
	 * @throws NoSuchElementException - if the index does not exists in the Singly Linked
	 * List 
	 */
	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if(index == 0) {
			insertFirst(element);
			return;
		}

		Node<E> newNode = new Node<>(element);
		Node<E> currentNode = getNode(index - 1);
		newNode.next = currentNode.next;
		currentNode.next = newNode;
		size++;
	}

	/**
	 * This method returns the head of the Singly Linked List
	 * @throws NoSuchElementException - if the Singly Linked List is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * This method returns the item at the given index
	 * @param index - the index of the item that is being returned
	 * @throws NoSuchElementException - if the index does not exists in the Singly Linked
	 * List 
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = getNode(index);
		return current.data;
	}

	/**
	 * This method deletes the head of the Singly Linked List
	 * @throws NoSuchElementException - if the Singly Linked List is empty
	 */
	@Override
	public E deleteFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<E> removedNode = head;
		head = head.next;
		size--;
		return removedNode.data;
	}

	/**
	 * This method deletes the element at the provided index of the Singly Linked List.
	 * @param index - the index that the element is being deleted
	 * @throws NoSuchElementException - if the index does not exists in the Singly Linked
	 * List 
	 */
	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = head;
		for(int i = 0; i < index - 1; i++) {
			temp = temp.next;
		}
		E ret = (E) temp.next.data;
		temp.next = temp.next.next;
		size--;
		return ret;
	}

	/**
	 * This method returns the index of the element that is passed in the Singly Linked
	 * List
	 * -1 is returned if element does not exist in the Singly Linked List
	 * @param element - this elements index is being searched for 
	 * @return the index of the element
	 */
	@Override
	public int indexOf(E element) {
		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (element.equals(current.data)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}
	
	/**
	 * This method returns the size of the Singly Linked List
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This method returns true if the Singly Linked List is empty and false if not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	/**
	 * This method clears the Singly Linked List
	 */
	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * This method converts the Singly Linked List into an Array
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		Node<E> current = head;
		int index = 0;
		while (current != null) {
			array[index] = current.data;
			current = current.next;
			index++;
		}
		return array;
	}

	/**
	 * This method sets the iterator for the Singly Linked List
	 */
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This method gets the Node at the index
	 * @param index - the index of the Node;
	 * @return - the Node at that index
	 */
	private Node<E> getNode(int index) {
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	/**
	 * This private class represents a Node.
	 * @author Sachin Jampala and Jadon Olson
	 * @version June 21, 2023
	 * @param <E> - A Generic Type
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next;

		/**
		 * This constructor uses the generic data parameter to set the data of this Node
		 * @param data - the data that is in the Node
		 */
		public Node(E data) {
			this.data = data;
			next = null;
		}
	}

	/**
	 * This private class represents a Linked List Iterator that is used to iterate through
	 * a Singly Linked List 
	 * This class implements the Iterator interface
	 * @author Sachin Jampala and Jadon Olson
	 * @version June 21, 2023
	 */
	private class LinkedListIterator implements Iterator<E> {
		private Node<E> current = null;
		private boolean nextCalled = false;
		private int index = 0;

		/**
		 * This method returns true if the current node has a next Node.
		 */
		@Override
		public boolean hasNext() {
			if(current == null)
				return head != null;
			return current.next != null;
		}

		/**
		 * This method returns the data in the next node
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No next element");
			}
			
			if(current == null) {
				current = head;
			} else {
				// last = current;
				current = current.next;
			}
				
			E data = current.data;
			nextCalled = true;
			index++;
			return data;
		}

		/**
		 * This method removes the node at the current index
		 */
		@Override
		public void remove() {
			if(!nextCalled)
				throw new IllegalStateException("Next has not been called");
			delete(index);
			index--;
			size--;
			nextCalled = false;
		}
	}
}
