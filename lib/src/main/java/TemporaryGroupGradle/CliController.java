package TemporaryGroupGradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.lang.model.element.Element;

public class CliController {
    
    public static UMLDiagram model = new UMLDiagram();
    public static String[] commands = new String [] {"add", "rename", "delete", "change", "help", "save", "load", "list", "exit", ""};
    public static boolean hasUnsavedWork = false;
	public static Save saver = new Save();
	public static Load loader = new Load();

    public static Scanner input = new Scanner (System.in);

    
    public static void main(String[] args) {
        
        CLIView view = new CLIView();
        view.startup();
        view.setTerminal(model);
        ArrayList<String> commands;
        Boolean userInput = true;
        while(userInput){
            commands=view.getInput();
            try{
                if(checkCommand(commands, 0, "exit")){
                    // if user hasn't saved since last change, inform them and give them a chance to not exit
                    if(hasUnsavedWork) {
                        System.out.print("There is currently unsaved work. ");
                    }
                    System.out.println("Are you sure you want to exit? [y/n]");
                    ArrayList<String> answer = view.getInput();
                    // if "y", exit program
		            if(checkCommand(answer, 0, "y")) {
			            System.exit(0);
		            }
		            // if neither "y" nor "n", invalid command (resume program)
		            else if(!checkCommand(answer, 0, "y") && !(checkCommand(answer, 0, "n"))) {
			            commandNotRecognized();
		            }
		            // if "n", do nothing (resume program)
                    
                }
                // ADD
                else if(checkCommand(commands, 0, "add")){
                    if(checkCommand(commands, 1, "class")){
                        if(commandSize(commands, 2)){
                            hasUnsavedWork = model.addClass(commands.get(2));
                        }else{
                            System.out.println("Please enter a name for the class.");
                        }
                    }else if(checkCommand(commands, 1, "field")){ ///CHECK
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.addField(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter designated class, name and type of field.");
                        } 
                    }else if(checkCommand(commands, 1, "method")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.addMethod(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter designated class, name and type of method.");
                        }
                    }else if(checkCommand(commands, 1, "parameter")){
                        if(commandSize(commands, 5)){
                            hasUnsavedWork = model.addParameter(commands.get(2), commands.get(3), commands.get(4), commands.get(5));
                        }else{
                            System.out.println("Please enter designated class, method, name and type of parameter.");
                        }
                    }else if(checkCommand(commands, 1, "relationship")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.addRelationship(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter designated class, name and type of method.");
                        }
                    }else{
                        commandNotRecognized();
                    }
                // DELETE
                }else if(checkCommand(commands, 0, "delete")){
                    if(checkCommand(commands, 1, "class")){
                        if(commandSize(commands, 2)){
                            hasUnsavedWork = model.removeClass(commands.get(2));
                        }else{
                            System.out.println("Please enter the name of the class.");
                        }
                    }else if(checkCommand(commands, 1, "field")){
                        if(commandSize(commands, 3)){
                            hasUnsavedWork = model.removeField(commands.get(2), commands.get(3));
                        }else{
                            System.out.println("Please enter a name for the class and field.");
                        }
                    }else if(checkCommand(commands, 1, "method")){
                        if(commandSize(commands, 3)){
                            hasUnsavedWork = model.removeMethod(commands.get(2), commands.get(3));
                        }else{
                            System.out.println("Please enter a name for the class and method.");
                        }
                    }else if(checkCommand(commands, 1, "parameter")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.removeParameter(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter a name for the class, method, and parameter.");
                        }
                    }else if(checkCommand(commands, 1, "relationship")){
                        if(commandSize(commands, 3)){
                            hasUnsavedWork = model.deleteRelationship(commands.get(2), commands.get(3));
                        }else{
                            System.out.println("Please enter a src and dst.");
                        }
                    }else{
                        commandNotRecognized();
                    }
                // RENAME
                }else if(checkCommand(commands, 0, "rename")){
                    if(checkCommand(commands, 1, "class")){
                        if(commandSize(commands, 3)){
                            hasUnsavedWork = model.renameClass(commands.get(2), commands.get(3));
                        }else{
                            System.out.println("Please enter the current name and new name.");
                        }
                    }else if(checkCommand(commands, 1, "field")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.renameField(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter class name, the current name, and new name.");
                        }
                    }else if(checkCommand(commands, 1, "method")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.renameMethod(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter class name, the current name, and new name.");
                        }
                    }else if(checkCommand(commands, 1, "parameter")){
                        if(commandSize(commands, 5)){
                            hasUnsavedWork = model.renameParameter(commands.get(2), commands.get(3), commands.get(4), commands.get(5));
                        }else{
                            System.out.println("Please enter class, method, the current name, and new name.");
                        }
                    }else{
                        commandNotRecognized();
                    }
                // CHANGE TYPE
                }else if(checkCommand(commands, 0, "change")){
                    if(checkCommand(commands, 1, "field")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.renameFieldType(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter class name, field name, and new type.");
                        }
                    }else if(checkCommand(commands, 1, "method")){
                        if(commandSize(commands, 4)){
                            hasUnsavedWork = model.renameMethodType(commands.get(2), commands.get(3), commands.get(4));
                        }else{
                            System.out.println("Please enter class name, method name, and new type.");
                        }
                    }else if(checkCommand(commands, 1, "parameter")){
                        if(commandSize(commands, 5)){
                            hasUnsavedWork = model.renameParameterType(commands.get(2), commands.get(3), commands.get(4), commands.get(5));
                        }else{
                            System.out.println("Please enter class name, method name, parameter name and new type.");
                        }
                    }else{
                        commandNotRecognized();
                    }
                // LIST
                }else if(checkCommand(commands, 0, "list")){
                    if(checkCommand(commands, 1, "class")){
                        if(commandSize(commands, 2)){
                            listClass(commands.get(2));
                        }else{
                            System.out.println("Please enter name of class to list.");
                        }
                    }else if(checkCommand(commands, 1, "all")){
                        listClasses();
                    }else if(checkCommand(commands, 1, "relationships")){
                        listRelationships();
                    }
                    else{
                        commandNotRecognized();
                    }
                // SAVE    
                }else if(checkCommand(commands, 0, "save")){
                    saveDiagram();
                // LOAD
                }else if(checkCommand(commands, 0, "load")){
                    loadDiagram();
                // UNDO
                }else if(checkCommand(commands, 0, "undo")) {
                    undoCommand();
                // REDO
                }else if(checkCommand(commands, 0, "redo")) {
                    redoCommand();
                // HELP
                }else if(checkCommand(commands, 0, "help")){
                    System.out.println();
		            System.out.println("With this UML editor, you can create a UML diagram.");
                    System.out.println("You can create classes with attributes and define relationships between those classes.");
                    System.out.println("Have fun with your UML project!");
                    System.out.println();
                    System.out.println("List of commands: ");
                    System.out.println("\tadd: Add a new [class] or [relationship] to the diagram, add a [field] or [method] to an existing class, or add a [parameter] to an existing field or method.");
                    System.out.println("\trename: Rename an existing [class], [field], [method], or [parameter].");
                    System.out.println("\tdelete: Delete an existing [class], [relationship], [field], [method], or [parameter].");
                    System.out.println("\tlist: List all [classes] in the diagram, all [relationships], or one specific [class].");
                    System.out.println("\tsave: Save the current UML diagram to a JSON or YAML file.");
                    System.out.println("\tload: Load an existing UML diagram from a JSON or YAML file.");
                    System.out.println("\tundo: Undo the previous action.");
                    System.out.println("\tredo: Redo the last undone action, as long as no new actions have been done since then.");
                    System.out.println("\thelp: View all editor commands.");
                    System.out.println("\texit: Quit the editor.");
                    System.out.println();
                // NOTHING
                }else if(checkCommand(commands, 0, "")){
                    commandNotRecognized();
                }else{
                    commandNotRecognized();
                }
            }catch(Exception error){
                System.out.println(error.getMessage());
            }
        
        }

    }

