package Mole.Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class MoleUI extends JFrame {
	private MolePanel molePanel;

	public MoleUI() throws IOException, InterruptedException { // Mole UI 창
		setTitle("Mole Game");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		molePanel = new MolePanel(); // molePanel 생성

		add(molePanel);
		setVisible(true);
	}
}

class MolePanel extends JPanel {
	private BufferedImage backImage;
	private ImageIcon human = new ImageIcon("img/human.png");
	private ImageIcon intHuman = new ImageIcon("img/humanint.png");
	private ImageIcon intMole = new ImageIcon("img/moleint.png");
	private ImageIcon chicken = new ImageIcon("img/chicken.gif");
	private JButton humanButton, ch, moleInt, humanInt;
	private JLabel counterLabel;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	Timer timer;
	MoleThread m1;
	MoleThread m2;
	MoleThread m3;
	MoleThread m4;
	MoleThread m5;
	MoleThread m6;
	MoleThread m7;
	MoleThread m8;
	MoleThread m9;
	vegetableThread v0;
	vegetableThread v1;
	vegetableThread v2;
	
	int second, minute;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	
	public MolePanel() {
		try {
			setLayout(null);
			backImage = ImageIO.read(new File("img/Back4.png"));

			humanButton = new JButton(human);
			humanButton.setBorderPainted(false);
			humanButton.setFocusPainted(false);
			humanButton.setContentAreaFilled(false);
			humanButton.setBounds(300, 192, 70, 70);
			add(humanButton);

			humanInt = new JButton(intHuman);
			humanInt.setBorderPainted(false);
			humanInt.setFocusPainted(false);
			humanInt.setContentAreaFilled(false);
			humanInt.setBounds(0, 0, 50, 50);
			add(humanInt);
			
			moleInt = new JButton(intMole);
			moleInt.setBorderPainted(false);
			moleInt.setFocusPainted(false);
			moleInt.setContentAreaFilled(false);
			moleInt.setBounds(745, 0, 50, 50);
			add(moleInt);
			// ch.setVisible(false);

			m1 = new MoleThread(50, 400);
			m2 = new MoleThread(100, 400);
			m3 = new MoleThread(150, 400);
			m4 = new MoleThread(50, 450);
			m5 = new MoleThread(100, 450);
			m6 = new MoleThread(150, 450);
			m7 = new MoleThread(50, 500);
			m8 = new MoleThread(100, 500);
			m9 = new MoleThread(150, 500);
			v0 = new vegetableThread(0);
			v1 = new vegetableThread(1);
			v2 = new vegetableThread(2);

			add(v0);
			add(v1);
			add(v2);

//			v0.setVisible(false);
			
			counterLabel = new JLabel("");
			counterLabel.setBounds(345, -30, 100, 100);
			counterLabel.setHorizontalAlignment(JLabel.CENTER);
			counterLabel.setFont(font1);
			
			add(counterLabel);
			
			counterLabel.setText("03:00");
			second  = 0;
			minute = 3;
			normalTimer();
			timer.start();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void normalTimer() {
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				second--;
				
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);
				
				counterLabel.setText(ddMinute + ":"+ ddSecond);
				
				if(second==-1) {
					second=59;
					minute--;
					
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					counterLabel.setText(ddMinute + ":" + ddSecond);
				}
				if(minute==0 && second==0) {
					timer.stop();
				}
			}
		});
	}
	
	public int v0getx() {
		return v0.getx();
	}

	public int v1getx() {
		return v1.getx();
	}

	public int v2getx() {
		return v2.getx();
	}
	
	class vegetableThread extends JLabel {
		private ImageIcon veget = new ImageIcon("img/vegetables.png");

		int x, y,section;

		public vegetableThread(int section) {
			this.section = section;
			x = ((int) (Math.random() * 260)) + 263 * this.section;
			y = 260;
			// vegetable = new JLabel(veget);
			this.setBounds(x, y, 16, 16);
			this.setIcon(veget);
			// add();
			System.out.println("작물위치 " + x + " " + y);
		}

		public int getx() {
			return x;
		}
		public void setposition() {
			x = ((int) (Math.random() * 260)) + 263 * this.section;
			y = 260;
			this.setBounds(x, y, 16, 16);
			 
		}

	}

	class MoleThread extends Thread {
		private int x, y;
		private JButton moleButton;
		private Rectangle champion;
		private ImageIcon mole = new ImageIcon("img/moleimg.png");
		private ImageIcon moleSelect = new ImageIcon("img/moleselect.png");

		private Timer timer;
		private double speed = 0.15;
		private Long startTime;

		private double targetX, targetY;
		private double startX, startY;
		private double runTime;
		private boolean move = false;;

		public int getx() {
			return x;
		}

		public int gety() {
			return y;
		}

		public MoleThread(int x, int y) {
			this.x = x;
			this.y = y;

			champion = new Rectangle(x, y, 10, 10);

			moleButton = new JButton(mole);
			moleButton.setBorderPainted(false);
			moleButton.setFocusPainted(false);
			moleButton.setContentAreaFilled(false);
			moleButton.setBounds(x, y, 30, 30);
			add(moleButton);

			moleButton.addActionListener(e -> {
				if (e.getSource() == moleButton) {
					moleButton.setIcon(moleSelect);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (moleButton.getIcon().equals(moleSelect)) {

									timer.stop();
									calculateChampionMovement(e.getX(), e.getY(), champion);
									startTime = System.currentTimeMillis();
									timer.start();

								}
							}

							if (e.getButton() == MouseEvent.BUTTON1) {
								if (moleButton.getIcon().equals(moleSelect))
									moleButton.setIcon(mole);
							}
						}
					});
				}

			});
			timer = new Timer(10, e -> {
				TimeMove();
			});

		}

		public void run() {

		}

		public void TimeMove() {
			long duration = System.currentTimeMillis() - startTime;
			double progress = duration / runTime;

			if (progress >= 1.0) {
				progress = 1.0;
				timer.stop();
			}

			double x = (int) (startX + ((targetX - startX) * progress));
			double y = (int) (startY + ((targetY - startY) * progress));
			
			repaint();
			if (y >= 270 && x >= 12 && x <= 770) {
				moleButton.setBounds((int) x - 15, (int) y - 15, 30, 30);
				champion.setRect(x - 5, y - 5, 10, 10);
			}
			System.out.println("두더지의 위치"+x+" "+y);
			if (v0.getX() == x && v0.getY() >= y - 15) {
				v0.setVisible(false);
				v0.setposition();
				v0.setVisible(true);
			} else if (v1.getX() == x && v1.getY() >= y - 15) {
				v1.setVisible(false);
				v1.setposition();
				v1.setVisible(true);
			} else if (v2.getX() == x && v2.getY() >= y - 15) {
				v2.setVisible(false);
				v2.setposition();
				v2.setVisible(true);
			}

		}

		public void calculateChampionMovement(double x, double y, Rectangle champion) {

			if (x != champion.getCenterX() || y != champion.getCenterY()) {

				targetX = x;
				targetY = y;

				startX = champion.getCenterX();
				startY = champion.getCenterY();
				double distance = Math
						.sqrt((startX - targetX) * (startX - targetX) + (startY - targetY) * (startY - targetY));

				runTime = distance / (double) speed;
//				if ((x <= v0.getx() + 10 && x > v0.getx() - 10) || (x <= v1.getx() + 10 && x > v1.getx() - 10)
//						|| (x <= v2.getx() + 10 && x > v2.getx() - 10) && y < 290) {
//					eat = true;
//				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {// 그리는 함수
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, null);
	}
}