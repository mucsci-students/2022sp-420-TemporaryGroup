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
    void removeClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertTrue(umld.removeClass("Car"));
    }
    
    @Test
    void renameClass() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertTrue(umld.renameClass("Car", "NewCar"));
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
    void renameParameter() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	umld.addParameter("Car", "CarMethod", "CarParam", "string");
    	assertTrue(umld.renameParameter("Car", "CarMethod", "CarParam", "CarNewParam"));
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
    void renameMethod() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addClass("Road");
    	umld.addMethod("Car", "CarMethod", "void");
    	assertTrue(umld.renameMethod("Car", "CarMethod", "CarNewMethod"));
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
    void addFields() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	assertTrue(umld.addField("Car", "CarField", "string"));
    }
    
    @Test
    void removeFields() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertTrue(umld.removeField("Car","CarField"));
    }
   
    @Test 
    void renameFields() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");	
    	assertTrue(umld.renameField("Car","CarField", "CarNewField"));
    }
    
    @Test
    void renameFieldType() {
    	UMLDiagram umld = new UMLDiagram();
    	umld.addClass("Car");
    	umld.addField("Car", "CarField", "string");
    	assertTrue(umld.renameFieldType("Car", "CarField", "int"));
    }
    
   

}
