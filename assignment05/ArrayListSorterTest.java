package assignment05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListSorterTest {

	ArrayListSorter als;
	ArrayList<Integer> arrList;
	
	@BeforeEach
	void setUp(){
		als = new ArrayListSorter();
	}

	@Test
	void testGenerateAscending() {
		arrList = als.generateAscending(10);
		assertEquals(1, arrList.get(0));
	}
	
	@Test
	void testGenerateDescending() {
		arrList = als.generateDescending(10);
		assertEquals(10, arrList.get(0));
	}
	
	@Test
	void testGeneratePermuted() {
		arrList = als.generatePermuted(10);
		assertEquals(10, arrList.size());
	}
	
	@Test
	void testOneElementQuicksort() {
		arrList = new ArrayList<>();
		arrList.add(1);
		als.quicksort(arrList);
		assertEquals(1, arrList.get(0));
	}
	
	@Test
	void testTwoElementQuicksort() {
		arrList = new ArrayList<>();
		arrList.add(7);
		arrList.add(1);
		als.quicksort(arrList);
		assertEquals(1, arrList.get(0));
		assertEquals(7, arrList.get(1));
	}
	
	@Test
	void testMergesortSmall() {
		arrList = als.generatePermuted(10);
		als.mergesort(arrList);
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testMergeSort() {
		arrList = als.generatePermuted(10);
		als.mergesort(arrList);
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testMergeSortSorted() {
		arrList = als.generateAscending(500);
		als.mergesort(arrList);
		for(int i = 0; i < 500; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testMergeSortReversed() {
		arrList = als.generateDescending(15);
		als.mergesort(arrList);
		for(int i = 0; i < 15; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testQuickSort() {
		arrList = als.generatePermuted(500);
		als.quicksort(arrList);
		for(int i = 0; i < 500; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testQuickSortSorted() {
		arrList = als.generateAscending(500);
		als.quicksort(arrList);
		for(int i = 0; i < 500; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testQuickSortReversed() {
		arrList = als.generateDescending(500);
		als.quicksort(arrList);
		for(int i = 0; i < 500; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testQuickSortSmall() {
		arrList = als.generatePermuted(10);
		als.quicksort(arrList);
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testInsertionSort() {
		arrList = als.generatePermuted(10);
		als.mergesort(arrList);
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testInsertionSortSorted() {
		arrList = als.generateAscending(10);
		als.mergesort(arrList);
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testInsertionSortReversed() {
		arrList = als.generateDescending(10);
		als.mergesort(arrList);
		for(int i = 0; i < 10; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testNegativeMerge() {
		arrList = new ArrayList<>();
		for(int j = 0; j < 100; j++)
			arrList.add(-(j+1));
		als.mergesort(arrList);
		int k = 0;
		for(int i = 100; i > 0; i--) {
			assertEquals(-i, arrList.get(k));
			k++;
		}
	}
	
	@Test
	void testNegativeQuick() {
		arrList = new ArrayList<>();
		for(int j = 0; j < 100; j++)
			arrList.add(-(j+1));
		als.quicksort(arrList);
		int k = 0;
		for(int i = 100; i > 0; i--) {
			assertEquals(-i, arrList.get(k));
			k++;
		}
	}

	@Test
	void testNegativeInsertion() {
		arrList = new ArrayList<>();
		for(int j = 0; j < 10; j++)
			arrList.add(-(j+1));
		als.mergesort(arrList);
		int k = 0;
		for(int i = 10; i > 0; i--) {
			assertEquals(-i, arrList.get(k));
			k++;
		}
	}
	
	@Test
	void testMergeSortString() {
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("a");
		stringList.add("b");
		stringList.add("c");
		stringList.add("d");
		stringList.add("e");
		stringList.add("f");
		stringList.add("g");
		Collections.shuffle(stringList);
		als.mergesort(stringList);
		assertEquals("a", stringList.get(0));
	}
	
	@Test
	void testMergeSortDuplicates() {
		int count = 0;
		arrList = als.generatePermuted(22);
		for(int j = 0; j < 10; j++)
			arrList.add(16);
		
		for(int i = 0; i < arrList.size(); i++) {
			if(arrList.get(i).compareTo(16) == 0) {
				count++;
			}
		}
		als.mergesort(arrList);
		assertEquals(11, count);
	}
	
	@Test
	void testQuickSortDuplicates() {
		int count = 0;
		arrList = als.generatePermuted(22);
		for(int j = 0; j < 10; j++)
			arrList.add(16);
		
		for(int i = 0; i < arrList.size(); i++) {
			if(arrList.get(i).compareTo(16) == 0) {
				count++;
			}
		}
		als.quicksort(arrList);
		assertEquals(11, count);
	}
	
	@Test
	void testMergeSortAscending() {
		arrList = als.generateAscending(100);
		als.mergesort(arrList);
		for(int i = 0; i < 100; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testMergeSortDescending() {
		arrList = als.generatePermuted(100);
		als.mergesort(arrList);
		for(int i = 0; i < 100; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testQuickSortAscending() {
		arrList = als.generateAscending(100);
		als.quicksort(arrList);
		for(int i = 0; i < 100; i++)
			assertEquals(i+1, arrList.get(i));
	}
	
	@Test
	void testQuickSortDescending() {
		arrList = als.generatePermuted(100);
		als.quicksort(arrList);
		for(int i = 0; i < 100; i++)
			assertEquals(i+1, arrList.get(i));
	}
}
