/**
 * This class represents a relationship between two classes in a UML diagram.
 * It consists of a constructor and getter and setter methods for the class's data.
 */

public class UMLRelationship {
    /**
     * Class data. This is accessed using getter and setter methods below.
     */
    private String src, dest, type, name;

    // Valid relationship types
    private static final String[] validTypes = ["Nondirectional"];

    /**
     * Constructor for a relationship in a UML diagram
     * @param relSrc The source class for the relationship
     * @param relDest The destination class for the relationship
     * @param relType The type of this relationship. Must be: Nondirectional
     * @param relName The name of this relationship.
     */
    public UMLRelationship(String relSrc, String relDest, String relType, String relName) {
        src = relSrc;
        dest = relDest;
        type = relType;
        name = relName;
    }

    /////////////////////////////////////////////////////////////

    /**
     * Returns the source class of this relationship
     * @return This relationship's source class
     */
    public String getSource() {
        return src;
    }

    /**
     * Sets the source class of this relationship
     * @param newSrc New source class for this relationship
     */
    public void setSource(String newSrc) {
        src = newSrc;
    }

    /////////////////////////////////////////////////////////////

    /**
     * Returns the destination class of this relationship
     * @return This relationship's destination class
     */
    public String getDestination() {
        return dest;
    }

    /**
     * Sets the destination class of this relationship
     * @param newDest New destination class for this relationship
     */
    public void setDestination(String newDest) {
        dest = newDest;
    }

    /////////////////////////////////////////////////////////////

    /**
     * Returns the type of this relationship
     * @return This relationship's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of this relationship
     * @param newType New type for this relationship
     */
    public void setType(String newType) {
        type = newType;
    }

    /**
     * Helper method to determine if a relationship type is valid.
     * Currently unused
     * @param relType The relationship type being checked
     * @return True if the relationship type is valid, false if it's not
     */
    /*private boolean isValidType(String relType) {
        // Iterate through array containing valid types
        for(String elem : UMLRelationship.validTypes)
        {
            if(relType.equals(elem))
            {
                // Type matches a valid type in the array
                return true;
            }
        }
        // Reached end of array
        return false;
    }*/

    /////////////////////////////////////////////////////////////

    /**
     * Returns the name of this relationship
     * @return This relationship's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this relationship
     * @param newName New name for this relationship
     */
    public void setName(String newName) {
        name = newName;
    }
}