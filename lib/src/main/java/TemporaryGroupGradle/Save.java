package TemporaryGroupGradle;
import java.io.File;



import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

//import javax.swing.JFileChooser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;

public class Save {
	
	
	UMLDiagram saveDiagram = new UMLDiagram();
	UMLClass atTest = new UMLClass("create!");
	ArrayList<UMLClass> classes;
	SaveTemplate saveTemplate = new SaveTemplate();

	
	/*
	 * To save the UMLDiagram, user will be prompted through Java Swing to create a save location or pick a file that they have already saved.
	 * If file picked, system will prompt user if they want to overwrite the save file. 
	 */
	public Boolean saveFile() throws IOException {
		
		//User will input name of file first that they want to create (AT THIS MOMENT MUST TYPE .json AT THE END)
		
		//GSON for JSON file to be converted (Test TO BE DELETED)
/*		Iterator<HashMap.Entry<String, UMLClass>> hmIter = saveDiagram.Classes.entrySet().iterator();
		while (hmIter.hasNext()) {
			
			Map.Entry<String, UMLClass> mapElem = (Map.Entry<String, UMLClass>) hmIter.next();
			classes.add(saveDiagram.Classes.get(mapElem.getKey()));
		}
		
*/
		Scanner filepath = new Scanner(System.in);
		Scanner filename = new Scanner(System.in);
		
		System.out.println("Please enter a name for your file (do not add extension) or q for quitting");
		System.out.print("> ");
		String FileName = filename.next();
		if (FileName.equals("q") || FileName.equals("q ") || FileName.equals(" q")) {
			return false;
		}
		System.out.println("Please enter a path for the file");
		System.out.print("> ");
		String FilePath = filepath.next();
		
		classes = new ArrayList<>(saveDiagram.umlDiagram.values());
		saveTemplate.classes = classes;
		saveTemplate.relationships = saveDiagram.relationships;
		String Json = new Gson().toJson(saveTemplate);	
		
		ObjectMapper mapper = new ObjectMapper();
		
		Object jsonObject = mapper.readValue(Json, Object.class);
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		
			

		//Assigned location for save file
		//String fileLocation = saveFileLocation();
		String fileLocation = FilePath + FileName + ".json";
		

		//cancel or 'X' button pressed on save prompt
		/*
		if (fileLocation.equals("failed")) {
			System.out.println("Save cancelled: Exiting save...");
			return false;
		}
		*/
		
		//File creation, if file already exists prompt overwrite method will run
		try {
		      File name = new File(fileLocation);
		      if (name.createNewFile()) {
		        System.out.println("File created: " + name.getName());
		        FileWriter file = new FileWriter(fileLocation);
		        file.write(prettyJson.toString());
		        file.flush();
				file.close();
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
	public Boolean overwrite(String fileLocation, String Json) throws IOException{
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
			file.close();
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
	
public Boolean saveFileGUI() throws IOException {
		
		//User will input name of file first that they want to create (AT THIS MOMENT MUST TYPE .json AT THE END)
		
		
		
		String Json = new Gson().toJson(saveDiagram);	
		
		ObjectMapper mapper = new ObjectMapper();
		
		Object jsonObject = mapper.readValue(Json, Object.class);
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		
			

		//Assigned location for save file
		String fileLocation = saveFileLocation() + ".json";
		

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
		        file.write(prettyJson.toString());
		        file.flush();
				file.close();
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
	 * UI to show where the user can save their file. Must enter '.json' at the end of user's save file name.
	 * If '.json' is not entered, system will error out and user will have to re-enter save location.
	 */
	public String saveFileLocation() {
	      JFileChooser file = new JFileChooser();
	      file.setMultiSelectionEnabled(true);
	      file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      file.setFileHidingEnabled(false);
	      if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	         java.io.File f = file.getSelectedFile();
	         System.err.println(f.getPath());
	         return f.getPath();
	      }
	      return "failed";
	   }
}