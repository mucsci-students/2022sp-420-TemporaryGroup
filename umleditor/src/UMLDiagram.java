import java.util.ArrayList;
import java.util.HashMap;

public class UMLDiagram {
    
    HashMap<String, UMLClass> umlDiagram = new HashMap<String, UMLClass>();
    ArrayList<UMLRelationship> relationships = new ArrayList<UMLRelationship>();
    String[] validTypes = {"Nondirectional"};

    public Boolean addClass(String className){
        if(!(classExists(className))){
            UMLClass myClass = new UMLClass(className);
            umlDiagram.put(className, myClass);
            System.out.println("Added class '" + className + "' to the diagram.");
            return true;
        }else{
            System.out.println("The class '" + className + "' already exists in the diagram.");
            return false;
        }
    }

    public Boolean removeClass(String className){
        if(classExists(className)){
            for(int i = 0 ; i < relationships.size(); i++){
                UMLRelationship tempRel = relationships.get(i);
                if((tempRel.getSource().equals(className)) || (tempRel.getDestination().equals(className))){
                    relationships.remove(i);
                }
            }
            umlDiagram.remove(className);
            System.out.println("Removed class '" + className + "' from the diagram.");
            return true;
        }else{
            System.out.println("The class '" + className + "' does not exist in the diagram.");
            return false;
        }
    }

    public Boolean renameClass(String oldClassName, String newClassName){
        if(!classExists(newClassName)){
            UMLClass classCopy = umlDiagram.get(oldClassName);
            classCopy.renameClass(newClassName);
            umlDiagram.remove(oldClassName);
            umlDiagram.put(newClassName, classCopy);
            System.out.println("Renamed class '" + oldClassName + "' to '" + newClassName + "'.");
            return true;
        }
        else{
            System.out.println("A class named '" + newClassName + "' already exists in the diagram.");
            return false;
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

    public Boolean addAttribute(String className, String newAttribute){
        if(!getClass(className).attributeExists(newAttribute)){
            getClass(className).addAttribute(newAttribute);
            System.out.println("Added attribute '" + newAttribute + "' to class '" + className + "'.");
            return true;
        }
        else{
            System.out.println("The attribute '" + newAttribute + "' already exists in the class '" + className + "'.");
            return false;
        }
    }

    public Boolean removeAttribute(String className, String removeAttribute){
        if(getClass(className).attributeExists(removeAttribute)){
            getClass(className).removeAttribute(removeAttribute);
            System.out.println("Removed attribute '" + removeAttribute + "' from class '" + className + "'.");
            return true;
        }
        else{
            System.out.println("The attribute '" + removeAttribute + "' does not exist in the class.");
            return false;
        }
    }

    public Boolean renameAttribute(String className, String oldAttributeName, String newAttributeName){
        if(!(getClass(className).attributeExists(newAttributeName))){
            getClass(className).renameAttribute(oldAttributeName, newAttributeName);
            System.out.println("Renamed attribute '" + oldAttributeName + "' to '" + newAttributeName + "' in class '" + className + "'.");
            return true;
        }
        else {
            System.out.println("An attribute named '" + newAttributeName + "' already exists in class '" + className + "'.");
            return false;
        }
    }

    /**
     * Helper method to determine if a relationship type is valid
     * @param relType The relationship type being checked
     * @return True if the relationship type is valid, false if it's not
     */
    private boolean isValidType(String relType)
    {
        // Array of valid types
        // Iterate through array containing valid types
        for(String elem : validTypes)
        {
            if(relType == elem)
            {
                // Type matches a valid type in the array
                return true;
            }
        }
        // Reached end of array
        return false;
    }

    /**
     * Add a relationship to the diagram
     * The same two classes cannot have multiple relationships with the same name.
     * @param source The source class for this relationship
     * @param dest The destination class for this relationship
     * @param type The type of this relationship
     * @param name The name of this relationship
     */
    public Boolean addRelationship(String source, String dest, String type) {
        // Check that the relationship is valid
        if(!classExists(source)) {
            System.out.println("The class '" + source + "' does not exist.");
            return false;
        }
        if(!classExists(dest)) {
            System.out.println("The class '" + source + "' does not exist.");
            return false;
        }
        if(!isValidType(type)) {
            System.out.println("'" + type + "' is not a valid relationship type.");
            return false;
        }
        // Check that the relationship doesn't already exist
        for(UMLRelationship rel : relationships) {
            if(rel.getSource().equals(source) && rel.getDestination().equals(dest)) {
                System.out.println("A relationship between '" + source + "' and '" + dest + "' already exists.");
                return false;
            }
        }
        // Add the relationship
        UMLRelationship newRel = new UMLRelationship(source, dest, type);
        relationships.add(newRel);
        System.out.println("Added new relationship between class '" + source + "' and class '" + dest + "'.");
        return true;
    }

    /**
     * Delete a relationship from the diagram.
     * @param source The source class for the relationship to delete
     * @param dest The destination class for the relationship to delete
     * @param name The name of the relationship to delete
     */
    public Boolean deleteRelationship(String source, String dest) {
        for(UMLRelationship rel : relationships) {
            if(rel.getSource().equals(source) && rel.getDestination().equals(dest)) {
                relationships.remove(rel);
                System.out.println("Removed relationship between class '" + source + "' and class '" + dest + "'.");
                return true;
            }
        }
        System.out.println("A relationship between '" + source + "' and '" + dest + "' does not exist.");
        return false;
    }

    /**
     * Rename a relationship in the diagram
     * The same two classes cannot have multiple relationships with the same name.
     * @param source The source class for this relationship
     * @param dest The destination class for this relationship
     * @param oldName The current name of this relationship
     * @param newName The new name for this relationship
     */
    
    public void setUMLDiagram(HashMap<String, UMLClass> c) {
    	umlDiagram = c; 
    }
    
    public HashMap<String, UMLClass> getUMLDiagram() {
    	return umlDiagram; 
    }

}
