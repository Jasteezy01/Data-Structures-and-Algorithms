package assignment09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HashTableTest {

	HashTable<Integer,String> ht;
	
	@BeforeEach
	void setUp() {
		 ht = new HashTable();
	}

	@Test
	void testClear() {
		ht.put(3, "test");
		ht.clear();
		assertEquals(0,ht.size());
	}

	@Test
	void testContainsKey() {
		ht.put(3, "test");
		ht.put(3, "test1");
		ht.put(3, "test2");
		
		assertTrue(ht.containsKey(3));
	}
	
	@Test
	void testContainsKeyFalse() {
		ht.put(3, "test");
		ht.put(3, "test1");
		ht.put(3, "test2");
		
		assertFalse(ht.containsKey(2));
	}

	@Test
	void testContainsValue() {
		ht.put(3, "test");
		ht.put(3, "test1");
		ht.put(3, "test2");
		
		assertTrue(ht.containsValue("test2"));
	}
	
	@Test
	void testContainsValueFalse() {
		ht.put(3, "test");
		ht.put(3, "test1");
		ht.put(3, "test2");
		
		assertFalse(ht.containsValue("test"));
	}

	@Test
	void testEntries() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		
		List list = ht.entries();
		
		assertEquals(3,list.size());
	}
	
	@Test
	void testEntriesSameValue() {
		ht.put(1, "test");
		ht.put(2, "test");
		ht.put(3, "test");
		
		List list = ht.entries();
		
		assertEquals(3,list.size());
	}

	@Test
	void testGet1() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
			
		assertEquals("test1",ht.get(2));
	}
	
	@Test
	void testGet2() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
			
		assertEquals(null,ht.get(4));
	}
	
	@Test
	void testIsEmptyFalse1() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		
		ht.remove(1);
			
		assertFalse(ht.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse2() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
			
		assertFalse(ht.isEmpty());
	}
	
	@Test
	void testIsEmptyTrue1() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		
		ht.remove(1);
		ht.remove(2);
		ht.remove(3);
			
		assertTrue(ht.isEmpty());
	}
	
	@Test
	void testIsEmptyTrue2() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		
		ht.clear();
			
		assertTrue(ht.isEmpty());
	}
	
	@Test
	void testIsEmptyTrue3() {
		assertTrue(ht.isEmpty());
	}

	@Test
	void testPut1() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		
		assertEquals(3, ht.size());
	}
	
	@Test
	void testPut2() {
		for(int i = 0; i < 1000; i++) {
			ht.put(i, "test" + i);
		}
		
		assertEquals(1000, ht.size());
	}

	@Test
	void testRemove() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		
		List<MapEntry<Integer,String>> list = ht.entries();
		

		ht.remove(2);
		list = ht.entries();
		
		assertFalse(ht.containsValue("test1"));
	}

	@Test
	void testSize() {
		ht.put(1, "test");
		ht.put(2, "test1");
		ht.put(3, "test2");
		ht.put(4, "test3");
		ht.put(5, "test4");
		
		assertEquals(5, ht.size());
	}
	
	@Test
	void testCollisions() {
		HashTable<StudentBadHash, Integer> bht = new HashTable<>();
		bht.put(new StudentBadHash(17, "A", "B"), 20);
		bht.put(new StudentBadHash(12, "B", "B"), 20);
		bht.put(new StudentBadHash(13, "C", "B"), 20);
		
		System.out.print(bht.collisionCount);
	}

}
