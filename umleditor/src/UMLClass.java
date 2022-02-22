import java.util.ArrayList;

/**
 * This is a class that the UML Class diagram can hold with a name and its attributes.
 */

public class UMLClass {
    
    String className;
    ArrayList<String> methods = new ArrayList<String>();
    ArrayList<String> fields = new ArrayList<String>();

    /**
     * Constructor for a class in the uml diagram.
     * @param className
     */
    public UMLClass(String className){
        this.className = className;
    }

    /**
     * Returns a String of the class name. 
     * @return className
     */
    public String getClassName(){
        return this.className;
    }

    public ArrayList<String> getAttributes() {
        return this.attributes;
    }

    /**
     * Renames a class.
     * @param newClassName
     */
    public void renameClass(String newClassName){
        this.className = newClassName;
    }

    /**
     * Adds a new attribute to the class.
     * @param newAttribute
     */
    public void addAttribute(String newAttribute) {
       attributes.add(newAttribute);  
    }

    /**
     * Removes an attribute from the class if it exists. 
     * @param deleteAttribute
     */
    public void removeAttribute(String deleteAttribute){
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i).equals(deleteAttribute)){
                attributes.remove(i);
            }
        }
    }

    /**
     * Renames an attribute in the class if it exists and new name doesn't exist yet. 
     * @param oldAttribute
     * @param renameAttribute
     */
    public void renameAttribute(String oldAttribute, String renameAttribute){
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i).equals(oldAttribute)){
                attributes.set(i, renameAttribute);
            }
        }
    }

    /**
     * Returns an attribute by name. 
     * @param attributeName
     * @return attribute name
     */
    public String getAttribute(String attributeName){
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i) == attributeName){
                return attributes.get(i);
            }
        }
        return null;
    }

    /**
     * Checks to see if the attribute in the class exists. 
     * @param attributeName
     * @return boolean
     */
    public boolean attributeExists(String attributeName){
        for(int i = 0; i < attributes.size(); i++) {
            if(attributes.get(i).equals(attributeName)) {
                return true;
            }
        }
        return false;
    }

}
