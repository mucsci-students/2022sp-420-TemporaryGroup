import java.util.*;


public class UMLCli {
	
	//commands available in the editor 
	// Top level commands. Other commands can be prompted by the specific command functions.
	// ie, user types:
	// > add
	// If command equals "add", then runCommand prompts
	// "What would you like to add?""
	// > class
	// Then prompt for names and call the associated method on our UMLDiagram object
	public static String[] commands = new String [] {"add", "rename", "delete", "change", "help", "save", "load", "list", "exit", ""};
	public static Scanner input = new Scanner (System.in);
	public static UMLDiagram umld = new UMLDiagram();
	public static boolean hasUnsavedWork = false;
	public static Save saver = new Save();
	public static Load loader = new Load();
	
	
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
	
	public static int getInt() {
		System.out.print(">");
		return input.nextInt();
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
		System.out.println("\tadd: Add a new [class] or [relationship] to the diagram, add a [field] or [method] to an existing class, or add a [parameter] to an existing field or method.");
		System.out.println("\trename: Rename an existing [class], [field], [method], or [parameter].");
		System.out.println("\tdelete: Delete an existing [class], [relationship], [field], [method], or [parameter].");
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
		else if(command.equals("change")) {
			changeCommand();
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
		System.out.println("What do you want to add? [class/relationship/method/field/parameter]");
		String toAdd = getInput();
		if (toAdd.equals("class")) {
			System.out.println("Enter class name: ");
			String className = getInput();
			if(isValidName(className)) {
				hasUnsavedWork = umld.addClass(className);
			}
		}
		// Adds field 
		else if(toAdd.equals("field")) {
			System.out.println("Add field to which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter field name: ");
				String fieldName = getInput();
				System.out.println("Enter field type: ");
				String fieldType = getInput();
				if(isValidAttributeName(fieldName)) {
					hasUnsavedWork = umld.addField(whichClass, fieldName, fieldType);
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Adds relationship 
		else if(toAdd.equals("relationship")) {
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
			System.out.println("Enter relationship type: [aggregation/composition/inheritance/realization]");
			String type = getInput();
			if(!umld.isValidType(type)) {
				System.out.println("Error: invalid relationship type.");
				return;
			}
			hasUnsavedWork = umld.addRelationship(source, dest, type);
      		

		}
		// Adds method
		else if(toAdd.equals("method")) {
			System.out.println("Add method to which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter method name: ");
				String methodName = getInput();
				System.out.println("Enter method type: ");
				String methodType = getInput();
				//change method once uml diagram is updated
				if(isValidAttributeName(methodName)) {
					hasUnsavedWork = umld.addMethod(whichClass, methodName, methodType);
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Adds parameter
		else if(toAdd.equals("parameter")) {
			System.out.println("Add parameter to which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Add parameter to which method?");
				String methodName = getInput();
				if(umld.getClass(whichClass).methodExists(methodName)){
					System.out.println("Enter parameter name: ");
					String parameterName = getInput();
					System.out.println("Enter parameter type: ");
					String parameterType = getInput();
					if(isValidAttributeName(parameterName)){
						hasUnsavedWork = umld.addParameter(whichClass, methodName, parameterName, parameterType);
					}
				}
				else{
					System.out.println("The method '" + methodName + "' does not exist in the class '" + whichClass + "'.");
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		/** else if(toAdd.equals("method")) {
			System.out.println("add method to which class?");
			String className = getInput();
			if(!umld.classExists(className)) {
				classDoesNotExist(className);
				return;
			}
			System.out.println("Enter method name:");
			String methodName = getInput();
			if (isValidAttributeName(methodName)) {
				System.out.println("Enter number of parameters up to 10");//need limit ask client
				int numParameters = getInt();
				if (numParameters == 0) {
					hasUnsavedWork = umld.addMethod(methodName , {} );
				
				} 
				else if (numParameters >= 1 || numParameters <= 10 ) {
					ArrayList<String []> parameter = new ArrayList <String []> ();
					for (int i = 1; i <= numParameters; ++i)
					{
						System.out.println ("Enter name of parameter " + i);
						String pName = getInput();
						if (isValidAttributeName (pName)) {
							
						}
						
						
					}
				}
				
			
			}
			
				`
			// fixed type for now, can be changed when more relationship types are needed
			
      		

		} */
		
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
			listClassCommand();
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
		System.out.println("What do you want to rename? [class/field/method/parameter]");
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
		// Renames a field
		else if(toRename.equals("field")) {
			System.out.println("Rename a field in which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Rename which field in class '" + whichClass + "'?");
				String oldName = getInput();
				if(umld.getClass(whichClass).fieldExists(oldName)) {
					System.out.println("Enter new field name:");
					String newName = getInput();
					if(isValidAttributeName(newName)) {
						hasUnsavedWork = umld.renameField(whichClass, oldName, newName);
					}
				}
				else {
					System.out.println("Field '" + oldName + "' does not exist in class '" + whichClass + "'.");
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Renames a method
		else if(toRename.equals("method")) {
			System.out.println("Rename a method in which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Rename which method in class '" + whichClass + "'?");
				String oldName = getInput();
				if(umld.getClass(whichClass).methodExists(oldName)) {
					System.out.println("Enter new method name: ");
					String newName = getInput();
					if(isValidAttributeName(newName)) {
						hasUnsavedWork = umld.renameMethod(whichClass, oldName, newName);
					}
				}
				else {
					System.out.println("Method '" + oldName + "' does not exist in class '" + whichClass + "'.");
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Renames a parameter
		else if(toRename.equals("parameter")) {
			System.out.println("Rename a parameter in which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Rename a parameter in which method?");
				String methodName = getInput();
				if(umld.getClass(whichClass).methodExists(methodName)){
					System.out.println("Rename which parameter in method '" + methodName + "'?");
					String oldParam = getInput();
					if(umld.getClass(whichClass).getMethod(methodName).paramExists(oldParam)){
						System.out.println("Enter new parameter name: ");
						String newParam = getInput();
						if(isValidAttributeName(newParam)){
							hasUnsavedWork = umld.renameParameter(whichClass, methodName, oldParam, newParam);
						}
					}
					else{
						System.out.println("The parameter '" + oldParam + "' does not exist in the method '" + methodName + "'.");
					}
				}
				else{
					System.out.println("The method '" + methodName + "' does not exist in the class '" + whichClass + "'.");
				}
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		else {
			commandNotRecognized();
		}
		
	}
	
	public static void changeCommand () {
		if(umld.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!");
			return;
		}
		System.out.println("What type would you like to change? ? [field/method/parameter]");
		String toChange = getInput();
		// Renames field type
		if(toChange.equals("field")){
			System.out.println("Change field type in which class?");
			String className = getInput();
			if(umld.classExists(className)){
				System.out.println("Change type in which field?");
				String whichField = getInput();
				if(umld.getClass(className).fieldExists(whichField)){
					System.out.println("Enter a new field type:");
					String newType = getInput();
					hasUnsavedWork = umld.renameFieldType(className, whichField, newType);
				}
				else{
					System.out.println("Field '" + whichField + "' does not exist in class '" + className + "'.");
				}
			}
			else{
				classDoesNotExist(className);
			}
		}
		// Renames method type
		else if(toChange.equals("method")){
			System.out.println("Change method return type in which class?");
			String className = getInput();
			if(umld.classExists(className)){
				System.out.println("Change return type in which method?");
				String whichMethod = getInput();
				if(umld.getClass(className).methodExists(whichMethod)){
					System.out.println("Enter a new method return type:");
					String newType = getInput();
					hasUnsavedWork = umld.renameMethodType(className, whichMethod, newType);
				}
				else{
					System.out.println("Method '" + whichMethod + "' does not exist in class '" + className + "'.");
				}
			}
			else{
				classDoesNotExist(className);
			}
		}
		// Renames parameter type
		else if(toChange.equals("parameter")){
			System.out.println("Change parameter type in which class?");
			String className = getInput();
			if(umld.classExists(className)){
				System.out.println("Change parameter type in which method?");
				String whichMethod = getInput();
				if(umld.getClass(className).methodExists(whichMethod)){
					System.out.println("Change type for which parameter?");
					String whichParam = getInput();
					if(umld.getClass(className).getMethod(whichMethod).paramExists(whichParam)){
						System.out.println("Enter a new parameter type:");
						String newType = getInput();
						hasUnsavedWork = umld.renameParameterType(className, whichMethod, whichParam, newType);
					}
					else{
						System.out.println("Parameter '" + whichParam + "' does not exist in method '" + whichMethod + "'.");
					}
				}
				else{
					System.out.println("Method '" + whichMethod + "' does not exist in class '" + className + "'.");
				}
			}
			else{
				classDoesNotExist(className);
			}
		}
	}
	
	public static void deleteCommand() {
		if(umld.umlDiagram.isEmpty()) {
			System.out.println("The diagram is empty!");
			return;
		}
		System.out.println("What do you want to delete? [class/relationship/field/method/parameter]");
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
		// Removes a field
		else if(toRemove.equals("field")) {
			System.out.println("Delete field from which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter field to delete:");
				String whichField = getInput();
				hasUnsavedWork = umld.removeField(whichClass, whichField);
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Removes a method
		else if(toRemove.equals("method")) {
			System.out.println("Delete method from which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Enter method to delete:");
				String whichMethod = getInput();
				hasUnsavedWork = umld.removeMethod(whichClass, whichMethod);
			}
			else {
				classDoesNotExist(whichClass);
			}
		}
		// Removes a parameter
		else if(toRemove.equals("parameter")) {
			System.out.println("Delete parameter from which class?");
			String whichClass = getInput();
			if(umld.classExists(whichClass)) {
				System.out.println("Remove parameter from which method?");
				String methodName = getInput();
				if(umld.getClass(whichClass).methodExists(methodName)){
					System.out.println("Enter parameter to delete:");
					String parameterName = getInput();
					hasUnsavedWork = umld.removeParameter(whichClass, methodName, parameterName);
				}
				else{
					System.out.println("The method '" + methodName + "' does not exist in the class '" + whichClass + "'.");
				}
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
			hasUnsavedWork = umld.deleteRelationship(srcClass, destClass);
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
			Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
			while (hmIter.hasNext()) {
				Map.Entry<String, UMLClass> mapElem = (Map.Entry<String, UMLClass>) hmIter.next();
				listClass(mapElem.getKey());
			}
		}	
	}

	public static void listClass (String name) {
		System.out.println();
		System.out.println("Class: ");
		System.out.println("- " + name);
		System.out.println("Fields:");
		for(int i = 0; i < umld.getClass(name).fields.size(); i++) {
			System.out.println("- " + umld.getClass(name).fields.get(i).getFieldName() + ": " + umld.getClass(name).fields.get(i).getFieldType());
		}
		System.out.println();
		System.out.println("Methods:");
		for(int i = 0; i < umld.getClass(name).methods.size(); i++) {
			System.out.print("- " + umld.getClass(name).methods.get(i).getMethodName() + ": " + umld.getClass(name).methods.get(i).getMethodType() + " ");
			System.out.print("(");
			for(int j = 0; j < umld.getClass(name).methods.get(i).getParameterList().size(); j++){
				if(j > 0){
					System.out.print(", ");
				}
				System.out.print(umld.getClass(name).methods.get(i).getParameterList().get(j).getParamType() + " " + umld.getClass(name).methods.get(i).getParameterList().get(j).getParamName());
			}
			System.out.print(")");
			System.out.println();
		}
	}
	
	public static void listClassCommand () {
		System.out.println("Enter class name: ");
		String className = getInput();
		if(umld.classExists(className)) {
			listClass(className);
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
			for(int i = 0; i < umld.relationships.size(); i++) {
				System.out.println();
				System.out.println("Source: " + umld.relationships.get(i).getSource());
				System.out.println("Destination: " + umld.relationships.get(i).getDestination());
				System.out.println("Type: " + umld.relationships.get(i).getType());
			}
		} 
	}
	public static void saveDiagram() throws Exception {
		saver.saveDiagram = umld;
		if(saver.saveFile()) {
			hasUnsavedWork = false;
		}
	}

	public static void loadDiagram() throws Exception {
		if(hasUnsavedWork) {
			System.out.println("There is unsaved work. Are you sure you want to load a file? [y/n]");
			String answer = getInput();
			if(answer.equals("n")) {
				return;
			}
			else if(!answer.equals("y") && !answer.equals("n")) {
				commandNotRecognized();
			}
		}
		loader.loadFile();
		umld = loader.loadDiagram;
	
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