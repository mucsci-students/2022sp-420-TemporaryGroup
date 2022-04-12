package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestClass {
	
	@Test
	void testUMLClassConstructor() {
		UMLClass test = new UMLClass ("car");
		assertNotNull (test);
	}

	@Test
	void testGetClassName() {
		UMLClass test = new UMLClass ("car");
		assertEquals ("car", test.getClassName());
	}

	@Test
	void testRenameClass() {
		UMLClass test = new UMLClass ("car");
		test.renameClass("bank");
		assertEquals ("bank", test.getClassName());
	}
	
	@Test
	void testGetFields() {
		Field f1 = new Field ("wheels", "int");
		Field f2 = new Field ("tankCapacity", "double");
		UMLClass test = new UMLClass ("car");
		test.addField("wheels", "int");
		test.addField("tankCapacity", "double");
		assertEquals (f1.getFieldName(), test.getFields().get(0).getFieldName());
		assertEquals (f1.getFieldType(), test.getFields().get(0).getFieldType());
		assertEquals (f2.getFieldName(), test.getFields().get(1).getFieldName());
		assertEquals (f2.getFieldType(), test.getFields().get(1).getFieldType());
	}

	@Test
	void testGetMethods() {
		Method m1 = new Method ("tools", "string");
		Method m2 = new Method ("setBreaks", "bool");
		UMLClass test = new UMLClass ("car");
		test.addMethod("tools", "string");
		test.addMethod("setBreaks", "bool");
		assertEquals (m1.getMethodName(), test.getMethods().get(0).getMethodName());
		assertEquals (m1.getMethodType(), test.getMethods().get(0).getMethodType());
		assertEquals (m2.getMethodName(), test.getMethods().get(1).getMethodName());
		assertEquals (m2.getMethodType(), test.getMethods().get(1).getMethodType());
	}
	
	@Test
	void testAddField() {
		Field f1 = new Field ("firstName", "string");
		UMLClass test = new UMLClass ("Students");
		test.addField("firstName", "string");
		assertEquals (f1.getFieldName(), test.getFields().get(0).getFieldName());
		assertEquals (f1.getFieldType(), test.getFields().get(0).getFieldType());
		
	}
	
	@Test
	void testRemoveField() {
		UMLClass test = new UMLClass ("Students");
		test.addField("firstName", "string");
		int size = test.getFields().size();
		test.removeField("firstName");
		assertEquals (size - 1, test.getFields().size());
		assertEquals (false, test.fieldExists("firstName"));

	}

	@Test
	void testRenameField() {
		UMLClass test = new UMLClass ("Students");
		test.addField("lastName", "string");
		test.renameField("lastName", "Surname");
		assertEquals ("Surname", test.getField("Surname").getFieldName());
	}

	@Test
	void testRenameFieldType() {
		UMLClass test = new UMLClass ("Students");
		test.addField("lastName", "string");
		test.renameFieldType("lastName", "char *");
		assertEquals ("char *", test.getField("lastName").getFieldType());
	}
	
	@Test
	void testGetField() {
		Field f1 = new Field ("wheels", "int");
		UMLClass test = new UMLClass ("car");
		test.addField("wheels", "int");
		assertEquals (f1.getFieldName(), test.getFields().get(0).getFieldName());
		assertEquals (f1.getFieldType(), test.getFields().get(0).getFieldType());
	
	}
	
	@Test
	void testFieldExists() {
		UMLClass test = new UMLClass ("car");
		test.addField("wheels", "int");
		assertEquals (true, test.fieldExists("wheels"));
	}
	
	@Test
	void testAddMethod() {
		Method m1 = new Method ("firstName", "string");
		UMLClass test = new UMLClass ("Students");
		test.addMethod("firstName", "string");
		assertEquals (m1.getMethodName(), test.getMethods().get(0).getMethodName());
		assertEquals (m1.getMethodType(), test.getMethods().get(0).getMethodType());
	}

	@Test
	void testRemoveMethod() {
		UMLClass test = new UMLClass ("Students");
		test.addMethod("firstName", "string");
		int size = test.getMethods().size();
		test.removeMethod("firstName");
		assertEquals (size - 1, test.getMethods().size());
		assertEquals (false, test.methodExists("firstName"));
	}

	@Test
	void testRenameMethod() {
		UMLClass test = new UMLClass ("Students");
		test.addMethod("firstName", "string");
		test.renameMethod("firstName", "fullName");
		assertEquals ("fullName", test.getMethod("fullName").getMethodName());
	}

	@Test
	void testRenameMethodType() {
		UMLClass test = new UMLClass ("Students");
		test.addMethod("firstName", "string");
		test.renameMethodType("firstName", "char *");
		assertEquals ("char *", test.getMethod("firstName").getMethodType());
	}

	@Test
	void testGetMethod() {
		Method m1 = new Method ("firstName", "string");
		UMLClass test = new UMLClass ("Students");
		test.addMethod("firstName", "string");
		assertEquals (m1.getMethodName(), test.getMethods().get(0).getMethodName());
		assertEquals (m1.getMethodType(), test.getMethods().get(0).getMethodType());
	}
	
	@Test
	void testMethodExists() {
		UMLClass test = new UMLClass ("bank");
		test.addMethod("accountId", "string");
		assertEquals(true, test.methodExists("accountId"));
	}

	@Test
	void testAddParameter() {
		UMLClass test = new UMLClass ("building");
		test.addMethod("address", "string");
		Parameter p1 = new Parameter ("street number", "int");
		test.addParameter("address", "street number", "int");
		assertEquals (p1.getParamName(), test.getMethod("address").getParameterList().get(0).getParamName());
		assertEquals (p1.getParamType(), test.getMethod("address").getParameterList().get(0).getParamType());
	}

	@Test
	void testRemoveParameter() {
		UMLClass test = new UMLClass ("building");
		test.addMethod("address", "string");
		test.addParameter("address", "street number", "int");
		int size = test.getMethod("address").getParameterList().size();
		test.removeParameter("address", "street number");
		assertEquals (size - 1, test.getMethod("address").getParameterList().size());
	}

	@Test
	void testRemoveAllParameters() {
		UMLClass test = new UMLClass ("building");
		test.addMethod("address", "string");
		test.addParameter("address", "street number", "int");
		test.addParameter ("address", "street", "string");
		test.addParameter("address", "state", "string");
		test.removeAllParameters("address");
		assertEquals (0, test.getMethod("address").getParameterList().size());
	}
	
	@Test
	void testRenameParameter() {
		UMLClass test = new UMLClass ("building");
		test.addMethod("address", "string");
		test.addParameter("address", "street number", "int");
		test.renameParameter("address", "street number", "number");
		assertEquals ("number", test.getMethod("address").getParameter("number").getParamName());
	}
}