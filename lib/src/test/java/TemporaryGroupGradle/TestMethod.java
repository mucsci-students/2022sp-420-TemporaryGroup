package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestMethod {
	
	@Test
	void testMethodConstructor() {
		Method test = new Method ("calcDeterminant", "float");
		assertNotNull (test);
	}
	
	@Test
	void testGetMethodName() {
		Method test = new Method ("getSize", "double");
		assertEquals ("getSize", test.getMethodName());
	}

	@Test
	void testGetMethodType() {
		Method test = new Method ("getSize", "double");
		assertEquals ("double", test.getMethodType());
	}

	@Test
	void testRenameMethod() {
		Method test = new Method ("badName", "int");
		test.renameMethod("goodName");
		assertEquals ("goodName", test.getMethodName());
	}

	@Test
	void testRenameMethodType() {
		Method test = new Method ("getSize", "double");
		test.renameMethodType("size_t");
		assertEquals ("size_t", test.getMethodType());
	}

	@Test
	void testAddParameter() {
		Method test = new Method ("calcArea", "double");
		int oldSize = test.getParameterList().size();
		test.addParameter("lentgh", "int");
		assertEquals (oldSize + 1, test.getParameterList().size());
	}
	
	@Test
	void testRemoveParameter() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("length", "int");
		int oldSize = test.getParameterList().size();
		test.removeParameter("length");
		assertEquals (oldSize - 1, test.getParameterList().size());
	}

	@Test
	void testRemoveAllParameters() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("lentgh", "int");
		test.addParameter("width", "int");
		test.addParameter("par3", "int");
		test.removeAllParameters();
		assertEquals (0, test.getParameterList().size());
	}

	@Test
	void testRenameParameter() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("length", "int");
		Parameter parTest = test.getParameter("length");
		test.renameParameter(parTest.getParamName(), "width");
		assertEquals ("width", parTest.getParamName());
	}
	
	@Test
	void testRenameParamType() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("length", "int");
		test.renameParamType("length", "double");
		assertEquals ("double", test.getParameter("length").getParamType());
	}
	
	@Test
	void testGetParameter() {
		Parameter par1 = new Parameter ("firstName", "string");
		Method test = new Method ("getName", "string");
		test.addParameter("firstName", "string");
		assertEquals (par1.getParamName(), test.getParameter("firstName").getParamName());
		assertEquals (par1.getParamType(), test.getParameter("firstName").getParamType());
	}

	@Test
	void testParamExists() {
		Method test = new Method ("getName", "string");
		test.addParameter("firstName", "string");
		assertEquals (true, test.paramExists("firstName"));
	}
	
	@Test
	void testGetParameterList() {
		Parameter par1 = new Parameter ("firstName", "string");
		Parameter par2 = new Parameter ("lastName", "string");
		Method test = new Method ("getName", "string");
		test.addParameter("firstName", "string");
		test.addParameter("lastName", "string");
		assertEquals (par1.getParamName(), test.getParameterList().get(0).getParamName());
		assertEquals (par1.getParamType(), test.getParameterList().get(0).getParamType());
		assertEquals (par2.getParamName(), test.getParameterList().get(1).getParamName());
		assertEquals (par2.getParamType(), test.getParameterList().get(1).getParamType());
	}

}
