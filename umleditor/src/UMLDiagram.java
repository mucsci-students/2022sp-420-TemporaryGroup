import java.util.ArrayList;
import java.util.HashMap;

public class UMLDiagram {
    
    /**
     * The hashmap contains the name (key) and class object (value)
     */
    HashMap<String, UMLClass> umlDiagram = new HashMap<String, UMLClass>();

    /**
     * Holds all the relationships in the class diagram
     */
    ArrayList<String> relationships = new ArrayList<String>();

    /**
     * Adds a class to the uml class diagram if it does not already exist
     */
    public void addClass(String className){
        if(!(classExists(className))){
            UMLClass myClass = new UMLClass(className);
            umlDiagram.put(className, myClass);
        }else{
            System.out.println("The class " + className + " already exists in the diagram.");
        }
    }

    /**
     * Removes a class from the uml class diagram if it exists
     */
    public void removeClass(String className){
        if(classExists(className)){
            for(int i = 0 ; i < relationships.size(); i++){
                // finish implementation once relationship class is done
            }
            umlDiagram.remove(className);
        }else{
            System.out.println("The class " + className + " does not exist in the diagram.");
        }
    }

    /**
     * Renames a class in the uml class diagram if the class exists
     */
    public void renameClass(String oldClassName, String newClassName){
        if(classExists(oldClassName)){
            if(!classExists(newClassName)){
                UMLClass classCopy = umlDiagram.get(oldClassName);
                classCopy.renameClass(newClassName);
                umlDiagram.remove(oldClassName);
                umlDiagram.put(newClassName, classCopy);
            }else{
                System.out.println("The class " + newClassName + " already exists in the diagram.");
            }
            System.out.println("The class " + oldClassName + " does not exist in the diagram.");
        }
    }

    /**
     * Checks to see whether the class exists in the uml class diagram
     */
    public boolean classExists(String className){
        return umlDiagram.containsKey(className);
    }

    /**
     * Returns the class requested by name if it exists in the uml class diagram
     */
    public UMLClass getClass(String className){
        if(umlDiagram.containsKey(className)){
            return umlDiagram.get(className);
        }
        return null;
    }

    /**
     * Adds an attribute to the class in the uml class diagram if the class exists
     */
    public void addAttribute(String className, String newAttribute){
        if(classExists(className)){
            if(newAttribute != getClass(className).getAttribute(newAttribute)){
                getClass(className).addAttribute(newAttribute);
            }else{
                System.out.println("The attribute " + newAttribute + " already exists in the class.");
            }
        }else{
            System.out.println("The class " + className + " does not exist in the diagram.");
        }
    }

    /**
     * Removes an attribute from the class if the attribute and class exist
     */
    public void removeAttribute(String className, String removeAttribute){
        if(classExists(className)){
            if(getClass(className).attributeExists(removeAttribute)){
                getClass(className).removeAttribute(removeAttribute);
            }else{
                System.out.println("The attribute " + removeAttribute + " does not exist in the class.");
            }
        }else{
            System.out.println("The class " + className + " does not exist in the diagram.");
        }
    }

    /**
     * Renames an attribute in the class if the attrivbute and class exist
     * @param className
     * @param oldAttributeName
     * @param newAttributeName
     */
    public void renameAttribute(String className, String oldAttributeName, String newAttributeName){
        if(classExists(className)){
            if(getClass(className).attributeExists(oldAttributeName)){
                if(!(getClass(className).attributeExists(newAttributeName))){
                    getClass(className).renameAttribute(oldAttributeName, newAttributeName);
                }else{
                    System.out.println("The attribute " + newAttributeName + " already exists in the class.");
                }
            }else{
                System.out.println("The attribute " + oldAttributeName + " does not exist in the class.");
            }
        }else{
            System.out.println("The class " + className + " does not exist in the diagram.");
        }
    }

}
