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
	static ArrayList<Arrow> relationships = new ArrayList<> ();
	static final int CLASSESPERROW = (screenSize.height - 75) / 200;
	static final int CLASSESPERCOL = (screenSize.width - 50) / 250;
	//store class name -- y pos for field -- y position for method -- y position for parameter
	static String[] classNames = new String [100];
	
	//keep track of current index
	static int index = 0;
	//keep track of relationship and classes associated
	static int relationshipID = 0;

	//keep track of available indexes if any
	static int [] available = new int [CLASSESPERROW * CLASSESPERCOL];
	//use for dragging
	static ComponentMover cm = new ComponentMover();

	
	
    //Driver function
    public static void main(String[] args)
    {
    	//setting frame up
    	main.setSize(screenSize);
    	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main.setLayout(null);
    	main.getContentPane().setBackground(Color.DARK_GRAY);
    	
    	//Create an object
    	GUIView obj = new GUIView();
    	
    	//fill available indexes array
    	fillAvailable();
    	
    	//Create Classes Menu
    	JMenu classes = new JMenu("Classes");
		
    	//Create Menu Items for classes
    	JMenuItem[] itemC = new JMenuItem[3];
    	itemC [0] = new JMenuItem ("Add Class");
    	itemC [1] = new JMenuItem ("Rename Class");
    	itemC [2] = new JMenuItem ("Delete Class");
    	for (int i = 0; i < itemC.length; ++i) {
    		itemC[i].addActionListener(obj);
    		classes.add(itemC[i]);
    	}
    
    	
    	//Create Fields Menu
    	JMenu fields = new JMenu("Fields");
    	
    	//Create Menu Items for fields
    	JMenuItem[] itemF = new JMenuItem[3];
    	itemF [0] = new JMenuItem ("Add Field");
    	itemF [1] = new JMenuItem ("Rename Field");
    	itemF [2] = new JMenuItem ("Delete Field");
    	for (int i = 0; i < itemF.length ; ++i) {
    		itemF[i].addActionListener(obj);
    		fields.add(itemF[i]);
    	}
    	
    	
    	//Create Methods Menu
    	JMenu methods = new JMenu("Methods");
    	
    	//Create Menu Items for methods
    	JMenuItem[] itemM = new JMenuItem[3];
    	itemM [0] = new JMenuItem ("Add Method");
    	itemM [1] = new JMenuItem ("Rename Method");
    	itemM [2] = new JMenuItem ("Delete Method");
    	for (int i = 0; i < itemM.length; ++i) {
    		itemM[i].addActionListener(obj);
    		methods.add(itemM[i]);
    	}
    	
    	
    	//Create parameters Menu
    	JMenu parameters = new JMenu("Parameters");
    	
    	//Create Menu Items for parameters
    	JMenuItem[] itemP = new JMenuItem[3];
    	itemP [0] = new JMenuItem ("Add Parameter");
    	//itemP [1] = new JMenuItem ("Add parameters");
    	itemP [1] = new JMenuItem ("Remove Parameter");
    	//itemP [3] = new JMenuItem ("Remove parameters");
    	itemP [2] = new JMenuItem ("Change Parameter");
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

		// create menu for undo/redo
		JMenu edit = new JMenu("Edit");
		JMenuItem[] itemEdit = new JMenuItem[2];
		itemEdit [0] = new JMenuItem ("Undo");
		itemEdit [1] = new JMenuItem ("Redo");
		for(int i = 0; i < itemEdit.length; i++) {
			itemEdit[i].addActionListener(obj);
			edit.add(itemEdit[i]);
		}
    	
    	
    	//Create relationships menu
    	JMenu relationship = new JMenu("Relationships");
    	
    	//Create Menu Items for relationships
    	JMenuItem[] itemR = new JMenuItem[2];
    	itemR [0] = new JMenuItem ("Add Relationship");
    	itemR [1] = new JMenuItem ("Delete Relationship");
    	for (int i = 0; i < itemR.length; ++i) {
    		itemR[i].addActionListener(obj);
    		relationship.add(itemR[i]);
    	}
    	    	
    	//Create a menu bar
    	JMenuBar mb=new JMenuBar();
    	main.setJMenuBar(mb);
    	mb.add(file);
		mb.add(edit);
    	mb.add(classes);
    	mb.add(fields);
    	mb.add(methods);
    	mb.add(parameters);
    	mb.add(relationship);
    	
    	//Display the frame
    	main.setVisible(true);
    	
    	
    }
    
    //Function to implement menu items 
    public void actionPerformed(ActionEvent e)
    {    	
    	
    	if (e.getActionCommand().equals("Add Class")) {
    		if (index == CLASSESPERROW * CLASSESPERCOL ) {
    			JOptionPane.showMessageDialog(main,"Class name not valid, try again");
    		} else {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (className == null) {
    			JOptionPane.showMessageDialog(main,"Error when creating class, try again");
    		} else if ( umld.addClass(className)) {
    			//draw class
    			addClass(className, index / CLASSESPERROW, index % CLASSESPERROW, index);
    			available [0] = CLASSESPERROW * CLASSESPERCOL;
    			index = valIndex();
        	} else {
        		JOptionPane.showMessageDialog(main,"Error when creating a class, try again");    			
        	}
    		}
    	
    	} else if (e.getActionCommand().equals("Rename Class")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
    		
    	} else if (e.getActionCommand().equals("Delete Class")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
    		
    		//handling fields menu
    	} else if (e.getActionCommand().equals("Add Field")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
    		} else if (e.getActionCommand().equals("Rename Field")) { 
    			if (umld.umlDiagram.isEmpty()) {
        			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
        		} else {
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
        		}
    		} else if (e.getActionCommand().equals("Delete Field")) { 
    			if (umld.umlDiagram.isEmpty()) {
        			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
        		} else {
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
    			}  }  	
    				   		
    	//methods menu
    	} else if (e.getActionCommand().equals("Add Method")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
    	} else if (e.getActionCommand().equals("Rename Method")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
    	} else if (e.getActionCommand().equals("Delete Method")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
			
    	//parameters menu
    	} else if (e.getActionCommand().equals("Add Parameter")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
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
    		}
		} else if (e.getActionCommand().equals("Change Parameter")) {
    			JOptionPane.showMessageDialog(main,"Error when renaming parameter, try again");
    			
    		
    		
    	} else if (e.getActionCommand().equals("Delete Parameter")) {
    		JOptionPane.showMessageDialog(main,"Error when deleting parameter, try again");
    		
    	} else if (e.getActionCommand().equals("Add Relationship")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else {
    			String[] myInputs = getRelInput ();	
    			if (myInputs[0] != null && myInputs[1] != null && myInputs[2] != null) {
    				if ( umld.addRelationship (myInputs[0], myInputs[1], myInputs[2])) {
    					int srcIndex = findIndex (myInputs[0]);
    					int destIndex = findIndex (myInputs[1]);
    					addRel (srcIndex, destIndex, myInputs[2]);
    				} else {
    					JOptionPane.showMessageDialog (main, "Input is wrong, try again");
    				}
    			} else {
    				JOptionPane.showMessageDialog (main, "Error when creating relationship, try again"); 
    			}
    		}
    		
    	} else if (e.getActionCommand().equals("Delete Relationship")) {
    		if (umld.umlDiagram.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"Diagram is empty, add a class first");
    		} else if (relationships.isEmpty()) {
    			JOptionPane.showMessageDialog(main,"No relationships are present on diagram");
    		} else {
        		ListRelationshipsWindow relationshipsList = new ListRelationshipsWindow (main, umld.relationships);
        		if (umld.deleteRelationship(relationshipsList.getSource(), relationshipsList.getDestination())) {
        			removeRel (relationshipsList.getSource(),relationshipsList.getDestination());
        		} else {
        			JOptionPane.showMessageDialog(main,"deleting relationship failed");
        		}
    		} 
    		
    	} else if (e.getActionCommand().equals("Save")) {
    		saver.saveDiagram = umld;
    		try {
				saver.saveFileGUI();
			} catch (IOException e1) {
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
    	} else if (e.getActionCommand().equals("Undo")) {
			  if(!umld.canUndo()) {
				JOptionPane.showMessageDialog(main,"There are no actions to undo.");
			  }
			  else {
				  // needs implementation
			  	  //umld.undo();
				  JOptionPane.showMessageDialog(main,"Under construction!");
			  }
		  } 
		  else if (e.getActionCommand().equals("Redo")) {
			if(!umld.canRedo()) {
			  JOptionPane.showMessageDialog(main,"There are no actions to redo.");
			}
			else {
				// needs implementation
				  //umld.redo();
				JOptionPane.showMessageDialog(main,"Under construction!");
			}
		} 
    }
    
    //helper functions 
    
   //fill a class rectangle area  given an index for the position on the screen 
    public static void addClass (String className, int x, int y, int index) {
    	int [] myComp = calculateXY (x, y);
    	classRep.add(index, new Classes(myComp[0], myComp[1], WIDTH, HEIGHT));
    	classNames[index] = className;
    	classRep.get(index).addName(className);
    	main.getLayeredPane().add(classRep.get(index), Integer.valueOf(1));
    	cm.registerComponent(umld.getClass(className), classRep.get(index));
    	umld.getClass(className).setLoc(classRep.get(index).getLocation());
    	main.validate();
    	main.repaint();
    	++ index; 
    }
    
    public static void removeClass (int classIndex) {
    	cm.deregisterComponent(classRep.get(classIndex));
    	main.getLayeredPane().remove(classRep.get(classIndex));
    	main.validate();
    	main.repaint();
    }
    
    //get the x and y position for the given indexes 
    //x = 1 y = 4 would go position [1,4] on a matrix of 6x4
    public static int[] calculateXY (int x, int y) {
    	int [] xAndY = new int [] {50 + x * 275, 50 + y * 225};
    	if (xAndY[0] >= (int)screenSize.getWidth() - WIDTH || xAndY[1] >= (int)screenSize.getHeight()- HEIGHT) {
    		return new int [] {50, 50};
    	}
    	return xAndY;
    }
    
    public static void addRel (int src, int dest, String type) {
    	relationships.add( new Arrow (classRep.get(src).getX(),
    						   classRep.get(src).getY(),
    						   classRep.get(dest).getX(),
    						   classRep.get(dest).getY(), type));
    	main.getLayeredPane().add(relationships.get(relationshipID), Integer.valueOf(0));
    	classRep.get(src).setRelID (relationshipID);
    	classRep.get(dest).setRelID(relationshipID);
    	++relationshipID;
    	main.validate();
    	main.repaint();	
    }
    
    public static void removeRel (String src, String dest) {
    	int id = classRep.get(findIndex(src)).getRelID();
    	main.getLayeredPane().remove(relationships.get(id));
    	relationships.remove(id);
    	classRep.get(findIndex(src)).setRelID(-1);
    	classRep.get(findIndex(dest)).setRelID(-1);
    	--relationshipID;
    	main.validate();
    	main.repaint();
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