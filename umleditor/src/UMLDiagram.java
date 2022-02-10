import java.util.ArrayList;
import java.util.HashMap;

public class UMLDiagram {
    
    HashMap<String, UMLClass> umlDiagram = new HashMap<String, UMLClass>();
    ArrayList<UMLRelationship> relationships = new ArrayList<UMLRelationship>();

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

    /**
     * Helper method to determine if a relationship type is valid
     * @param relType The relationship type being checked
     * @return True if the relationship type is valid, false if it's not
     */
    private boolean isValidType(String relType)
    {
        // Array of valid types
        private String[] validTypes = ["Nondirectional"];
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
    public void addRelationship(String source, String dest, String type, String name) {
        bool addRel = true;
        // Check that the relationship is valid
        if(!classExists(source)) {
            System.out.println("The class " + source + " does not exist.");
            addRel = false;
        }
        if(!classExists(dest)) {
            System.out.println("The class " + source + " does not exist.");
            addRel = false;
        }
        if(!isValidType(type)) {
            System.out.println(type " is not a valid relationship type.");
            addRel = false;
        }
        // Check that the relationship doesn't already exist
        for(rel : relationships) {
            if(rel.getSource() == source && rel.getDest() == dest && rel.getName() == name) {
                System.out.println("A relationship between " + source + " and " + dest + " named " + name + " already exists.");
                addRel = false;
            }
        }
        // Add the relationship
        if(addRel) {
            UMLRelationship newRel = new UMLRelationship(source, dest, type, name);
            relationships.add(newRel);
        }
    }

    /**
     * Delete a relationship from the diagram.
     * @param source The source class for the relationship to delete
     * @param dest The destination class for the relationship to delete
     * @param name The name of the relationship to delete
     */
    public void deleteRelationship(String source, String dest, String name) {
        bool removed = false;
        for(rel : relationships) {
            if(rel.getSource() == source && rel.getDest() == dest && rel.getName() == name) {
                relationships.remove(rel);
                removed = false;
            }
        }
        if(!removed) {
            System.out.println("A relationship between " + source + " and " + dest + " named " + name + " does not exist.");
        }
    }

    /**
     * Rename a relationship in the diagram
     * The same two classes cannot have multiple relationships with the same name.
     * @param source The source class for this relationship
     * @param dest The destination class for this relationship
     * @param oldName The current name of this relationship
     * @param newName The new name for this relationship
     */
    public void renameRelationship(String source, String dest, String oldName, String newName) {
        bool renameRel = true;
        // Check that the relationship is valid
        if(!classExists(source)) {
            System.out.println("The class " + source + " does not exist.");
            renameRel = false;
        }
        if(!classExists(dest)) {
            System.out.println("The class " + source + " does not exist.");
            renameRel = false;
        }
        // Check that the relationship won't become a duplicate
        for(rel : relationships) {
            if(rel.getSource() == source && rel.getDest() == dest && rel.getName() == oldName) {
                System.out.println("A relationship between " + source + " and " + dest + " named " + oldName + " already exists.");
                renameRel = false;
            }
        }
        // Find and rename relationship
        if(renameRel) {
            bool renamed = false;
            for(rel : relationships) {
                if(rel.getSource() == source && rel.getDest() == dest && rel.getName() == oldName) {
                    rel.setName(newName);
                    renamed = true;
                }
            }
            if(!renamed)
            {
                System.out.println("A relationship between " + source + " and " + dest + " named " + oldName + " does not exist.");
            }
        }
    }
    
    public void setUMLDiagram(HashMap<String, UMLClass> c) {
    	
    	umlDiagram = c; 
    }
    
    public HashMap<String, UMLClass> getUMLDiagram() {
    	return umlDiagram; 
    }

}
