package TemporaryGroupGradle;
/**
 * This class represents a relationship between two classes in a UML diagram.
 * It consists of a constructor and getter and setter methods for the class's data.
 */

public class UMLRelationship {
    /**
     * Class data. This is accessed using getter and setter methods below.
     */
    private String source, destination, type;

    // Valid relationship types
    private static final String[] validTypes = {"aggregation", "composition", "inheritance", "realization"};

    /**
     * Constructor for a relationship in a UML diagram
     * @param relSrc The source class for the relationship
     * @param relDest The destination class for the relationship
     * @param relType The type of this relationship. 
     * @param relName The name of this relationship.
     */
    public UMLRelationship(String relSrc, String relDest, String relType) {
        source = relSrc;
        destination = relDest;
        type = relType;
    }

    /////////////////////////////////////////////////////////////

    /**
     * Returns the source class of this relationship
     * @return This relationship's source class
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source class of this relationship
     * @param newSrc New source class for this relationship
     */
    public void setSource(String newSrc) {
        source = newSrc;
    }

    /////////////////////////////////////////////////////////////

    /**
     * Returns the destination class of this relationship
     * @return This relationship's destination class
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination class of this relationship
     * @param newDest New destination class for this relationship
     */
    public void setDestination(String newDest) {
        destination = newDest;
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

    public String[] listValidTypes() {
        return validTypes;
    }

    /**
     * Helper method to determine if a relationship type is valid.
     * Currently unused
     * @param relType The relationship type being checked
     * @return True if the relationship type is valid, false if it's not
     */
    public boolean isValidType(String relType) {
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
    }
}