package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUMLRelationship {

	void testUMLRelationshipConstructor() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertNotNull (test, "failed when creating a new relationship");
	}

	void testGetSource() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertEquals ("car", test.getSource(), "source class is not as expected");
	}

	void testSetSource() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		test.setSource("church");
		assertEquals ("church", test.getSource(), "setSource failed");
	}

	void testGetDestination() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertEquals ("bank", test.getDestination(), "source destination is not as expected");
	}

	void testSetDestination() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		test.setDestination("park");
		assertEquals ("park", test.getDestination(), "setDestination failed");
	}

	void testGetType() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		assertEquals ("aggregation",test.getType(), "type of relationship is not as expected");
	}

	void testSetType() {
		UMLRelationship test = new UMLRelationship ("car", "bank", "aggregation");
		test.setType("composition");
		assertEquals ("composition", test.getType(), "setType failed");
	}

	void testListValidTypes() {
		UMLRelationship test = new UMLRelationship (null, null, null);
		String [] correctTypes = {"aggregation", "composition", "inheritance", "realization"}; 
		assertEquals (correctTypes, test.listValidTypes(), "validTypes array error");
	}

	void testIsValidType() {
		UMLRelationship test = new UMLRelationship (null, null, null);
		boolean isValid = test.isValidType ("composition");
		boolean isNotValid = test.isValidType ("sequence");
		assertEquals (isValid, true, "isValid failed");
		assertEquals (isNotValid, false, "isValid failed");
	}
}