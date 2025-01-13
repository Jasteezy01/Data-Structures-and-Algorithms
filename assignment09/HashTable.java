package assignment09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This generic class represents a Hash Table
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version July 12, 2023
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for values type
 */
public class HashTable<K, V> implements Map<K, V> {
	
	private ArrayList<LinkedList<MapEntry<K,V>>> table;
	private int size;
	private int capacity;
	public int collisionCount;
	
	public HashTable() {
		table = new ArrayList<>();
		capacity = 10;
		for(int i = 0; i < capacity; i++) {
			table.add(new LinkedList<MapEntry<K,V>>());
		}
		size = 0;
		collisionCount = 0;
	}

	/**
	 * Removes all mappings from this map.
	 *
	 * O(table length) for quadratic probing or separate chaining
	 */
	@Override
	public void clear() {
		table = new ArrayList<>();
		capacity = 10;
		for(int i = 0; i < capacity; i++) {
			table.add(new LinkedList<MapEntry<K,V>>());
		}
		size = 0;
		collisionCount = 0;
	}

	/**
	 * Determines whether this map contains the specified key.
	 *
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key - the key that is being checked in the Hash Table 
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		int index = Math.abs(key.hashCode()) % capacity;
		
		LinkedList<MapEntry<K,V>> temp = table.get(index);
		
		for(MapEntry<K,V> entry : temp) {
			if(entry.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether this map contains the specified value.
	 *
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 *
	 * @param value - The value that is being searched for in the Hash Table.
	 * @return true if this map contains one or more keys to the specified value,
	 * false otherwise
	 */
	@Override
	public boolean containsValue(V value) {
		for(int i = 0; i < table.size(); i++) {
			LinkedList<MapEntry<K,V>> temp = table.get(i);
			
			for(MapEntry<K,V> entry : temp) {
				if(entry.getValue().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the
	 * ordering of
	 * mapping in the list is insignificant.
	 *
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 *
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> ret = new ArrayList<MapEntry<K,V>>();
		
		for(int i = 0; i < table.size(); i++) {
			LinkedList<MapEntry<K,V>> temp = table.get(i);
			for(MapEntry<K,V> entry : temp) {
				ret.add(entry);
			}
		}
		return ret;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 *
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key - The key of the value to be returned.
	 * @return the value to which the specified key is mapped, or null if this
	 * map
	 * contains no mapping for the key
	 */
	@Override
	public V get(K key) {
		int index = Math.abs(key.hashCode()) % capacity;
		
		LinkedList<MapEntry<K,V>> temp = table.get(index);
		
		for(MapEntry<K,V> entry : temp) {
			if(entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Determines whether this map contains any mappings.
	 *
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @return true if this map contains no mappings, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value;
	 * otherwise adds the specified key-value pair.)
	 *
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key - The key of the map entry being added.
	 * @param value - The value of the map entry being added.
	 * @return the previous value associated with key, or null if there was no
	 * mapping for key
	 */
	@Override
	public V put(K key, V value) {
		if(size / capacity >= 9) {
			resize();
		}
		int index = Math.abs(key.hashCode()) % capacity;
		
		
		if(containsKey(key)) {
			LinkedList<MapEntry<K,V>> temp = table.get(index);
			for(MapEntry<K,V> entry : temp) {
				if(entry.getKey().equals(key)) {
					V ret = entry.getValue();
					entry.setValue(value);
					return ret;
				}
			}
		}
		size++;
		MapEntry me = new MapEntry(key,value);
		LinkedList<MapEntry<K,V>> temp = table.get(index);
		if(!temp.isEmpty())
			collisionCount++;
		temp.add(me);
		
		return null;
	}

	/**
	 * This method resizes the Hash Table when the load factor is over 9.0.
	 */
	private void resize() {
		List<MapEntry<K,V>> list = entries();
		
		table = new ArrayList<>();
		capacity = capacity * 2;
		for(int i = 0; i < capacity; i++) {
			table.add(new LinkedList<MapEntry<K,V>>());
		}
		size = 0;
		for(MapEntry<K,V> entry : list) {
			put(entry.getKey(), entry.getValue());
		}
		
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 *
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key - The key of the map entry being removed.
	 * @return the previous value associated with key, or null if there was no
	 * mapping for key
	 */
	@Override
	public V remove(K key) {
		int index = Math.abs(key.hashCode()) % capacity;
		MapEntry<K,V> me = null;
		V val = null;
		LinkedList<MapEntry<K,V>> temp = table.get(index);
		
		for(MapEntry<K,V> entry : temp) {
			if(entry.getKey().equals(key)) {
				val = entry.getValue();
				me = entry;
				break;
			}
		}
		size--;
		temp.remove(me);
		return val;
	}

	/**
	 * Determines the number of mappings in this map.
	 *
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @return the number of mappings in this map
	 */
	@Override
	public int size() {
		return size;
	}

}
