package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import root.MyHashMap;
import tests.Node;



public class MyHashMapTests {
	static MyHashMap<Integer, Node> map;
	static Node node1;
	static Node node2;
	static Node node3;
	static Node node4;
	static Method reset;
	static Method getCapacity;
	static Method getLoadFactor;
	static Method getArray;
	static Method resize;
	static ArrayList whatShouldMapContainNow;

	@BeforeClass
	public static void prepareForTests() {
		map = new MyHashMap<Integer, Node>();
		whatShouldMapContainNow = new ArrayList();
		node1 = new Node(1, "Joseph");
		node2 = new Node(2, "Jose");
		node3 = new Node(3, "Vicki");
		node4 = new Node(4, "Luis");
		
		//Initialize Private Methods
		try {getCapacity =	MyHashMap.class.getDeclaredMethod("getCapacity");
			 getCapacity.setAccessible(true);
		} catch(Exception e) {System.out.println("getCapacity exception!");}
		try {getArray = 	MyHashMap.class.getDeclaredMethod("getArray");
			 getArray.setAccessible(true);
		} catch(Exception e) {System.out.println("getArray exception!");}
		try {getLoadFactor =MyHashMap.class.getDeclaredMethod("getLoadFactor");
		 	 getLoadFactor.setAccessible(true);
		} catch(Exception e) {System.out.println("getLoadFactor exception!");}
		try {resize = 		MyHashMap.class.getDeclaredMethod("resize");
			 resize.setAccessible(true);
		} catch(Exception e) {System.out.println("resize exception!");}
	}
	@Before
	public void beforeEach() {
		map = new MyHashMap<Integer, Node>();
		whatShouldMapContainNow.clear();
	}
	@Test
	public void testInitial1() {
		//Caes: map initialization
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));
	}
	@Test
	public void testPut1() {
		//Case: invalid entries
		map.put(null,null);
		map.put(null, node1);
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));
	}
	@Test
	public void testPut2() {
		//Case: null entry
		map.put(1,null);
		whatShouldMapContainNow.add(null);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));
	}
	@Test
	public void testPut3() {
		//Case: duplicate entries
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);		
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		whatShouldMapContainNow.add(node1);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));
	}
	@Test
	public void testPut4() {
		//Case: many entries, one duplicate
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);		
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.put(node4.getId(), node4);		
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		whatShouldMapContainNow.add(node4);
		assertTrue(testParameters(11, 4, whatShouldMapContainNow));
	}
	@Test
	public void testPut5() {
		//Case: inserting over existing entries (put should return old entry).
		Node temp;
		temp = map.put(1, node4);
		assertEquals(null, temp);
		
		temp = map.put(1, node2);
		assertEquals(node4, temp);
		
		temp = map.put(1, node3);
		assertEquals(node2, temp);
		
		whatShouldMapContainNow.add(node3);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));
	}
	@Test
	public void testClear1() {	
		//Case: clearing a map (size is 0, capacity doesnt change)
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));
		
		map.clear();
		whatShouldMapContainNow.clear();
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testClear2() {	
		//Case: clearing a map after resize
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		resize();
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		assertTrue(testParameters(23, 3, whatShouldMapContainNow));
		
		map.clear();
		whatShouldMapContainNow.clear();
		assertTrue(testParameters(23, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testGetSize1() {
		//Case: empty array
		assertEquals(0, map.size());
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testGetSize2() {
		//Case: non empty array
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		assertEquals(3, map.size());
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));	
	}
	@Test
	public void testGetSize3() {
		//Case: after manipulating array
		map.put(node1.getId(), node1);
		map.remove(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.remove(node1.getId(), node1);
		whatShouldMapContainNow.add(node3);
		assertEquals(1, map.size());
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
	}
	@Test
	public void testResize1() {
		//Case: simple resize
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
		resize();
		assertTrue(testParameters(23, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testResize2() {
		resize();
		resize();
		assertTrue(testParameters(47, 0, whatShouldMapContainNow));	
		resize();
		assertTrue(testParameters(97, 0, whatShouldMapContainNow));	
		resize();
		assertTrue(testParameters(197, 0, whatShouldMapContainNow));	
		resize();
		assertTrue(testParameters(397, 0, whatShouldMapContainNow));	
	}
	
	@Test
	public void testResize3() {
		//Case: resize only when needed
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);
		whatShouldMapContainNow.add(node1);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
	}
	@Test
	public void testGet1() {
		//Case: invalid gets
		assertEquals(null, map.get(null));
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	

		assertEquals(null, map.get(1));
	}
	@Test
	public void testGet2() {
		//Case: non existent keys
		assertEquals(null, map.get(1));
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testGet3() {
		//Case: simple get
		map.put(node1.getId(), node1);
		whatShouldMapContainNow.add(node1);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	

		assertEquals(node1, map.get(node1.getId()));
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
	}
	@Test
	public void testGet4() {
		//Case: multiple gets
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));	
		
		assertEquals(node1, map.get(node1.getId()));
		assertEquals(node2, map.get(node2.getId()));
		assertEquals(node3, map.get(node3.getId()));
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));	
	}
	@Test
	public void testGet5() {
		//Case: getting non existant keys
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));	
		
		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());
		whatShouldMapContainNow.clear();
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	

		assertEquals(null, map.get(node1.getId()));
		assertEquals(null, map.get(node2.getId()));
		assertEquals(null, map.get(node3.getId()));
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testGet6() {
		//Case: getting keys after removing
		map.put(node1.getId(), node1);
		whatShouldMapContainNow.add(node1);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
		assertEquals("", node1, map.get(node1.getId()));

		map.remove(node1.getId());
		whatShouldMapContainNow.clear();
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
		assertEquals(null, map.get(node1.getId()));
	}
	@Test
	public void testGet7() {
		//Case: getting keys after over writing
		map.put(node1.getId(), node1);
		whatShouldMapContainNow.add(node1);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
		assertEquals("", node1, map.get(node1.getId()));

		map.put(node1.getId(), node2);
		whatShouldMapContainNow.clear();
		whatShouldMapContainNow.add(node2);
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
		assertEquals(node2, map.get(node1.getId()));
	}
	@Test
	public void testRemove1() {
		//Case: invalid remove
		assertEquals(null, map.remove(null));
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
	}
	public void testRemove2() {
		//Case: removing non existent keys
		assertEquals(null, map.remove("1"));
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
	}
	@Test
	public void testRemove3() {
		//Case: simple removes
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.put(node4.getId(), node4);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		whatShouldMapContainNow.add(node4);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));	
		
		Node x = map.remove(node2.getId());
		Node y = map.remove(node3.getId());
		Node z = map.remove(node4.getId());
		whatShouldMapContainNow.clear();
		assertTrue(testParameters(11, 0, whatShouldMapContainNow));	
		assertEquals(node2, x);
		assertEquals(node3, y);
		assertEquals(node4, z);
		
		x = (Node) map.get(node1.getId());
		y = (Node) map.get(node2.getId());
		z = (Node) map.get(node3.getId());
		assertEquals("", null, x);
		assertEquals("", null, y);
		assertEquals("", null, z);
	}
	@Test
	public void testRemove4() {
		//Case: remove manipulation
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		whatShouldMapContainNow.add(node1);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));	
		
		Node x = map.remove(node1.getId());
		Node y = map.remove(node3.getId());
		whatShouldMapContainNow.remove(node1);
		whatShouldMapContainNow.remove(node3);		
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));	
		assertEquals(node1, x);
		assertEquals(node3, y);
		
		x = (Node) map.get(node1.getId());
		y = (Node) map.get(node2.getId());
		Node z = (Node) map.get(node3.getId());
		assertEquals("", null, x);
		assertEquals("", node2, y);
		assertEquals("", null, z);
		
		x = map.remove(node1.getId());
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));
		assertEquals("", null, x);
	}
	@Test
	public void testContainsKey1() {
		//Case: manipulation
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.put(node4.getId(), node4);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		whatShouldMapContainNow.add(node4);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));
	
		assertEquals("", false, map.containsKey(node1.getId()));
		assertEquals("", true, map.containsKey(node2.getId()));
		assertEquals("", true, map.containsKey(node3.getId()));
		
		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());
		whatShouldMapContainNow.remove(node2);
		whatShouldMapContainNow.remove(node3);		
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));

		assertEquals("", false, map.containsKey(node1.getId()));
		assertEquals("", false, map.containsKey(node2.getId()));
		assertEquals("", false, map.containsKey(node3.getId()));
	}
	@Test
	public void testContainsValue1() {
		//Case: manipulation
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.put(node4.getId(), node4);
		whatShouldMapContainNow.add(node2);
		whatShouldMapContainNow.add(node3);
		whatShouldMapContainNow.add(node4);
		assertTrue(testParameters(11, 3, whatShouldMapContainNow));
	
		assertEquals("", false, map.containsValue(node1));
		assertEquals("", true, map.containsValue(node2));
		assertEquals("", true, map.containsValue(node3));
		assertEquals("", true, map.containsValue(node4));

		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());
		whatShouldMapContainNow.remove(node2);
		whatShouldMapContainNow.remove(node3);		
		assertTrue(testParameters(11, 1, whatShouldMapContainNow));

		assertEquals("", false, map.containsValue(node1));
		assertEquals("", false, map.containsValue(node2));
		assertEquals("", false, map.containsValue(node3));
		assertEquals("", true, map.containsValue(node4));
	}	
	@Test
	public void testSetMethodsAgainstRealHashMap1() {
		//Case: create a real java.Hashmap and compare my set methods to it
		HashMap<Integer, Node> table = new HashMap<Integer, Node>();
		table.put(node1.getId(), node1);
		table.put(node2.getId(), node2);
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);			
		//System.out.println("    struct: " + table);
		//System.out.println("  mystruct: " + map);
		//System.out.println("  entryset: " + table.entrySet());
		//System.out.println("myentryset: " + map.entrySet());
		//System.out.println("    keyset: " + table.keySet());
		//System.out.println("  mykeyset: " + map.keySet());
		//System.out.println("    values: " + table.values());
		//System.out.println("  myvalues: " + map.values());
		assertEquals(table.entrySet(), map.entrySet());
		assertEquals(table.keySet(), map.keySet());
		//assertEquals(((Collection)table.values()), map.values());		
	}
	@Test
	public void testKeySet() {
		//Case: create a real java.Hashmap and compare my set methods to it
		HashMap<Integer, Node> table = new HashMap<Integer, Node>();
		table.put(node1.getId(), node1);
		table.put(node2.getId(), node2);
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);	
		assertEquals("", table.keySet(), map.keySet());
	}
	@Test
	public void testPutAll1() {	
		//Case: simple putAll
		HashMap<Integer, Node> table = new HashMap<Integer, Node>();
		table.put(node1.getId(), node1);
		table.put(node2.getId(), node2);
		map.putAll(table);
		assertEquals("", table.entrySet(), map.entrySet());
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//F O R     T E S T I N G     U S E     O N L Y
	public void resize() {
		try {resize.invoke(map);} catch(Exception e){};
	}
	public int getCapacity() {
		int i = 1000000;
		try {i = (int) getCapacity.invoke(map);} catch(Exception e) {};
		return i;
	}
	public double getLoadFactor() {
		double i = 1000000;
		try {i = (double) getLoadFactor.invoke(map);} catch(Exception e) {};
		return i;
	}
	public ArrayList<Map.Entry<?,?>>[] getArray() {
		ArrayList<Map.Entry<?,?>>[] i = null;
		try {i = (ArrayList<Entry<?,?>>[]) getArray.invoke(map);} catch(Exception e) {};
		return i;
	}
	public boolean testParameters(int testCapacity, int testSize, ArrayList content) {
		//Testing Capacity
		assertEquals(testCapacity, getArray().length);
		assertEquals(testCapacity, getCapacity());
		
		//Testing Size
		assertEquals(testSize, map.size());
		assertEquals(testSize, testNumberOfEntries());
		
		//Testing Content
		ArrayList temp = testContent();
		assertEquals(content, temp);
		
		//Testing Load Factor
		assertTrue(testCapacity());
		return true;		
	}
	public boolean testCapacity() {
		return (map.size()/getCapacity()) < getLoadFactor();
	}
	public int testNumberOfEntries() {
		int i = 0;
		for(ArrayList<Map.Entry<?,?>> a : getArray()) {
			if(a.size()>0) {
				for(Map.Entry<?,?> e : a) {
					i++;
				}
			}
		}
		return i;
	}
	public ArrayList testContent() {
		ArrayList content = new ArrayList();
		for(ArrayList<Map.Entry<?,?>> a : getArray()) {
			if(a.size()>0) {
				for(Map.Entry<?,?> e : a) {
					content.add(e.getValue());
				}
			}
		}
		return content;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
