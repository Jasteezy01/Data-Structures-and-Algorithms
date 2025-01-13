package assignment10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {

	BinaryMaxHeap<Integer> bmh;
	BinaryMaxHeap<String> stringHeap;
	Comparator<String> cmp;
	FindKLargest fkl;
	@BeforeEach
	void setUp() throws Exception {
		bmh = new BinaryMaxHeap<>();
		fkl = new FindKLargest();
		cmp = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				
				return o2.compareTo(o1);
			}
		};
		
		stringHeap = new BinaryMaxHeap<>(cmp);
	}

	@Test
	void testAdd() {
		bmh.add(3);
		assertEquals(1, bmh.size());
	}
	
	@Test
	void testAddString() {
		BinaryMaxHeap<String> bhm = new BinaryMaxHeap<>();
		bhm.add("Hello");
		bhm.add("World");
		assertEquals("World", bhm.extractMax());
	}
	
	@Test
	void testAdd1() {
		bmh.add(3);
		bmh.add(1);
		bmh.add(2);
		assertEquals(3, bmh.size());
	}

	@Test
	void testPeek() {
		bmh.add(3);
		bmh.add(1);
		bmh.add(2);
		assertEquals(3, bmh.peek());
	}

	@Test
	void testExtractMax() {
		bmh.add(3);
		bmh.add(1);
		bmh.add(2);
		assertEquals(3, bmh.extractMax());
	}
	
	@Test
	void testExtractMaxandPeek() {
		bmh.add(3);
		bmh.add(1);
		bmh.add(2);
		bmh.extractMax();
		assertEquals(2, bmh.peek());
	}

	@Test
	void testSize() {
		bmh.add(3);
		bmh.add(1);
		bmh.add(2);
		assertEquals(3, bmh.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(bmh.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse() {
		bmh.add(1);
		assertFalse(bmh.isEmpty());
	}

	@Test
	void testClear() {
		bmh.add(1);
		bmh.add(2);
		bmh.clear();
		assertTrue(bmh.isEmpty());
	}

	@Test
	void testToArray() {
		bmh.add(3);
		bmh.add(1);
		bmh.add(2);
		Object[] arr = bmh.toArray();
		assertEquals(3, arr.length);
	}
	
	@Test
	void testList() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		bmh = new BinaryMaxHeap(list);
		assertEquals(3,bmh.size());
	}
	
	@Test
	void testList1() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		bmh = new BinaryMaxHeap(list);
		assertEquals(3,bmh.extractMax());
	}

	@Test
	void testAddMany() {
		for(int i = 0; i < 100; i++) {
			bmh.add(i);
			if(i%2 == 0)
				bmh.extractMax();
		}
		
		assertEquals(99, bmh.extractMax());
		
		for(int i = 100; i < 110; i++) {
			bmh.add(i);
			if(i%2 == 0)
				bmh.extractMax();
		}
		
		assertEquals(54, bmh.size());
		
	}
	
	@Test
	void testReverseCMP() {
		stringHeap.add("Neon");
		stringHeap.add("Fish");
		stringHeap.add("Zebra");
		stringHeap.add("Apple");
		stringHeap.add("Xylophone");

		assertEquals("Apple", stringHeap.extractMax());
		
		stringHeap.add("Flow");
		
		assertEquals("Fish", stringHeap.extractMax());
		
		stringHeap.add("Bear");
		
		assertEquals("Bear", stringHeap.extractMax());
	}
	
	@Test
	void testReverseCMPKth() {
		List<String> list = new ArrayList<>();
		list.add("Neon");
		list.add("Fish");
		list.add("Zebra");
		list.add("Apple");
		list.add("Xylophone");
		
		List<String> arr = FindKLargest.findKLargestHeap(list, 3, cmp);
		
		assertEquals("Apple", arr.get(0));
		assertEquals("Fish", arr.get(1));
		assertEquals("Neon", arr.get(2));
	}
	
	@Test
	void testKthThrows() {
		List<String> list = new ArrayList<>();
		list.add("Neon");
		list.add("Fish");
		list.add("Zebra");
		list.add("Apple");
		list.add("Xylophone");
		

		
		assertThrows(IllegalArgumentException.class, () -> {fkl.findKLargestHeap(list, 612);});
		
	}
	
	@Test
	void testListOfTen() {
		List<Integer> list = new ArrayList<>();	
		for(int i = 0; i < 10; i++)
			list.add(i);
		bmh = new BinaryMaxHeap<Integer>(list);
		
		assertEquals(9, bmh.extractMax());
	}
	
	@Test
	void testShuffledListof1000() {
		List<Integer> list = new ArrayList<>();	
		for(int i = 0; i < 1000; i++)
			list.add(i+1);
		Collections.shuffle(list);
		bmh = new BinaryMaxHeap<Integer>(list);
		for(int i = 1000; i > 0; i--)
			assertEquals(i, bmh.extractMax());
	}
	
	
	
}
