package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestParameter {

	@Test
	void testParameterConstructor() {
		Parameter test = new Parameter ("size", "int");
		assertNotNull (test);
	}

	@Test
	void testGetParamName() {
		Parameter test = new Parameter ("size", "int");
		assertEquals ("size", test.getParamName());
		
	}

	@Test
	void testGetParamType() {
		Parameter test = new Parameter ("size", "int");
		assertEquals ("int", test.getParamType());
	}

	@Test
	void testRenameParam() {
		Parameter test = new Parameter ("size", "double");
		test.renameParam("length");
		assertEquals ("length", test.getParamName());
	}

	@Test
	void testRenameParamType() {
		Parameter test = new Parameter ("size", "double");
		test.renameParamType("int");
		assertEquals ("int", test.getParamType());
	}
}
