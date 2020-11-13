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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import DB.DBConnection;

class MainFrame extends JFrame {

	private MainFrame frame;
	private Font font;
	private JButton start, end, query, rank, angry, player, title;
	private BackG mainBG = new BackG();

	ImageIcon star_img = new ImageIcon("img/rank.png");
	ImageIcon star1_img = new ImageIcon("img/rank1.png");

	ImageIcon question_img = new ImageIcon("img/question.png");
	ImageIcon question1_img = new ImageIcon("img/question1.png");

	ImageIcon start_img = new ImageIcon("img/start.png");
	ImageIcon start1_img = new ImageIcon("img/start1.png");

	ImageIcon end_img = new ImageIcon("img/end.png");
	ImageIcon end1_img = new ImageIcon("img/end1.png");

	ImageIcon titles_img = new ImageIcon("img/titles.png");
	ImageIcon angry_img = new ImageIcon("img/angry.png");

	public MainFrame() {
		CustomCursor();
		setTitle("Mole Game"); // Ÿ��Ʋ
		setSize(800, 600); // �������� ũ��
		setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null); // â�� ��� ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ� ��.
		mainBG.setLayout(null);

		// ������ �̹��� ����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("img/moleicon.png");
		setIconImage(icon);

		// ���� ��ư ����
		start = new JButton(start_img);
		start.setBorderPainted(false);
		start.setFocusPainted(false);
		start.setContentAreaFilled(false);
		start.setBounds(270, 350, 250, 75);
		start.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				start.setIcon(start1_img);
			}

			public void mouseExited(MouseEvent e) {
				start.setIcon(start_img);
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("���� Ŭ��");
			}
		});
		mainBG.add(start);

		// ���� ��ư ����
		end = new JButton(end_img);
		end.setBorderPainted(false);
		end.setFocusPainted(false);
		end.setContentAreaFilled(false);
		end.setBounds(270, 430, 250, 75);
		end.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				end.setIcon(end1_img);
			}

			public void mouseExited(MouseEvent e) {
				end.setIcon(end_img);
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("����");
				System.exit(0);
			}
		});
		mainBG.add(end);

		// ���� ��ư ����
		query = new JButton(question_img);
		query.setBorderPainted(false);
		query.setFocusPainted(false);
		query.setContentAreaFilled(false);
		query.setBounds(720, 500, 50, 50);
		query.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				query.setIcon(question1_img);
			}

			public void mouseExited(MouseEvent e) {
				query.setIcon(question_img);
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("���� Ŭ��");
				setVisible(false);
				new QuestionFrame();
			}
		});
		mainBG.add(query);

		// �������� ��ư ����
		rank = new JButton(star_img);
		rank.setBorderPainted(false);
		rank.setFocusPainted(false);
		rank.setContentAreaFilled(false);
		rank.setBounds(15, 500, 50, 50);
		rank.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				rank.setIcon(star1_img);
			}

			public void mouseExited(MouseEvent e) {
				rank.setIcon(star_img);
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("�������� Ŭ��");
				setVisible(false);
				new LeaderBoardFrame();
			}
		});
		
		player = new JButton(LoginForm.getId());
		//player.setBorderPainted(false);
		//player.setFocusPainted(false);
		//player.setContentAreaFilled(false);
		player.setBounds(675, 10, 100,50 );
		player.addMouseListener(new MouseAdapter() {
			/*
			public void mouseEntered(MouseEvent e) {
				start.setIcon(start1_img);
			}

			public void mouseExited(MouseEvent e) {
				start.setIcon(start_img);
			}
			*/
			public void mousePressed(MouseEvent e) {
				System.out.println("���� ���� Ȱ��ȭ");
				try {
					Connection con = DBConnection.makeConnection(); // DB����
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM gamer");
					String value = "";
					while(rs.next()) {
						if((LoginForm.getId()).equals(rs.getString("ID"))) {
								value = String.format("계정명: %s \n총 플레이수: %3d \n인간승리수: %3d \n두더지승리수: %3d \n승률: %.1f \n점수: %4d" 
	                                   ,rs.getString("ID"),rs.getInt("PLAYCOUNT"),rs.getInt("HUMANWIN"),rs.getInt("MOLEWIN"),((rs.getDouble("HUMANWIN")+rs.getInt("MOLEWIN"))/rs.getInt("PLAYCOUNT"))*100,rs.getInt("SCORES"));
						} else
							continue;
						//JOptionPane.showInputDialog(this,value);
						//JOptionPane.showMessageDialog(frame,value);
						JOptionPane.showMessageDialog(frame, value, "���� ����", JOptionPane.PLAIN_MESSAGE);
					}
					rs.close();
					st.close();
					con.close();
				} catch (Exception a) {
					a.printStackTrace();
				}

			}
		});
		mainBG.add(player);
		
		mainBG.add(rank);

		angry = new JButton(angry_img);
		angry.setBorderPainted(false);
		angry.setFocusPainted(false);
		angry.setContentAreaFilled(false);
		angry.setBounds(400, 200, 75, 75);
		mainBG.add(angry);

		title = new JButton(titles_img);
		title.setBorderPainted(false);
		title.setFocusPainted(false);
		title.setContentAreaFilled(false);
		title.setBounds(170, 60, 476, 146);
		mainBG.add(title);

		add(mainBG);
		setVisible(true);// â�� ���̰�

	}

	public void CustomCursor() { // Ŀ���� Ŀ��(���콺 Ŀ��)

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("img/cropcursor.png");
		Point point = new Point(20, 20);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "crop");
		mainBG.setCursor(cursor);
	}

	class BackG extends JPanel {
		private BufferedImage background;

		public BackG() {
			try {
				background = ImageIO.read(new File("img/mole.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {// �׸��� �Լ�
			super.paintComponent(g);
			g.drawImage(background, 0, 0, null);// background�� �׷���
		}
	}
}

public class MoleGame {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			new MainFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}