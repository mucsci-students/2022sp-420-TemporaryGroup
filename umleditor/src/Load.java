
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Load {
	
	public static UMLDiagram loadDiagram = new UMLDiagram();
	
	public static void main(String[] args) throws Exception {
		loadFile();
	}

	public static void loadFile() throws Exception {
		
		HashMap<String,UMLClass> diagramLoadFile;
		
		//User will input name of file first that they want to create (AT THIS MOMENT MUST TYPE .json AT THE END)
		String nameOfFile;
		//User will input the file path that they want to make (test for me C:\\Users\\nickc\\Desktop\) WILL FAIL IF C:/ IS PATH
		String filePath; 
		
		//Inputs for file names as well as the path name
		Scanner fileName = new Scanner(System.in);
		Scanner pathOfFile = new Scanner(System.in);
		
		//File path creation
		System.out.print("Enter file name: ");
		nameOfFile = fileName.nextLine();
				
		System.out.print("Enter file path: "); 
		filePath = pathOfFile.nextLine();
				
		String fileLocation = filePath + nameOfFile;
		System.out.println(fileLocation);
		String json = readFileAsString(fileLocation);
		
		Gson gson = new Gson();
		
		Type typeOfHashMap = new TypeToken<HashMap<String, UMLClass>>() { }.getType();
		HashMap<String, UMLClass> savedDiagram = gson.fromJson(json, typeOfHashMap);
		
		loadDiagram.setUMLDiagram(savedDiagram); 
		
		System.out.println("Successfully loaded!");
		System.out.println(loadDiagram);
		
		//TODO: Convert savedDiagram (Map<String, UMLClass> to HashMap<String,UMLClass>)
	}
	
	public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
	
	
	
}
