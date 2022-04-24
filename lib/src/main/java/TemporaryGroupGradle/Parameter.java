package TemporaryGroupGradle;


public class Parameter {

    private String name;
    private String type;

    /**
     * Creates a parameter.
     * @param paramName
     */
    public Parameter(String paramName, String paramType){
        this.name = paramName;
        this.type = paramType;
    }

    /**
     * Returns the name of the parameter.
     * @return paramName
     */
    public String getParamName(){
        return this.name;
    }

    /**
     * Returns the type of the parameter.
     * @return paramType
     */
    public String getParamType(){
        return this.type;
    }

    /**
     * Renames a parameter.
     * @param newParamName
     */
    public void renameParam(String newParamName){
        this.name = newParamName;
    }

    /**
     * Changes the type of the parameter.
     * @param newParamType
     */
    public void renameParamType(String newParamType){
        this.type = newParamType;
    }
    
}