package TemporaryGroupGradle;
import java.util.ArrayList;

import java.util.HashMap;


public class UMLDiagram {
    
    HashMap<String, UMLClass> umlDiagram = new HashMap<String, UMLClass>();
    ArrayList<UMLRelationship> relationships = new ArrayList<UMLRelationship>();
    private static String[] validTypes = {"aggregation", "composition", "inheritance", "realization"};

    public Boolean addClass(String className){
        if(isValidClassName(className)){
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
        System.out.println("Error when validating name of Class");
        return false;
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
        if(isValidClassName(newClassName)){
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
        System.out.println("Error when validating name of Class");
        return false;
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

    /**
     * Adds a new field in the class if the class exists.
     * @param className
     * @param newField
     * @return
     */
    public Boolean addField(String className, String newField, String newFieldType){
        if(isValidFieldName(newField)){
            if(!getClass(className).fieldExists(newField)){
                getClass(className).addField(newField, newFieldType);
                System.out.println("Added field '" + newField + "' to class '" + className + "'.");
                return true;
            }
            else{
                System.out.println("The field '" + newField + "' already exists in the class '" + className + "'.");
                return false;
            }
        }
        System.out.println("Error when validating name of field");
        return false;
    }

    /**
     * Removes a field from the class if class and field exist.
     * @param className
     * @param removeField
     * @return
     */
    public Boolean removeField(String className, String removeField){
        if(getClass(className).fieldExists(removeField)){
            getClass(className).removeField(removeField);
            System.out.println("Removed field '" + removeField + "' from class '" + className + "'.");
            return true;
        }
        else{
            System.out.println("The field '" + removeField + "' does not exist in the class.");
            return false;
        }
    }

    /**
     * Renames a field in the class if class and field exist.
     * @param className
     * @param oldFieldName
     * @param newFieldName
     * @return
     */
    public Boolean renameField(String className, String oldFieldName, String newFieldName){
        if(isValidFieldName(newFieldName)){
            if((getClass(className).fieldExists(oldFieldName))){
                if(!(getClass(className).fieldExists(newFieldName))){
                    getClass(className).renameField(oldFieldName, newFieldName);
                    System.out.println("Renamed field '" + oldFieldName + "' to '" + newFieldName + "' in class '" + className + "'.");
                    return true;
                }
                else{
                    System.out.println("An field named '" + newFieldName + "' already exists in class '" + className + "'.");
                    return false;
                }  
            }
            else {
                System.out.println("The field named '" + oldFieldName + "' does not exist in class '" + className + "'.");
                return false;
            }
        }
        return false;
    }

    /**
     * Changes a field type if the class and field exist. 
     * @param className
     * @param fieldName
     * @param newFieldType
     * @return
     */
    public Boolean renameFieldType(String className, String fieldName, String newFieldType){
        if(classExists(className)){
            if(getClass(className).fieldExists(fieldName)){
                getClass(className).getField(fieldName).renameFieldType(newFieldType);
                System.out.println("Renamed type in field '" + fieldName + "'.");
                return true;
            }
            else{
                System.out.println("The field named '" + fieldName + "' does not exist in class '" + className + "'.");
                return false;
            }
        }
        return false;
    }

    /**
     * Adds a new method in the class if the class exists.
     * @param className
     * @param newMethod
     * @return
     */
    public Boolean addMethod(String className, String newMethod, String newMethodType){
        if(isValidMethodName(newMethod)){
            if(!getClass(className).methodExists(newMethod)){
                getClass(className).addMethod(newMethod, newMethodType);
                System.out.println("Added method '" + newMethod + "' to class '" + className + "'.");
                return true;
            }
            else{
                System.out.println("The method '" + newMethod + "' already exists in the class '" + className + "'.");
                return false;
            }
        }
        return false;
    }

    /**
     * Removes a method from the class if class and method exist.
     * @param className
     * @param removeMethod
     * @return
     */
    public Boolean removeMethod(String className, String removeMethod){
        if(getClass(className).methodExists(removeMethod)){
            getClass(className).removeMethod(removeMethod);
            System.out.println("Removed method '" + removeMethod + "' from class '" + className + "'.");
            return true;
        }
        else{
            System.out.println("The method '" + removeMethod + "' does not exist in the class.");
            return false;
        }
    }
    
    /**
     * Renames a method in the class if class and method exist.
     * @param className
     * @param oldMethodName
     * @param newMethodName
     * @return
     */
    public Boolean renameMethod(String className, String oldMethodName, String newMethodName){
        if(isValidMethodName(newMethodName)){
            if((getClass(className).methodExists(oldMethodName))){
                if(!(getClass(className).methodExists(newMethodName))){
                    getClass(className).renameMethod(oldMethodName, newMethodName);
                    System.out.println("Renamed method '" + oldMethodName + "' to '" + newMethodName + "' in class '" + className + "'.");
                    return true;
                }
                else{
                    System.out.println("A method named '" + newMethodName + "' already exists in class '" + className + "'.");
                    return false;
                }
            }
            else {
                System.out.println("The method named '" + oldMethodName + "' does not exist in class '" + className + "'.");
                return false;
            }
        }
        return false;
    }

    /**
     * Renames the return type for the method if it exists in the class.
     * @param className
     * @param methodName
     * @param newMethodType
     * @return
     */
    public Boolean renameMethodType(String className, String methodName, String newMethodType){
        if(classExists(className)){
            if(getClass(className).methodExists(methodName)){
                getClass(className).getMethod(methodName).renameMethodType(newMethodType);
                System.out.println("Renamed type in method '" + methodName + "'.");
                return true;
            }
            else{
                System.out.println("The method named '" + methodName + "' does not exist in class '" + className + "'.");
                return false;
            }
        }
        return false;
    }

    /**
     * Adds a parameter to the method if method and class exist.
     * @param className
     * @param methodName
     * @param paramName
     * @return
     */
    public Boolean addParameter(String className, String methodName, String paramName, String paramType){
        if(isValidName(paramName)){
            if(getClass(className).methodExists(methodName)){
                if(!getClass(className).getMethod(methodName).paramExists(paramName)){
                    getClass(className).getMethod(methodName).addParameter(paramName, paramType);
                    System.out.println("Added parameter '" + paramName + "' to method '" + methodName + "'.");
                    return true;
                }
                else{
                    System.out.println("A parameter named '" + paramName + "' already exists in method '" + methodName + "'.");
                    return false;
                }
            }
            System.out.println("The method '" + methodName + "' does not exist in the class.");
            return false;
        }
        return false;
    }

    /**
     * Removes a parameter from the method if the method and class exist.
     * @param className
     * @param methodName
     * @param paramName
     * @return
     */
    public Boolean removeParameter(String className, String methodName, String paramName){
        if(getClass(className).methodExists(methodName)){
            if(getClass(className).getMethod(methodName).paramExists(paramName)){
                getClass(className).getMethod(methodName).removeParameter(paramName);
                System.out.println("Removed parameter '" + paramName + "' from the method '" + methodName + "'.");
                return true;
            }
            else{
                System.out.println("The parameter named '" + paramName + "' does not exist in method '" + methodName + "'.");
                return false;
            }
        }
        System.out.println("The method '" + methodName + "' does not exist in the class.");
        return false;
    }

    /**
     * Renames a parameter in the method if the method and class exist.
     * @param className
     * @param methodName
     * @param oldParamName
     * @param newParamName
     * @return
     */
    public Boolean renameParameter(String className, String methodName, String oldParamName, String newParamName){
        if(isValidName(newParamName)){
            if(getClass(className).methodExists(methodName)){
                if(getClass(className).getMethod(methodName).paramExists(oldParamName)){
                    if(!getClass(className).getMethod(methodName).paramExists(newParamName)){
                        getClass(className).getMethod(methodName).renameParameter(oldParamName, newParamName);
                        System.out.println("Renamed parameter '" + oldParamName + "' to '" + newParamName + "' in method '" + methodName + "'.");
                        return true;
                    }
                    System.out.println("The parameter '" + newParamName + "' already exists in the method '" + methodName + "'.");
                    return false;
                }
                else{
                    System.out.println("The parameter named '" + oldParamName + "' does not exist in method '" + methodName + "'.");
                    return false;
                }
            }
            System.out.println("The method '" + methodName + "' does not exist in the class.");
            return false;
        }
        return false;
    }

    /**
     * Changes the type for the parameter if it exists in the method.
     * @param className
     * @param methodName
     * @param paramName
     * @param newParamType
     * @return
     */
    public Boolean renameParameterType(String className, String methodName, String paramName, String newParamType){
        if(classExists(className)){
            if(getClass(className).methodExists(methodName)){
                if(getClass(className).getMethod(methodName).paramExists(paramName)){
                    getClass(className).getMethod(methodName).getParameter(paramName).renameParamType(newParamType);
                    System.out.println("Renamed type for parameter '" + paramName + "'.");
                    return true;
                }
                else{
                    System.out.println("The parameter named '" + paramName + "' does not exist in method '" + methodName + "'.");
                    return false;
                }
            }
            else{
                System.out.println("The method named '" + methodName + "' does not exist in class '" + className + "'.");
                return false;
            }
        }
        return false;
    }

    /**
     * Removes ALL parameters from the method in the class.
     * @param className
     * @param methodName
     * @return
     */
    public Boolean removeAllParameters(String className, String methodName){
        if(getClass(className).methodExists(methodName)){
            getClass(className).getMethod(methodName).removeAllParameters();
            System.out.println("All of the parameters from the method '" + methodName + "' were removed.");
            return true;
        }
        else{
            System.out.println("The method '" + methodName + "' does not exist in the class.");
            return false;
        }
    }

    /**
     * Helper method to determine if a relationship type is valid
     * @param relType The relationship type being checked
     * @return True if the relationship type is valid, false if it's not
     */
    public boolean isValidType(String relType) {
		for(String ele : validTypes) {
			if(relType.equals(ele)) {
				return true;
			}
		}
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
        // Check that the relationship doesn't already exist
        for(UMLRelationship rel : relationships) {
            if(rel.getSource().equals(source) && rel.getDestination().equals(dest)) {
                System.out.println("A relationship between '" + source + "' and '" + dest + "' already exists.");
                return false;
            }
        }
        // Add the relationship
        UMLRelationship newRel;
        try {
        	newRel = new UMLRelationship(source, dest, type);
        } catch (Exception e){
        	System.out.println("Error when creating relationship");
        	return false;
        }
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
    
    

    /**
     * Checks to see if input is valid for a name.
     * @param name
     * @return
     */
    public static Boolean isValidName(String name) {
		if(name.equals("")) {
			return false;
		}
		if(name.matches("^[-_A-Za-z0-9]+$")) {
			return true;
		}
		else {
			System.out.println("Error: Invalid name. Names can only contain A-Z, a-z, 0-9, and underscore.");
			return false;
		}
    }
    
    /**
     * Checks to see if field name is valid.
     * @param name
     * @return
     */
    public static Boolean isValidFieldName(String name) {
		if(name.equals("")) {
			return false;
		}
		if(name.matches("^[-_A-Za-z0-9]+$")) {
			if(name.charAt(0) >= '0' && name.charAt(0) <= '9') {
				System.out.println("Error: Invalid field name. Field names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Field names must follow standard Java naming conventions.");
				return false;
			}
			else if(name.charAt(0) == ('_')) {
				System.out.println("Error: Invalid field name. Field names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Field names must follow standard Java naming conventions.");
				return false;
			}
			else {
				return true;
			}
		}
		else {
			System.out.println("Error: Invalid field name. Field names can only contain A-Z, a-z, 0-9, and underscore.");
			System.out.println("Field names must follow standard Java naming conventions.");
			return false;
		}
    }
    
    /**
     * Checks to see if method name is valid.
     * @param name
     * @return
     */
    public static Boolean isValidMethodName(String name) {
		if(name.equals("")) {
			return false;
		}
		if(name.matches("^[-_A-Za-z0-9]+$")) {
			if(name.charAt(0) >= '0' && name.charAt(0) <= '9') {
				System.out.println("Error: Invalid method name. Method names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Method names must follow standard Java naming conventions.");
				return false;
			}
			else if(name.charAt(0) == ('_')) {
				System.out.println("Error: Invalid method name. Method names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Method names must follow standard Java naming conventions.");
				return false;
			}
			else {
				return true;
			}
		}
		else {
			System.out.println("Error: Invalid method name. Method names can only contain A-Z, a-z, 0-9, and underscore.");
			System.out.println("Method names must follow standard Java naming conventions.");
			return false;
		}
	}

    /**
     * Checks to see if class name is valid.
     * @param name
     * @return
     */
    public static Boolean isValidClassName(String name) {
		if(name.equals("")) {
			return false;
		}
		if(name.matches("^[-_A-Za-z0-9]+$")) {
			if(name.charAt(0) >= '0' && name.charAt(0) <= '9') {
				System.out.println("Error: Invalid class name. Class names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Class names must follow standard Java naming conventions.");
				return false;
			}
			else if(name.charAt(0) == ('_')) {
				System.out.println("Error: Invalid Class name. Class names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Class names must follow standard Java naming conventions.");
				return false;
			}
			else {
				return true;
			}
		}
		else {
			System.out.println("Error: Invalid Class name. Class names can only contain A-Z, a-z, 0-9, and underscore.");
			System.out.println("Class names must follow standard Java naming conventions.");
			return false;
		}
	}
    
    

}