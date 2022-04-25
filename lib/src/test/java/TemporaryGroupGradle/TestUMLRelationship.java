package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUMLRelationship {

	@Test
	void testUMLRelationshipConstructor() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertNotNull (test);
	}

	@Test
	void testGetSource() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertEquals ("car", test.getSource());
	}

	@Test
	void testSetSource() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		test.setSource("church");
		assertEquals ("church", test.getSource());
	}

	@Test
	void testGetDestination() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertEquals ("bank", test.getDestination());
	}

	@Test
	void testSetDestination() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		test.setDestination("park");
		assertEquals ("park", test.getDestination());
	}

	@Test
	void testGetType() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertEquals ("aggregation",test.getType());
	}

	@Test
	void testSetType() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		test.setType("composition");
		assertEquals ("composition", test.getType());
	}
}