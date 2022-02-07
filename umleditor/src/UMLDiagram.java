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
        if(classExists(oldClassName)){
            if(!classExists(newClassName)){
                UMLClass classCopy = umlDiagram.get(oldClassName);
                classCopy.renameClass(newClassName);
                umlDiagram.remove(oldClassName);
                umlDiagram.put(newClassName, classCopy);
                System.out.println("Renamed class '" + oldClassName + "' to '" + newClassName + "'.");
            }else{
                System.out.println("The class '" + newClassName + "' already exists in the diagram.");
            }
            System.out.println("The class '" + oldClassName + "' does not exist in the diagram.");
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
        if(classExists(className)){
            if(newAttribute != getClass(className).getAttribute(newAttribute)){
                getClass(className).addAttribute(newAttribute);
                System.out.println("Added attribute '" + newAttribute + "' to class '" + className + "'.");
            }else{
                System.out.println("The attribute '" + newAttribute + "' already exists in the class.");
            }
        }else{
            System.out.println("The class '" + className + "' does not exist in the diagram.");
        }
    }

    public void removeAttribute(String className, String removeAttribute){
        if(classExists(className)){
            if(getClass(className).attributeExists(removeAttribute)){
                getClass(className).removeAttribute(removeAttribute);
                System.out.println("Removed attribute '" + removeAttribute + "' from class '" + className + "'.");
            }else{
                System.out.println("The attribute '" + removeAttribute + "' does not exist in the class.");
            }
        }else{
            System.out.println("The class '" + className + "' does not exist in the diagram.");
        }
    }

    public void renameAttribute(String className, String oldAttributeName, String newAttributeName){
        if(classExists(className)){
            if(getClass(className).attributeExists(oldAttributeName)){
                if(!(getClass(className).attributeExists(newAttributeName))){
                    getClass(className).renameAttribute(oldAttributeName, newAttributeName);
                    System.out.println("Renamed attribute '" + oldAttributeName + "' to '" + newAttributeName + "' in class '" + className + "'.");
                }else{
                    System.out.println("The attribute '" + newAttributeName + "' already exists in the class.");
                }
            }else{
                System.out.println("The attribute '" + oldAttributeName + "' does not exist in the class.");
            }
        }else{
            System.out.println("The class '" + className + "' does not exist in the diagram.");
        }
    }

}
