package TemporaryGroupGradle;

import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListRelationshipsWindow extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	JDialog listRelationships;
	JLabel myRelationships;
	JComboBox<String> listOfRelationships;
	
	ListRelationshipsWindow (JFrame frame, ArrayList<UMLRelationship> relationships) {
		m_relationships = new String[relationships.size()];
		for (int i = 0; i < relationships.size(); ++i) {
			m_relationships[i] = "source - " + relationships.get(i).getSource() 
								 + " --> destination - " + relationships.get(i).getDestination();
		}
		listRelationships = new JDialog (frame, "", ModalityType.APPLICATION_MODAL);
		listRelationships.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listRelationships.setResizable(false);
		listRelationships.setLayout(null);
		listRelationships.setSize(450, 450);
		myRelationships = new JLabel();
		myRelationships.setBounds(50, 50, 390, 25);
		myRelationships.setText(" Select relationship");
		myRelationships.setFont(new Font ("Arial", Font.BOLD, 20));
		listOfRelationships = new JComboBox<String> (m_relationships);
		listOfRelationships.setBounds(75, 75, 200, 25);
		listOfRelationships.addActionListener(this);
		listOfRelationships.setSelectedIndex(-1);
		listRelationships.add(myRelationships);
		listRelationships.add(listOfRelationships);
		listRelationships.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == listOfRelationships) {
			selected = (String) listOfRelationships.getSelectedItem();
			//selected = listOfRelationships.getItemAt(listOfRelationships.getSelectedIndex());
			listRelationships.dispose();
		}
	}
	
	//return index of select
	public String getSource () {
		return selected.split(" ")[2];
	}
	
	public String getDestination () {
		return selected.split(" ")[6];
	}
	
	private String selected = "";
	private String [] m_relationships;
}
