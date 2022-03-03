/*Java Program to Create a Menu and Display the Menu Item Selected*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Canvas;
//import java.awt.color.*;


public class GUIView extends Canvas implements ActionListener {
    
	static JLabel text;
	static JFrame main = new JFrame("UMLEditor");
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static UMLDiagram umld = new UMLDiagram();
	static final int WIDTH = 250;
	static final int HEIGHT = 200;
	//keep tracks of classes rectangles
	static ArrayList<Rectangle> classRep = new ArrayList<> ();
	static final int CLASSESPERROW = (screenSize.height - 75) / 200;
	static final int CLASSESPERCOL = (screenSize.width - 50) / 250;
	//store class name -- y pos for field -- y position for method -- y position for parameter
	static String[] classNames = new String [CLASSESPERROW * CLASSESPERCOL];
	
	//keep track of current index
	static int index = 0;
	

	//keep track of available indexes if any
	static int [] available = new int [CLASSESPERROW * CLASSESPERCOL];
	
    //Driver function
    public static void main(String[] args)
    {
    	//Create a frame
    	
    	//main.pack();
    	main.setSize(screenSize);
    	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main.setLayout(new FlowLayout());
    	main.setResizable(false);
    	
    	//Create an object
    	GUIView obj = new GUIView();
    	
    	//fill available indexes array
    	fillAvailable();
    	
    	//Create Classes Menu
    	JMenu classes = new JMenu("Classes");
		
    	//Create Menu Items for classes
    	JMenuItem[] itemC = new JMenuItem[5];
    	itemC [0] = new JMenuItem ("Add class");
    	itemC [1] = new JMenuItem ("Rename class");
    	itemC [2] = new JMenuItem ("Delete class");
    	itemC [3] = new JMenuItem ("List class");
    	itemC [4] = new JMenuItem ("List all classes");
    	for (int i = 0; i < itemC.length; ++i) {
    		itemC[i].addActionListener(obj);
    		classes.add(itemC[i]);
    	}
    
    	
    	//Create Fields Menu
    	JMenu fields = new JMenu("Fields");
    	
    	//Create Menu Items for fields
    	JMenuItem[] itemF = new JMenuItem[4];
    	itemF [0] = new JMenuItem ("Add field");
    	itemF [1] = new JMenuItem ("Rename field");
    	itemF [2] = new JMenuItem ("Delete field");
    	itemF [3] = new JMenuItem ("List fields");
    	for (int i = 0; i < itemF.length ; ++i) {
    		itemF[i].addActionListener(obj);
    		fields.add(itemF[i]);
    	}
    	
    	
    	//Create Methods Menu
    	JMenu methods = new JMenu("Methods");
    	
    	//Create Menu Items for methods
    	JMenuItem[] itemM = new JMenuItem[4];
    	itemM [0] = new JMenuItem ("Add method");
    	itemM [1] = new JMenuItem ("Rename method");
    	itemM [2] = new JMenuItem ("Delete method");
    	itemM [3] = new JMenuItem ("List methods");
    	for (int i = 0; i < itemM.length; ++i) {
    		itemM[i].addActionListener(obj);
    		methods.add(itemM[i]);
    	}
    	
    	
    	//Create parameters Menu
    	JMenu parameters = new JMenu("Parameters");
    	
    	//Create Menu Items for parameters
    	JMenuItem[] itemP = new JMenuItem[6];
    	itemP [0] = new JMenuItem ("Add parameter");
    	itemP [1] = new JMenuItem ("Add parameters");
    	itemP [2] = new JMenuItem ("Remove parameter");
    	itemP [3] = new JMenuItem ("Remove parameters");
    	itemP [4] = new JMenuItem ("Change parameter");
    	itemP [5] = new JMenuItem ("Change parameters");
    	for (int i = 0; i < itemP.length; ++i) { 
    		itemP[i].addActionListener(obj);
    		parameters.add(itemP[i]);
    	}
    	
    	
    	//Create File Menu
    	JMenu file = new JMenu("File");
    	
    	//Create Menu Items for file
    	JMenuItem[] itemFi = new JMenuItem[2];
    	itemFi [0] = new JMenuItem ("Save");
    	itemFi [1] = new JMenuItem ("Load");
    	for (int i = 0; i < itemFi.length; ++i) {
    		itemFi[i].addActionListener(obj);
    		file.add(itemFi[i]);
    	}
    	
    	
    	//Create relationships menu
    	JMenu relationship = new JMenu("Relationships");
    	
    	//Create Menu Items for relationships
    	JMenuItem[] itemR = new JMenuItem[5];
    	itemR [0] = new JMenuItem ("Add relationship");
    	itemR [1] = new JMenuItem ("Change relationship");
    	itemR [2] = new JMenuItem ("Delete relationship");
    	itemR [3] = new JMenuItem ("List relationship");
    	itemR [4] = new JMenuItem ("List all relationships");
    	for (int i = 0; i < itemR.length; ++i) {
    		itemR[i].addActionListener(obj);
    		relationship.add(itemR[i]);
    	}
    	
    	
    	//Create program menu
    	JMenu editor = new JMenu ("Editor");
    	
    	//Create menu items for program menu
    	JMenuItem[] itemE = new JMenuItem[2];
    	itemE [0] = new JMenuItem ("Help");
    	itemE [1] = new JMenuItem ("CLI mode");
    	for (int i = 0; i < itemE.length; ++i) {
    		itemE[i].addActionListener(obj);
    		editor.add(itemE[i]);
    	}
    	
    	//Create a menu bar
    	JMenuBar mb=new JMenuBar();
    	main.setJMenuBar(mb);
    	mb.add(file);
    	mb.add(classes);
    	mb.add(fields);
    	mb.add(methods);
    	mb.add(parameters);
    	mb.add(relationship);
    	mb.add(editor);
    	
    	//Create the label
    	text = new JLabel();
    	main.add(text);

    	//Display the frame
    	main.setVisible(true);
    }
    
    //Function to implement menu items 
    public void actionPerformed(ActionEvent e)
    {    	
    	
    	if (e.getActionCommand().equals("Add class")) {
    		if (index == CLASSESPERROW * CLASSESPERCOL ) {
    			text.setText("No more classes can be displayed ");
    		} else {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Error when creating class, try again");
    		} else if ( umld.addClass(className)) {
    			//draw class
    			fillClassRep(className, index / CLASSESPERROW, index % CLASSESPERROW, index);
    			available [0] = CLASSESPERROW * CLASSESPERCOL;
    			index = valIndex();
    			updateFirstRow();
        	} else {
    			text.setText("class already exists, try again");
    			updateFirstRow();
        	}
    		}
    	
    	} else if (e.getActionCommand().equals("Rename class")) {
    		String oldName = JOptionPane.showInputDialog (main, "Enter class to rename");
    		if (oldName == null) {
    			text.setText("Error when renaming class, try again");
    		}
    		int localIndex = findIndex(oldName);
    		if (localIndex != -1 ) {
    			String newName = JOptionPane.showInputDialog (main, "Enter new class name");
    			if (newName == null) {
        			text.setText("Error when renaming class, try again");
    			}
        		umld.renameClass(oldName, newName);
    			classNames[localIndex] = newName;
    			Graphics classAdded = main.getGraphics();
    			classAdded.clearRect(classRep.get(localIndex).x, classRep.get(localIndex).y - 20, 125, 19);
    			classAdded.drawString (classNames[localIndex], classRep.get(localIndex).x + 20 ,classRep.get(localIndex).y - 5);
    		} else {
    			JOptionPane.showMessageDialog(main, "Class not found");
    			updateFirstRow();
    		}
    		updateFirstRow();
    		
    	} else if (e.getActionCommand().equals("Delete class")) {
    		String className = JOptionPane.showInputDialog (main, "Enter class to delete");
    		if ( umld.removeClass(className) ) { 
    		int localIndex = findIndex (className);
    		if (localIndex != -1) {
    			Graphics classAdded = main.getGraphics();
    			classAdded.clearRect (classRep.get(localIndex).x - 10, classRep.get(localIndex).y - 20, WIDTH + 25, HEIGHT + 25);
    			classRep.remove(localIndex);
    			classNames[localIndex] = " ";
    			available[available.length - 1] = localIndex;
    			index = valIndex();
    			updateFirstRow();
    		}
    		} else {
    			JOptionPane.showMessageDialog(main, "Class not found");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("List class")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Error when creating class, try again");
    		}
    		JOptionPane.showMessageDialog(main, listClass(className));
    		updateFirstRow();
    		
    	} else if (e.getActionCommand().equals("List all classes")) {
    		JOptionPane.showMessageDialog(main, listOfClasses());
    		updateFirstRow();
    		
    		//handling fields menu
    		//need to fix diagram when classname is null
    	} else if (e.getActionCommand().equals("Add field")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    		}
    		String fieldName = JOptionPane.showInputDialog(main, "Enter field name");
    		if (fieldName == null) {
    			text.setText("Field name error, try again.");
    		} else if (umld.getClass(className).getFields().size() >=7 ) {
    			text.setText("Field will not be displayed");
    		} else if (umld.addField(className, fieldName)) {
    			clearFields(className);
    			drawFields(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when adding field, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Rename field")) { 
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		String oldName = JOptionPane.showInputDialog(main, "Enter field to rename");
    		if (oldName == null) {
    			text.setText("Field name error, try again.");
    			updateFirstRow();
    		} 
    		String newName = JOptionPane.showInputDialog(main, "Enter field new name");
    		if (newName == null) {
    			text.setText("Field name error, try again.");
    			updateFirstRow();
    		} else if ( umld.renameField(className, oldName, newName) ) {
    			clearFields(className);
    			drawFields(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when adding field, try again");
    			updateFirstRow();
    		}	
    		
    	} else if (e.getActionCommand().equals("Delete field")) { 
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		} 
    		if (umld.classExists(className)) {
    			String fieldName = JOptionPane.showInputDialog(main, "Enter field to delete");
    			if (fieldName == null) {
    			text.setText("Field name error, try again.");
    			updateFirstRow();
    			} else if ( umld.removeField(className, fieldName) ) {
    				clearFields(className);
    				drawFields(className);
    				updateFirstRow();
    			}
    			} else {
    			text.setText("Error when deleting field, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("List fields")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		} else if (umld.classExists (className)){
    			JOptionPane.showMessageDialog(main, listFields (className));
    			updateFirstRow();
    		} else {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Add method")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    		}
    		String methodName = JOptionPane.showInputDialog(main, "Enter method name");
    		if (methodName == null) {
    			text.setText("Field name error, try again.");
    		} else if (umld.getClass(className).getFields().size() >=5 ) {
    			text.setText("Method will not be displayed");
    		} else if (umld.addMethod(className, methodName)) {
    			clearMethods(className);
    			drawMethods(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when adding method, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Rename method")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		String oldName = JOptionPane.showInputDialog(main, "Enter method to rename");
    		if (oldName == null) {
    			text.setText("Method name error, try again.");
    			updateFirstRow();
    		} 
    		String newName = JOptionPane.showInputDialog(main, "Enter method new name");
    		if (newName == null) {
    			text.setText("Method name error, try again.");
    			updateFirstRow();
    		} else if ( umld.renameMethod(className, oldName, newName) ) {
    			clearMethods(className);
    			drawMethods(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when adding field, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Delete method")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		} 
    		if (umld.classExists(className)) {
    			String methodName = JOptionPane.showInputDialog(main, "Enter method to delete");
    			if (methodName == null) {
    			text.setText("Field name error, try again.");
    			updateFirstRow();
    			} else if ( umld.removeMethod(className, methodName) ) {
    				clearMethods(className);
    				drawMethods(className);
    				updateFirstRow();
    			}
    			} else {
    			text.setText("Error when deleting method, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("List methods")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		} else if (umld.classExists (className)){
    			JOptionPane.showMessageDialog(main, listMethods (className));
    			updateFirstRow();
    		} else {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Add parameter")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		String methodName = JOptionPane.showInputDialog(main, "Enter method name");
    		if (methodName == null) {
    			text.setText("Method name error, try again.");
    			updateFirstRow();
    		} 
    		String parameterName = JOptionPane.showInputDialog(main, "Enter parameter name");
    		if (methodName == null) {
    			text.setText("Parameter name error, try again.");
    			updateFirstRow();
    		}
    		else if (umld.addParameter(className, methodName, parameterName)) {
    			clearMethods(className);
    			drawMethods(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when adding parameter, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Add parameters")) {
    		text.setText("still working on it");  
    		updateFirstRow();
    		
    	} else if (e.getActionCommand().equals("Change parameter")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		String methodName = JOptionPane.showInputDialog(main, "Enter method name");
    		if (methodName == null) {
    			text.setText("Method name error, try again.");
    			updateFirstRow();
    		} 
    		String oldName = JOptionPane.showInputDialog(main, "Enter parameter to change");
    		if (methodName == null) {
    			text.setText("Parameter name error, try again.");
    			updateFirstRow();
    		}
    		String newName = JOptionPane.showInputDialog(main, "Enter parameter name");
    		if (methodName == null) {
    			text.setText("Parameter name error, try again.");
    			updateFirstRow();
    		}
    		else if (umld.renameParameter(className, methodName, oldName, newName)) {
    			clearMethods(className);
    			drawMethods(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when renaming parameter, try again");
    			updateFirstRow();
    		}
    		
    	} else if (e.getActionCommand().equals("Change parameters")) {
    		text.setText("still working on it");
    		updateFirstRow();
    		
    	} else if (e.getActionCommand().equals("Delete parameter")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			text.setText("Class name error, try again");
    			updateFirstRow();
    		}
    		String methodName = JOptionPane.showInputDialog(main, "Enter method name");
    		if (methodName == null) {
    			text.setText("Method name error, try again.");
    			updateFirstRow();
    		} 
    		String parameterName = JOptionPane.showInputDialog(main, "Enter parameter to delete");
    		if (methodName == null) {
    			text.setText("Parameter name error, try again.");
    			updateFirstRow();
    		}
    		else if (umld.removeParameter(className, methodName, parameterName)) {
    			clearMethods(className);
    			drawMethods(className);
    			updateFirstRow();
    		} else {
    			text.setText("Error when deleting parameter, try again");
    			updateFirstRow();
    		}
    		
    		
    	} else if (e.getActionCommand().equals("Delete parameters")) {
    		text.setText("still working on it");
    		updateFirstRow();
    		
    		
    	} else if (e.getActionCommand().equals("Add relationship")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("Change relationship")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("Delete relationship")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("List relationship")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("List all relationships")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("Save")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("Load")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("Help")) {
    		//TO DO
    		
    		
    	} else if (e.getActionCommand().equals("CLI mode")) {
    		
    		
    	}
    }
    
    //helper functions 
    
    //return string with names of all classes
    public static String listOfClasses () {
    	if (umld.umlDiagram.isEmpty()) {
    		return "Diagram is empty"; 
		} 
		else {
			Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
			String toReturn = "";
			ArrayList<String> classes = new ArrayList<String> ();
			while (hmIter.hasNext()) {
				Map.Entry<String, UMLClass> mapElem = (Map.Entry<String, UMLClass>) hmIter.next();
				classes.add(mapElem.getKey()); 
				//need to add fields and methods 
			}
			for (int i = 0; i < classes.size(); ++i ) {
				toReturn += classes.get(i) + '\n';
			}
			return toReturn;
		}
    }
    
    //returns string listing characteristics of the given class
    public static String listClass (String className) {
    	if (umld.umlDiagram.isEmpty()) {
    		return "Diagram is empty";
    	} else  {
    		Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
			String toReturn = "";
			ArrayList<String> classToList = new ArrayList<String> ();
				classToList.add(" Class:		" + className); 
				classToList.add(" Fields: 		 - ");
				for(int i = 0; i < umld.getClass(className).fields.size(); i++) {
					classToList.get(1).concat (umld.getClass(className).fields.get(i).getFieldName());
				}
				classToList.add(" Methods:		 - ");
				
				for (int i = 0; i < umld.getClass(className).methods.size(); i++) {
					classToList.get(2).concat(umld.getClass(className).methods.get(i).getMethodName());
				}
				
				for (int i = 0; i < classToList.size(); ++i ) {
				toReturn += classToList.get(i) + '\n';
				}
				return toReturn;
			}
    }
    
    //fill a class rectangle area  given an index for the position on the screen 
    public static void fillClassRep (String className, int x, int y, int index) {
    	int [] myComp = calculateXY (x, y);
    	classRep.add(index, new Rectangle (myComp[0], myComp[1], WIDTH, HEIGHT));    	
    	classNames[index] = className;
    	Graphics classAdded = main.getGraphics();
		classAdded.drawRect(classRep.get(index).x, classRep.get(index).y, WIDTH, HEIGHT);
		classAdded.drawString(className, myComp[0] + 20, myComp[1] - 5);
		classAdded.drawString ("Fields		: ", myComp[0] + 25, myComp[1] + 10);
		classAdded.drawString ("Methods		: ", myComp[0] + 25, myComp[1] + 90);
		classAdded.drawString ("Relationship: ", myComp[0] + 25, myComp[1] + 190);
		++ index;
    }
    
    //get the x and y position for the given indexes 
    //x = 1 y = 4 would go position [1,4] on a matrix of 6x4
    public static int[] calculateXY (int x, int y) {
    	int startX = 50;
    	int startY = 75;
    	return new int[] {startX + x * 275, startY + y * 225};
    }
    
    //gets the number of classes in the diagram
    //might not need 
    public static int getNumberOfClasses () {
    	int numberOfClasses = 0;
		Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
    	while (hmIter.hasNext()) {
			++numberOfClasses; 
		}
    	return numberOfClasses;
    }
    
    //update the first row that was destroyed by menu item dropdowns
    public static void updateFirstRow () {
    	for (int i = 0; i < classRep.size(); ++i)
    	{
    		if (i % 3 == 0) {
    			//JPanel localPanel = new JPanel();
    			Graphics classAdded = main.getGraphics();
    			classAdded.drawRect (classRep.get(i).x, classRep.get(i).y, WIDTH, HEIGHT);
    			classAdded.drawString (classNames[i], classRep.get(i).x + 20 ,classRep.get(i).y - 5);
    			classAdded.drawString ("Fields		: ", classRep.get(i).x + 25, classRep.get(i).y + 10);
    			classAdded.drawString ("Methods		: ", classRep.get(i).x + 25, classRep.get(i).y + 90);
    			classAdded.drawString ("Relationship: ", classRep.get(i).x + 25, classRep.get(i).y + 190);
    			clearFields(classNames[i]);
    			drawFields(classNames[i]);
    		}
    	}
    }
    
    //return index of class with the given name
    //if no class is found return -1
    public static int findIndex (String className) {
    	for (int i = 0; i < index; ++i) {
    		if (classNames[i].equals(className)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    //initialize the available array 
    //all indexes are available when empty
    public static void fillAvailable () {
    	for (int i = 0; i < available.length; ++i) {
    		available[i] = i;
    	}
    }
    
    //sort available index array
    //first element is guaranteed to be smallest available index
    public static int valIndex () {
    	Arrays.sort(available);
    	return available[0];
    }
    
    public static void drawFields (String className) {
    	if (umld.classExists(className)) {
    		ArrayList<Field> localFields = umld.getClass(className).getFields();
    		int localIndex = findIndex (className);
    		Graphics classAdded = main.getGraphics();
    		for (int i = 0; i < localFields.size(); ++i) {
    			classAdded.drawString(localFields.get(i).getFieldName() , classRep.get(localIndex).x + 50, classRep.get(localIndex).y + (i + 2) * 10);
        		if (i == 6) {
        			break;
        		}
    		}
    	}
    }
    
    public static void clearFields (String className) {
    	if (umld.classExists(className)) {
    		int localIndex = findIndex (className);
    		Graphics classAdded = main.getGraphics();
    		classAdded.clearRect(classRep.get(localIndex).x + 25, classRep.get(localIndex).y + 20, WIDTH - 40, 60);
    		
    	} 
    }

    public static String listFields (String className) {
    	String toReturn = "Fields ";
    	for (int i = 0; i < umld.getClass(className).getFields().size(); ++i) {
    		toReturn += "\n " + " " + ( umld.getClass(className).getFields().get(i).getFieldName());
    	}
    	return toReturn;
    	
    }
    
    //methods
    public static void drawMethods (String className) {
    	if (umld.classExists(className)) {
    		ArrayList<Method> localMethods = umld.getClass(className).getMethods();
    		int localIndex = findIndex (className);
    		Graphics classAdded = main.getGraphics();
    		for (int i = 0; i < localMethods.size(); ++i) {
    			classAdded.drawString(localMethods.get(i).getMethodName() + "==>" , classRep.get(localIndex).x + 50, classRep.get(localIndex).y + (2*i + 2) * 10 + 80);
    			ArrayList<Parameter> localParameters = localMethods.get(i).getParameterList();
    			int yOffset = 10;
    			int xOffset = localMethods.get(i).getMethodName().length() * 5 + 75;
    			for (int j = 0; j < localParameters.size(); ++j) {
    				int newXOffset = localParameters.get(j).getParamName().length() * 5 + xOffset;  
    				if (newXOffset >= 200) {
    					yOffset += 10;
    					xOffset = localMethods.get(i).getMethodName().length() * 5 + 75;
    				}
    				if (yOffset > 20) {
    					break;
    				}
    				classAdded.drawString(" " + localParameters.get(j).getParamName(), classRep.get(localIndex).x + xOffset, classRep.get(localIndex).y + (i + 2) * 10 + yOffset + 70);
    				xOffset = newXOffset; 
    				
    			}
    			
        		if (i == 4) {
        			break;
        		}
    		}
    	}
    }
    
    public static void clearMethods (String className) {
    	if (umld.classExists(className)) {
    		int localIndex = findIndex (className);
    		Graphics classAdded = main.getGraphics();
    		classAdded.clearRect(classRep.get(localIndex).x + 25, classRep.get(localIndex).y + 70, WIDTH - 40, 90);
    		
    	} 
    }

    public static String listMethods (String className) {
    	String toReturn = "Methods ";
    	for (int i = 0; i < umld.getClass(className).getMethods().size(); ++i) {
    		toReturn += "\n " + " " + ( umld.getClass(className).getMethods().get(i).getMethodName());
    	}
    	return toReturn;
    	
    }
    
    public static void drawRelationships (String className) {
    	
    }
    
    
    
    //canvas paint method
    /*public void paint (Graphics g, int x,  int y, String className) {
    	int [] myComp = calculateXY (x, y);
    	classRep.add(index, new Rectangle (myComp[0], myComp[1], WIDTH, HEIGHT));    	
    	classNames[index] = className;
    	Graphics classAdded = main.getGraphics();
		classAdded.drawRect(classRep.get(index).x, classRep.get(index).y, WIDTH, HEIGHT);
		classAdded.drawString(className, myComp[0] + 20, myComp[1] - 5);
		++ index;
    }
    
    class DrawTest extends JPanel {
    	public void paintClass(Graphics g, int x, int y, String className) {
    		int [] myComp = calculateXY (x, y);
        	classRep.add(index, new Rectangle (myComp[0], myComp[1], WIDTH, HEIGHT));    	
        	classNames[index] = className;
        	g.drawRect(classRep.get(index).x, classRep.get(index).y, WIDTH, HEIGHT);
    		g.drawString(className, myComp[0] + 20, myComp[1] - 5);
    		
    		++ index;
    	}
    } */
}