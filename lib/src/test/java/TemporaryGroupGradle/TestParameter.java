package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestParameter {

	@Test
	void testParameterConstructor() {
		Parameter test = new Parameter ("size", "int");
		assertNotNull (test, "failed when creating new parameter");
	}

	@Test
	void testGetParamName() {
		Parameter test = new Parameter ("size", "int");
		assertEquals ("size", test.getParamName(), "error when setting Parameter name");
		
	}

	@Test
	void testGetParamType() {
		Parameter test = new Parameter ("size", "int");
		assertEquals ("int", test.getParamType(), "error when setting Parameter type");
	}

	@Test
	void testRenameParam() {
		Parameter test = new Parameter ("size", "double");
		test.renameParam("length");
		assertEquals ("length", test.getParamName(), "renameParam failed");
	}

	@Test
	void testRenameParamType() {
		Parameter test = new Parameter ("size", "double");
		test.renameParamType("int");
		assertEquals ("int", test.getParamType(), "renameParamType failed");
	}
}
