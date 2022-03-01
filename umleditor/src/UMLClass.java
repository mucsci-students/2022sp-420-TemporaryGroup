import java.util.ArrayList;

/**
 * This is a class that the UML Class diagram can hold with a name and its attributes.
 */

public class UMLClass {
    
    String className;
    ArrayList<Field> fields = new ArrayList<Field>();
    ArrayList<Method> methods = new ArrayList<Method>();

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

    /**
     * Renames a class.
     * @param newClassName
     */
    public void renameClass(String newClassName){
        this.className = newClassName;
    }

    /**
     * Returns the array list of fields that the class contains.
     * @return ArrayList<Field>
     */
    public ArrayList<Field> getFields() {
        return this.fields;
    }

    /**
     * Adds a new field to the class.
     * @param newField
     */
    public void addField(String newField) {
       fields.add(new Field(newField));  
    }

    /**
     * Removes a field from the class if it exists. 
     * @param deleteField
     */
    public void removeField(String deleteField){
        for(int i = 0; i < fields.size(); i++){
            if(fields.get(i).getFieldName().equals(deleteField)){
                fields.remove(i); 
            }
        }
    }

    /**
     * Renames a field in the class if it exists and new name doesn't exist yet. 
     * @param oldField
     * @param renameField
     */
    public void renameField(String oldField, String renameField){
        for(int i = 0; i < fields.size(); i++){
            if(fields.get(i).getFieldName().equals(oldField)){
                getField(oldField).renameField(renameField);
            }
        }
    }

    /**
     * Returns a field by name. 
     * @param fieldName
     * @return field
     */
    public Field getField(String fieldName){
        for(int i = 0; i < fields.size(); i++){
            if(fields.get(i).getFieldName().equals(fieldName)){
                return fields.get(i);
            }
        }
        return null;
    }

    /**
     * Checks to see if the field in the class exists. 
     * @param fieldName
     * @return boolean
     */
    public boolean fieldExists(String fieldName){
        return (getField(fieldName) != null);
    }

}
