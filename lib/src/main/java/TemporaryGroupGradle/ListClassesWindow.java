package TemporaryGroupGradle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListClassesWindow extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JDialog listClasses;
	JLabel myClasses;
	JComboBox<String> listOfClasses;
	
	ListClassesWindow (JFrame frame, String[] classes ) {
		listClasses = new JDialog (frame, "", ModalityType.APPLICATION_MODAL);
		m_classes = classes;
		listClasses.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listClasses.setResizable(false);
		listClasses.setLayout(null);
		listClasses.setSize(450, 450);
		myClasses = new JLabel();
		myClasses.setBounds(50, 50, 390, 25);
		myClasses.setText(" Select class");
		myClasses.setFont(new Font ("Arial", Font.BOLD, 20));
		listOfClasses = new JComboBox<String> (m_classes);
		listOfClasses.setBounds(75, 75, 200, 25);
		listOfClasses.addActionListener(this);
		listOfClasses.setSelectedIndex(-1);
		listClasses.add(myClasses);
		listClasses.add(listOfClasses);
		listClasses.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
			myClassI = listOfClasses.getSelectedIndex();
			listClasses.dispose();
		
			
	}
	
	//return index of select
	public int getMyClassI () {
		return myClassI;
	}
	
	
	private String[] m_classes;
	private int myClassI;
	
}
