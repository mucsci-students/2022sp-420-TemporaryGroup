package TemporaryGroupGradle;

public class Field {

    private String name;
    private String type;

    /**
     * Creates a new field.
     * @param fieldName
     * @param fieldType
     */
    public Field(String fieldName, String fieldType){
        this.name = fieldName;
        this.type = fieldType;
    }

    /**
     * Returns the name of the field.
     * @return fieldName
     */
    public String getFieldName(){
        return this.name;
    }

    /**
     * Returns the type of the field.
     * @return
     */
    public String getFieldType(){
        return this.type;
    }

    /**
     * Renames the field.
     * @param newName
     */
    public void renameField(String newName){
        this.name = newName;
    }

    /**
     * Changes the field type.
     * @param newFieldType
     */
    public void renameFieldType(String newFieldType){
        this.type = newFieldType;
    }


}

