package assignment06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTests {

	SinglyLinkedList<Integer> sll;

	@BeforeEach
	void setUp() throws Exception {
		sll = new SinglyLinkedList<>();
	}

	@Test
	void testSize () {
		assertEquals(0, sll.size());
	}

	@Test
	void testInsertFirst() {
		sll.insertFirst(7);

		assertEquals(1, sll.size());
	}

	@Test
	void testGetFirst() {
		sll.insertFirst(7);

		assertEquals(7, sll.getFirst());
	}

	@Test
	void testGetFirst2() {
		sll.insert(0, 2);
		sll.insert(1, 4);
		sll.insert(2, 6);

		assertEquals(2, sll.getFirst());
	}

	@Test
	void testInsertThree() {
		sll.insert(0, 2);
		sll.insert(1, 4);
		sll.insert(2, 6);

		assertEquals(3, sll.size());
	}

	@Test
	void testGet() {
		sll.insert(0, 2);
		sll.insert(1, 4);
		sll.insert(2, 6);

		assertEquals(6, sll.get(2));
	}

	@Test
	void testRemoveFirst() {
		sll.insert(0, 2);
		sll.insert(1, 4);
		sll.insert(2, 6);
		sll.deleteFirst();
		assertEquals(2, sll.size());
	}

	@Test
	void testRemoveFirst2() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		sll.deleteFirst();
		assertEquals(40, sll.getFirst());
	}

	@Test
	void testAddFirstMultiple() {
		for(int i = 1; i <= 10; i ++) {
			sll.insertFirst(i*100);
		}
		assertEquals(1000, sll.getFirst());
	}
	
	@Test
	void testAddFirstMultiple2() {
		for(int i = 1; i <= 10; i ++) {
			sll.insertFirst(i*100);
		}
		sll.deleteFirst();
		assertEquals(900, sll.getFirst());
	}
	
	@Test
	void testInsertOutOfBounds() {
		sll.insert(0, 7);
		assertThrows(IndexOutOfBoundsException.class, ()->{sll.insert(4, 20);});
	}

	@Test
	void testGetFirstOnEmpty() {
		assertThrows(NoSuchElementException.class, ()->{sll.getFirst();});
	}
	
	@Test
	void testGetOutOfBounds() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		assertThrows(IndexOutOfBoundsException.class, ()->{sll.get(7);});
	}
	
	@Test
	void testDeleteFirstOnEmpty() {
		assertThrows(NoSuchElementException.class, ()->{sll.deleteFirst();});
	}
	
	@Test
	void testDelete() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		sll.delete(2);
		assertEquals(3, sll.size());
	}
	
	@Test
	void testDelete2() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		sll.delete(2);
		sll.delete(1);
		sll.deleteFirst();
		assertEquals(5, sll.getFirst());
	}
	
	@Test
	void testDeleteOutOfBounds() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		assertThrows(IndexOutOfBoundsException.class, ()->{sll.delete(7);});
	}
	
	@Test
	void testIndexOf() {
		for(int i = 1; i <= 10; i ++) {
			sll.insertFirst(i*100);
		}
		assertEquals(7, sll.indexOf(300));
	}
	
	@Test
	void testIndexOfLast() {
		for(int i = 1; i <= 10; i ++) {
			sll.insertFirst(i*100);
		}
		assertEquals(9, sll.indexOf(100));
	}
	
	@Test
	void testIndexOfNotFound() {
		for(int i = 1; i <= 10; i ++) {
			sll.insertFirst(i*100);
		}
		assertEquals(-1, sll.indexOf(23));
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(sll.isEmpty());
	}
	
	@Test
	void testClear() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		assertEquals(4, sll.size());
		sll.clear();
		assertEquals(0, sll.size());
		assertThrows(NoSuchElementException.class, ()->{sll.getFirst();});
	}
	
	@Test
	void testToArray() {
		for(int i = 1; i <= 10; i ++) {
			sll.insertFirst(i*100);
		}
		Object[] arr = sll.toArray();
		assertEquals(500, arr[5]);
	}
	
	@Test
	void testHasNext() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		assertTrue(sll.iterator().hasNext());
	}
	
	@Test
	void testNext() {
		sll.insert(0, 2);
		sll.insert(1, 40);
		sll.insert(2, 10);
		sll.insert(3, 5);
		assertEquals(2, sll.iterator().next());
	}
}
