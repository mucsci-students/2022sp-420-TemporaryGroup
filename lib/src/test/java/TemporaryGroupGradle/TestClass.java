package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestClass {
	
	
	@Test
	void testUMLClassConstructor() {
		UMLClass test = new UMLClass ("car");
		assertNotNull (test, "failed when creating a class");
	}

	@Test
	void testGetClassName() {
		UMLClass test = new UMLClass ("car");
		assertEquals ("car", test.getClassName(), "getClassName failed");
	}

	@Test
	void testRenameClass() {
		UMLClass test = new UMLClass ("car");
		test.renameClass("bank");
		assertEquals ("bank", test.getClassName(), "renameClass failed");
	}

	@Test
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

	@Test
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
		
		
	}

	void testRemoveField() {
	}

	void testRenameField() {
	}

	void testRenameFieldType() {
	}

	@Test
	void testGetField() {
		Field ftest = new Field ("wheels", "int");
		UMLClass test = new UMLClass ("car");
		test.addField("wheels", "int");
		assertEquals (ftest, test.getField(null), "getFields failed");
	}

	void testFieldExists() {
	}

	void testAddMethod() {
	}

	void testRemoveMethod() {
	}

	void testRenameMethod() {
	}

	void testRenameMethodType() {
	}

	void testGetMethod() {
	}

	void testMethodExists() {
	}

	void testAddParameter() {
	}

	void testRemoveParameter() {
	}

	void testRemoveAllParameters() {
	}

	void testRenameParameter() {
	}
}