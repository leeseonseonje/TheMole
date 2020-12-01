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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Mole.Game.Sound.*;

import javazoom.jl.decoder.JavaLayerException;

public class MoleUI extends JFrame {
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
	private BufferedImage backImage, humanHud, moleHud, humanInv, moleInv, intHuman, intMole;

	private JLabel counterLabel;
	private JLabel vegcountLabel;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	private Font font2 = new Font("Arial", Font.BOLD, 15);

	public MoleUI frame;

	Timer timer;

	SoundJLayer soundToPlay = new SoundJLayer("res/ingameBG_Lisport.mp3");
	EffectSound moleDeadSound = new EffectSound();
	EffectSound vegEatSound = new EffectSound();
	EffectSound itemGetSound = new EffectSound();
	EffectSound itemCreateSound = new EffectSound();
	
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

	itemBoxThread i0;
	itemBoxThread i1;

	Human hum;
	// itemBoxThread i2;
	// itemBoxThread i3;

	int second, minute;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");

	public MolePanel() {
		setLayout(null);
		// this.setFocusable(true); // 키 리스너

		try {
			backImage = ImageIO.read(new File("img/Back4.png"));
			humanHud = ImageIO.read(new File("img/humanHud.png"));
			moleHud = ImageIO.read(new File("img/moleHud.png"));
			humanInv = ImageIO.read(new File("img/inventory.png"));
			moleInv = ImageIO.read(new File("img/inventory.png"));
			intHuman = ImageIO.read(new File("img/humanint.png"));
			intMole = ImageIO.read(new File("img/moleint.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		m1 = new MoleThread(50, 400);
		m1.start();
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

		i0 = new itemBoxThread(0);
		i1 = new itemBoxThread(1);

		this.add(new Human(this, 200, 225));

		counterLabel = new JLabel("");
		counterLabel.setBounds(345, 0, 100, 50);
		counterLabel.setHorizontalAlignment(JLabel.CENTER);
		counterLabel.setFont(font1);

		add(counterLabel);

		counterLabel.setText("03:00");
		second = 0;
		minute = 3;
		normalTimer();
		timer.start();

		vegcountLabel = new JLabel("15");
		vegcountLabel.setBounds(758, 90, 20, 20);
		vegcountLabel.setHorizontalAlignment(JLabel.CENTER);
		vegcountLabel.setFont(font2);

		add(vegcountLabel);

		soundToPlay.play();
		
		// add(hum);
	}

	// 인터페이스 그리는 위치
	public void paintComponent(Graphics g) {// 그리는 함수
		super.paintComponent(g); // 여기서 super인 패널에 그림을 그리는것
		g.drawImage(backImage, 0, 0, null);
		// g.drawImage(humanHud, 0, 70, null);
		g.drawImage(moleHud, 715, 70, null);
		// g.drawImage(humanInv, 55, 0, null);
		g.drawImage(moleInv, 650, 0, null);
		// g.drawImage(intHuman, 0, 0, null);
		g.drawImage(intMole, 740, 0, null);
	}

	public void normalTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				second--;

				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);

				counterLabel.setText(ddMinute + ":" + ddSecond);
				vegcountLabel.setText(15 - (v0.getvegcount() + v1.getvegcount() + v2.getvegcount()) + "");

				if (second == -1) {
					second = 59;
					minute--;

					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					counterLabel.setText(ddMinute + ":" + ddSecond);
				}
				if (minute == 0 && second == 0) {
					timer.stop();
					try {
						try {
							soundToPlay.pause();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "인간 승리!!", "Result", JOptionPane.PLAIN_MESSAGE);
					MainFrame main = new MainFrame();
					main.setVisible(true);
				}
				if (vegcountLabel.getText().equals("0")) {
					timer.stop();
					try {
						try {
							soundToPlay.pause();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "두더지는 자유다 두더지 만만세", "Result", JOptionPane.PLAIN_MESSAGE);
					MainFrame main = new MainFrame();
					main.setVisible(true);
				}
			}
		});
	}

	class itemBoxThread {
		private ImageIcon itemB = new ImageIcon("img/itemBox.png");
		JLabel itemBox = new JLabel(itemB);
		private int x, y, section;
		private Timer itemtimer;
		private int itemsecond;
		private int itemcount = 0;
		private boolean timerstop = false;

