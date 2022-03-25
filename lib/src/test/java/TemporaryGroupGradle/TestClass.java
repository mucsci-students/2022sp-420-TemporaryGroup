package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
		Field f1 = new Field ("wheels", "int");
		Field f2 = new Field ("tankCapacity", "double");
		ArrayList<Field> ftest = new ArrayList<Field> () {
			{
				add(f1);
				add(f2);
			}
		};
		UMLClass test = new UMLClass ("car");
		test.addField("wheels", "int");
		test.addField("tankCapacity", "double");
		assertEquals (ftest, test.getFields(), "getFields failed");
	}

	void testGetMethods() {
		Method m1 = new Method ("tools", "string");
		Method m2 = new Method ("setBreaks", "bool");
		ArrayList<Method> mtest = new ArrayList<Method> () {
			{
				add(m1);
				add(m2);
			}
		};
		UMLClass test = new UMLClass ("car");
		test.addMethod("tools", "string");
		test.addMethod("setBreaks", "bool");
		assertEquals (mtest, test.getMethods(), "getMethods failed");
		
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
		Field ftest = new Field ("wheels", "int");
		UMLClass test = new UMLClass ("car");
		test.addField("wheels", "int");
		assertEquals (ftest, test.getField(null), "getFields failed");
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
		assertEquals("null", "null");
		fail("Not yet implemented");
	}
}
