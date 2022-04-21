package TemporaryGroupGradle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestUMLDiagram {
    
    @Test
	void testCanUndo() {
        UMLDiagram umld = new UMLDiagram();
        umld.addClass("c1");
        assertTrue(umld.canUndo());
    }

    @Test
    void testCanRedo() {
        UMLDiagram umld = new UMLDiagram();
        umld.addClass("c1");
        umld.undo();
        assertTrue(umld.canRedo());
    }

}
