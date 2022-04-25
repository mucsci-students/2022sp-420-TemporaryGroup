package TemporaryGroupGradle;

import java.awt.*;
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
			if (x2 < x1) {
				this.setBounds(x2, y2, Math.abs(x2 - x1) + 5, Math.abs(y2 - y1) + 25);
			} else {
				this.setBounds(x1, y1, x2 - x1 + 5, y2 - y1 + 25);
			}
			this.setOpaque(false);
	}
		
	@Override
	public void paintComponent (Graphics g) { 
		super.paintComponent(g);
		g.setColor(Color.RED);
		int xEP = Math.abs(x2 - x1);
		int yEP = Math.abs(y2 - y1);
		//draw line with filled diamond at the end 
		if (m_type.equals ("realization")) {
			//draw dashed line
			drawDashedLine (g,0,0,xEP, yEP);
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
		//int array to be returned
		//contains x coordinates (0-3) indexes 
		//followed by y coordinates (4-7) indexes
		int [] vertices = new int [] {x2, x2 - 25, x2 - 50, x2 - 25, 
									  y2, y2 - 20, y2, y2 + 20};		
		return vertices;
	}
	
	public int[] produceTriangleVertices (int x2, int y2) {
		int [] vertices = new int [] {x2, x2 - 25, x2 - 25, 
				  					  y2, y2 - 20, y2 + 20};		
		return vertices;
	}
	
	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){
		  // Create a copy of the Graphics instance
		  Graphics2D g2d = (Graphics2D) g.create();
		  // Set the stroke of the copy, not the original 
		  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
		                                  0, new float[]{9}, 0);
		  g2d.setStroke(dashed);
		  // Draw to the copy
		  g2d.drawLine(x1, y1, x2, y2);
		  // Get rid of the copy
		  g2d.dispose();
	}
}