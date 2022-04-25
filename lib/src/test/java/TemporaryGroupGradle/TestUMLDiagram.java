package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestUMLDiagram {
    
    @Test
	void testCanUndoTrue() {
        UMLDiagram umld = new UMLDiagram();
        umld.addClass("c1");
        assertTrue(umld.canUndo());
    }

    @Test
    void testCanRedoTrue() {
        UMLDiagram umld = new UMLDiagram();
        umld.addClass("c1");
        umld.undo();
        assertTrue(umld.canRedo());
    }

    @Test
    void testCanUndoFalse() {
        /*UMLDiagram umld = new UMLDiagram();
        umld.undo();
        assertFalse(umld.canUndo());*/
    }

    @Test
    void testCanRedoFalse() {
        UMLDiagram umld = new UMLDiagram();
        umld.redo();
        assertFalse(umld.canRedo());
    }
    
    @Test
    void testClass() {
    	UMLDiagram umld = new UMLDiagram();
    	assertTrue(umld.addClass("Car"));
    }
    
    @Test
    void addClass() {
    	UMLDiagram umld = new UMLDiagram();
    	assertTrue(umld.addClass("Car"));
    }
    
    @Test
    void removeClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertTrue(umld.removeClass("Car"));
    }
    
    @Test
    void removeClassWithRelationship() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Tire");
    	umld.addRelationship("Car", "Tire", "aggregation");
    	assertTrue(umld.removeClass("Car"));
    }
    
    @Test
    void removeFakeClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertFalse(umld.removeClass("FakeClass"));
    }
    
    @Test
    void renameClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertTrue(umld.renameClass("Car", "NewCar"));
    }
    
    @Test
    void renameRepeatClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Tire");
    	assertFalse(umld.renameClass("Car", "Tire"));
    }
    
    @Test
    void repeatClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertFalse(umld.addClass("Car"));
    }
    
    @Test
    void Relationships() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addRelationship("Car", "Road", "aggregation");
    	assertTrue(umld.deleteRelationship("Car", "Road"));
    }
    
    @Test
    void addRelationshipFakeClassSrc() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	assertFalse(umld.addRelationship("FakeClass", "Road", "aggregation"));
    }
    
    @Test
    void addRelationshipFakeClassDest() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	assertFalse(umld.addRelationship("Car", "FakeClass", "aggregation"));
    }
    
    @Test
    void addParameter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertTrue(umld.addParameter("Car", "CarMethod", "CarParam", "string"));
    }
    
    @Test
    void addNonExistantParamClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.addParameter("Car", "FakeMethod", "CarParam", "string"));
    }
    
    @Test
    void addDuplicateParamter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertFalse(umld.addParameter("Car", "CarMethod", "CarParam", "string"));
    }
    
    @Test
    void removeParam() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertTrue(umld.removeParameter("Car", "CarMethod", "CarParam"));
    }
    
    @Test
    void removeNullParam() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.removeParameter("Car", "CarMethod", "NullParam"));
    }
    
    @Test
    void removeNullMethodParam() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.removeParameter("Car", "FakeMethod", "void"));
    }
    
    @Test
    void removeAllParam() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	umld.addParameter("Car", "CarMethod", "CarParam2", "string");
    	assertTrue(umld.removeAllParameters("Car","CarMethod"));
    }
    
    @Test
    void removeAllFakeMethodParams() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.removeAllParameters("Car", "FakeMethod"));
    	
    }
    
    @Test
    void renameParameter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertTrue(umld.renameParameter("Car", "CarMethod", "CarParam", "CarNewParam"));
    }
    
    @Test
    void renameDuplicateParameter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	umld.addParameter("Car", "CarMethod", "DupParam", "string");
    	assertFalse(umld.renameParameter("Car", "CarMethod", "CarParam", "DupParam"));
    }
    
    @Test
    void renameNonExistantParameter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertFalse(umld.renameParameter("Car", "CarMethod", "FakeParam", "string"));
    }
    
    @Test
    void renameNonExistantMethodParameter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertFalse(umld.renameParameter("Car", "FakeMethod", "CarParam", "string"));
    }
    
    @Test
    void renameParameterType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertTrue(umld.renameParameterType("Car", "CarMethod", "CarParam", "int"));
    }
    
    @Test
    void renameFakeMethodParameterType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertFalse(umld.renameParameterType("Car", "FakeMethod", "CarParam", "int"));
    }
    
    @Test
    void renameFakeParameterType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertFalse(umld.renameParameterType("Car", "CarMethod", "FakeParam", "int"));
    }
    
    @Test
    void removeMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertTrue(umld.removeMethod("Car", "CarMethod"));
    }
    
    @Test
    void removeFakeMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.removeMethod("Car", "CarFakeMethod"));
    }
    
    @Test
    void renameMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertTrue(umld.renameMethod("Car", "CarMethod", "CarNewMethod"));
    }
    
    @Test
    void renameNonExistantMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addMethod("Car", "CarMethod","void");
    	assertFalse(umld.renameMethod("Car", "FakeMethod", "CarNewMethod"));
    }
    
    @Test
    void addDuplicateMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.addMethod("Car", "CarMethod", "void"));
    }
    
    @Test
    void renameDuplicateMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addMethod("Car", "CarMethod2", "void");
    	assertFalse(umld.renameMethod("Car", "CarMethod", "CarMethod2"));
    }
    
    @Test
    void renameMethodType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertTrue(umld.renameMethodType("Car", "CarMethod", "void"));
    }
    
    @Test
    void renameNonExistantMethodType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertFalse(umld.renameMethodType("Car", "FakeMethod", "void"));
    }
    
    @Test
    void addFields() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertTrue(umld.addField("Car", "CarField", "string"));
    }
    
    @Test
    void addRepeatField() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertFalse(umld.addField("Car", "CarField", "string"));
    }
    
    @Test
    void addRandomLettersField() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertFalse(umld.addField("Car", "%sÍs", "string"));
    }
    
    
   
    @Test
    void removeFields() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertTrue(umld.removeField("Car","CarField"));
    }
    
    @Test
    void removeNonExistantField() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertFalse(umld.removeField("Car", "FakeField"));
    }
   
    @Test 
    void renameFields() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");	
    	assertTrue(umld.renameField("Car","CarField", "CarNewField"));
    }
    
    @Test
    void renameSameField() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");	
    	umld.addField("Car", "CarField2", "string");
    	assertFalse(umld.renameField("Car", "CarField", "CarField2"));
    }
    
    @Test
    void renameNonExistantField() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertFalse(umld.renameField("Car", "CarFieldFake", "CarField2"));
    }	
    
    @Test
    void renameFieldType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertTrue(umld.renameFieldType("Car", "CarField", "int"));
    }
    
    @Test
    void renameNonExistantFieldType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertFalse(umld.renameFieldType("Car", "CarFieldFake", "int"));
    }
    
    @Test
    void isValidTypeTrue() {
    	UMLDiagram umld = new UMLDiagram();
    	assertTrue(umld.isValidType("aggregation"));
    }
    
    @Test
    void isValidTypeFalse() {
    	UMLDiagram umld = new UMLDiagram();
    	assertFalse(umld.isValidType("FakeType"));
    }
    
    @Test
    void setUMLDiagram() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.setUMLDiagram(umld.umlDiagram);
    }
   
   

}
