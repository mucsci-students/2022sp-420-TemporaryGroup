/*Java Program to Create a Menu and Display the Menu Item Selected*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.color.*;


class GUIView implements ActionListener
{
    static JLabel text;
	static JFrame main = new JFrame("UMLEditor");
	static UMLDiagram umld = new UMLDiagram();
	static Graphics update;
	//static Graphics2D g2 = new Graphics2D(Graphics g);

    //Driver function
    public static void main(String args[])
    {
    	//Create a frame
    	main.setSize(500,500);
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
    		Graphics classAdded = main.getGraphics();
    		classAdded.drawRect(30, 75, 150, 150);
    		classAdded.drawString(className, 50, 75);
    		update = classAdded.create();
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
				classes.add(" Class:		" + mapElem.getKey()); 
				//need to add fields and methods 
			}
			for (int i = 0; i < classes.size(); ++i ) {
				toReturn += classes.get(i) + '\n';
			}
			return toReturn;
		}
    	
    	
    	
    }
}