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
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i) != newAttribute){
              attributes.add(newAttribute);  
            }else{
                System.out.println("The attribute " + newAttribute + " already exists in this class.");
            }
        }
    }

    public void removeAttribute(String deleteAttribute){
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i) == deleteAttribute){
                attributes.remove(i);
            }else{
                System.out.println("The attribute " + deleteAttribute + " does not exist in this class.");
            }
        }
    }

    public void renameAttribute(String oldAttribute, String renameAttribute){
        for(int i = 0; i < attributes.size(); i++){
            if(attributes.get(i) == oldAttribute){
                attributes.set(i, renameAttribute);
            }else{
                System.out.println("The attribute " + oldAttribute + " does not exist in this class.");
            }
        }
    }

}
