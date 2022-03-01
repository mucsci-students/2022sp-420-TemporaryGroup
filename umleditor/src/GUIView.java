/*Java Program to Create a Menu and Display the Menu Item Selected*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
//import java.awt.color.*;


class GUIView implements ActionListener
{
    static JLabel text;
	static JFrame main = new JFrame("UMLEditor");
	static UMLDiagram umld = new UMLDiagram();
	static final int WIDTH = 150;
	static final int HEIGHT = 125;
	//can have up to 6 classes across by up to 4 classes down
	static ArrayList<Rectangle> classRep = new ArrayList<Rectangle> ();
	static String[] classNames = new String [25];
	static int index = 0;
	//static Graphics2D g2 = new Graphics2D(Graphics g);

    //Driver function
    public static void main(String args[])
    {
    	//Create a frame
    	main.setSize(1920,1080);
    	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main.setLayout(new FlowLayout());
    	
    	//Create an object
    	GUIView obj = new GUIView();
    	
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
    	JMenuItem[] itemF = new JMenuItem[5];
    	itemF [0] = new JMenuItem ("Add field");
    	itemF [1] = new JMenuItem ("Rename field");
    	itemF [2] = new JMenuItem ("Delete field");
    	itemF [3] = new JMenuItem ("List field");
    	itemF [4] = new JMenuItem ("List all fields");
    	for (int i = 0; i < itemF.length ; ++i) {
    		itemF[i].addActionListener(obj);
    		fields.add(itemF[i]);
    	}
    	
    	
    	//Create Methods Menu
    	JMenu methods = new JMenu("Methods");
    	
    	//Create Menu Items for methods
    	JMenuItem[] itemM = new JMenuItem[5];
    	itemM [0] = new JMenuItem ("Add method");
    	itemM [1] = new JMenuItem ("Rename method");
    	itemM [2] = new JMenuItem ("Delete method");
    	itemM [3] = new JMenuItem ("List method");
    	itemM [4] = new JMenuItem ("List all methods");
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
    	itemE [1] = new JMenuItem ("Run on cli mode");
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
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (!umld.classExists(className)) {
    		umld.addClass(className);
    		//draw class
    		fillClassRep(className, index / 4, index % 4, index);
    		++index;
    		updateFirstRow();
        	} else {
    			text.setText("class already exists, try again");
    		}
    
    	} else if (e.getActionCommand().equals("Rename class")) {
    		String oldName = JOptionPane.showInputDialog (main, "Enter class to rename");
    		int localIndex = findIndex(oldName);
    		if (localIndex != -1 ) {
    			String newName = JOptionPane.showInputDialog (main, "Enter new class name");
    			umld.renameClass(oldName, newName);
    			classNames[localIndex] = newName;
    			Graphics classAdded = main.getGraphics();
    			classAdded.clearRect(classRep.get(localIndex).x, classRep.get(localIndex).y - 20, 125, 19);
    			classAdded.drawString (classNames[localIndex], classRep.get(localIndex).x + 20 ,classRep.get(localIndex).y - 5);
    		} else {
    			JOptionPane.showMessageDialog(main, "Class not found");
    		}
    		updateFirstRow();
    		//need to finish
    	} else if (e.getActionCommand().equals("Delete class")) {
    		String className = JOptionPane.showInputDialog (main, "Enter class to delete");
    		umld.removeClass(className);
    		
    		
    	} else if (e.getActionCommand().equals("List class")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		JOptionPane.showMessageDialog(main, listClass(className));
    		updateFirstRow();
    	} else if (e.getActionCommand().equals("List all classes")) {
    		JOptionPane.showMessageDialog(main, listOfClasses());
    		updateFirstRow();
    	} else {
    		text.setText("Still working on it");
    	}
    	
    }
    
    //helper functions 
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
    
    public static String listClass (String className) {
    	if (umld.umlDiagram.isEmpty()) {
    		return "Diagram is empty";
    	} else  {
    		Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
			String toReturn = "";
			ArrayList<String> classToList = new ArrayList<String> ();
				classToList.add(" Class:		" + className); 
				//need to add fields and methods 
				for (int i = 0; i < classToList.size(); ++i ) {
				toReturn += classToList.get(i) + '\n';
			}
			return toReturn;
    	}
    }
    
    public static void fillClassRep (String className, int x, int y, int index) {
    	int [] myComp = calculateXY (x, y);
    	classRep.add(index, new Rectangle (myComp[0], myComp[1], WIDTH, HEIGHT));    	
    	classNames[index] = className;
    	Graphics classAdded = main.getGraphics();
		classAdded.drawRect(classRep.get(index).x, classRep.get(index).y, WIDTH, HEIGHT);
		classAdded.drawString(className, myComp[0] + 20, myComp[1] - 5);
		++ index;
    }
    
    public static int[] calculateXY (int x, int y) {
    	int startX = 50;
    	int startY = 100;
    	return new int[] {startX + x * 180, startY + y * 150};
    }
    
    public static int getNumberOfClasses () {
    	int numberOfClasses = 0;
		Iterator<HashMap.Entry<String, UMLClass>> hmIter = umld.umlDiagram.entrySet().iterator();
    	while (hmIter.hasNext()) {
			++numberOfClasses; 
		}
    	return numberOfClasses;
    }
    
    public static void updateFirstRow () {
    	for (int i = 0; i < classRep.size(); ++i)
    	{
    		if (i % 4 == 0) {
    			Graphics classAdded = main.getGraphics();
    			classAdded.drawRect(classRep.get(i).x, classRep.get(i).y, WIDTH, HEIGHT);
    			classAdded.drawString (classNames[i], classRep.get(i).x + 20 ,classRep.get(i).y - 5);
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
}