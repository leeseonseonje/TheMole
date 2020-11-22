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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import MoleServer.MoleClientMainHandler;
import io.netty.channel.ChannelHandlerContext;

public class MainFrame extends JFrame{
	private Font font;
	private JButton start, end, query, rank, angry, player, title;
	public BackG mainBG = new BackG();

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

	public MainFrame(ChannelHandlerContext ctx) {
		CustomCursor();
		setTitle("Mole Game"); // 타이틀
		setSize(800, 600); // 프레임의 크기
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게 함.
		mainBG.setLayout(null);

		// 아이콘 이미지 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("img/moleicon.png");
		setIconImage(icon);

		// 시작 버튼 설정
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
				ctx.writeAndFlush("[LIST]");
				mainBG.setVisible(false);
			}
		});
		mainBG.add(start);

		// 종료 버튼 설정
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
				System.exit(0);
			}
		});
		mainBG.add(end);

		// 도움말 버튼 설정
		query = new JButton(question_img);
		query.setBorderPainted(false);
		query.setFocusPainted(false);
		query.setContentAreaFilled(false);
		query.setBounds(720, 500, 50, 50);
		QuestionFrame question = new QuestionFrame(mainBG);
		query.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				query.setIcon(question1_img);
			}

			public void mouseExited(MouseEvent e) {
				query.setIcon(question_img);
			}

			public void mousePressed(MouseEvent e) {
				add(question);
				mainBG.setVisible(false);
				question.setVisible(true);
			}
		});
		mainBG.add(query);

		// 리더보드 버튼 설정
		rank = new JButton(star_img);
		rank.setBorderPainted(false);
		rank.setFocusPainted(false);
		rank.setContentAreaFilled(false);
		rank.setBounds(15, 500, 50, 50);
		LeaderBoardFrame leaderBoard = new LeaderBoardFrame(mainBG);
		rank.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				rank.setIcon(star1_img);
			}

			public void mouseExited(MouseEvent e) {
				rank.setIcon(star_img);
			}

			public void mousePressed(MouseEvent e) {
				ctx.writeAndFlush("[RANKING]");
				add(leaderBoard);
				leaderBoard.setVisible(true);
				mainBG.setVisible(false);
			}
		});
		
		player = new JButton(LoginForm.getId());
		player.setBounds(675, 10, 100,50 );
		player.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ctx.writeAndFlush("[INFORMATION]" + "," + LoginForm.getId());
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
		setVisible(true);// 창이 보이게

	}

	public void CustomCursor() { // 커스텀 커서(마우스 커서)

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
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {// 그리는 함수
			super.paintComponent(g);
			g.drawImage(background, 0, 0, null);// background를 그려줌
		}
	}
}
