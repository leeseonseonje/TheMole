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

class LeaderBoardFrame extends JFrame { // �������� ��ư�� Ŭ������ �� ������ ������

	private LeaderBG leaderBoardBG = new LeaderBG();
	private JButton back;
	private JLabel label1;
	private JPanel board;
	private JScrollPane scroll;
	private JTextArea boardcontent;
	private static int num = 1; // �ö�

	ImageIcon back_img = new ImageIcon("img/back.png");
	ImageIcon back1_img = new ImageIcon("img/back1.png");

	LeaderBoardFrame() {
		CustomCursor();
		setTitle("��������"); // Ÿ��Ʋ
		setSize(800, 600); // �������� ũ��
		setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null); // â�� ��� ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ� ��.

		leaderBoardBG.setLayout(null);

		// ������ �̹��� ����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("img/moleicon.png");
		setIconImage(icon);

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
				System.out.println("�������� �ݱ�");
				setVisible(false);
				new MainFrame();
			}
		});
		leaderBoardBG.add(back);
		
		board = new JPanel();
		board.setLayout(null);
		board.setBounds(50, 100, 700, 450);
		boardcontent = new JTextArea(10,30);
		boardcontent.setEditable(false);
		scroll = new JScrollPane(boardcontent);
		scroll.setBounds(50, 100, 700, 450);
		
		boardcontent.append(" No \t Name \t PlayCount \t HumanWin \t MoleWin \t Rate \t Scores \n\n");
		Font wr = new Font("HY견고딕", Font.PLAIN, 13); 
		boardcontent.setFont(wr);
		try {
			Connection con = DBConnection.makeConnection(); // DB����
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM gamer ORDER BY scores DESC");
			while(rs.next()) {
				String contents =  String.format(" %3d \t %s \t %3d \t %3d \t %3d \t %.1f \t %4d\n", 
						num++, rs.getString("ID"),rs.getInt("PLAYCOUNT"),rs.getInt("HUMANWIN"),
						rs.getInt("MOLEWIN"),((rs.getDouble("HUMANWIN")+rs.getInt("MOLEWIN"))/rs.getInt("PLAYCOUNT"))*100,rs.getInt("SCORES"));
				boardcontent.append(contents + "\n"); 
			}
			num = 1;
			rs.close();
			st.close();
			con.close();
		} catch (Exception a) {
			System.out.println("�����ͺ��̽� ���� ���� : " + a.getMessage());
		}
		
		leaderBoardBG.add(scroll);
		
		add(leaderBoardBG);
		setVisible(true);
	}

	public void CustomCursor() { // Ŀ���� Ŀ��(���콺 Ŀ��)

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("img/cropcursor.png");
		Point point = new Point(20, 20);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "crop");
		leaderBoardBG.setCursor(cursor);
	}

	class LeaderBG extends JPanel {
		private BufferedImage backs;
		private BufferedImage crown;
		public LeaderBG() {
			try {
				backs = ImageIO.read(new File("img/threemoles.png"));
				crown = ImageIO.read(new File("img/crown.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backs, 0, 0, null);
			g.drawImage(crown, 10, 15, null);
		}
	}
}