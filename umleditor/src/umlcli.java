import java.util.*;
import UMLClass.java;
import UMLDiagram.java;
import java.io.Console;

public class umlcli {
	
	//commands available in the editor 
	public static String[] commands = new String [] {"add", "rename", "delete", "help", "exit", 
			"List Classes", "List class", "class", "attributes", "relationships"};
	public static Console input = System.console();
	
	
	//Driver for the cli class
	public static void main (String[] args) {
		prompt();
		while (true) {
			String command = input.readLine();
			//if command not valid keep prompting for new one 
			while (!isCommand(command)) {
				System.out.println("command not valid, try again");
				command = input.readLine();
			}
			runCommand(command);
		}	
		
	}
	
	
	//cli functions 
	
	//Initial prompt 
	public static void prompt () {
		System.out.println("Welcome to UML Editor");
		System.out.println("press h for help");
	}
	
	//verify if word entered is a command
		public static boolean isCommand (String command) {
			for (String ele: commands)
			{
				if (command == ele) {
					return true;
				}
			}
			return false;
		}
	
	//exit the program if user intended to 
	public static void exitCommand ()
	{
		System.out.println("Are you sure you want to exit?");
		String answer = input.readLine();
		if (answer == "Yes") {
			//if not saved save file
			//need to implement isSaved()
			if (!isSaved()) {
				runCommand("save");
			}
			System.exit(0);
		}
		//no need to do anything else 
	}
	
	//help command 
	public static void helpCommand () {
		System.out.println("With this UML Editor you can create an UML diagram");
		System.out.println("to see all available commands enter commands");
		System.out.println("have fun with your uml project!");
	}
	
	//list commands 
	public static void listCommands () {
		System.out.print("commands: ");
		for (String ele : commands) {
			System.out.print(" " + ele + " ,");
		}
		System.out.println();
	}
	
	
	//run given command 
	public static void runCommand (String command) {
		if (command == "exit") {
			exitCommand();
		}
		else if (command == "add") {
			System.out.println("What do you want to add?");
			//ned to finish
			
		}
		else if (command == "commands") {
			listCommands();
		}
	}
	
	//list all classes in the diagram 
	public void listClasses () {
		System.out.println ("This are all the classes in the diagram");
		if (umlDiagram.isEmpty())
		{
			System.out.println("Nevermind there are no classes for now"); 
		} else {
			Iterator hmIter = umlDiagram.entrySet.iterator();
			while (hmIter .hasNext()) {
				Map.Entry mapElem = (Map.Entry) hmIter.next();
				System.out.println(mapElem.getKey());
			}
		  }	
	}
		
	//need to implement (not finished)
	public void listClass (String classname) {
		Iterator hmIter = umlDiagram.entrySet.iterator();
		while (hmIter .hasNext()) {
			Map.Entry mapElem = (Map.Entry) hmIter.next();
			if (mapElem.getKey() == classname)
			{
				//print attributes for the class 
				System.out.print(mapElem.getValue());
			}			
		}
		System.out.println("Class does not exists");
	}
	
}