    public static boolean checkCommand(ArrayList<String> commands, int position, String word){
       if(commands.size() > position){
            if(commands.get(position).equals(word)){
                return true;
            }
       }
       else{
           return false;
       }
       return false;
    }

    public static Boolean commandSize(ArrayList<String> commands, int size){
        return (commands.size() > size);
    }

    public static void listClasses () {
		if (model.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!"); 
		} 
		else {
			Iterator<HashMap.Entry<String, UMLClass>> hmIter = model.umlDiagram.entrySet().iterator();
			while (hmIter.hasNext()) {
				Map.Entry<String, UMLClass> mapElem = (Map.Entry<String, UMLClass>) hmIter.next();
				listClass(mapElem.getKey());
			}
		}	
	}

    public static void listClass (String name) {
        System.out.println();
		System.out.println("Class: " + name);
		System.out.println("Fields:");
		for(int i = 0; i < model.getClass(name).fields.size(); i++) {
			System.out.println("- " + model.getClass(name).fields.get(i).getFieldName() + ": " + model.getClass(name).fields.get(i).getFieldType());
		}
		System.out.println("Methods:");
		for(int i = 0; i < model.getClass(name).methods.size(); i++) {
			System.out.print("+ " + model.getClass(name).methods.get(i).getMethodType() + " " + model.getClass(name).methods.get(i).getMethodName());
			System.out.print("(");
			for(int j = 0; j < model.getClass(name).methods.get(i).getParameterList().size(); j++){
				if(j > 0){
					System.out.print(", ");
				}
				System.out.print(model.getClass(name).methods.get(i).getParameterList().get(j).getParamType() + " " + model.getClass(name).methods.get(i).getParameterList().get(j).getParamName());
			}
			System.out.print(")");
			System.out.println();
		}
	}

