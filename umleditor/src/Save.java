import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import org.json.*;

import com.google.gson.Gson;

public class Save {
	
	
	public static UMLDiagram saveDiagram = new UMLDiagram();
	public static UMLClass atTest = new UMLClass("create!");

	public static void main(String[] args) throws IOException {
		saveFile();
	}
	
	

	public static void saveFile() throws IOException {
		atTest.addAttribute("24");
		atTest.addAttribute("25");
		
		//TEST TO BE DELETED
		saveDiagram.addClass("test1");
		saveDiagram.addAttribute("test1", "this is a test");
		saveDiagram.addClass("test2");
		saveDiagram.addAttribute("test2", "1");
		
		//Import diagram to save to JSON
		HashMap<String,UMLClass> diagramSaveFile = saveDiagram.getUMLDiagram();
		//User will input name of file first that they want to create (AT THIS MOMENT MUST TYPE .json AT THE END)
		String nameOfFile;
		//User will input the file path that they want to make (test for me C:\\Users\\nickc\\Desktop\)
		String filePath; 
		
		//GSON for JSON file to be converted (Test TO BE DELETED)
		String Json = new Gson().toJson(diagramSaveFile);
		System.out.println(Json);
		
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
		
		//File creation, if file already exists overwrite old file
		try {
		      File name = new File(fileLocation);
		      if (name.createNewFile()) {
		        System.out.println("File created: " + name.getName());
		        FileWriter file = new FileWriter(fileLocation);
		        file.write(Json.toString());
		        file.flush();
		      } else {
		        System.out.println("File already exists. Overwriting Save File");
		        FileWriter file = new FileWriter(fileLocation);
		        file.write(Json.toString());
		        file.flush();
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

}
