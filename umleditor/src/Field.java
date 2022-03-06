
public class Field {

    private String fieldName;
    private String fieldType;

    /**
     * Creates a new field.
     * @param fieldName
     * @param fieldType
     */
    public Field(String fieldName, String fieldType){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    /**
     * Returns the name of the field.
     * @return fieldName
     */
    public String getFieldName(){
        return this.fieldName;
    }

    /**
     * Returns the type of the field.
     * @return
     */
    public String getFieldType(){
        return this.fieldType;
    }

    /**
     * Renames the field.
     * @param newName
     */
    public void renameField(String newName){
        this.fieldName = newName;
    }

    /**
     * Changes the field type.
     * @param newFieldType
     */
    public void renameFieldType(String newFieldType){
        this.fieldType = newFieldType;
    }

}