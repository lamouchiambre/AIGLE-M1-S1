package dico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFastDictionary {

	private FastDictionary dico;
	
	@Before
	public void setUp() throws Exception {
		dico = new FastDictionary();
	}


	@Test
	public void testAddOneElementToEmptyDico() {
		assertEquals(0, dico.size());
	}
	
	@Test
	public void testSizeOfOneElementDico() {
		dico.put(42, "Ambre");
		assertEquals(1, dico.size());
	}
	
	@Test
	public void testContainsKey() {
		dico.put(42, "Ambre");
		assertTrue(dico.containsKey(42));
	}
	
	@Test
	public void testIndexOf() {
		dico.put(42, "Ambre");
		assertEquals(0, dico.indexOf(42));
	}
	
	@Test
	public void testNewIndexOf() {
		dico.put(42, "Ambre");
		assertEquals(1, dico.newIndexOf(69));
	}
	
//	@Test Hashmap
//	public void testToString() {
//		dico.put(42, "Ambre");
//		assertEquals("FastDictionary [keys=[" + dico.getKeys()[dico.indexOf(42)] 
//				+ "], values=[" + dico.getValues()[dico.indexOf(42)] + "]]"
//				, dico.toString());
//	}
	
	@Test
	public void testGrow() {
		assertEquals(0, dico.getKeys().length);
		dico.grow();
		assertEquals(2, dico.getKeys().length);
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(dico.isEmpty());
	}


}
