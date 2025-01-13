package assignment04;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class LargestNumberSolverTest {

	LargestNumberSolver lns;
	
	@Test
	public void testFindLargestNumber() {
		Integer[] arr = {1, 2, 3, 4, 5};
		assertEquals(new BigInteger("54321"),lns.findLargestNumber(arr));
	}
	
	@Test
	public void testFindLargestNumberTwo() {
		Integer[] arr = {11, 67, 79, 7, 22, 13};
		assertEquals(new BigInteger("79767221311"),lns.findLargestNumber(arr));
	}
	
	@Test
	public void testInsertionSort() {
		Integer[] arr = {23, 4, 5, 17, 200, -28};
		lns.insertionSort(arr, (a, b) -> {return a.compareTo(b);});
		assertEquals(-28, arr[0]);
	}
	
	@Test
	public void testFindLargestOnEmpty() {
		Integer[] arr = new Integer[10];
		assertEquals(new BigInteger("0"), lns.findLargestNumber(arr));
	}
	
	@Test
	public void testFindLargestIntSmall() {
		Integer[] arr = {1, 7, 9};
		assertEquals(971,lns.findLargestInt(arr));
	}
	
	@Test
	public void testFindLargestLong() {
		Integer[] arr = new Integer[10];
		for(int i = 0; i < 10; i++)
			arr[i] = i * 10;
		
		assertEquals(9080706050403020100l,lns.findLargestLong(arr));
	}
	
	@Test
	public void testFindLargestIntMedium() {
		Integer[] arr = new Integer[5];
		for(int i = 0; i < 5; i++)
			arr[i] = i * 10;
		
		assertEquals(403020100,lns.findLargestInt(arr));
	}
	
	@Test
	public void testFindLargestSingle() {
		Integer[] arr = {23};
		
		assertEquals(23,lns.findLargestInt(arr));
	}
	
	@Test
	public void testReadFile() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		Integer[] arr = list.get(0);
		assertEquals(37, arr[0]);
	}
	
	@Test
	public void testReadFileFindMax() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		Integer[] arr = list.get(0);
		assertEquals(37319, lns.findLargestInt(arr));
	}
	
	@Test
	public void testReadFileFindMaxTwo() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		Integer[] arr = list.get(1);
		assertEquals(912, lns.findLargestInt(arr));
	}
	
	@Test
	public void testReadFileFindMaxThree() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		Integer[] arr = list.get(2);
		assertEquals(79332210, lns.findLargestInt(arr));
	}
	
	@Test
	public void testSum() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		assertEquals(new BigInteger("79370441"), lns.sum(list));
	}
	
	@Test
	public void testKthLargest() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		assertEquals(list.get(2), lns.findKthLargest(list, 0));
	}
	
	@Test
	public void testKthLargestTwo() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		assertEquals(list.get(0), lns.findKthLargest(list, 1));
	}
	
	@Test
	public void testKthLargestOutOfBounds() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/arrays.txt");
		assertThrows(IllegalArgumentException.class, () -> {lns.findKthLargest(list, 7);});
	}
	
	@Test
	public void testFileNotFound() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/doesntexist.txt");
		assertEquals(0, list.size());
	}
	
	@Test
	public void testIntOutOfRange() {
		Integer[] arr = new Integer[10];
		for(int i = 0; i < 10; i++)
			arr[i] = i * 10;
		assertThrows(OutOfRangeException.class,() -> {lns.findLargestInt(arr);});
	}
	
	@Test
	public void testLongOutOfRange() {
		Integer[] arr = new Integer[100];
		for(int i = 0; i < 100; i++)
			arr[i] = i * 100;
		assertThrows(OutOfRangeException.class,() -> {lns.findLargestLong(arr);});
	}
	
	@Test
	public void testAllSameNumber() {
		Integer[] arr = {1, 1, 1, 1, 1};
		assertEquals(11111l, lns.findLargestLong(arr));
	}
	
	@Test
	public void testReadLargeFile() {
		List<Integer[]> list = lns.readFile("/Users/jadonolson/Desktop/Eclipse/CS2420Summer/src/assignment04/integers.txt");
		assertEquals(903, list.size());
	}
	
	@Test
	public void testFindLargestNumberEmpty() {
		Integer[] arr = {};
		assertEquals(0, lns.findLargestInt(arr));
	}
	
	
}
