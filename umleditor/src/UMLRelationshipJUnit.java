import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author irifa
 *
 */
public class UMLRelationshipJUnit {

	
	UMLRelationship testRel = new UMLRelationship(null, null, null);
	String source = "test1";
	String dest = "test2";
	
	
	/**
	 * Test method for {@link UMLRelationship#UMLRelationship(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUMLRelationshipDefConstructor() {
		assert testRel.getSource() == null;
		assert testRel.getDestination() == null;
	}

	@Test
	public final void testAddingSourceDest () {
		testRel.setSource(source);
		testRel.setDestination(dest);
		assert testRel.getSource() == "test1";
		assert testRel.getDestination() == "test2";
	}
	
	
	/**
	 * Test method for {@link UMLRelationship#setSource(java.lang.String)}.
	 */
	@Test
	public final void testSetSource() {
		UMLRelationship temp = new UMLRelationship ("newSource","test1", null);
		testRel.setSource(temp.getSource());
		assertEquals("newSource", testRel.getSource());
	}

	/**
	 * Test method for {@link UMLRelationship#setDestination(java.lang.String)}.
	 */
	@Test
	public final void testSetDestination() {
		UMLRelationship temp = new UMLRelationship ("newSource","newDest", null);
		testRel.setDestination(temp.getDestination());
		assertEquals("newDest", testRel.getDestination());
	}

	/**
	 * Test method for {@link UMLRelationship#getType()}.
	 */
	@Test
	public final void testGetType() {
		assert testRel.getType() == null;
	}
}
