package Mole.Game;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DB.DBConnection;

class LeaderBoardFrame extends JPanel { // 리더보드 버튼을 클릭했을 때 나오는 프레임

	//private LeaderBG leaderBoardBG = new LeaderBG();
	private JButton back;
	private JLabel label1;
	private JPanel board;
	private JScrollPane scroll;
	private JTextArea boardcontent;
	private static int num = 1; // 올라감
	private BufferedImage backs;
	private BufferedImage crown;
	
	ImageIcon back_img = new ImageIcon("img/back.png");
	ImageIcon back1_img = new ImageIcon("img/back1.png");

	LeaderBoardFrame(JPanel mainBG) {
		try {
			backs = ImageIO.read(new File("img/threemoles.png"));
			crown = ImageIO.read(new File("img/crown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CustomCursor();

		setLayout(null);

		back = new JButton(back_img);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(720, 0, 50, 50);
		back.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				back.setIcon(back1_img);
			}

			public void mouseExited(MouseEvent e) {
				back.setIcon(back_img);
			}

			public void mousePressed(MouseEvent e) {
				setVisible(false);
				mainBG.setVisible(true);
			}
		});
		add(back);
		
		board = new JPanel();
		board.setLayout(null);
		board.setBounds(50, 100, 700, 450);
		boardcontent = new JTextArea(10,30);
		boardcontent.setEditable(false);
		scroll = new JScrollPane(boardcontent);
		scroll.setBounds(50, 100, 700, 450);
		
		Font wr = new Font("HY견고딕", Font.PLAIN, 13);
		
		boardcontent.setFont(wr);
		
		boardcontent.append(" No \t Name \t PlayCount \t HumanWin \t MoleWin \t Rate \t Scores \n\n");
		
		try {
			Connection con = DBConnection.makeConnection(); // DB연결
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM gamer  ORDER BY SCORES DESC");
			while(rs.next()) {
				String contents =  String.format(" %3d \t %s \t %3d \t %3d \t %3d \t %.1f \t %4d \n", 
						num++, rs.getString("ID"),rs.getInt("PLAYCOUNT"),rs.getInt("HUMANWIN"),
						rs.getInt("MOLEWIN"),((rs.getDouble("HUMANWIN")+rs.getInt("MOLEWIN"))/rs.getInt("PLAYCOUNT"))*100,rs.getInt("SCORES"));
				boardcontent.append(contents + "\n"); 
			}
			num = 1;
			rs.close();
			st.close();
			con.close();
		} catch (Exception a) {
			System.out.println("데이터베이스 연결 오류 : " + a.getMessage());
		}
		
		add(scroll);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backs, 0, 0, null);
		g.drawImage(crown, 10, 15, null);
	}

	public void CustomCursor() { // 커스텀 커서(마우스 커서)

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("img/cropcursor.png");
		Point point = new Point(20, 20);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "crop");
		setCursor(cursor);
	}
}