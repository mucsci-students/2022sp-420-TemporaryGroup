import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Save {
	
	
	public static UMLDiagram saveDiagram = new UMLDiagram();
	public static UMLClass atTest = new UMLClass("create!");

	public static void main(String[] args) throws IOException {
		atTest.addAttribute("24");
		atTest.addAttribute("25");
		
		//TEST TO BE DELETED
		saveDiagram.addClass("test1");
		saveDiagram.addAttribute("test1", "this is a test");
		saveDiagram.addClass("test2");
		saveDiagram.addAttribute("test2", "1");
		
		saveFile();
	}
	
	/*
	 * To save the UMLDiagram, user will be prompted through Java Swing to create a save location or pick a file that they have already saved.
	 * If file picked, system will prompt user if they want to overwrite the save file. 
	 */
	public static Boolean saveFile() throws IOException {
		
		
		//Import diagram to save to JSON
		HashMap<String,UMLClass> diagramSaveFile = saveDiagram.getUMLDiagram();
		//User will input name of file first that they want to create (AT THIS MOMENT MUST TYPE .json AT THE END)
		
		//GSON for JSON file to be converted (Test TO BE DELETED)
		String Json = new Gson().toJson(diagramSaveFile);
		//Assigned location for save file
		String fileLocation = saveFileLocation();
		
		//cancel or 'X' button pressed on save prompt
		if (fileLocation.equals("failed")) {
			System.out.println("Save cancelled: Exiting save...");
			return false;
		}
		
		
		//File creation, if file already exists prompt overwrite method will run
		try {
		      File name = new File(fileLocation);
		      if (name.createNewFile()) {
		        System.out.println("File created: " + name.getName());
		        FileWriter file = new FileWriter(fileLocation);
		        file.write(Json.toString());
		        file.flush();
		        return true;
		      } else {
		        overwrite(fileLocation, Json);
		        return true;
		      }
		    } catch (IOException|IllegalStateException|JsonSyntaxException e) {
		      System.out.println("Error: Please ensure you have named your file and add '.json' at the end.");
		      saveFile();
		      return false;
		    }
		
	}
	
	/*
	 * If file name exists in particular directory, a question will show asking if the user wants to overwrite file.
	 * Returns a boolean to proceed or exit the overwrite.
	 */
	public static Boolean overwrite(String fileLocation, String Json) throws IOException{
		System.out.println("File already exists. Would you like to overwrite save file?");
        System.out.print("Please enter y/n: ");
        
		Scanner a = new Scanner(System.in);
		String answer = a.nextLine();
		answer.toLowerCase();
		
		//Depending on user's answer, it will overwrite, cancel, or ask user to re-enter answer if it cannot read answer.
		if (answer.equals("y") || answer.equals("yes")) {
			FileWriter file = new FileWriter(fileLocation);
			file.write(Json.toString());
			file.flush();
			System.out.println("Successfully saved!");
			return true;
		}
		else if (answer.equals("n") || answer.equals("no")) {
			System.out.println("Save interrupted, cancelling save");
			return false;
		}
		else {
			System.out.println("ERROR: Please input y or n");
			overwrite(fileLocation, Json);
			return false;
		}
			
	}
	/*
	 * UI to show where the user can save their file. Must enter '.json' at the end of user's save file name.
	 * If '.json' is not entered, system will error out and user will have to re-enter save location.
	 */
	public static String saveFileLocation() {
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