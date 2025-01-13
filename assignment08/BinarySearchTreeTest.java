package assignment08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> bst;
	
	@BeforeEach
	void setUp() throws Exception {
		bst = new BinarySearchTree<>();
	}

	@Test
	void testAdd() {
		bst.add(7);
		assertEquals(1, bst.size());
	}

	@Test
	void testAddAll() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		assertEquals(5, bst.size());
	}

	@Test
	void testClear() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		bst.clear();
		assertEquals(0, bst.size());
	}

	@Test
	void testContains() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		assertTrue(bst.contains(3));
	}

	@Test
	void testContainsAll() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			arrList.add(i+1);
		}
		
		ArrayList<Integer> arrList2 = new ArrayList<>();
		for(int i = 0; i < 100; i+= 10) {
			arrList2.add(i+1);
		}
		
		bst.addAll(arrList);
		
		assertTrue(bst.containsAll(arrList2));
	}

	@Test
	void testFirst() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		assertEquals(1, bst.first());
	}
	
	@Test
	void testEmptyFirst() {
		assertThrows(NoSuchElementException.class, () -> {bst.first();});
	}
	
	@Test
	void testFirstCleared() {
		for(int i = 10; i < 100; i++)
			bst.add(i*10);
		
		bst.clear();
		assertThrows(NoSuchElementException.class, () -> {bst.first();});
	}

	@Test
	void testIsNotEmpty() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		assertFalse(bst.isEmpty());
	}
	
	@Test
	void testIsEmpty() {
		
		assertTrue(bst.isEmpty());
	}
	
	@Test
	void testIsEmpty2() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		bst.clear();
		assertTrue(bst.isEmpty());
	}

	@Test
	void testLast() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		
		assertEquals(5, bst.last());
	}

	@Test
	void testEmptyLast() {
		assertThrows(NoSuchElementException.class, () -> {bst.last();});
	}
	
	@Test
	void testLastCleared() {
		for(int i = 10; i < 100; i++)
			bst.add(i*10);
		
		bst.clear();
		assertThrows(NoSuchElementException.class, () -> {bst.last();});
	}
	
	@Test
	void testRemove() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			arrList.add(i+1);
		}
		
		bst.addAll(arrList);
		bst.remove(4);
		
		assertEquals(4, bst.size());
	}

	@Test
	void testRemoveAll() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			arrList.add(i+1);
		}
		
		ArrayList<Integer> removeList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			removeList.add(i+1);
		}
		
		bst.addAll(arrList);
		bst.removeAll(removeList);
		
		assertEquals(6, bst.first());
	}

	@Test
	void testSize() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			arrList.add(i+1);
		}
		
		ArrayList<Integer> removeList = new ArrayList<>();
		for(int i = 0; i < 45; i++) {
			removeList.add(i+1);
		}
		
		bst.addAll(arrList);
		bst.removeAll(removeList);
		
		assertEquals(955, bst.size());
	}

	@Test
	void testToArrayList() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			arrList.add(i+1);
		}
		bst.addAll(arrList);
		
		ArrayList<Integer> tester = bst.toArrayList();
		
		assertEquals(1000, tester.size());
	}
	
	@Test
	void testToArrayListTwo() {
		ArrayList<Integer> arrList = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			arrList.add(i+1);
		}
		bst.addAll(arrList);
		
		ArrayList<Integer> tester = bst.toArrayList();
		
		assertEquals(1, tester.get(0));
	}
	
	@Test
	void testToArrayListAdd() {
		for(int i = 0; i < 50; i++) {
			bst.add(i+1);
		}
				
		ArrayList<Integer> tester = bst.toArrayList();
		
		for(int i = 0; i < 50; i++)
			assertEquals(i+1, tester.get(i));
	}
	
	@Test
	void testToArrayListAddSmall() {
		bst.add(5);
		bst.add(2);
		bst.add(7);
		bst.add(3);
		bst.add(1);
		bst.add(6);
		bst.add(8);
		bst.add(4);
		bst.add(9);
		bst.add(10);
				
		ArrayList<Integer> tester = bst.toArrayList();
		
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, tester.get(i));
	}
	
	@Test
	void testAddThenRemove() {
		for(int i = 0; i < 50; i++) {
			bst.add(i+1);
		}
		
		for(int i = 0; i < 50; i++) {
			if(!bst.remove(i+1))
				fail("Unable to remove" + i);
		}
		
		
		assertEquals(0, bst.size());
	}
	
	@Test
	void testRemoveTwo() {
		for(int i = 0; i < 50; i++) {
			bst.add(i+1);
		}
		
		for(int i = 0; i < 25; i++) {
			if(!bst.remove(i+1))
				fail("Unable to remove" + i);
		}
		
		
		assertEquals(26, bst.first());
	}
	
	@Test
	void testRemoveLast() {
		for(int i = 0; i < 50; i++) {
			bst.add(i+1);
		}
		
		bst.remove(50);
		
		
		assertEquals(49, bst.last());
	}

}
