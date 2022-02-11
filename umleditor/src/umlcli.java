import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import com.google.gson.*;
import java.nio.file.*;

public class umlcli {
	
	//commands available in the editor 
	// Top level commands. Other commands can be prompted by the specific command functions.
	// ie, user types:
	// > add
	// If command equals "add", then runCommand prompts
	// "What would you like to add?""
	// > class
	// Then prompt for names and call the associated method on our UMLDiagram object
	public static String[] commands = new String [] {"add", "rename", "delete", "help", "save", "load", "list", "exit", ""};
	public static Scanner input = new Scanner (System.in);
	public static UMLDiagram umld = new UMLDiagram();
	public static boolean hasUnsavedWork = false;
	public static Save saver = new Save();
	//public static Load loader = new Load();
	
	
	//Driver for the cli class
	public static void main (String[] args) throws Exception {
		prompt();
		while (true) {
			String command = getInput();
			//if command not valid keep prompting for new one 
			while (!isCommand(command)) {
				commandNotRecognized();
				command = getInput();
			}
			runCommand(command);
		}	
	}	
	
	//cli functions 
	
	//Initial prompt 
	public static void prompt () {
		System.out.println("Welcome to the UML editor.");
		System.out.println("Type 'help' for list of valid commands.");
		System.out.println("Type 'exit' to quit the editor.");
  	}
  
	public static String getInput () {
		System.out.print("> ");
		return input.nextLine();
	}
	
	//verify if word entered is a command
	public static boolean isCommand (String command) {
		for (String ele : commands) {
			if (command.equals(ele)) {
				return true;
			}
		}
		return false;
	}
	
	//exit the program if user intended to 
	public static void exitCommand () {
		// if user hasn't saved since last change, inform them and give them a chance to not exit
		if(hasUnsavedWork) {
			System.out.print("There is currently unsaved work. ");
		}
		System.out.println("Are you sure you want to exit? [y/n]");
		String answer = getInput();
		// if "y", exit program
		if(answer.equals("y")) {
			System.exit(0);
		}
		// if neither "y" nor "n", invalid command (resume program)
		else if(!answer.equals("y") && !answer.equals("n")) {
			commandNotRecognized();
		}
		// if "n", do nothing (resume program)
	}
	
	//help command 
	public static void helpCommand () {
		System.out.println();
		System.out.println("With this UML editor, you can create a UML diagram.");
		System.out.println("You can create classes with attributes and define relationships between those classes.");
		System.out.println("Have fun with your UML project!");
		System.out.println();
		System.out.println("List of commands: ");
		System.out.println("\tadd: Add a new [class] or [relationship] to the diagram, or add an [attribute] to an existing class.");
		System.out.println("\trename: Rename an existing [class], [relationship], or [attribute].");
		System.out.println("\tdelete: Delete an existing [class], [relationship], or [attribute].");
		System.out.println("\tlist: List all [classes] in the diagram, all [relationships], or one specific [class].");
		System.out.println("\tsave: Save the current UML diagram to a JSON or YAML file.");
		System.out.println("\tload: Load an existing UML diagram from a JSON or YAML file.");
		System.out.println("\thelp: View all editor commands.");
		System.out.println("\texit: Quit the editor.");
		System.out.println();
	}
	
	//run given command 
	public static void runCommand (String command) throws Exception {
		if (command.equals("exit")) {
			exitCommand();
		}
		else if (command.equals("help")) {
			helpCommand();
		}
		else if (command.equals("add")) {
			addCommand();			
		}
		else if(command.equals("delete")) {
			deleteCommand();
		}
		else if(command.equals("rename")) {
			renameCommand();
		}
		else if(command.equals("list")) {
			listCommand();
		}
		else if(command.equals("save")) {
			saveDiagram();
		}
		else if(command.equals("load")) {
			loadDiagram();
		}
		else if(command.equals("")) {
			return;
		}
		else {
			commandNotRecognized();
			// might be redundant
		}	
	}	
	
