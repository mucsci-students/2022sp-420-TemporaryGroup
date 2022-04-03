package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestMethod {
	
	void testMethodConstructor() {
		Method test = new Method ("calcDeterminant", "float");
		assertNotNull (test, "a new method failed to be created");
	}

	void testGetMethodName() {
		Method test = new Method ("getSize", "double");
		assertEquals ("getSize", test.getMethodName(), "method name is not as expected");
	}

	void testGetMethodType() {
		Method test = new Method ("getSize", "double");
		assertEquals ("double", test.getMethodType(), "method name is not as expected");
	}

	void testRenameMethod() {
		Method test = new Method ("badName", "int");
		test.renameMethod("goodName");
		assertEquals ("goodName", test.getMethodName(), "renameMethod failed");
	}

	void testRenameMethodType() {
		Method test = new Method ("getSize", "double");
		test.renameMethodType("size_t");
		assertEquals ("size_t", test.getMethodType(), "renameMethodType failed");
	}

	void testAddParameter() {
		Method test = new Method ("calcArea", "double");
		int oldSize = test.getParameterList().size();
		test.addParameter("lentgh", "int");
		assertEquals (oldSize + 1, test.getParameterList().size(), "addParameter failed");
	}
	
	/*
	void testRemoveParameter() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("lentgh", "int");
		int oldSize = test.getParameterList().size();
		test.removeParameter("calcArea");
		assertEquals (oldSize - 1, test.getParameterList().size(), "removeParameter failed");
	}*/

	void testRemoveAllParameters() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("lentgh", "int");
		test.addParameter("width", "int");
		test.addParameter("par3", "int");
		test.removeAllParameters();
		assertEquals (0, test.getParameterList().size(), "removeAllParameters failed");
	}

	void testRenameParameter() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("lentgh", "int");
		Parameter parTest = test.getParameter("lentgh");
		test.renameParameter(parTest.getParamName(), "width");
		assertEquals ("width", parTest.getParamName(), "renameParameter failed");
	}
	
	/*
	void testRenameParamType() {
		Method test = new Method ("calcArea", "double");
		test.addParameter("lentgh", "int");
		Parameter parTest = test.getParameter("calcArea");
		test.renameParamType(parTest.getParamName(), "double");
		assertEquals ("double", parTest.getParamType(), "renameParameterType failed");
	}
	*/
	void testGetParameter() {
		Parameter par1 = new Parameter ("firstName", "string");
		Method test = new Method ("getName", "string");
		test.addParameter("firstName", "string");
		assertEquals (par1.getParamName(), test.getParameter("firstName").getParamName(), "getParameter failed");
		assertEquals (par1.getParamType(), test.getParameter("firstName").getParamType(), "getParameter failed");
	}

	void testParamExists() {
		Method test = new Method ("getName", "string");
		test.addParameter("firstName", "string");
		assertEquals (true, test.paramExists("firstName"), "paramExists failed");
	}
	
	/*
	void testGetParameterList() {
		Parameter par1 = new Parameter ("firstName", "string");
		Parameter par2 = new Parameter ("middleName", "string");
		Parameter par3 = new Parameter ("lastName", "string");
		ArrayList<Parameter> pars = new ArrayList<Parameter> () { 
			{
				add(par1);
				add(par2);
				add(par3);
			} 
		};
		Method test = new Method ("getName", "string");
		test.addParameter("firstName", "string");
		test.addParameter("middleName", "string");
		test.addParameter("lastName", "string");
		assertEquals (pars, test.getParameterList(), "getParameterList failed");
	}*/

}
