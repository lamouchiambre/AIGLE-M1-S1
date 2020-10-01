package dico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSortedDictionary {

	private SortedDictionary od;
	
	@Before
	public void setUp() throws Exception {
		od = new SortedDictionary();
	}

	@Test
	public void testAddOneElementToEmptyDico() {
		assertEquals(0, od.size());
	}
	
	@Test
	public void testSizeOfOneElementDico() {
		od.put(42, "Ambre");
		assertEquals(1, od.size());
	}
	
	@Test
	public void testContainsKey() {
		od.put(42, "Ambre");
		assertTrue(od.containsKey(42));
	}
	
	@Test
	public void testIndexOf() {
		od.put(42, "Ambre");
		assertEquals(0, od.indexOf(42));
	}
	
	@Test
	public void testNewIndexOf() {
		od.put(42, "Ambre");
		assertEquals(1, od.newIndexOf(69));
	}
	
	@Test
	public void testToString() {
		od.put(42, "Ambre");
		assertEquals("SortedDictionary [keys=[" + od.getKeys()[od.indexOf(42)] 
				+ "], values=[" + od.getValues()[od.indexOf(42)] + "]]"
				, od.toString());
	}
	
	@Test
	public void testGrow() {
		assertEquals(0, od.getKeys().length);
		od.grow();
		assertEquals(1, od.getKeys().length);
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(od.isEmpty());
	}

}
