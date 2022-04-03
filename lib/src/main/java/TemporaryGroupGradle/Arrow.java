package TemporaryGroupGradle;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;


public class Arrow extends JComponent {

	private static final long serialVersionUID = 1L;
	private
		double x1;
		double y1;
		double x2;
		double y2;
        final int ARR_SIZE = 4;

	
	public Arrow (double xOne, double yOne, double xTwo, double yTwo) {  
			x1 = xOne;
			x2 = xTwo;
			y1 = yOne;
			y2 = yTwo;
		}
		
	public void paintComponent (Graphics g) { 
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g.create();
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx*dx + dy*dy);
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g2D.transform(at);
		g2D.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
        new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
	}
}