package week14.p8;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RectanglesFrame2 extends JFrame{
	
	private RectanglesComponent2 component;

	RectanglesFrame2() {
		setTitle("객체를 파일에 저장하는 예");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenu menu = new JMenu("파일");
		JMenuItem menuItem = new JMenuItem("열기");
		menu.add(menuItem);
		
		class OpenMenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				File f = new File("rectangles.dat");
				if( !f.exists()) {
					return;
				}
				else {
					ObjectInputStream in = null;
					try {
						
						in = new ObjectInputStream(new FileInputStream(f));
						ArrayList<Rectangle2D.Double> rectangles =
								(ArrayList<Rectangle2D.Double>)in.readObject();
						component.setRectangles(rectangles);
						repaint();
						in.close();
					}
					catch (IOException e) {
						System.out.println("파일을 읽을 수가 없습니다.");
					}
					catch (ClassNotFoundException e) {
						System.out.println("무엇인지 알 수 없는 객체가 파일에 들어 있습니다.");
						e.printStackTrace();
					}
					
				}
			}
		}
		menuItem.addActionListener(new OpenMenuItemListener());
		
		menuItem = new JMenuItem("저장");
		menu.add(menuItem);
		
		class SaveMenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				File f = new File("rectangles.dat");
				ArrayList<Rectangle2D.Double> rectangles = 
						component.getRectangles();
				ObjectOutputStream out = null;
				try {
					out = new ObjectOutputStream(new FileOutputStream(f));
					out.writeObject(rectangles);
					out.close();
				}
				catch (IOException e) {
					System.out.println("파일에 쓸 수가 없습니다.");
					e.printStackTrace();
				}
			}
		}
		
		menuItem.addActionListener(new SaveMenuItemListener());
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);
		
		setJMenuBar(menubar);
		
		component = new RectanglesComponent2();
		add(component, BorderLayout.CENTER);
		
		pack();
	}
	
	public static void main(String[] args) {
		
		RectanglesFrame2 frame = new RectanglesFrame2();
		frame.setVisible(true);

	}

}