	public static void addCommand () {
		System.out.println("What do you want to add? [class/relationship/attribute]");
		String toAdd = getInput();
		if (toAdd.equals("class")) {
			System.out.println("Enter class name: ");
			String className = getInput();
			if(isValidName(className)) {
				hasUnsavedWork = umld.addClass(className);
			}
		}
		else if(toAdd.equals("attribute")) {
			System.out.println("Add attribute to which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter attribute name: ");
				String attributeName = getInput();
				if(isValidName(attributeName)) {
					hasUnsavedWork = umld.addAttribute(whichClass, attributeName);
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Add a relationship
		else if(toAdd.equals("relationship")) {
			System.out.println("Enter relationship name: ");
			String name = getInput();
			if(!isValidName(name)) {
				return;
			}
			System.out.println("Enter source class:");
			String source = getInput();
			if(!umld.classExists(source)) {
				classDoesNotExist(source);
				return;
			}
			System.out.println("Enter destination class:");
			String dest = getInput();
			if(!umld.classExists(dest)) {
				classDoesNotExist(dest);
				return;
			}
			// fixed type for now, can be changed when more relationship types are needed
			String relType = "Nondirectional";
			hasUnsavedWork = umld.addRelationship(source, dest, relType, name);
      		

		}
		else {
			commandNotRecognized();
		}
	}
	
	public static void listCommand () {
		if(umld.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!");
			return;
		}
		System.out.println("What do you want to list? [class/classes/relationships]");
		String toList = getInput();
		if (toList.equals("classes")) {
			listClasses();
		} 
		else if (toList.equals("relationships")) {
			listRelationships();
		} 
		else if (toList.equals("class")) {
			listClass();
		}
		else {
			commandNotRecognized();
		}
	}

	public static void renameCommand() {
		if(umld.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!");
			return;
		}
		System.out.println("What do you want to rename? [class/relationship/attribute]");
		String toRename = getInput();
		if(toRename.equals("class")) {
			System.out.println("Rename which class?");
			String oldName = getInput();
			if(umld.classExists(oldName)) {
				System.out.println("Enter new class name:");
				String newName = getInput();
				if(isValidName(newName)) {
					hasUnsavedWork = umld.renameClass(oldName, newName);
				}
			}
			else {
				classDoesNotExist(oldName);
			}
		}
		else if(toRename.equals("attribute")) {
			System.out.println("Rename an attribute in which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Rename which attribute in class '" + whichClass + "'?");
				String oldName = getInput();
				if(umld.getClass(whichClass).attributeExists(oldName)) {
					System.out.println("Enter new attribute name:");
					String newName = getInput();
					hasUnsavedWork = umld.renameAttribute(whichClass, oldName, newName);
				}
				else {
					System.out.println("Attribute '" + oldName + "' does not exist in class '" + whichClass + "'.");
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Rename a relationship
		else if(toRename.equals("relationship")) {
			System.out.println("Enter source class name: ");
			String srcClass = getInput();
			if(!umld.classExists(srcClass)) {
				classDoesNotExist(srcClass);
				return;
			}
			System.out.println("Enter destination class name: ");
			String destClass = getInput();
			if(!umld.classExists(destClass)) {
				classDoesNotExist(destClass);
				return;
			}
			System.out.println("Rename which relationship?");
			String oldName = getInput();
			if(!isValidName(oldName)) {
				return;
			}
			System.out.println("Enter new relationship name: ");
			String newName = getInput();
			if(isValidName(newName)) {
				hasUnsavedWork = umld.renameRelationship(srcClass, destClass, oldName, newName);
			}
		}
		else {
			commandNotRecognized();
		}
	}

	public static void deleteCommand() {
		if(umld.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!");
			return;
		}
		System.out.println("What do you want to delete? [class/relationship/attribute]");
		String toRemove = getInput();
		if(toRemove.equals("class")) {
			System.out.println("Enter class to delete:");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				hasUnsavedWork = umld.removeClass(whichClass);
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		else if(toRemove.equals("attribute")) {
			System.out.println("Delete attribute from which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter attribute to delete:");
				String whichAttribute = getInput();
				hasUnsavedWork = umld.removeAttribute(whichClass, whichAttribute);
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Remove a relationship
		else if(toRemove.equals("relationship")) {
			System.out.println("Enter source class name: ");
			String srcClass = getInput();
			if(!umld.classExists(srcClass)) {
				classDoesNotExist(srcClass);
				return;
			}
			System.out.println("Enter destination class name: ");
			String destClass = getInput();
			if(!umld.classExists(destClass)) {
				classDoesNotExist(destClass);
				return;
			}
			System.out.println("Enter relationship to delete: ");
			String name = getInput();
			hasUnsavedWork = umld.deleteRelationship(srcClass, destClass, name);
		}
		else {
			commandNotRecognized();
		}
	}
	
	//list all classes in the diagram 
	public static void listClasses () {
		if (umld.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!"); 
		} 
		else {
			System.out.println ("Classes: ");
			System.out.println();
			Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
			while (hmIter.hasNext()) {
				Map.Entry<String, UMLClass> mapElem = (Map.Entry<String, UMLClass>) hmIter.next();
				System.out.println(mapElem.getKey());
			}
		}	
	}

	public static void listClass () {
		System.out.println("Enter class name: ");
		String className = getInput();
		if(umld.classExists(className)) {
			System.out.println("Class: " + className);
			System.out.print("[Attributes: ");
			StringJoiner joiner = new StringJoiner(", ");
			for(int i = 0; i < umld.getClass(className).attributes.size(); i++) {
				joiner.add(umld.getClass(className).attributes.get(i));
			}
			String attList = joiner.toString();
			System.out.print(attList + "]");
			System.out.println();
		}
		else {
			classDoesNotExist(className);
		}
	}

	public static void listRelationships() {
		if(umld.umlDiagram.isEmpty()) {
			System.out.println("No classes in the diagram.");
		}
		else if(umld.relationships.isEmpty()) {
			System.out.println("No relationships in the diagram.");
		}
		else {
			System.out.println("Relationships: ");
			System.out.println();
			for(int i = 0; i < umld.relationships.size(); i++) {
				System.out.println(umld.relationships.get(i).getName() + " [Type: " + umld.relationships.get(i).getType() + "]");
				System.out.println("[Source: " + umld.relationships.get(i).getSource() + "]");
				System.out.println("[Destination: " + umld.relationships.get(i).getSource() + "]");
				System.out.println();
			}
		} 
	}
	
	public static void saveDiagram() throws IOException {
		// to implement
		if (saveFile()) {
			System.out.println("File saved sucessfully");
		} else {
			System.out.println("Could not saved file");
		}
		
	}

	public static void loadDiagram() throws Exception {
		// to implement
	}
	
	public static void classDoesNotExist(String className) {
		System.out.println("Class '" + className + "' does not exist.");
	}

	public static void commandNotRecognized() {
		System.out.println("Command not recognized. Type 'help' for list of valid commands.");
	}

	public static Boolean isValidName(String name) {
		if(name.equals("")) {
			return false;
		}
		if(name.matches("^[-_A-Za-z0-9]+$")) {
			return true;
		}
		else {
			System.out.println("Error: Invalid name. Names can only contain A-Z, a-z, 0-9, and underscore.");
			return false;
		}
	}
	///////////////////////////////////////////////////////////////////////////
	//save implementation added to cli class 
	//is functional for now
	//need to find nicer way to integrate it 
	
	/*
	 * To save the UMLDiagram, user will be prompted through Java Swing to create a save location or pick a file that they have already saved.
	 * If file picked, system will prompt user if they want to overwrite the save file. 
	 */
	public static Boolean saveFile() throws IOException {
		
		//User will input name of file first that they want to create (AT THIS MOMENT MUST TYPE .json AT THE END)
		//GSON for JSON file to be converted (Test TO BE DELETED)

		String Json = new Gson().toJson(umld);

		//Assigned location for save file
		System.out.println("enter path");
		String fileLocation = getInput();
				
		//cancel or 'X' button pressed on save prompt
		if (!validPath (fileLocation)) {
			System.out.println("Can not find path for saving");
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
				file.close();
		        return true;
		      } else {
		        overwrite(fileLocation, Json);
		        return true;
		      }
		    } catch (IOException|IllegalStateException e) {
		      System.out.println("Error: Please ensure you have named your file and add '.json' at the end.");
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
			file.close();
			a.close();
			return true;
		}
		else if (answer.equals("n") || answer.equals("no")) {
			System.out.println("Save interrupted, cancelling save");
			a.close();
			return false;
		}
		else {
			System.out.println("ERROR: Please input y or n");
			overwrite(fileLocation, Json);
			a.close();
			return false;
		}
			
	}
	/*
	 * UI to show where the user can save their file. Must enter '.json' at the end of user's save file name.
	 * If '.json' is not entered, system will error out and user will have to re-enter save location.
	 
	public static String saveFileLocation() {
	      JFileChooser file = new JFileChooser();
	      file.setMultiSelectionEnabled(true);
	      file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      file.setFileHidingEnabled(false);
	      file.sho
	      if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	 java.io.File f = file.getSelectedFile();
	         System.err.println(f.getPath());
	         return f.getPath();
	      } 
	      return "failed"; 
	 } 
	 maybe we can fix this by Sunday
	 */
	
	//check if path is valid 
	public static boolean validPath (String path) {
		try {
            Paths.get(path);
        } catch (InvalidPathException ex) {
            return false;
        }
        return true;
	}
	
	
}