package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestField {

	@Test
	void testFieldConstructor() {
		Field test = new Field ("main", "int");
		assertNotNull (test);
	}
	
	@Test
	void testGetFieldName() {
		Field test = new Field ("main", "int");
		assertEquals ("main", test.getFieldName());
	}

	@Test
	void testGetFieldType() {
		Field test = new Field ("numOfWheels", "int");
		assertEquals ("int", test.getFieldType());
	}

	@Test
	void testRenameField() {
		Field test = new Field ("main", "int");
		test.renameField("sum");
		assertEquals ("sum", test.getFieldName());
	}

	@Test
	void testRenameFieldType() {
		Field test = new Field ("main", "int");
		test.renameFieldType("String");
		assertEquals ("String", test.getFieldType());
	}
}
