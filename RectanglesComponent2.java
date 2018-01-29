package week14.p8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * 마우스 클릭에 의해 사각형 그림이 그려지는 패널.
 * 사각형들을 리스트에 넣어 관리함.
 */

public class RectanglesComponent2 extends JComponent {
	
	ArrayList<Rectangle2D.Double> rectangles;
	
	public static double LENGTH = 20;
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public RectanglesComponent2() {
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		rectangles = new ArrayList<Rectangle2D.Double>();
		
		class ClickListener implements MouseListener {
			
			public void mousePressed(MouseEvent event) {
				
				double x = (double)event.getX();
				double y = (double)event.getY();
				
				boolean removed = false;
				for (int i = 0; i < rectangles.size(); i++) {
					if(rectangles.get(i).contains(x, y)) {
						rectangles.remove(i);
						removed = true;
						i--;
					}
				}
				
				if ( !removed) {
					Rectangle2D.Double r = new Rectangle2D.Double(x, y, LENGTH, LENGTH);
					rectangles.add(r);
				}
				repaint();
			}
			
			public void mouseReleased(MouseEvent event) {}
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		}
		
		addMouseListener(new ClickListener());
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for ( Rectangle2D.Double r : rectangles) {
			g2.draw(r);
		}
	}
	
	public ArrayList<Rectangle2D.Double> getRectangles() {
		return rectangles;
	}
	
	public void setRectangles(ArrayList<Rectangle2D.Double> list) {
		rectangles = list;
	}
}
