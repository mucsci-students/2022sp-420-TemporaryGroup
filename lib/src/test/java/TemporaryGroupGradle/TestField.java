package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestField {

	@Test
	void testFieldConstructor() {
		Field test = new Field ("main", "int");
		assertNotNull (test, "a new field object failed to be created");
	}
	
	@Test
	void testGetFieldName() {
		Field test = new Field ("main", "int");
		assertEquals ("main", test.getFieldName(), "field name is not as expected");
	}

	@Test
	void testGetFieldType() {
		Field test = new Field ("numOfWheels", "int");
		assertEquals ("int", test.getFieldType(), " field type is not as expected");
	}

	@Test
	void testRenameField() {
		Field test = new Field ("main", "int");
		test.renameField("sum");
		assertEquals ("sum", test.getFieldName(), "the renameField method failed");
	}

	@Test
	void testRenameFieldType() {
		Field test = new Field ("main", "int");
		test.renameFieldType("String");
		assertEquals ("String", test.getFieldType(), "the renameFieldType method failed");
	}
}