		public itemBoxThread(int section) {
			this.section = section;
			x = ((int) (Math.random() * 260)) + 263 * this.section;
			y = 235;
			itemBox.setBounds(x, y, 40, 40);
			add(itemBox);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setsecond(int second) {
			itemsecond = second;
		}

		public int getsecond() {
			return itemsecond;
		}

		public int getcouont() {
			return itemcount;
		}

		public int setcount(int count) {
			return itemcount = count;
		}

		public void setposition() {
			x = ((int) (Math.random() * 260)) + 263 * this.section;
			y = 235;
			itemBox.setBounds(x, y, 40, 40);
		}

		public void itemtimer() {
			timerstop = true;
			itemtimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					itemsecond++;
					if (itemsecond == 30) {
						setposition();
						itemCreateSound.playSound("sound/itemCreated.mp3");
						itemBox.setVisible(true);
						itemtimer.stop();
						timerstop = false;
						itemcount++;
					}
				}
			});
		}

		public void setVisible(boolean b) {
			if (b == true)
				itemBox.setVisible(true);
			else if (b == false)
				itemBox.setVisible(false);
		}
		public Rectangle getBounds() {
			return new Rectangle( x, y, 32, 32);
		}
	}

	class vegetableThread {
		private ImageIcon vegetables[] = { new ImageIcon("img/vegetableResource/vegetable0.png"),
				new ImageIcon("img/vegetableResource/vegetable1.png"),
				new ImageIcon("img/vegetableResource/vegetable2.png"),
				new ImageIcon("img/vegetableResource/vegetable3.png"),
				new ImageIcon("img/vegetableResource/vegetable4.png"),
				new ImageIcon("img/vegetableResource/vegetable5.png"),
				new ImageIcon("img/vegetableResource/vegetable6.png"),
				new ImageIcon("img/vegetableResource/vegetable7.png"),
				new ImageIcon("img/vegetableResource/vegetable8.png"),
				new ImageIcon("img/vegetableResource/vegetable9.png"), };
		JLabel vegetable = new JLabel(vegetables[(int)(Math.random()*10)]);
		private int x, y, section;
		private Timer vegtimer;
		private int vegsecond;
		private int vegcount = 0;
		private boolean timerstop = false;

		public vegetableThread(int section) {
			this.section = section;
			x = ((int) (Math.random() * 260)) + 247 * this.section;
			y = 235;
			vegetable.setBounds(x, y, 32, 32);
			add(vegetable);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setsecond(int second) {
			vegsecond = second;
		}

		public int getsecond() {
			return vegsecond;
		}

		public int getvegcount() {
			return vegcount;
		}

		public int plusvegcount() {
			return vegcount++;
		}

		public void setposition() {
			x = ((int) (Math.random() * 260)) + 263 * this.section;
			y = 235;
			vegetable.setBounds(x, y, 32, 32);
		}

		public void vegtimer() {
			timerstop = true;
			vegtimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vegsecond++;
					if (vegsecond == 10) {
						setposition();
						itemCreateSound.playSound("sound/itemCreated.mp3");
						vegetable.setVisible(true);
						vegtimer.stop();
						timerstop = false;
					}
				}
			});
		}

		public void setVisible(boolean b) {
			if (b == true)
				vegetable.setVisible(true);
			else if (b == false)
				vegetable.setVisible(false);
		}
		public Rectangle getBounds() {
			return new Rectangle( x, y, 32, 32);
		}
	}

	class MoleThread extends Thread {
		private int x, y;
		private int direction = 0;
		public int molesecond = 0;
		private JButton moleButton;
		private Rectangle champion;
		// mole 스프라이트 14장
		private ImageIcon mole[] = { new ImageIcon("img/moleResource/moleL1.png"),
				new ImageIcon("img/moleResource/moleL2.png"), new ImageIcon("img/moleResource/moleL3.png"),
				new ImageIcon("img/moleResource/moleLS1.png"), new ImageIcon("img/moleResource/moleLS2.png"),
				new ImageIcon("img/moleResource/moleLS3.png"), new ImageIcon("img/moleResource/moleR1.png"),
				new ImageIcon("img/moleResource/moleR2.png"), new ImageIcon("img/moleResource/moleR3.png"),
				new ImageIcon("img/moleResource/moleRS1.png"), new ImageIcon("img/moleResource/moleRS2.png"),
				new ImageIcon("img/moleResource/moleRS3.png"), new ImageIcon("img/moleResource/moleS.png"),
				new ImageIcon("img/moleResource/moleSS.png") };

		private Timer timer;
		private double speed = 0.15;
		private Long startTime;

		private Timer eattimer;
		private int eatsecond;
		private boolean eating = false;

		private double targetX, targetY;
		private double startX, startY;
		private double runTime;

		private Human human;

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public MoleThread(int x, int y) {
			this.x = x;
			this.y = y;

			champion = new Rectangle(x, y, 32, 32);

			moleButton = new JButton(mole[12]);
			moleButton.setBorderPainted(false);
			moleButton.setFocusPainted(false);
			moleButton.setContentAreaFilled(false);
			moleButton.setBounds(x, y, 30, 30);
			add(moleButton);

			moleButton.addActionListener(e -> {
				if (e.getSource() == moleButton && eating == false) {
					moleButton.setIcon(mole[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (eating == true) // 먹었을 때 해제하게 만듬
									moleButton.setIcon(mole[12]);
								if (moleButton.getIcon().equals(mole[13]) || moleButton.getIcon().equals(mole[11])
										|| moleButton.getIcon().equals(mole[10]) || moleButton.getIcon().equals(mole[9])
										|| moleButton.getIcon().equals(mole[5]) || moleButton.getIcon().equals(mole[4])
										|| moleButton.getIcon().equals(mole[3])) {
									timer.stop();
									calculateChampionMovement(e.getX(), e.getY(), champion);
									startTime = System.currentTimeMillis();
									timer.start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (moleButton.getIcon().equals(mole[13]))
									moleButton.setIcon(mole[12]);
								else if (moleButton.getIcon().equals(mole[11]))
									moleButton.setIcon(mole[8]);
								else if (moleButton.getIcon().equals(mole[10]))
									moleButton.setIcon(mole[7]);
								else if (moleButton.getIcon().equals(mole[9]))
									moleButton.setIcon(mole[6]);
								else if (moleButton.getIcon().equals(mole[5]))
									moleButton.setIcon(mole[2]);
								else if (moleButton.getIcon().equals(mole[4]))
									moleButton.setIcon(mole[1]);
								else if (moleButton.getIcon().equals(mole[3]))
									moleButton.setIcon(mole[0]);
							}
						}
					});
				}
			});
			timer = new Timer(30, e -> {
				if (eating == false) {

					molesecond++;
					molesecond = molesecond % 4;
					if (direction == 1) { // 오른쪽방향으로 움직일때 -누름
						if (molesecond == 1)
							moleButton.setIcon(mole[8]);
						else if (molesecond == 2)
							moleButton.setIcon(mole[9]);
						else if (molesecond == 3)
							moleButton.setIcon(mole[10]);
					}
					if (direction == 2) { // 왼쪽방향으로 움직일때 -누름
						if (molesecond == 1)
							moleButton.setIcon(mole[3]);
						else if (molesecond == 2)
							moleButton.setIcon(mole[4]);
						else if (molesecond == 3)
							moleButton.setIcon(mole[5]);
					}
					TimeMove();
					/*
					if (getBounds().intersects(human.getBounds()))
						this.moleButton.setVisible(false);
						*/
				} else
					timer.stop();
			});
		}

		public void eatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
					}
				}
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

			if (i0.getBounds().intersects(champion) && i0.timerstop == false) {
				i0.setVisible(false);
				i0.setsecond(0);
				i0.itemtimer();
				i0.itemtimer.start();
				itemGetSound.playSound("sound/itemGet.mp3");
				eatsecond = 0;
				eatTimer();
				eattimer.start();

			} else if (i1.getBounds().intersects(champion) && i1.timerstop == false) {
				i1.setVisible(false);
				i1.setsecond(0);
				i1.itemtimer();
				i1.itemtimer.start();
				eatsecond = 0;
				eatTimer();
				eattimer.start();
				itemGetSound.playSound("sound/itemGet.mp3");
			}

			if (v0.getBounds().intersects(champion) && v0.timerstop == false && eating == false) {
				v0.setVisible(false);
				v0.setsecond(0);
				v0.vegtimer();
				v0.vegtimer.start();
				vegEatSound.playSound("sound/vegEat.wav");
				eatsecond = 0;
				eatTimer();
				eattimer.start();
				v0.plusvegcount();
			} else if (v1.getBounds().intersects(champion) && v1.timerstop == false && eating == false) {
				vegEatSound.playSound("sound/vegEat.wav");
				v1.setVisible(false);
				v1.setsecond(0);
				v1.vegtimer();
				v1.vegtimer.start();
				eatsecond = 0;
				eatTimer();
				eattimer.start();
				v1.plusvegcount();
			} else if (v2.getBounds().intersects(champion) && v2.timerstop == false && eating == false) {
				vegEatSound.playSound("sound/vegEat.wav");
				v2.setVisible(false);
				v2.setsecond(0);
				v2.vegtimer();
				v2.vegtimer.start();
				eatsecond = 0;
				eatTimer();
				eattimer.start();
				v2.plusvegcount();
			}
		}

		public void calculateChampionMovement(double x, double y, Rectangle champion) {

			if (x != champion.getCenterX() || y != champion.getCenterY()) {

				targetX = x;
				targetY = y;

				startX = champion.getCenterX();
				startY = champion.getCenterY();

				if (targetX - startX > 0) // 방향체크, 사분면
					direction = 1;
				else
					direction = 2;

				double distance = Math
						.sqrt((startX - targetX) * (startX - targetX) + (startY - targetY) * (startY - targetY));

				runTime = distance / (double) speed;
			}
		}

	}
} // MolePanel 종지부
