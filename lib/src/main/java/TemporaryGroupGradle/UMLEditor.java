package TemporaryGroupGradle;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UMLEditor extends JFrame
{
  private JButton button1, button2;
  private JLabel label;
  
  public static void main(String[] args) {
    new UMLEditor();
  }
  public UMLEditor()
  {
    this.setSize(300,300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("UMLEditor");
    this.setLayout(null);
    this.setLocationRelativeTo(null);
    ImageIcon img = new ImageIcon("src/main/java/TemporaryGroupGradle/Background.png");
    Clicklistener click = new Clicklistener();
    ImageIcon icon = new ImageIcon("src/main/java/TemporaryGroupGradle/UMLIcon.png");
    this.setIconImage(icon.getImage());
    button1 = new JButton ("cli");
    button1.setBounds(40,100,100,40);
    button1.addActionListener(click);
    button2 = new JButton ("gui");
    button2.setBounds(150,100,100,40);
    button2.addActionListener(click);
    JLabel text = new JLabel("Welcome to the UML Editor!");
    text.setBounds(60,50,175,30);
    label = new JLabel("",img,JLabel.CENTER);
    label.setBounds(0,0,300,300);
    
    this.add(text);
    this.add(button1);
    this.add(button2);
    this.add(label);
    this.setVisible(true);
  }
    
  private class Clicklistener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == button1)
      {
        String[] args = {};
        try {
			UMLCli.main(args);
        				
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
      }
      
      if (e.getSource() == button2)
      {
    	  String[] args = {};
          try {
  			GUIView.main(args);
  		} catch (Exception e1) {
  			
  			e1.printStackTrace();
  		}
      }
    }
  }
}
