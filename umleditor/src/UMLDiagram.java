import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UMLDiagram {
    
    HashMap<String, UMLClass> umlDiagram = new HashMap<String, UMLClass>();

    public void addClass(String className){
        if(!(classExists(className))){
            UMLClass myClass = new UMLClass(className);
            umlDiagram.put(className, myClass);
        }else{
            System.out.println("The class " + className + " already exists in the diagram.");
        }
    }

    public void removeClass(String className){
        if(classExists(className)){
            umlDiagram.remove(className);
        }else{
            System.out.println("The class " + className + " does not exist in the diagram.");
        }
    }

    public void renameClass(String oldClassName, String newClassName){
        if(classExists(oldClassName) && !(classExists(newClassName))){
            UMLClass classCopy = umlDiagram.get(oldClassName);
            classCopy.renameClass(newClassName);
            umlDiagram.remove(oldClassName);
            umlDiagram.put(newClassName, classCopy);
        }
    }

    public boolean classExists(String className){
        return umlDiagram.containsKey(className);
    }

}
