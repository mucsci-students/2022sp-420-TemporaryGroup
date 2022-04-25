package TemporaryGroupGradle;

// import java.util.HashMap;

//import javax.swing.JFileChooser;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;

import java.util.Scanner;

import javax.swing.JFileChooser;

public class Load {
	
	UMLDiagram loadDiagram = new UMLDiagram();
	SaveTemplate saveTemplate = new SaveTemplate();

	public Boolean loadFile() throws Exception {
		//file location picked from JSwing
		//String fileLocation = loadFileLocation().toString();
		
		Scanner filepath = new Scanner (System.in);
		Scanner filename = new Scanner (System.in);
		
		
		System.out.println("Please enter name of the file (do not add extension) or q for quitting");
		System.out.print("> ");
		String FileName = filename.next();
		if (FileName.equals("q") || FileName.equals("q ") || FileName.equals(" q")) {
			return false;
		}
		System.out.println("Please enter a path for the file");
		System.out.print("> ");
		String FilePath = filepath.next();
		
		String fileLocation = FilePath + FileName + ".json";
		
		//User pressed cancel or 'X' button on load prompt
		if (fileLocation.equals("failed")) {
			System.out.println("Load cancelled: Exiting load...");
			return false;
		}
				
		//System will attempt to load the file, if user picked incorrect file type, it will error out and re-prompt user to load new file. 
		try {
			String json = readFileAsString(fileLocation);
			Gson gson = new Gson();
			
			Type typeOfUMLDiagram = new TypeToken<SaveTemplate>() { }.getType();
			SaveTemplate savedDiagram = gson.fromJson(json, typeOfUMLDiagram);

			saveTemplate = savedDiagram;
			for (int i = 0; i < saveTemplate.classes.size(); i++) { 		      
				loadDiagram.umlDiagram.put(saveTemplate.classes.get(i).getClassName(), saveTemplate.classes.get(i));
		    }   
			loadDiagram.relationships = saveTemplate.relationships;
			System.out.println("Successfully loaded!");
			return true;
		} catch (AccessDeniedException|IllegalStateException|JsonSyntaxException | NoSuchFileException e){
			System.out.println("Error: The file you entered was invalid or cannot be read. Please try again.");
			loadFile();
			return false;
			
		}
		
	}
	
	public Boolean loadFileGUI() throws Exception {
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
			
			Type typeOfUMLDiagram = new TypeToken<UMLDiagram>() { }.getType();
			UMLDiagram savedDiagram = gson.fromJson(json, typeOfUMLDiagram);

			loadDiagram = savedDiagram; 
			UMLDiagram.clearUndoRedo();
			
			System.out.println("Successfully loaded!");
			//Print out for proof of it working TO BE DELETED
			String Json = new Gson().toJson(loadDiagram);
			System.out.println("Successfully loaded!");
			return true;
		} catch (AccessDeniedException|IllegalStateException|JsonSyntaxException | NoSuchFileException e){
			System.out.println("Error: The file you entered was invalid or cannot be read. Please try again.");
			loadFile();
			return false;
			
		}
		
	}
	
	
	
	public String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

	

	public String loadFileLocation() {

	      JFileChooser file = new JFileChooser();
	      file.setMultiSelectionEnabled(true);
	      file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      file.setFileHidingEnabled(false);
	      if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	         java.io.File f = file.getSelectedFile();
	         System.err.println(f.getPath());
	         return f.getPath();
	      }
	      System.out.println("lol");
	      return "failed";
	      
	   }
	}
	   
	
	
