package root;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import math.Primes;

public class MyHashMap<K,V> implements Map<K,V>{
	public ArrayList<Map.Entry<K,V>>[] hashmap;
	public int capacity;
	public int size;
	public double loadfactor = 0.5;
	
	//////////////////////////////
	//Constructors
	//////////////////////////////
	/**
	 * Create a default <code>Map</code> with size capacity 11.
	 */
	public MyHashMap() {
		this.capacity = 11;
		this.hashmap = (ArrayList<Map.Entry<K,V>>[]) new ArrayList[this.capacity];
		for(int i = 0; i<this.capacity; i++) {
			hashmap[i] = new ArrayList<Map.Entry<K,V>>();
		}
		this.size = 0;
	}
	
	/**
	 * Creates a map with a given size capacity.
	 * @param capacity an integer to determine initial capacity.
	 */
	public MyHashMap(int capacity) {
		this.capacity = capacity;
		this.hashmap = (ArrayList<Map.Entry<K,V>>[]) new ArrayList[this.capacity];
		for(int i = 0; i<this.capacity; i++) {
			hashmap[i] = new ArrayList<Map.Entry<K,V>>();
		}
		this.size = 0;
	}
	
	
	//////////////////////////////
	//Standard methods
	//////////////////////////////
	/**
	 * Insert a new entry into the map. The Value object is mapped to its Key. If the Key already exists,
	 * its Value will be replaced with the new one.
	 * @param key a unique identifier for the Value
	 * @param value the object that will get mapped
	 * @return if the already exists, the old Value is returned
	 */
	@Override
	public V put(K key, V value) {
		//0. Safety checks.
		if(key==null) return null;
		
		//1. Compute the index using hashcode and compression.
		int index = this.getKeyIndex(key);
		
		//2. Check to see if object already exists.
		V oldMapping = null;
		if(this.containsKey(key)) {
			oldMapping = this.get(key);
			ArrayList<Map.Entry<K,V>> bucket = hashmap[index];
			bucket.remove(oldMapping);
			size--;
		}
		
		//3. Check to see if resizing is needed.
		if(size/capacity > loadfactor) {
			resize();
		}
		
		//4. Add the object to the map.
		Map.Entry<K,V> node = new AbstractMap.SimpleEntry<K,V>(key,value);
		hashmap[index].add(node);
		size++;
		
		//System.out.println("Adding at index: "+index);
		return oldMapping;
	}
	
	/**
	 * Remove the Value object from the map. Return the removed entry, or <code>null</code> if it does 
	 * not exist.
	 * @param key the key for the object that will be removed
	 * @return the removed object, or null if it does not exist.
	 */
	@Override
	public V remove(Object key) {
		//0. Safety checks.
		if(key==null) return null;
		
		//1. Find location of the object (the bucket).
		int index = this.getKeyIndex(key);
		ArrayList<Map.Entry<K,V>> bucket = hashmap[index];

		//2. Iterate through the bucket to find object.
		Map.Entry<K,V> nodeToRemove = null;
		if(bucket.size()>0) {
			for(Map.Entry<K,V> n : bucket) {
				if(n.getKey()==key) {
					nodeToRemove = n;
					break;
				}
			}
		}
		
		//3. Remove the object from the bucket if it exists.
		if(nodeToRemove!=null) {
			bucket.remove(nodeToRemove);
			size--;
			return nodeToRemove.getValue();
		} else return null;
	}
	
	/**
	 * Return the Value object located mapped to the Key if it exists, or <code>null</code> if it does not.
	 * @param key the Key object used to search the <code>Map</code>
	 * @return the Value object mapped to Key, or null if it does not exist
	 */
	@Override
	public V get(Object key) {
		//0. Safety checks.
		if(key==null) return null;
		
		//1. Find where the object is stored (the bucket).
		int index = getKeyIndex(key);
		ArrayList<Map.Entry<K,V>> bucket = hashmap[index];
		
		//2. Iterate the bucket to find 
		if( bucket.size()>0 ) {
			for(Map.Entry<K,V> n : bucket) {
				if(n.getKey()==key) {
					return n.getValue();
				}
			}
		}
		
		//3. Return null if the object is not found.
		return null;
	}
	
	/**
	 * Returns <code>true</code> if the Key maps to an Object, or <code>false</code> otherwise.
	 * @param key the Key object used to search the <code>Map</code>
	 * @return a boolean stating if the Key maps to a Value object
	 */
	@Override
	public boolean containsKey(Object key) {
		//0. Safety checks.
		if(key==null) return false;
		
		//1. Find location of object (the bucket).
		int index = this.getKeyIndex(key);
		ArrayList<Map.Entry<K,V>> bucket = hashmap[index];
		
		//2. Iterate through the bucket to find object.
		boolean flag = false;		
		if(bucket.size()>0) {
			for(Map.Entry<K,V> node : bucket) {
				if(node.getKey() == key) {
					flag = true;
					break;
				}
			}
		}
		
		//3. Return true or false.
		return flag;
	}
	
