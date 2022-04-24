package TemporaryGroupGradle;
import java.awt.Point;
import java.util.ArrayList;

/**
 * This is a class that the UML Class diagram can hold with a name and its attributes.
 */

public class UMLClass {
    
    String className;
    ArrayList<Field> fields = new ArrayList<Field>();
    ArrayList<Method> methods = new ArrayList<Method>();
    Point location = new Point (0,0);

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
        return fields;
    }
    
    public ArrayList<Method> getMethods() {
    	return methods;
    }

    /**
     * Adds a new field to the class.
     * @param newField
     * @param newFieldType
     */
    public void addField(String newField, String newFieldType) {
       fields.add(new Field(newField, newFieldType));  
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
     * Changes the type of the field in the class if it exists.
     * @param fieldName
     * @param newFieldType
     */
    public void renameFieldType(String fieldName, String newFieldType){
        if(fieldExists(fieldName)){
            getField(fieldName).renameFieldType(newFieldType);
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

    /**
     * Adds a new method to the class.
     * @param newMethod
     * @param newMethodType
     */
    public void addMethod(String newMethod, String newMethodType) {
        methods.add(new Method(newMethod, newMethodType));  
     }

     /**
     * Removes a method from the class if it exists. 
     * @param deleteMethod
     */
    public void removeMethod(String deleteMethod){
        for(int i = 0; i < methods.size(); i++){
            if(methods.get(i).getMethodName().equals(deleteMethod)){
                methods.remove(i); 
            }
        }
    }

    /**
     * Renames a method in the class if it exists and new name doesn't exist yet. 
     * @param oldMethod
     * @param renameMethod
     */
    public void renameMethod(String oldMethod, String renameMethod){
        for(int i = 0; i < methods.size(); i++){
            if(methods.get(i).getMethodName().equals(oldMethod)){
                getMethod(oldMethod).renameMethod(renameMethod);
            }
        }
    }

    /**
     * Changes the type of the method if it exists in the class.
     * @param methodName
     * @param newMethodType
     */
    public void renameMethodType(String methodName, String newMethodType){
        if(methodExists(methodName)){
            getMethod(methodName).renameMethodType(newMethodType);
        }
    }

    /**
     * Returns a method by name. 
     * @param methodName
     * @return method
     */
    public Method getMethod(String methodName){
        for(int i = 0; i < methods.size(); i++){
            if(methods.get(i).getMethodName().equals(methodName)){
                return methods.get(i);
            }
        }
        return null;
    }

    /**
     * Checks to see if the method in the class exists. 
     * @param methodName
     * @return boolean
     */
    public boolean methodExists(String methodName){
        return (getMethod(methodName) != null);
    }

    /**
     * Adds a new parameter to the method in the class.
     * @param methodName
     * @param paramName
     * @param paramType
     */
    public void addParameter(String methodName, String paramName, String paramType){
        if(methodExists(methodName)){
            getMethod(methodName).addParameter(paramName, paramType);
        }
    }

    /**
     * Removes a parameter from the method in the class.
     * @param methodName
     * @param paramName
     */
    public void removeParameter(String methodName, String paramName){
        if(methodExists(methodName)){
            getMethod(methodName).removeParameter(paramName);
        }
    }

    /**
     * Removes ALL of the parameters from the method in the class.
     * @param methodName
     */
    public void removeAllParameters(String methodName){
        if(methodExists(methodName)){
            getMethod(methodName).removeAllParameters();
        }
    }

    /**
     * Renames a parameter in a method.
     * @param methodName
     * @param oldParamName
     * @param newParamName
     */
    public void renameParameter(String methodName, String oldParamName, String newParamName){
        if(methodExists(methodName)){
            getMethod(methodName).renameParameter(oldParamName, newParamName);
        }
    }
    
    public void setLoc (Point loc) {
    	location.setLocation(loc);
    }

}