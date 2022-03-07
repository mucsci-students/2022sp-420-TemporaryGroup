package TemporaryGroupGradle;


public class Parameter {

    private String paramName;
    private String paramType;

    /**
     * Creates a parameter.
     * @param paramName
     */
    public Parameter(String paramName, String paramType){
        this.paramName = paramName;
        this.paramType = paramType;
    }

    /**
     * Returns the name of the parameter.
     * @return paramName
     */
    public String getParamName(){
        return this.paramName;
    }

    /**
     * Returns the type of the parameter.
     * @return paramType
     */
    public String getParamType(){
        return this.paramType;
    }

    /**
     * Renames a parameter.
     * @param newParamName
     */
    public void renameParam(String newParamName){
        this.paramName = newParamName;
    }

    /**
     * Changes the type of the parameter.
     * @param newParamType
     */
    public void renameParamType(String newParamType){
        this.paramType = newParamType;
    }
    
}