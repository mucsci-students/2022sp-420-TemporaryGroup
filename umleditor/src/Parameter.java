

public class Parameter {

    private String paramName;

    /**
     * Creates a parameter.
     * @param paramName
     */
    public Parameter(String paramName){
        this.paramName = paramName;
    }

    /**
     * Returns the name of the parameter.
     * @return paramName
     */
    public String getParamName(){
        return paramName;
    }

    /**
     * Renames a parameter.
     * @param newParamName
     */
    public void renameParam(String newParamName){
        this.paramName = newParamName;
    }
    
}
