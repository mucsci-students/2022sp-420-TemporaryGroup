import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListMethodsWindow extends JDialog implements ActionListener {
	
	JDialog listMethods;
	JLabel myMethods;
	JComboBox listOfMethods;
	
	ListMethodsWindow (JFrame panel, ArrayList<Method> methods) {
		listMethods = new JDialog (panel, "", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		m_methods = new String [methods.size()];
		for (int i = 0; i < methods.size(); ++i) {
			ArrayList<Parameter> parameters = methods.get(i).getParameterList();
			if (parameters.size() == 0) { 
			m_methods[i] = methods.get(i).getMethodType() + " " + methods.get(i).getMethodName() + " ()";
			} else {
				m_methods[i] = methods.get(i).getMethodType() + " " + methods.get(i).getMethodName() + "( ";
				int count = 0;
				while (count < parameters.size()) {
					m_methods[i] += parameters.get(count).getParamType()
								 + " " + parameters.get(count).getParamName();
				}
				m_methods[i] += " )";
			}
		}
		listMethods.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listMethods.setResizable(false);
		listMethods.setLayout(null);
		listMethods.setSize(450, 450);
		myMethods = new JLabel();
		myMethods.setBounds(50, 50, 390, 25);
		myMethods.setText(" Select field");
		myMethods.setFont(new Font ("Arial", Font.BOLD, 20));
		listOfMethods = new JComboBox<Object> (m_methods);
		listOfMethods.setBounds(75, 75, 200, 25);
		listOfMethods.addActionListener(this);
		listMethods.add(myMethods);
		listMethods.add(listOfMethods);
		listMethods.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		myMethodsI = -1;
		if (e.getSource() == listOfMethods) {
			myMethodsI = listOfMethods.getSelectedIndex();
			listMethods.dispose();
		}
			
	}
	
	//return index of select
	public int getIndex () {
		return myMethodsI;
	}
	
	
	private String[] m_methods;
	private int myMethodsI;
}
