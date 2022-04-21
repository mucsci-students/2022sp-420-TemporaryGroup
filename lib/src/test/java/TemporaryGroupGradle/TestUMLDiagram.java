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
        UMLDiagram umld = new UMLDiagram();
        umld.undo();
        assertFalse(umld.canUndo());
    }

}
