import java.util.ArrayList;

/**
 * This is a class that the UML Class diagram can hold with a name and its attributes.
 */

public class UMLClass {
    
    String className;
    ArrayList<String> attributes = new ArrayList<String>();

    public UMLClass(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }

    public void renameClass(String newClassName){
        this.className = newClassName;
    }

    public void addAttribute(String newAttribute){

    }

    public void removeAttribute(String deleteAttribute){

    }

    public void renameAttribute(String renameAttribute){

    }

}
