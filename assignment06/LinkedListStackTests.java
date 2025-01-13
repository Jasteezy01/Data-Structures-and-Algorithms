package assignment06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListStackTests {
	
	LinkedListStack lls;
	
	@BeforeEach
	void setUp() throws Exception {
		lls = new LinkedListStack<>();
	}
	
	@Test
	void testClear() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		assertEquals(3, lls.size());
		lls.clear();
		assertEquals(0, lls.size());
	}
	
	@Test
	void testIsEmpty() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		lls.clear();
		assertTrue(lls.isEmpty());
	}
	
	@Test
	void testIsEmpty1() {
		assertTrue(lls.isEmpty());
	}
	
	@Test
	void testIsEmpty2() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		lls.pop();
		lls.pop();
		lls.pop();
		assertTrue(lls.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		assertFalse(lls.isEmpty());
	}
	
	@Test
	void testPeek() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		assertEquals(3,lls.peek());
	}
	
	@Test
	void testPeekEmpty() {
		assertThrows(NoSuchElementException.class, () -> {lls.peek();});
	}
	
	@Test
	void testPop() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		
		assertEquals(3, lls.pop());
	}
	
	@Test
	void testPopEmpty() {
		lls.push(1);
		lls.push(2);
		lls.push(3);
		lls.clear();
		assertThrows(NoSuchElementException.class, () -> {lls.pop();});
	}
}
