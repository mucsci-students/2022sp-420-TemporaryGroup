import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

class ClassTest {

    UMLClass testClass;

    @BeforeEach
    void newClass(){
        testClass = new UMLClass("testClass");
    }
    
    @Test 
    void testAddMethod() {
        assertEquals("testClass", testClass.getClassName());
    }

}