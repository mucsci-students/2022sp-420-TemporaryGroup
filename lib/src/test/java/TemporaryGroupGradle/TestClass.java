package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass {

	void testUMLClassConstructor() {
		UMLClass test = new UMLClass ("car");
		assertNotNull (test, "failed when creating a class");
	}

	void testGetClassName() {
		UMLClass test = new UMLClass ("car");
		assertEquals ("car", test.getClassName(), "getClassName failed");
	}

	void testRenameClass() {
		UMLClass test = new UMLClass ("car");
		test.renameClass("bank");
		assertEquals ("bank", test.getClassName(), "renameClass failed");
	}

	void testGetFields() {
		fail("Not yet implemented");
	}

	void testGetMethods() {
		fail("Not yet implemented");
	}

	void testAddField() {
		fail("Not yet implemented");
	}

	void testRemoveField() {
		fail("Not yet implemented");
	}

	void testRenameField() {
		fail("Not yet implemented");
	}

	void testRenameFieldType() {
		fail("Not yet implemented");
	}

	void testGetField() {
		fail("Not yet implemented");
	}

	void testFieldExists() {
		fail("Not yet implemented");
	}

	void testAddMethod() {
		fail("Not yet implemented");
	}

	void testRemoveMethod() {
		fail("Not yet implemented");
	}

	void testRenameMethod() {
		fail("Not yet implemented");
	}

	void testRenameMethodType() {
		fail("Not yet implemented");
	}

	void testGetMethod() {
		fail("Not yet implemented");
	}

	void testMethodExists() {
		fail("Not yet implemented");
	}

	void testAddParameter() {
		fail("Not yet implemented");
	}

	void testRemoveParameter() {
		fail("Not yet implemented");
	}

	void testRemoveAllParameters() {
		fail("Not yet implemented");
	}

	void testRenameParameter() {
		fail("Not yet implemented");
	}

}