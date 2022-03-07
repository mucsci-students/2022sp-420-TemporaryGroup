package TemporaryGroupGradle;
/*Java Program to Create a Menu and Display the Menu Item Selected*/
import javax.swing.*;




import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Canvas;
//import java.awt.color.*;


public class GUIView implements ActionListener {
    
	static JLabel text;
	static JFrame main = new JFrame("UMLEditor");

	public static Save saver = new Save();
	public static Load loader = new Load();
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static UMLDiagram umld = new UMLDiagram();
	static final int WIDTH = 250;
	static final int HEIGHT = 200;
	//keep tracks of classes rectangles
	static ArrayList<Classes> classRep = new ArrayList<> ();
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
    	//setting frame up
    	main.setSize(screenSize);
    	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main.setLayout(null);
    	main.setResizable(false);
    	
    	//Create an object
    	GUIView obj = new GUIView();
    	
    	//fill available indexes array
    	fillAvailable();
    	
    	//Create Classes Menu
    	JMenu classes = new JMenu("Classes");
		
    	//Create Menu Items for classes
    	JMenuItem[] itemC = new JMenuItem[3];
    	itemC [0] = new JMenuItem ("Add class");
    	itemC [1] = new JMenuItem ("Rename class");
    	itemC [2] = new JMenuItem ("Delete class");
    	for (int i = 0; i < itemC.length; ++i) {
    		itemC[i].addActionListener(obj);
    		classes.add(itemC[i]);
    	}
    
    	
    	//Create Fields Menu
    	JMenu fields = new JMenu("Fields");
    	
    	//Create Menu Items for fields
    	JMenuItem[] itemF = new JMenuItem[3];
    	itemF [0] = new JMenuItem ("Add field");
    	itemF [1] = new JMenuItem ("Rename field");
    	itemF [2] = new JMenuItem ("Delete field");
    	for (int i = 0; i < itemF.length ; ++i) {
    		itemF[i].addActionListener(obj);
    		fields.add(itemF[i]);
    	}
    	
    	
    	//Create Methods Menu
    	JMenu methods = new JMenu("Methods");
    	
    	//Create Menu Items for methods
    	JMenuItem[] itemM = new JMenuItem[3];
    	itemM [0] = new JMenuItem ("Add method");
    	itemM [1] = new JMenuItem ("Rename method");
    	itemM [2] = new JMenuItem ("Delete method");
    	for (int i = 0; i < itemM.length; ++i) {
    		itemM[i].addActionListener(obj);
    		methods.add(itemM[i]);
    	}
    	
    	
    	//Create parameters Menu
    	JMenu parameters = new JMenu("Parameters");
    	
    	//Create Menu Items for parameters
    	JMenuItem[] itemP = new JMenuItem[3];
    	itemP [0] = new JMenuItem ("Add parameter");
    	//itemP [1] = new JMenuItem ("Add parameters");
    	itemP [1] = new JMenuItem ("Remove parameter");
    	//itemP [3] = new JMenuItem ("Remove parameters");
    	itemP [2] = new JMenuItem ("Change parameter");
    	//itemP [5] = new JMenuItem ("Change parameters");
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
    	JMenuItem[] itemR = new JMenuItem[3];
    	itemR [0] = new JMenuItem ("Add relationship");
    	itemR [1] = new JMenuItem ("Change relationship");
    	itemR [2] = new JMenuItem ("Delete relationship");
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
    	
