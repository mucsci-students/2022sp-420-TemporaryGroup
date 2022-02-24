
public class Method {

    private String methodName;

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

}
