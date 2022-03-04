
public class Field {

    private String fieldName;

    /**
     * Creates a new field.
     * @param fieldName
     */
    public Field(String fieldName){
        this.fieldName = fieldName;
    }

    /**
     * Returns the name of the field.
     * @return fieldName
     */
    public String getFieldName(){
        return this.fieldName;
    }

    /**
     * Renames the field.
     * @param newName
     */
    public void renameField(String newName){
        this.fieldName = newName;
    }

}
