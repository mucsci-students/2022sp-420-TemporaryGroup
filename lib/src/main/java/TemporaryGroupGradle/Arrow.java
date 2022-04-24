package TemporaryGroupGradle;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;


public class Arrow extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
    private String m_type;
	
	public Arrow (int xOne, int yOne, int xTwo, int yTwo, String type) {  
			x1 = xOne;
			y1 = yOne;
			x2 = xTwo;
			y2 = yTwo;
			m_type = type;
			this.setBounds(x1, y1, x2 - x1 + 5, y2 - y1 + 25);
			this.setOpaque(true);
	
	}
		
	@Override
	public void paintComponent (Graphics g) { 
		super.paintComponent(g);
		int xEP = x2 - x1;
		int yEP = y2 - y1;
		//draw line with filled diamond at the end 
		if (m_type.equals ("realization")) {
			//draw dashed line
			g.drawLine(0, 0, xEP, yEP);
			//draw empty triangle 
			int [] v = produceTriangleVertices (xEP, yEP);
			int [] xPoints = new int [] {v[0], v[1], v[2]};
			int [] yPoints = new int [] {v[3], v[4], v[5]};
			g.drawPolygon(xPoints, yPoints, 3);
			
		} else if (m_type.equals("inheritance")) {
			//draw solid line
			g.drawLine(0, 0, xEP, yEP);
			//draw solid arrow
			int [] v = produceTriangleVertices (xEP, yEP);
			int [] xPoints = new int [] {v[0], v[1], v[2]};
			int [] yPoints = new int [] {v[3], v[4], v[5]};
			g.fillPolygon(xPoints, yPoints, 3);
		} else if (m_type.equals("composition")) {
			//draw solid line
			g.drawLine(0, 0, xEP, yEP);
			//draw filled diamond
			int [] v = produceDiamondVertices (xEP, yEP);
			int [] xPoints = new int [] {v[0], v[1], v[2], v[3]};
			int [] yPoints = new int [] {v[4], v[5], v[6], v[7]};
			g.fillPolygon(xPoints, yPoints, 4);
		} else if (m_type.equals("aggregation")) {
			//draw solid line
			g.drawLine(0, 0, xEP, yEP);
			//draw empty diamond
			int [] v = produceDiamondVertices (xEP, yEP);
			int [] xPoints = new int [] {v[0], v[1], v[2], v[3]};
			int [] yPoints = new int [] {v[4], v[5], v[6], v[7]};
			g.drawPolygon(xPoints, yPoints, 4);
		}
		
	}
	
	public int[] produceDiamondVertices (int x2, int y2) {
		/*
		//getting the equation of line between given points
		double slope = (y2 - y1) / (x2 - x1);
		double yOffset = y1 - (slope * x1);
		//calculating the opposite vertices on diagonal
		int thirdX = x2 - 50; 
		int thirdY = (int)((slope * thirdX) + yOffset);
		//calculating vertices on second diagonal
		int secondX = x2 - 25; 
		int fourthX = secondX;
		int secondY = y2 - 30;
		int fourthY = y2 + 30;
		//int array to be returned
		//contains x coordinates (0-3) indexes 
		//followed by y coordinates (4-7) indexes */
		int [] vertices = new int [] {x2, x2 - 25, x2 - 50, x2 - 25, 
									  y2, y2 - 20, y2, y2 + 20};		
		return vertices;
	}
	
	public int[] produceTriangleVertices (int x2, int y2) {
		int [] vertices = new int [] {x2, x2 - 25, x2 - 25, 
				  					  y2, y2 - 20, y2 + 20};		
		return vertices;
	}
}