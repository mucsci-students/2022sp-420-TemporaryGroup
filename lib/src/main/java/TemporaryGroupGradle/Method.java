package TemporaryGroupGradle;
import java.util.ArrayList;


public class Method {

    private String name;
    private String return_type;

    private ArrayList<Parameter> params = new ArrayList<Parameter>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Method(String methodName, String methodType){
        this.name = methodName;
        this.return_type = methodType;
    }

    /**
     * Returns the name of the method.
     * @return methodName
     */
    public String getMethodName(){
        return this.name;
    }

    /**
     * Returns the method type.
     * @return methodType
     */
    public String getMethodType(){
        return this.return_type;
    }

    /**
     * Renames the method.
     * @param newName
     */
    public void renameMethod(String newName){
        this.name = newName;
    }

    /**
     * Changes the method return type.
     * @param newMethodType
     */
    public void renameMethodType(String newMethodType){
        this.return_type = newMethodType;
    }

    /**
     * Adds a new parameter to the method.
     * @param paramName
     * @param paramType
     */
    public void addParameter(String paramName, String paramType){
        params.add(new Parameter(paramName, paramType));
    }

    /**
     * Removes a parameter from the method if it exists.
     * @param paramName
     */
    public void removeParameter(String paramName){
        for(int i = 0; i < params.size(); i++){
            if(params.get(i).getParamName().equals(paramName)){
                params.remove(i);
            }
        }
    }

    /**
     * Removes all parameters from the method.
     */
    public void removeAllParameters(){
        params.clear();
    }

    /**
     * Renames a parameter in the method if it exists.
     * @param oldParamName
     * @param newParamName
     */
    public void renameParameter(String oldParamName, String newParamName){
        for(int i = 0; i < params.size(); i++){
            if(params.get(i).getParamName().equals(oldParamName)){
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
        for(int i = 0; i < params.size(); i++){
            if(params.get(i).getParamName().equals(paramName)){
                return params.get(i);
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
        return params;
    }

}