	/**
	 * Returns <code>true</code> if the Value object exists in the map, or <code>false</code> otherwise.
	 * @param value the Value object that is searched in the <code>Map</code>
	 * @return a boolean stating if the Value object is mapped
	 */
	@Override
	public boolean containsValue(Object value) {
		//0. No safety checks
		
		//1. Iterate through the bucket to find object.
		boolean flag = false;
		for(ArrayList<Map.Entry<K,V>> arraylist : hashmap) {
			for(Map.Entry<K,V> node : arraylist) {
				if(node.getValue().equals(value)) return true; 
			}
		}
		
		//2. Return the object.
		return flag;
	}
	
	
	//////////////////////////////
	//Extra methods
	//////////////////////////////
	/**
	 * Returns a set of all the keys for entries in the map.
	 * @return a set containing all the existing keys
	 */
	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for(ArrayList<Map.Entry<K,V>> arraylist : hashmap) {
			for(Map.Entry<K,V> node : arraylist) {
				set.add(node.getKey());
			}
		}
		return set;
	}

	/**
	 * Returns a set containing all the entries as <code>Map.Entries</code>.
	 * @return a set containing all existing <code>Map.Entries</code>
	 */
	@Override
	public Set<Map.Entry<K,V>> entrySet() {
		Set<Map.Entry<K,V>> set = new HashSet<Map.Entry<K,V>>();
		for(ArrayList<Map.Entry<K,V>> arraylist : hashmap) {
			for(Map.Entry<K,V> node : arraylist) {
				set.add(node);
			}
		}
		return set;
	}
	
	/**
	 * Returns a set of all the entries in a map.
	 * @return a set containing all existing entries
	 */
	@Override
	public Collection<V> values() {
		Collection<V> set = new HashSet<V>();
		for(ArrayList<Map.Entry<K,V>> arraylist : hashmap) {
			for(Map.Entry<K,V> node : arraylist) {
				set.add(node.getValue());
			}
		}
		return set;
	}
	
	/**
	 * Adds all the entries from the given <code>Map</code> into this current map.
	 * @param m the <code>Map</code> from which entries will be extracted
	 */
	@Override
	public void putAll(Map<? extends K,? extends V> m) {
		Set<?> set = m.entrySet();
		for(Object node : set) {
			Map.Entry<K,V> temp = (Map.Entry<K,V>) node;
			this.put(temp.getKey(), temp.getValue());
			size++;
		}
	}
	
	
	//////////////////////////////
	//Helper methods
	//////////////////////////////
	/**
	 * Compute the array index for a given key. This is done by calling <code>hashcode()</code> on the
	 * key, and then compressing the hash with <code>%</code> and the map size. 
	 * @param key the unique key an object should be hashed on
	 * @return an integer between <code>0</code> and <code>map.size-1</code>
	 */
	private int getKeyIndex(Object key) {
		int hashcode = key.hashCode();
		int compressIndex = hashcode % capacity;
		return compressIndex;
	}
	
	/**
	 * Helper method to resize a map to a larger size. A new array is created that is double the current size 
	 * rounded to the next prime number (Ex. a map of size 5 is resized to a new size of 11). The existing 
	 * entries are rehashed and inserted into the new array.
	 */
	public void resize() {
		//1. Calculate new table size.
		int doubleTableCapacity = this.getCapacity() * 2;
		int newTableCapacity = Primes.findNextPrime(doubleTableCapacity);
		this.capacity = newTableCapacity;
		
		//2. Build a new bigger table.
		ArrayList<Map.Entry<K,V>>[] newTable = (ArrayList<Map.Entry<K,V>>[]) new ArrayList[this.capacity];
		for(int i = 0; i<this.capacity; i++) {
			newTable[i] = new ArrayList<Map.Entry<K,V>>();
		}

		//3. Copy all objects from old table to new table.
		for(ArrayList<Map.Entry<K,V>> arraylist : hashmap) {
			if(arraylist.size()>0) {
				for(Map.Entry<K,V> node : arraylist) {
					int newIndex = getKeyIndex(node.getKey());
					newTable[newIndex].add(node);
				}
			}
		}
		
		//4. Replace old table with new table.
		hashmap = newTable;
		//System.out.println("Resize performed");
	}
	
	/**
	 * Reset the map to its original state, a map that is empty and with a capacity of 11.
	 */
	public void reset() {
		this.capacity = 11;
		this.size = 0;
		hashmap = (ArrayList<Map.Entry<K,V>>[]) new ArrayList[this.capacity];
		for(int i = 0; i<this.capacity; i++) {
			hashmap[i] = new ArrayList<Map.Entry<K,V>>();
		}
	}
	
	/**
	 * Remove all entries from the map. The map's capacity remains unchanged so that if the map has been resized,
	 * the map does not return back to its default size.
	 */
	@Override
	public void clear() {
		this.size = 0;
		hashmap = (ArrayList<Map.Entry<K,V>>[]) new ArrayList[this.capacity];
		for(int i = 0; i<this.capacity; i++) {
			hashmap[i] = new ArrayList<Map.Entry<K,V>>();
		}
	}
	
	
	//////////////////////////////
	//Standard structure methods
	//////////////////////////////
	/**
	 * Returns the number of entries in the map.
	 * @return the number of entries in the map
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns the current capacity of the map. This is, the amount of buckets it contains.
	 * @return the capacity of the map
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Returns <code>true</code> if the map's size is <code>0</code>, or <code>false</code> if size is 
	 * greater than <code>0</code>.
	 * @return a boolean that states if the map is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}
	
	/**
	 * Returns an <code>ArrayList</code> that is used as the underlying structure of the map. 
	 * @return an <code>ArrayList</code> representing this map.
	 */
	public ArrayList<Map.Entry<K,V>>[] getArray() {
		return hashmap;
	}
	
	/**
	 * Returns a <code>String</code> representation of this map.
	 */
	@Override
	public String toString() {
		return entrySet().toString();
	}	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
