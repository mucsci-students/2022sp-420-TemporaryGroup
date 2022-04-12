package TemporaryGroupGradle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

//test
public class Classes extends JPanel {
	
	JLabel name = new JLabel();
	JComboBox<String> fieldsDrop = new JComboBox<String>();
	JComboBox<String> methodsDrop = new JComboBox<String>();
	
	//creating class with given position
	Classes (int x, int y, int width, int height) {
		corner = new Point (x, y);
		//m_width = width;
		m_width = width;
		m_height = height;
		//ClickListener clickListener = new ClickListener ();
		//DragListener dragListener = new DragListener();
		this.setBackground(Color.white);
		this.setBounds(x, y, m_width, m_height);
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.setLayout(null);
	}
	
	public void addName (String className) {
		JLabel fields = new JLabel();
		JLabel methods = new JLabel();
		name.setBounds(0, 0, m_width - 10, 20);
		name.setFont(new Font ("Arial", Font.BOLD, 20));
		fields.setBounds(20, 30, m_width - 30, 15);
		fields.setFont(new Font ("Arial", Font.ITALIC, 15));
		methods.setBounds(20, 90, m_width - 30, 15);
		methods.setFont(new Font ("Arial", Font.ITALIC, 15));
		name.setText(className);
		fields.setText("Fields");
		methods.setText("Methods");
		fieldsDrop.setBounds(50, 60, m_width - 60, 20);
		methodsDrop.setBounds(50, 120, m_width - 60, 20);
		this.add(name);
		this.add(fields);
		this.add(fieldsDrop);
		this.add(methods);
		this.add(methodsDrop);

	}
	
	public void renameClass (String newName) {
		name.setBounds(0, 0, m_width - 10, 20);
		name.setFont(new Font ("Arial", Font.BOLD, 20));
		name.setText(newName);
		this.add(name);
	}
	
	public void addField(String type, String name) {
		String typeAndName = type + " " + name + " ()";
		fieldsDrop.addItem(typeAndName);
	
	}
	
	public void renameField (String newName, String name,  int localIndex) {
		String myOldName = fieldsDrop.getItemAt(localIndex);
		myOldName = myOldName.replaceAll(name, newName);
		fieldsDrop.removeItemAt(localIndex);
		fieldsDrop.addItem(myOldName);
		
	}
	
	public void deleteField (int fieldIndex) {
		fieldsDrop.removeItemAt(fieldIndex);
	}
	
	public void addMethod (String type, String name) {
		String typeAndName = type + " " + name + " ()";
		methodsDrop.addItem(typeAndName);
	}
	
	public void renameMethod (String newName, String name, int localIndex) {
		String myOldName = methodsDrop.getItemAt(localIndex);
		myOldName = myOldName.replaceAll(name, newName);
		methodsDrop.removeItemAt(localIndex);
		methodsDrop.addItem(myOldName);
	}
	
	public void deleteMethod (int methodIndex) {
		methodsDrop.removeItemAt(methodIndex);
	}
	
	public void addParameter (String parameterName, String parameterType, int indexOfMethod ) {
		String toAdd = methodsDrop.getItemAt(indexOfMethod);
		String insideMethod = parameterType + " " + parameterName + " )";
		toAdd = toAdd.replace( ")", insideMethod);
		methodsDrop.removeItemAt(indexOfMethod);
		methodsDrop.addItem(toAdd);
	}
	
	public void changeParameterType (String parameterName, String oldType, String newType, int methodIndex) {
		String toAdd = methodsDrop.getItemAt(methodIndex);
		String toReplace = oldType + " " + parameterName;
		String replaceWith = new String (newType + " " + parameterName);
		toAdd = toAdd.replace(toReplace, replaceWith);
		methodsDrop.removeItemAt(methodIndex);
		methodsDrop.addItem(toAdd);
	}
	
	public void drawRelArrow () {
		
	}
	
	//need to implement
	public void changeParameterName (String oldName, String newName, String type, int methodIndex) {
		
	}
	
	public void deleteParamater () {
		
	}
	
	public Point getCorner () {
		return corner;
	}
	
	public int getWidth () {
		return m_width;
	}
	
	public int getHeight () {
		return m_height;
	}
	
	public void setWidth (int width) {
		m_width = width;
	}
	
	public void setHeight (int height) {
		m_height = height;
	}
	
	private Point corner;
	//private Point prevPt;
	private int m_width;
	private int m_height;

	/*
	public class ClickListener extends MouseAdapter {
	 	public void mousePressed (MouseEvent e) {
			prevPt = e.getPoint();
		}
	}
	
	public class DragListener extends MouseMotionAdapter {
		public void mouseDragged (MouseEvent e) {
			Point currPt = e.getPoint();
			corner.translate(
						(int)(currPt.getX() - prevPt.getX()),
						(int)(currPt.getY() - prevPt.getY()));
			prevPt = currPt;
			
		}
	} */
	
}
