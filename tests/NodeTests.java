package tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import tests.Node;

public class NodeTests {
	static Node node1 = new Node(1, "1");
	static Node node2 = new Node(1, "1");
	static Node node3 = new Node(1, "2");
	
	@Test
	public void testEquivalence1() {
		assertEquals("", node1, node2);
	}
	@Test
	public void testHashCode1() {
		assertEquals("", node1.hashCode(), node2.hashCode());
	}
	@Test
	public void testHashCode2() {
		assertFalse("", node1.hashCode() == node3.hashCode());
	}
}
