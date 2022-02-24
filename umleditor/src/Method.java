import java.util.ArrayList;

public class Method {

    private String methodName;
    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Method(String methodName){
        this.methodName = methodName;
    }

    /**
     * Returns the name of the method.
     * @return methodName
     */
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * Renames the method.
     * @param newName
     */
    public void renameMethod(String newName){
        this.methodName = newName;
    }

    /**
     * Adds a new parameter to the method.
     * @param paramName
     */
    public void addParameter(String paramName){
        parameters.add(new Parameter(paramName));
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
     * Returns the arraylist of parameters.
     * @return
     */
    public ArrayList<Parameter> getParameterList(){
        return parameters;
    }

}
