import java.util.*;

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
	
	
	//Driver for the cli class
	public static void main (String[] args) {
		prompt();
		while (true) {
			String command = getInput();
			//if command not valid keep prompting for new one 
			while (!isCommand(command)) {
				System.out.println("Command not recognized. Type 'help' for list of valid commands.");
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
			System.out.println("Command not recognized.");
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
		System.out.println("\tadd: Add a new class or relationship to the diagram, or add an attribute to an existing class.");
		System.out.println("\trename: Rename an existing class, relationship, or attribute.");
		System.out.println("\tdelete: Delete an existing class, relationship, or attribute.");
		System.out.println("\tlist: List all classes in the diagram, all relationships, or one specific class.");
		System.out.println("\tsave: Save the current UML diagram to a JSON or YAML file.");
		System.out.println("\tload: Load an existing UML diagram from a JSON or YAML file.");
		System.out.println("\thelp: View all editor commands.");
		System.out.println("\texit: Quit the editor.");
		System.out.println();
	}
	
	//run given command 
	public static void runCommand (String command) {
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
			System.out.println("Command not recognized. Type 'help' for list of valid commands.");
			// might be redundant
		}	
	}	
	
	public static void addCommand () {
		System.out.println("What do you want to add? [class/relationship/attribute]");
		String toAdd = getInput();
		if (toAdd.equals("class")) {
			System.out.println("Enter class name: ");
			String className = getInput();
			umld.addClass(className);
		}
		else if(toAdd.equals("attribute")) {
			System.out.println("Add attribute to which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter attribute name: ");
				String attributeName = getInput();
				umld.addAttribute(whichClass, attributeName);
			}
			else {
				System.out.println("Class " + whichClass + " does not exist.");
			}
		}
		else if(toAdd.equals("relationship")) {
			// to be implemented
		}
	}
	
	public static void listCommand () {
		System.out.println("What do you want to list? [class/classes/relationships]");
		String toList = getInput();
		if (toList.equals("classes")) {
			listClasses();
		} else if (toList.equals("relationships")) {
			// to be implemented
		} else if (toList.equals("class")) {
			listClass();
		}
		else {
			System.out.println("Command not recognized. Type 'help' for list of valid commands.");
		}
	}

	public static void renameCommand() {
		System.out.println("What do you want to rename? [class/relationship/attribute]");
		String toRename = getInput();
		if(toRename.equals("class")) {
			System.out.println("Rename which class?");
			String oldName = getInput();
			if(umld.classExists(oldName)) {
				System.out.println("Enter new class name:");
				String newName = getInput();
				umld.renameClass(oldName, newName);
			}
			else {
				System.out.println("Class '" + oldName + "' does not exist.");
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
					umld.renameAttribute(whichClass, oldName, newName);
				}
				else {
					System.out.println("Attribute '" + oldName + "' does not exist in class '" + whichClass + "'.");
				}
			}
			else {
				System.out.println("Class '" + whichClass + "' does not exist.");
			}
		}
		else if(toRename.equals("relationship")) {
			// to be implemented
		}
	}

	public static void deleteCommand() {
		System.out.println("What do you want to delete? [class/relationship/attribute]");
		String toRemove = getInput();
		if(toRemove.equals("class")) {
			System.out.println("Enter class to delete:");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				umld.removeClass(whichClass);
			}
			else {
				System.out.println("Class '" + whichClass + "' does not exist.");
			}
		}
		else if(toRemove.equals("attribute")) {
			System.out.println("Delete attribute from which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter attribute to delete:");
				String whichAttribute = getInput();
				umld.removeAttribute(whichClass, whichAttribute);
			}
			else {
				System.out.println("Class '" + whichClass + "' does not exist.");
			}
		}
		else if(toRemove.equals("relationship")) {
			// to be implemented
		}
	}
	
	//list all classes in the diagram 
	public static void listClasses () {
		if (umld.umlDiagram.isEmpty()) {
			System.out.println("No classes in the diagram."); 
		} 
		else {
			System.out.println ("Classes: ");
			Iterator hmIter = umld.umlDiagram.entrySet().iterator();
			while (hmIter.hasNext()) {
				Map.Entry mapElem = (Map.Entry) hmIter.next();
				System.out.println(mapElem.getKey());
			}
		}	
	}
		

	public static void listClass () {
		System.out.println("Enter class name: ");
		String className = getInput();
		if(umld.classExists(className)) {
			System.out.println("Class: " + className);
			System.out.print("Attributes: ");
			for(int i = 0; i < umld.getClass(className).attributes.size(); i++) {
				System.out.print(umld.getClass(className).attributes.get(i) + ", ");
			}
			System.out.println();
		}
		else {
			System.out.println("Class '" + className + "' does not exist.");
		}
	}

	public static void saveDiagram() {
		// needs implementation
	}

	public static void loadDiagram() {
		// needs implementation
	}
	
}