    	//Display the frame
    	main.setVisible(true);
    	
    	
    }
    
    //Function to implement menu items 
    public void actionPerformed(ActionEvent e)
    {    	
    	
    	if (e.getActionCommand().equals("Add class")) {
    		if (index == CLASSESPERROW * CLASSESPERCOL ) {
    			JOptionPane.showMessageDialog(main,"No more classes can be displayed");
    		} else {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			JOptionPane.showMessageDialog(main,"Error when creating class, try again");
    		} else if ( umld.addClass(className)) {
    			//draw class
    			fillClassRep(className, index / CLASSESPERROW, index % CLASSESPERROW, index);
    			available [0] = CLASSESPERROW * CLASSESPERCOL;
    			index = valIndex();
        	} else {
    			JOptionPane.showMessageDialog(main,"class already exists, try again");    			
        	}
    		}
    	
    	} else if (e.getActionCommand().equals("Rename class")) {
    		ListClassesWindow myList = new ListClassesWindow (main, classNames);
    		int localIndex = myList.getMyClassI();
    		if (localIndex >= 0) {
    			String newName = JOptionPane.showInputDialog("Enter new class name");
    			if (newName == null) {
        			JOptionPane.showMessageDialog(main,"Error entering new class name, try again");
        		} else if (umld.renameClass(classNames[localIndex], newName)) {
    				classRep.get(localIndex).renameClass(newName);
    				classNames[localIndex] = newName;    				
    			}
    		} else {
    			JOptionPane.showMessageDialog(main,"Error when renaming the class, try again");
    		}
    		
    	} else if (e.getActionCommand().equals("Delete class")) {
    		ListClassesWindow myList = new ListClassesWindow (main, classNames);
    		int localIndex = myList.getMyClassI();
    		if (localIndex >= 0) {
    			if ( umld.removeClass(classNames[localIndex]) ) { 
    	    		removeClass (localIndex); 
    				classRep.remove(localIndex);
    	    		classNames[localIndex] = " ";
    	    		available[available.length - 1] = localIndex;
    	    		index = valIndex();		
    	    	}
    		} else {
    			JOptionPane.showMessageDialog(main, "Class not found");	
    		}
    		
    		//handling fields menu
    	} else if (e.getActionCommand().equals("Add field")) {
    		ListClassesWindow myList = new ListClassesWindow (main, classNames);
    		int localIndex = myList.getMyClassI();
    		if (localIndex >= 0) {
    			String [] typeAndName = getTypeAndName();
    			if (typeAndName[0] != null || typeAndName[1] != null) {
    	    		if (umld.addField(classNames[localIndex], typeAndName[1], typeAndName[0])) {
    	    			classRep.get(localIndex).addField(typeAndName[0], typeAndName[1]);
    	    		}
    			}     			
    		} else {
    			JOptionPane.showMessageDialog(main,"Error when adding field, try again");
    		
    		} 
    		} else if (e.getActionCommand().equals("Rename field")) { 
    			ListClassesWindow classesList = new ListClassesWindow (main, classNames);
    			int classIndex = classesList.getMyClassI();
    			if (classIndex >= 0) {
    				ListFieldsWindow fieldsList = new ListFieldsWindow (main, umld.getClass(classNames[classIndex]).getFields());
    				int fieldIndex = fieldsList.getIndex();
    				if (fieldIndex >= 0) {
    					String oldName = umld.getClass(classNames[classIndex]).getFields().get(fieldIndex).getFieldName();
    					String newName = JOptionPane.showInputDialog(main, "Enter new name for field");
    					if (newName != null) {
    						if (umld.renameField(classNames[classIndex], oldName, newName)) {
    							classRep.get(classIndex).renameField(newName, oldName, fieldIndex);
    						} else {
    	    					JOptionPane.showMessageDialog(main,"Error when renaming field, try again");    			
    		    			}
    					}
    				}    	
    				}
    		} else if (e.getActionCommand().equals("Delete field")) { 
    			ListClassesWindow classesList = new ListClassesWindow (main, classNames);
    			int classIndex = classesList.getMyClassI();
    			if (classIndex >= 0) {
    				ListFieldsWindow fieldsList = new ListFieldsWindow (main, umld.getClass(classNames[classIndex]).getFields());
    				int fieldIndex = fieldsList.getIndex();
    				if (fieldIndex >= 0) {
    					String fieldName = umld.getClass(classNames[classIndex]).getFields().get(fieldIndex).getFieldName();
    						if (umld.removeField(classNames[classIndex], fieldName)) {
    							classRep.get(classIndex).deleteField(fieldIndex);
    						} else {
    	    					JOptionPane.showMessageDialog(main,"Error when deleting field, try again");    			
    		    			}
    					}
    			}    	
    				   		
    	//methods menu
    	} else if (e.getActionCommand().equals("Add method")) {
    		ListClassesWindow myList = new ListClassesWindow (main, classNames);
    		int localIndex = myList.getMyClassI();
    		if (localIndex >= 0) {
    			String [] typeAndName = getTypeAndName();
    			if (typeAndName[0] != null || typeAndName[1] != null) {
    	    		if (umld.addMethod(classNames[localIndex], typeAndName[1], typeAndName[0])) {
    	    			classRep.get(localIndex).addMethod(typeAndName[0], typeAndName[1]);
    	    		}
    			}     			
    		} else {
    			JOptionPane.showMessageDialog(main,"Error when adding method, try again");
    		
    		} 
    	} else if (e.getActionCommand().equals("Rename method")) {
    		ListClassesWindow classesList = new ListClassesWindow (main, classNames);
			int classIndex = classesList.getMyClassI();
			if (classIndex >= 0) {
				ListMethodsWindow methodsList = new ListMethodsWindow (main, umld.getClass(classNames[classIndex]).getMethods());
				int methodIndex = methodsList.getIndex();
				if (methodIndex >= 0) {
					String oldName = umld.getClass(classNames[classIndex]).getMethods().get(methodIndex).getMethodName();
					String newName = JOptionPane.showInputDialog(main, "Enter new name for method");
					if (newName != null) {
						if (umld.renameMethod(classNames[classIndex], oldName, newName)) {
							classRep.get(classIndex).renameMethod(newName, oldName, methodIndex);
						} else {
	    					JOptionPane.showMessageDialog(main,"Error when renaming method, try again");    			
		    			}
					}
				}    	
				}
    	} else if (e.getActionCommand().equals("Delete method")) {
    		ListClassesWindow classesList = new ListClassesWindow (main, classNames);
			int classIndex = classesList.getMyClassI();
			if (classIndex >= 0) {
				ListMethodsWindow methodsList = new ListMethodsWindow (main, umld.getClass(classNames[classIndex]).getMethods());
				int methodIndex = methodsList.getIndex();
				if (methodIndex >= 0) {
					String methodName = umld.getClass(classNames[classIndex]).getMethods().get(methodIndex).getMethodName();
						if (umld.removeMethod(classNames[classIndex], methodName)) {
							classRep.get(classIndex).deleteMethod(methodIndex);
						} else {
	    					JOptionPane.showMessageDialog(main,"Error when deleting method, try again");    			
		    			}
					}
			}    
			
			
    	//parameters menu
    	} else if (e.getActionCommand().equals("Add parameter")) {
    		ListClassesWindow classesList = new ListClassesWindow (main, classNames);
			int classIndex = classesList.getMyClassI();
			if (classIndex >= 0) {
				ListMethodsWindow methodsList = new ListMethodsWindow (main, umld.getClass(classNames[classIndex]).getMethods());
				int methodIndex = methodsList.getIndex();
				if (methodIndex >= 0) {
					String [] typeAndName = getTypeAndName(); 
					if ( umld.addParameter (classNames[classIndex], 
							umld.getClass(classNames[classIndex]).getMethods().get(methodIndex).getMethodName(),
							typeAndName[1], typeAndName[0] )) {
							classRep.get(classIndex).addParameter(typeAndName[1], typeAndName[0], methodIndex);
						}
						} else {
	    					JOptionPane.showMessageDialog(main,"Error when renaming method, try again");    			
		    			}
					}
				   	
		} else if (e.getActionCommand().equals("Change parameter")) {
    			JOptionPane.showMessageDialog(main,"Error when renaming parameter, try again");
    			
    		
    		
    	} else if (e.getActionCommand().equals("Delete parameter")) {
    		JOptionPane.showMessageDialog(main,"Error when deleting parameter, try again");
    			
    		
    		
    	} else if (e.getActionCommand().equals("Add relationship")) {
    			String[] myInputs = getRelInput ();
    			int localIndex = findIndex (myInputs[0]);
    			if (myInputs[0] != null && myInputs[1] != null && myInputs[2] != null) {
    				if ( umld.addRelationship (myInputs[0], myInputs[1], myInputs[2])) {
    					classRep.get(localIndex).addRelationship (myInputs[0], myInputs[1], myInputs[2]);
    				}
    			} else {
    			JOptionPane.showMessageDialog (main, "Error when creating relationship, try again");
    			} 
    		
    	} else if (e.getActionCommand().equals("Change relationship")) {
    		//TO DO
    		JOptionPane.showMessageDialog(main,"still working on it");
    		
    		
    		
    	} else if (e.getActionCommand().equals("Delete relationship")) {
    			//need to complete
    		
    		
    		
    		
    	} else if (e.getActionCommand().equals("Save")) {
    		//TO DO
    		saver.saveDiagram = umld;
    		try {
				saver.saveFileGUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		
    		
    	} else if (e.getActionCommand().equals("Load")) {
    		//TO DO
    		try {
				loader.loadFileGUI();
				umld = loader.loadDiagram;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		
    		
    		
    	} else if (e.getActionCommand().equals("Help")) {
    		//TO DO
    		JOptionPane.showMessageDialog(main,"still working on it");
    		
    		
    		
    	} else if (e.getActionCommand().equals("CLI mode")) {
    		String[] args = new String[1];
    		try {
    			main.setVisible(false);    		
    			UMLCli.main(args);
    			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		
    	}
    }
    
    //helper functions 
    
   //fill a class rectangle area  given an index for the position on the screen 
    public static void fillClassRep (String className, int x, int y, int index) {
    	int [] myComp = calculateXY (x, y);
    	classRep.add(index, new Classes(myComp[0], myComp[1], WIDTH, HEIGHT));
    	classNames[index] = className;
    	classRep.get(index).addName(className);
    	main.add(classRep.get(index));
    	main.repaint();
    	++ index; 
    }
    
    public static void removeClass (int classIndex) {
    	main.remove(classRep.get(classIndex));
    	main.repaint();
    }
    
    //get the x and y position for the given indexes 
    //x = 1 y = 4 would go position [1,4] on a matrix of 6x4
    public static int[] calculateXY (int x, int y) {
    	int startX = 50;
    	int startY = 50;
    	return new int[] {startX + x * 275, startY + y * 225};
    }
    
    //get type and name for user 
    public String[] getTypeAndName () {
    	JTextField typeF = new JTextField(); 
    	JTextField nameF = new JTextField();
    	String [] toReturn = new String [2];
    	Object [] inputs = {
    			"type", typeF, 
    			"name", nameF
    	};
    	JOptionPane.showConfirmDialog(null, inputs, "Enter type and name", JOptionPane.OK_CANCEL_OPTION);
    	if (typeF.getText() != null || nameF.getText() != null) {
    		toReturn[0] = typeF.getText();
    		toReturn[1] = nameF.getText();
    	}
    	return toReturn;
    }
    
    public String[] getRelInput () {
    	JTextField srcF = new JTextField();
    	JTextField destF = new JTextField();
    	JTextField typeF = new JTextField();
    	String [] toReturn = new String [3];
    	Object [] inputs = {
    			"source",		srcF,
    			"destination",	destF,	
    			"type", 		typeF, 
    			
    	};
    	JOptionPane.showConfirmDialog(null, inputs, "Enter type and name", JOptionPane.OK_CANCEL_OPTION);
    	if (typeF.getText() != null || srcF.getText() != null || destF.getText() != null) {
    		toReturn[0] = srcF.getText();
    		toReturn[1] = destF.getText();
    		toReturn[2] = typeF.getText();
    	}
    	return toReturn;
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
}