    public static void listClassCommand (String className) {
		System.out.println("Enter class name: ");
		//String className = getInput();
		if(model.classExists(className)) {
			listClass(className);
		}
		else {
			classDoesNotExist(className);
		}
	}

    public static void listRelationships() {
		if(model.umlDiagram.isEmpty()) {
			System.out.println("No classes in the diagram.");
		}
		else if(model.relationships.isEmpty()) {
			System.out.println("No relationships in the diagram.");
		}
		else {
			System.out.println("Relationships: ");
			for(int i = 0; i < model.relationships.size(); i++) {
				System.out.println();
				System.out.println("Source: " + model.relationships.get(i).getSource());
				System.out.println("Destination: " + model.relationships.get(i).getDestination());
				System.out.println("Type: " + model.relationships.get(i).getType());
			}
		} 
	}

    public static void undoCommand() {
        model.undo();
    }

    public static void redoCommand() {
        model.redo();
    }

	public static void saveDiagram() throws Exception {
		saver.saveDiagram = model;
		if(saver.saveFile()) {
			hasUnsavedWork = false;
		}
	}

    public static void loadDiagram() throws Exception {
		if(hasUnsavedWork) {
			System.out.println("There is unsaved work. Are you sure you want to load a file? [y/n]");
			String answer = input.nextLine();
			if(answer.equals("n")) {
				return;
			}
			else if(!answer.equals("y") && !answer.equals("n")) {
				commandNotRecognized();
			}
		}
		loader.loadFile();
		model = loader.loadDiagram;
	
	}
	public static void classDoesNotExist(String className) {
		System.out.println("Class '" + className + "' does not exist.");
	}

    public static void commandNotRecognized() {
		System.out.println("Command not recognized. Type 'help' for list of valid commands.");
	}
	//change name to className
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

    public static Boolean isValidAttributeName(String name) {
		if(name.equals("")) {
			return false;
		}
		if(name.matches("^[-_A-Za-z0-9]+$")) {
			if(name.charAt(0) >= '0' && name.charAt(0) <= '9') {
				System.out.println("Error: Invalid name. Names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Names must follow standard Java naming conventions.");
				return false;
			}
			else if(name.charAt(0) == ('_')) {
				System.out.println("Error: Invalid name. Names can only contain A-Z, a-z, 0-9, and underscore.");
				System.out.println("Names must follow standard Java naming conventions.");
				return false;
			}
			else {
				return true;
			}
		}
		else {
			System.out.println("Error: Invalid name. Names can only contain A-Z, a-z, 0-9, and underscore.");
			System.out.println("Names must follow standard Java naming conventions.");
			return false;
		}
	}

}
