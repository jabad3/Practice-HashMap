package tests;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;

import root.MyHashMap;
import tests.Node;
import math.Primes;

public class MyHashMapTests {
	static MyHashMap map;
	static Node node1;
	static Node node2;
	static Node node3;
	static Node node4;
	
	@BeforeClass
	public static void prepareForTests() {
		map = new MyHashMap();
		node1 = new Node(1, "Joseph");
		node2 = new Node(2, "Jose");
		node3 = new Node(3, "Vicki");
		node4 = new Node(4, "Luis");
	}
	@Before
	public void beforeEach() {
		map.reset();
	}
	@Test
	public void testInitial1() {
		map = new MyHashMap();
		assertEquals("Test initial size", 0, map.size());
		assertEquals("Test initial map", 11, map.getArray().length);
	}
	@Test
	public void testClear1() {
		map = new MyHashMap<Integer, String>();
		map.hashmap[0].add(new AbstractMap.SimpleEntry(0,"0"));
		map.hashmap[1].add(new AbstractMap.SimpleEntry(1,"1"));
		map.hashmap[2].add(new AbstractMap.SimpleEntry(2,"2"));
		map.size = 3;
		assertEquals("", 3, map.size());
		assertEquals("", "0", ((SimpleEntry) map.hashmap[0].get(0)).getValue());
		assertEquals("", "1", ((SimpleEntry) map.hashmap[1].get(0)).getValue());
		assertEquals("", "2", ((SimpleEntry) map.hashmap[2].get(0)).getValue());
		map.clear();
		assertEquals("", 0, map.size());
		assertEquals("", new ArrayList<Node>(), map.hashmap[0]);
		assertEquals("", new ArrayList<Node>(), map.hashmap[1]);
		assertEquals("", new ArrayList<Node>(), map.hashmap[2]);		
	}
	@Test
	public void testGetSize1() {
		map = new MyHashMap();
		map.size = 3;
		assertEquals("", 3, map.size());		
	}
	@Test
	public void testGetArray1() {
		map = new MyHashMap();
		assertEquals("", map.hashmap, map.getArray());		
	}
	@Test
	public void testIsPrime1() {
		boolean answer = Primes.isPrime(11);
		assertTrue("", answer);		
	}
	@Test
	public void testIsPrime2() {
		boolean answer = Primes.isPrime(32);
		assertFalse("", answer);		
	}
	@Test
	public void testIsPrime3() {
		boolean flag = false;
		int[] tests = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
				43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 
				107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 
				173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 
				239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 
				311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 
				383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 
				457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 
				541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 
				613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 
				683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 
				769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 
				857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 
				941, 947, 953, 967, 971, 977, 983, 991, 997};
		for(int x : tests) {
			flag = Primes.isPrime(x);
			if(flag==false) break;
		}
		assertTrue("", flag);		
	}
	@Test
	public void testIsPrime4() {
		boolean flag = false;
		int[] tests = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
				43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 
				107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 
				173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 
				239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 
				311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 
				383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 
				457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 
				541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 
				613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 
				683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 
				769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 
				857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 
				941, 947, 953, 967, 971, 977, 983, 991, 997, 32};
		for(int x : tests) {
			flag = Primes.isPrime(x);
			if(flag==false) break;
		}
		assertFalse("", flag);		
	}
	@Test
	public void testFindNextPrime1() {
		int next = Primes.findNextPrime(5);
		assertEquals("", 7, next);		
	}
	@Test
	public void testFindNextPrime2() {
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
				43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 
				107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 
				173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 
				239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 
				311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 
				383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 
				457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 
				541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 
				613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 
				683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 
				769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 
				857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 
				941, 947, 953, 967, 971, 977, 983, 991, 997};
		int[] tests = new int[primes.length];
		for(int i=0; i<primes.length; i++) {
			tests[i] = Primes.findNextPrime(primes[i]);
		}
		int[] thisIsTheAnswer = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
				43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 
				109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 
				181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 
				257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 
				337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 
				419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 
				491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 
				587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 
				659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 
				751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 
				839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 
				937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009};
		
		assertTrue("", Arrays.equals(thisIsTheAnswer, tests));
	}
	
	@Test
	public void testFindNextPrime3() {
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
				43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 
				107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 
				173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 
				239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 
				311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 
				383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 
				457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 
				541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 
				613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 
				683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 
				769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 
				857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 
				941, 947, 953, 967, 971, 977, 983, 991, 997};
		int[] tests = new int[primes.length];
		for(int i=0; i<primes.length; i++) {
			tests[i] = Primes.findNextPrime(primes[i]);
		}
		int[] thisIsTheAnswer = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
				43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 
				109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 
				181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 
				257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 
				337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 
				419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 
				491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 
				587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 
				659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 
				751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 
				839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 
				937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1008};
		
		assertFalse("", Arrays.equals(thisIsTheAnswer, tests));
	}
	
	@Test
	public void testResize1() {
		assertEquals("", 11, map.getCapacity());
		assertEquals("", 11, map.hashmap.length);
		assertEquals("", 0, map.size());
		map.resize();
		assertEquals("", 23, map.getCapacity());
		assertEquals("", 23, map.hashmap.length);
		assertEquals("", 0, map.size());
	}
	
	@Test
	public void testResize2() {
		assertEquals("", 11, map.getCapacity());
		assertEquals("", 11, map.hashmap.length);
		assertEquals("", 0, map.size());
		map.resize();
		assertEquals("", 23, map.getCapacity());
		assertEquals("", 23, map.hashmap.length);
		assertEquals("", 0, map.size());
		map.resize();
		assertEquals("", 47, map.getCapacity());
		assertEquals("", 47, map.hashmap.length);
		assertEquals("", 0, map.size());
		map.resize();
		assertEquals("", 97, map.getCapacity());
		assertEquals("", 97, map.hashmap.length);
		assertEquals("", 0, map.size());
		map.resize();
		assertEquals("", 197, map.getCapacity());
		assertEquals("", 197, map.hashmap.length);
		assertEquals("", 0, map.size());
		map.resize();
		assertEquals("", 397, map.getCapacity());
		assertEquals("", 397, map.hashmap.length);
		assertEquals("", 0, map.size());
	}
	
	@Test
	public void testResize3() {
		assertEquals("", 11, map.getCapacity());
		assertEquals("", 11, map.hashmap.length);
		assertEquals("", 0, map.size());
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
		//map.toString();
		assertEquals("", 11, map.getCapacity());
		assertEquals("", 11, map.hashmap.length);
		assertEquals("", 1, map.size());
	}
	@Test
	public void testPut1() {
		map.put(node1.getId(), node1);
		map.put(node1.getId(), node1);		
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		//map.toString();
		assertEquals("", 3, map.size());
		assertEquals("", 11, map.getCapacity());
	}
	@Test
	public void testGet1() {
		Node temp = (Node) map.get(null);
		assertEquals("", temp, null);
		
		Node temp2 = (Node) map.get(node1.getId());
		assertEquals("", temp2, null);
	}
	@Test
	public void testGet2() {
		map.put(node1.getId(), node1);
		Node temp = (Node) map.get(node1.getId());
		assertEquals("", temp, node1);
	}
	@Test
	public void testGet3() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		Node temp1 = (Node) map.get(node1.getId());
		Node temp2 = (Node) map.get(node2.getId());
		Node temp3 = (Node) map.get(node3.getId());
		assertEquals("", temp1, node1);
		assertEquals("", temp2, node2);
		assertEquals("", temp3, node3);
	}
	@Test
	public void testGet4() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());		
		Node temp1 = (Node) map.get(node1.getId());
		Node temp2 = (Node) map.get(node2.getId());
		Node temp3 = (Node) map.get(node3.getId());
		//map.toString();
		assertEquals("", null, temp1);
		assertEquals("", null, temp2);
		assertEquals("", null, temp3);
		assertEquals("", 0, map.size());
		assertEquals("", 0, map.size);		
	}
	@Test
	public void testRemove1() {
		Node temp = (Node) map.remove(null);
		assertEquals("", null, temp);
		assertEquals("", 0, map.size());
		assertEquals("", 0, map.size);
		assertEquals("", 11, map.getCapacity());
	}
	public void testRemove2() {
		Node temp = (Node) map.remove("1");
		assertEquals("", null, temp);
		assertEquals("", 0, map.size());
		assertEquals("", 0, map.size);
		assertEquals("", 11, map.getCapacity());
	}
	@Test
	public void testRemove3() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		assertEquals("", 3, map.size());
		assertEquals("", 11, map.getCapacity());
		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());	
		assertEquals("", 0, map.size());
		assertEquals("", 0, map.size);
		assertEquals("", 11, map.getCapacity());
		
		Node temp1 = (Node) map.get(node1.getId());
		Node temp2 = (Node) map.get(node2.getId());
		Node temp3 = (Node) map.get(node3.getId());
		//map.toString();
		assertEquals("", null, temp1);
		assertEquals("", null, temp2);
		assertEquals("", null, temp3);
	}
	@Test
	public void testRemove4() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		assertEquals("", 3, map.size());
		assertEquals("", 11, map.getCapacity());
		
		map.remove(node1.getId());
		map.remove(node3.getId());
		assertEquals("", 1, map.size());
		assertEquals("", 1, map.size);
		assertEquals("", 11, map.getCapacity());
		
		Node temp1 = (Node) map.get(node1.getId());
		Node temp2 = (Node) map.get(node2.getId());
		Node temp3 = (Node) map.get(node3.getId());
		assertEquals("", null, temp1);
		assertEquals("", node2, temp2);
		assertEquals("", null, temp3);
		
		map.remove(node1.getId());
		assertEquals("", 1, map.size());
		assertEquals("", 1, map.size);
		assertEquals("", 11, map.getCapacity());
		//map.toString();
	}
	@Test
	public void testContainsKey1() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		assertEquals("", 3, map.size());
		assertEquals("", 11, map.getCapacity());
		boolean temp1 = map.containsKey(node1.getId());
		boolean temp2 = map.containsKey(node2.getId());
		boolean temp3 = map.containsKey(node3.getId());		
		assertEquals("", true, temp1);
		assertEquals("", true, temp2);
		assertEquals("", true, temp3);
		
		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());	
		assertEquals("", 0, map.size());
		assertEquals("", 0, map.size);
		assertEquals("", 11, map.getCapacity());
		
		Node ttemp1 = (Node) map.get(node1.getId());
		Node ttemp2 = (Node) map.get(node2.getId());
		Node ttemp3 = (Node) map.get(node3.getId());
		//map.toString();
		assertEquals("", null, ttemp1);
		assertEquals("", null, ttemp2);
		assertEquals("", null, ttemp3);
	}
	@Test
	public void testContainsValue1() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		assertEquals("", 3, map.size());
		assertEquals("", 11, map.getCapacity());
		
		boolean temp1 = map.containsValue(node1);
		boolean temp2 = map.containsValue(node2);
		boolean temp3 = map.containsValue(node3);		
		assertEquals("", true, temp1);
		assertEquals("", true, temp2);
		assertEquals("", true, temp3);
		
		map.remove(node1.getId());
		map.remove(node2.getId());
		map.remove(node3.getId());	
		assertEquals("", 0, map.size());
		assertEquals("", 0, map.size);
		assertEquals("", 11, map.getCapacity());
		
		Node ttemp1 = (Node) map.get(node1.getId());
		Node ttemp2 = (Node) map.get(node2.getId());
		Node ttemp3 = (Node) map.get(node3.getId());
		//map.toString();
		assertEquals("", null, ttemp1);
		assertEquals("", null, ttemp2);
		assertEquals("", null, ttemp3);
	}	
	//@Test
	//public void testSetMethods1() {
	//	HashMap<Integer, Node> table = new HashMap<Integer, Node>();
	//	table.put(node1.getId(), node1);
	//	table.put(node2.getId(), node2);
	//	//table.put(null, 12);
	//	map.put(node1.getId(), node1);
	//	map.put(node2.getId(), node2);		
	//	boolean answer = table.containsValue(node1);
	//
	//	System.out.println("    struct: " + table);
	//	System.out.println("  mystruct: " + map);
	//	System.out.println("  entryset: " + table.entrySet());
	//	System.out.println("myentryset: " + map.entrySet());
	//	System.out.println("    keyset: " + table.keySet());
	//	System.out.println("  mykeyset: " + map.keySet());
	//	System.out.println("    values: " + table.values());
	//	System.out.println("  myvalues: " + map.values());
	//}
	@Test
	public void testKeySet() {	
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		HashSet myAnswer = (HashSet) map.keySet();
		HashSet correctAnswer = new HashSet();
		correctAnswer.add(new AbstractMap.SimpleEntry(node1.getId(), node1));
		correctAnswer.add(new AbstractMap.SimpleEntry(node2.getId(), node2));		
		//System.out.println(correctAnswer);
		//System.out.println(myAnswer);
		//assertEquals("", correctAnswer, myAnswer);
	}
	@Test
	public void testPutAll1() {	
		HashMap<Integer, Node> table = new HashMap<Integer, Node>();
		table.put(node1.getId(), node1);
		table.put(node2.getId(), node2);
		map.putAll(table);
		//map.toString();
		
		//System.out.println(correctAnswer);
		//System.out.println(myAnswer);
		//assertEquals("", correctAnswer, myAnswer);
	}
}
