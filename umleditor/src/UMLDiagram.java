import java.util.ArrayList;
import java.util.HashMap;

public class UMLDiagram {
    
    HashMap<String, UMLClass> umlDiagram = new HashMap<String, UMLClass>();
    ArrayList<String> relationships = new ArrayList<String>();

    public void addClass(String className){
        if(!(classExists(className))){
            UMLClass myClass = new UMLClass(className);
            umlDiagram.put(className, myClass);
            System.out.println("Added class '" + className + "' to the diagram.");
        }else{
            System.out.println("The class '" + className + "' already exists in the diagram.");
        }
    }

    public void removeClass(String className){
        if(classExists(className)){
            for(int i = 0 ; i < relationships.size(); i++){
                // finish implementation once relationship class is done
            }
            umlDiagram.remove(className);
            System.out.println("Removed class '" + className + "' from the diagram.");
        }else{
            System.out.println("The class '" + className + "' does not exist in the diagram.");
        }
    }

    public void renameClass(String oldClassName, String newClassName){
        if(!classExists(newClassName)){
            UMLClass classCopy = umlDiagram.get(oldClassName);
            classCopy.renameClass(newClassName);
            umlDiagram.remove(oldClassName);
            umlDiagram.put(newClassName, classCopy);
            System.out.println("Renamed class '" + oldClassName + "' to '" + newClassName + "'.");
        }
        else{
            System.out.println("A class named '" + newClassName + "' already exists in the diagram.");
        }
    }

    public boolean classExists(String className){
        return umlDiagram.containsKey(className);
    }

    public UMLClass getClass(String className){
        if(umlDiagram.containsKey(className)){
            return umlDiagram.get(className);
        }
        return null;
    }

    public void addAttribute(String className, String newAttribute){
        if(!getClass(className).attributeExists(newAttribute)){
            getClass(className).addAttribute(newAttribute);
            System.out.println("Added attribute '" + newAttribute + "' to class '" + className + "'.");
        }
        else{
            System.out.println("The attribute '" + newAttribute + "' already exists in the class '" + className + "'.");
        }
    }

    public void removeAttribute(String className, String removeAttribute){
        if(getClass(className).attributeExists(removeAttribute)){
            getClass(className).removeAttribute(removeAttribute);
            System.out.println("Removed attribute '" + removeAttribute + "' from class '" + className + "'.");
        }
        else{
            System.out.println("The attribute '" + removeAttribute + "' does not exist in the class.");
        }
    }

    public void renameAttribute(String className, String oldAttributeName, String newAttributeName){
        if(!(getClass(className).attributeExists(newAttributeName))){
            getClass(className).renameAttribute(oldAttributeName, newAttributeName);
            System.out.println("Renamed attribute '" + oldAttributeName + "' to '" + newAttributeName + "' in class '" + className + "'.");
        }
        else {
            System.out.println("An attribute named '" + newAttributeName + "' already exists in class '" + className + "'.");
        }
    }
    
    public void setUMLDiagram(HashMap<String, UMLClass> c) {
    	
    	umlDiagram = c; 
    }
    
    public HashMap<String, UMLClass> getUMLDiagram() {
    	return umlDiagram; 
    }

}
