
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;

import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Load {
	
	public static UMLDiagram loadDiagram = new UMLDiagram();
	
	public static void main(String[] args) throws Exception {
		loadFile();
	}

	public static Boolean loadFile() throws Exception {
		
		//file location picked from JSwing
		String fileLocation = loadFileLocation().toString();
		
		//User pressed cancel or 'X' button on load prompt
		if (fileLocation.equals("failed")) {
			System.out.println("Load cancelled: Exiting load...");
			return false;
		}
				
		//System will attempt to load the file, if user picked incorrect file type, it will error out and re-prompt user to load new file. 
		try {
			String json = readFileAsString(fileLocation);
			Gson gson = new Gson();
			
			Type typeOfHashMap = new TypeToken<HashMap<String, UMLClass>>() { }.getType();
			HashMap<String, UMLClass> savedDiagram = gson.fromJson(json, typeOfHashMap);
			
			loadDiagram.setUMLDiagram(savedDiagram); 
			
			System.out.println("Successfully loaded!");
			System.out.println(loadDiagram);
			return true;
		} catch (AccessDeniedException|IllegalStateException|JsonSyntaxException e){
			System.out.println("Error: The file you entered was invalid or cannot be read. Please try again.");
			loadFile();
			return false;
			
		}
		
	}
	
	
	
	public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
	
	
	public static String loadFileLocation() {
	      JFileChooser file = new JFileChooser();
	      file.setMultiSelectionEnabled(true);
	      file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      file.setFileHidingEnabled(false);
	      if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	         java.io.File f = file.getSelectedFile();
	         System.err.println(f.getPath());
	         return f.getPath();
	      }
	      return "failed";
	   }
	
	
}