package TemporaryGroupGradle;
import java.util.ArrayList;


public class Method {

    private String methodName;
    private String methodType;

    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Method(String methodName, String methodType){
        this.methodName = methodName;
        this.methodType = methodType;
    }

    /**
     * Returns the name of the method.
     * @return methodName
     */
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * Returns the method type.
     * @return methodType
     */
    public String getMethodType(){
        return this.methodType;
    }

    /**
     * Renames the method.
     * @param newName
     */
    public void renameMethod(String newName){
        this.methodName = newName;
    }

    /**
     * Changes the method return type.
     * @param newMethodType
     */
    public void renameMethodType(String newMethodType){
        this.methodType = newMethodType;
    }

    /**
     * Adds a new parameter to the method.
     * @param paramName
     * @param paramType
     */
    public void addParameter(String paramName, String paramType){
        parameters.add(new Parameter(paramName, paramType));
    }

    /**
     * Removes a parameter from the method if it exists.
     * @param paramName
     */
    public void removeParameter(String paramName){
        for(int i = 0; i < parameters.size(); i++){
            if(parameters.get(i).getParamName().equals(paramName)){
                parameters.remove(i);
            }
        }
    }

    /**
     * Removes all parameters from the method.
     */
    public void removeAllParameters(){
        parameters.clear();
    }

    /**
     * Renames a parameter in the method if it exists.
     * @param oldParamName
     * @param newParamName
     */
    public void renameParameter(String oldParamName, String newParamName){
        for(int i = 0; i < parameters.size(); i++){
            if(parameters.get(i).getParamName().equals(oldParamName)){
                getParameter(oldParamName).renameParam(newParamName);
            }
        }
    }

    /**
     * Changes the type of the parameter. 
     * @param paramName
     * @param newParamType
     */
    public void renameParamType(String paramName, String newParamType){
        if(paramExists(paramName)){
            getParameter(paramName).renameParamType(newParamType);
        }
    }

    /**
     * Returns a parameter if it exists.
     * @param paramName
     */
    public Parameter getParameter(String paramName){
        for(int i = 0; i < parameters.size(); i++){
            if(parameters.get(i).getParamName().equals(paramName)){
                return parameters.get(i);
            }
        }
        return null;
    }

    /**
     * Returns true if parameter exists.
     * @param paramName
     * @return
     */
    public Boolean paramExists(String paramName){
        return (getParameter(paramName) != null);
    }

    /**
     * Returns the arraylist of parameters.
     * @return
     */
    public ArrayList<Parameter> getParameterList(){
        return parameters;
    }

}