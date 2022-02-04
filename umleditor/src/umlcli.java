import java.util.*;
import java.io.Console;

public class umlcli {
	
	//commands available in the editor 
	// Top level commands. Other commands can be prompted by the specific command functions.
	// ie, user types:
	// > add
	// If command equals "add", then runCommand prompts
	// "What would you like to add?""
	// > class
	// Then prompt for names and call the associated method on our UMLDiagram object
	public static String[] commands = new String [] {"add", "rename", "delete", "help", "list", "exit"};
	public static Console input = System.console();
	public static UMLDiagram umld = new UMLDiagram();
	public static boolean hasUnsavedWork = false;
	
	
	//Driver for the cli class
	public static void main (String[] args) {
		
		prompt();
		while (true) {
			System.out.print("> ");
			String command = input.readLine();
			//if command not valid keep prompting for new one 
			while (!isCommand(command)) {
				System.out.println("Command not recognized.");
				System.out.print("> ");
				command = input.readLine();
			}
			runCommand(command);
		}	
		
	}
	
	
	//cli functions 
	
	//Initial prompt 
	public static void prompt () {
		System.out.println("Welcome to the UML editor.");
		System.out.println("Please enter a command.");
		System.out.println("Type 'help' for list of valid commands.");
		System.out.println("Type 'exit' to quit the editor.");
	}
	
	//verify if word entered is a command
		public static boolean isCommand (String command) {
			for (String ele: commands)
			{
				if (command.equals(ele)) {
					return true;
				}
			}
			return false;
		}
	
	//exit the program if user intended to 
	public static void exitCommand ()
	{
		// if user hasn't saved since last change, inform them and give them a chance to not exit
		if(hasUnsavedWork) {
			System.out.print("There is currently unsaved work. ");
		}
		System.out.println("Are you sure you want to exit? (y/n)");
		System.out.print("> ");
		String answer = input.readLine();
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
		System.out.println("With this UML editor, you can create a UML diagram.");
		System.out.println("You can create classes with attributes and define relationships between those classes.");
		System.out.println("Have fun with your UML project!");
		listCommands();

		
	}
	
	//list commands 
	public static void listCommands () {
		System.out.print("List of commands: ");
		for (String ele : commands) {
			System.out.print(" " + ele + " ,");
		}
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
			System.out.println("What do you want to add?");
			//ned to finish
			
		}
		else if(command.equals("delete")) {
			// needs implementation
		}
		else if(command.equals("rename")) {
			// needs implementation
		}
		else if(command.equals("list")) {
			// needs implementation
		}
		else {
			System.out.println("Command not recognized.");
			// might be redundant
		}
		
	}
	
	//list all classes in the diagram 
	public void listClasses () {
		System.out.println ("Classes: ");
		if (umld.umlDiagram.isEmpty()) {
			System.out.println("No classes in the diagram."); 
			System.out.println();
		} 
		else {
			Iterator hmIter = umld.umlDiagram.entrySet().iterator();
			while (hmIter .hasNext()) {
				Map.Entry mapElem = (Map.Entry) hmIter.next();
				System.out.println(mapElem.getKey());
			}
		  }	
	}
		
	//need to implement (not finished)
	public void listClass (String classname) {
		Iterator hmIter = umld.umlDiagram.entrySet().iterator();
		while (hmIter .hasNext()) {
			Map.Entry mapElem = (Map.Entry) hmIter.next();
			if (mapElem.getKey() == classname)
			{
				//print attributes for the class 
				System.out.print(mapElem.getValue());
			}			
		}
		System.out.println("Class '" + classname + "' does not exist.");
	}
	
}
