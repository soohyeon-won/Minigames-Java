package week13.p10;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 메모리 게임 프로그램
 * @author 원수현
 */
public class MemoryGame3 extends JPanel {

	static final int ROWS = 4;
	static final int COLUMNS = 4;
	static final int IMAGES = ROWS * COLUMNS;

	ImageIcon[] icon = new ImageIcon[IMAGES]; //이미지 아이콘
	int[][] grid = new int[ROWS][COLUMNS];	//그리드

	List<Integer> iconNumbers = new ArrayList<Integer>();

	private JButton[][] buttons = new JButton[ROWS][COLUMNS];

	boolean secondOpen = false;

	JButton firstOpenButton = null;
	JButton secondOpenButton = null;

	int firstOpenNumber = 0;

	int tries = 0;
	int pairs = ROWS * COLUMNS / 2;

	int pairsToFind = pairs;

	boolean previousCorrect = true;


	ActionListener listener;

	public MemoryGame3() {

		super(new GridLayout(ROWS, COLUMNS));

		getImages();

		int height = icon[0].getIconHeight();
		int width = icon[0].getIconWidth();

		for(int i = 0; i < pairs; i++) {
			iconNumbers.add(i);
			iconNumbers.add(i);
		}

		shuffle();

		listener = new ClickListener();

		for(int i = 0; i < ROWS; i++) { 
			for(int j = 0; j < COLUMNS; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(height, width));
				add(buttons[i][j]);
				buttons[i][j].addActionListener(listener);
			}

		}
	}

	/**
	 * 배열의 순서를 뒤섞는다.
	 * 뒤섞은 다음에는 2차원 배열에 순서대로 저장하는 메소드.
	 */
	private void shuffle() {
		java.util.Collections.shuffle(iconNumbers);

		int k = 0;
		for (int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				grid[i][j] = iconNumbers.get(k++);
			}
		}
	}
	
	class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			tries++;
			
			int bi = 0, bj = 0;
			for ( int i = 0; i < ROWS; i++) {
				for(int j = 0; j < COLUMNS; j++) {
					if(event.getSource() == buttons[i][j]) {
						bi = i;
						bj = j;
					}
				}
			}
			
			buttons[bi][bj].removeActionListener(listener);
			buttons[bi][bj].setIcon(icon[grid[bi][bj]]);
			
			if(secondOpen) {
				secondOpenButton = buttons[bi][bj];
				if(firstOpenNumber == grid[bi][bj]) {
					pairsToFind--;
					if(pairsToFind == 0) {
						JOptionPane.showMessageDialog(null, tries + "회 만에 맞추셨습니다.");
						System.exit(0);
					}
					previousCorrect = true;
				}
				else {
					previousCorrect = false;
				}
				secondOpen = false;
			}
			else {
				if(!previousCorrect) {
					firstOpenButton.setIcon(null);
					secondOpenButton.setIcon(null);
					firstOpenButton.addActionListener(listener);
					secondOpenButton.addActionListener(listener);
				}
				firstOpenButton = buttons[bi][bj];
				firstOpenNumber = grid[bi][bj];
				secondOpen = true;
			}
		}
	}
	
	/**
	 * icon배열에 이미지 파일을 넣는 메소드.
	 */
	private void getImages(){
		// 16개의 이미지가 있는 경우 32개의 버튼까지 가능
		icon[0] = createImageIcon("/images/dog-icon.png", "cute dog image");
		icon[1] = createImageIcon("/images/cat-icon.png", "pretty cat image");
		icon[2] = createImageIcon("/images/angry-bird-icon.png", "cute dog image");
		icon[3] = createImageIcon("/images/bird-icon.png", "pretty cat image");
		icon[4] = createImageIcon("/images/clown-fish-icon.png", "cute dog image");
		icon[5] = createImageIcon("/images/cow-icon.png", "pretty cat image");
		icon[6] = createImageIcon("/images/eagles-icon.png", "cute dog image");
		icon[7] = createImageIcon("/images/elephant-icon.png", "pretty cat image");
		icon[8] = createImageIcon("/images/fish-icon.png", "cute dog image");
		icon[9] = createImageIcon("/images/jelly-fish-icon.png", "pretty cat image");
		icon[10] = createImageIcon("/images/lion-icon.png", "cute dog image");
		icon[11] = createImageIcon("/images/owl-icon.png", "pretty cat image");
		icon[12] = createImageIcon("/images/red-fish-icon.png", "cute dog image");
		icon[13] = createImageIcon("/images/snake-icon.png", "pretty cat image");
		icon[14] = createImageIcon("/images/tiger-icon.png", "cute dog image");
		icon[15] = createImageIcon("/images/bee-icon.png", "pretty cat image");
	}

	/** ImageIcon을 반환,
	 * 단, path가 유효하지 않은 경우에는 null을 반환.
	 * @param path 그림 파일 경로명 (가령, "/images/smile.jpg").
	 * @param description 그림에 대한 설명.
	 * @return 그림파일로부터 만들어진 ImageIcon (path가 유효한 경우),
	 *         null (path가 유효하지 않은 경우).
	 */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = MemoryGame3.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("MemoryGame3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MemoryGame3());
		
		frame.pack();
		frame.setVisible(true);
	}
}
