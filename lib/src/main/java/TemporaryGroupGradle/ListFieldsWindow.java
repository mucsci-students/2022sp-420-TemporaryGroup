package TemporaryGroupGradle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListFieldsWindow extends JDialog implements ActionListener {
	
	JDialog listFields;
	JLabel myFields;
	JComboBox listOfFields;
	
	ListFieldsWindow (JFrame panel, ArrayList<Field> fields) {
		listFields = new JDialog (panel, "", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		m_fields = new String [fields.size()];
		for (int i = 0; i < fields.size(); ++i) {
			m_fields[i] = fields.get(i).getFieldType() + " " + fields.get(i).getFieldName() + " ()";
		}
		listFields.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listFields.setResizable(false);
		listFields.setLayout(null);
		listFields.setSize(450, 450);
		myFields = new JLabel();
		myFields.setBounds(50, 50, 390, 25);
		myFields.setText(" Select field");
		myFields.setFont(new Font ("Arial", Font.BOLD, 20));
		listOfFields = new JComboBox<Object> (m_fields);
		listOfFields.setBounds(75, 75, 200, 25);
		listOfFields.addActionListener(this);
		listFields.add(myFields);
		listFields.add(listOfFields);
		listFields.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		myFieldsI = -1;
		if (e.getSource() == listOfFields) {
			myFieldsI = listOfFields.getSelectedIndex();
			listFields.dispose();
		}
			
	}
	
	//return index of select
	public int getIndex () {
		return myFieldsI;
	}
	
	
	private String[] m_fields;
	private int myFieldsI;
}
