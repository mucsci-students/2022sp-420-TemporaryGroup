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
	static Graphics update;
	static final int WIDTH = 150;
	static final int HEIGHT = 125;
	//can have up to 6 classes across by up to 4 classes down
	static Rectangle[] classRep = new Rectangle [25];
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
    	//Create a Menu
    	JMenu classes = new JMenu("Classes");
		
    	//Create Menu Items
    	JMenuItem item[] = new JMenuItem[5];
    	item [0] = new JMenuItem ("Add");
    	item [1] = new JMenuItem ("Rename");
    	item [2] = new JMenuItem ("Delete");
    	item [3] = new JMenuItem ("List class");
    	item [4] = new JMenuItem ("List all");
    	for(int i=0;i<5;i++)
    	{
    		item[i].addActionListener(obj);
    		classes.add(item[i]);
    	}
    
    	//Create a menu bar
    	JMenuBar mb=new JMenuBar();
    	mb.add(classes);
    	main.setJMenuBar(mb);
	
    	//Create the label
    	text = new JLabel();
    	main.add(text);
	
    	//Display the frame
    	main.setVisible(true);
    }
    
    //Function to display the menu item selected
    public void actionPerformed(ActionEvent e)
    {    	
    	
    	if (e.getActionCommand().equals("Add")) {
    		
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		if (!umld.classExists(className)) {
    		umld.addClass(className);
    		//draw class
    		
    		fillClassRep(className, index / 4, index % 4, index);
    		++index;
        	} else {
    			text.setText("class already exists, try again");
    		}
    
    	} else if (e.getActionCommand().equals("Rename")) {
    		String oldName = JOptionPane.showInputDialog (main, "Enter class to rename");
    		String newName = JOptionPane.showInputDialog (main, "Enter new class name");
    		umld.renameClass(oldName, newName);
    		
    		update.drawRect(30, 75, 150, 150);
    		update.drawString(newName, 50, 75);
    	} else if (e.getActionCommand().equals("Delete")) {
    		String className = JOptionPane.showInputDialog (main, "Enter class to delete");
    		umld.removeClass(className);
    		
    		update.clearRect(25, 70, 160, 160);
    	} else if (e.getActionCommand().equals("List class")) {
    		String className = JOptionPane.showInputDialog(main, "Enter class name");
    		JOptionPane.showMessageDialog(main, listClass(className));
    	} else if (e.getActionCommand().equals("List all")) {
    		JOptionPane.showMessageDialog(main, listOfClasses());
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
				listClass(mapElem.getKey()); 
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
    	classRep [index] = new Rectangle (myComp[0], myComp[1], WIDTH, HEIGHT);
    	Graphics classAdded = main.getGraphics();
		classAdded.drawRect(classRep[index].x, classRep[index].y, WIDTH, HEIGHT);
		classAdded.drawString(className, myComp[0] + 20, myComp[1]);
		++ index;
    }
    
    public static int[] calculateXY (int x, int y) {
    	int startX = 50;
    	int startY = 75;
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
}