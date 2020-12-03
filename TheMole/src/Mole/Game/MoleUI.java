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

import MoleServer.MoleClientMainHandler;
import io.netty.channel.ChannelHandlerContext;

/*public class MoleUI extends JFrame {
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
}*/

public class MoleUI extends JPanel {
/*	public static void main(String[] args) throws IOException, InterruptedException {
		new f();
	}*/

	private BufferedImage backImage, humanHud, moleHud, humanInv, moleInv, intHuman, intMole;
	public JLabel counterLabel;
	private JLabel vegcountLabel;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	private Font font2 = new Font("Arial", Font.BOLD, 15);

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
	private vegetableThread v1;
	private vegetableThread v2;
	private vegetableThread v3;

	private ImageIcon itemteeth = new ImageIcon("img/strongteeth.png");
	private ImageIcon itemtrap = new ImageIcon("img/trapM.png");
	private ImageIcon itemsnakepipe = new ImageIcon("img/Snakepipe.png");
	private JLabel itembox1;
	private JLabel itembox2;

	private itemBoxThread i1;
	private itemBoxThread i2;
	Human hum;
	// itemBoxThread i2;
	// itemBoxThread i3;

	int second, minute;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	public static MoleInHumanPerformance moleInHumanPerformance;
	private ChannelHandlerContext ctx;
	public MoleUI(ChannelHandlerContext ctx, String name, int v1Location, int v2Location, int v3Location, int crop1, int crop2, int crop3) {
		this.ctx = ctx;
		try {
			setLayout(null);
			backImage = ImageIO.read(new File("img/Back4.png"));
			humanHud = ImageIO.read(new File("img/humanHud.png"));
			moleHud = ImageIO.read(new File("img/moleHud.png"));
			humanInv = ImageIO.read(new File("img/inventory.png"));
			moleInv = ImageIO.read(new File("img/inventory.png"));
			intHuman = ImageIO.read(new File("img/humanint.png"));
			intMole = ImageIO.read(new File("img/moleint.png"));

			m1 = new MoleThread(ctx, name, 50, 400);
			m1.moleButton.addActionListener(e -> {
				if (e.getSource() == m1.moleButton) {
					m1.moleButton.setIcon(m1.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m1.moleButton.getIcon().equals(m1.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 1 + ",");
									m1.getTimer().stop();
									m1.calculateChampionMovement(e.getX(), e.getY(), m1.getChampion());
									m1.setStartTime(System.currentTimeMillis());
									m1.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m1.moleButton.getIcon().equals(m1.getMole()[13]))
									m1.moleButton.setIcon(m1.getMole()[12]);
							}
						}
					});
				}
			});
			m2 = new MoleThread(ctx, name, 100, 400);
			m2.moleButton.addActionListener(e -> {
				if (e.getSource() == m2.moleButton) {
					m2.moleButton.setIcon(m2.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m2.moleButton.getIcon().equals(m2.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 2 + ",");
									m2.getTimer().stop();
									m2.calculateChampionMovement(e.getX(), e.getY(), m2.getChampion());
									m2.setStartTime(System.currentTimeMillis());
									m2.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m2.moleButton.getIcon().equals(m2.getMole()[13]))
									m2.moleButton.setIcon(m2.getMole()[12]);
							}
						}
					});
				}
			});
			m3 = new MoleThread(ctx, name, 150, 400);
			m3.moleButton.addActionListener(e -> {
				if (e.getSource() == m3.moleButton) {
					m3.moleButton.setIcon(m3.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m3.moleButton.getIcon().equals(m3.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 3 + ",");
									m3.getTimer().stop();
									m3.calculateChampionMovement(e.getX(), e.getY(), m3.getChampion());
									m3.setStartTime(System.currentTimeMillis());
									m3.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m3.moleButton.getIcon().equals(m3.getMole()[13]))
									m3.moleButton.setIcon(m3.getMole()[12]);
							}
						}
					});
				}
			});
			m4 = new MoleThread(ctx, name, 50, 450);
			m4.moleButton.addActionListener(e -> {
				if (e.getSource() == m4.moleButton) {
					m4.moleButton.setIcon(m4.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m4.moleButton.getIcon().equals(m4.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 4 + ",");
									m4.getTimer().stop();
									m4.calculateChampionMovement(e.getX(), e.getY(), m4.getChampion());
									m4.setStartTime(System.currentTimeMillis());
									m4.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m4.moleButton.getIcon().equals(m4.getMole()[13]))
									m4.moleButton.setIcon(m4.getMole()[12]);
							}
						}
					});
				}
			});
			m5 = new MoleThread(ctx, name, 100, 450);
			m5.moleButton.addActionListener(e -> {
				if (e.getSource() == m5.moleButton) {
					m5.moleButton.setIcon(m5.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m5.moleButton.getIcon().equals(m5.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 5 + ",");
									m5.getTimer().stop();
									m5.calculateChampionMovement(e.getX(), e.getY(), m5.getChampion());
									m5.setStartTime(System.currentTimeMillis());
									m5.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m5.moleButton.getIcon().equals(m5.getMole()[13]))
									m5.moleButton.setIcon(m5.getMole()[12]);
							}
						}
					});
				}
			});
			m6 = new MoleThread(ctx, name, 150, 450);
			m6.moleButton.addActionListener(e -> {
				if (e.getSource() == m6.moleButton) {
					m6.moleButton.setIcon(m6.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m6.moleButton.getIcon().equals(m6.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 6 + ",");
									m6.getTimer().stop();
									m6.calculateChampionMovement(e.getX(), e.getY(), m6.getChampion());
									m6.setStartTime(System.currentTimeMillis());
									m6.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m6.moleButton.getIcon().equals(m6.getMole()[13]))
									m6.moleButton.setIcon(m6.getMole()[12]);
							}
						}
					});
				}
			});
			m7 = new MoleThread(ctx, name, 50, 500);
			m7.moleButton.addActionListener(e -> {
				if (e.getSource() == m7.moleButton) {
					m7.moleButton.setIcon(m7.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m7.moleButton.getIcon().equals(m7.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 7 + ",");
									m7.getTimer().stop();
									m7.calculateChampionMovement(e.getX(), e.getY(), m7.getChampion());
									m7.setStartTime(System.currentTimeMillis());
									m7.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m7.moleButton.getIcon().equals(m7.getMole()[13]))
									m7.moleButton.setIcon(m7.getMole()[12]);
							}
						}
					});
				}
			});
			m8 = new MoleThread(ctx, name, 100, 500);
			m8.moleButton.addActionListener(e -> {
				if (e.getSource() == m8.moleButton) {
					m8.moleButton.setIcon(m8.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m8.moleButton.getIcon().equals(m8.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 8 + ",");
									m8.getTimer().stop();
									m8.calculateChampionMovement(e.getX(), e.getY(), m8.getChampion());
									m8.setStartTime(System.currentTimeMillis());
									m8.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m8.moleButton.getIcon().equals(m8.getMole()[13]))
									m8.moleButton.setIcon(m8.getMole()[12]);
							}
						}
					});
				}
			});
			m9 = new MoleThread(ctx, name, 150, 500);
			m9.moleButton.addActionListener(e -> {
				if (e.getSource() == m9.moleButton) {
					m9.moleButton.setIcon(m9.getMole()[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (m9.moleButton.getIcon().equals(m9.getMole()[13])) {
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY() + "," + 9 + ",");
									m9.getTimer().stop();
									m9.calculateChampionMovement(e.getX(), e.getY(), m9.getChampion());
									m9.setStartTime(System.currentTimeMillis());
									m9.getTimer().start();
								}
							}
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (m9.moleButton.getIcon().equals(m9.getMole()[13]))
									m9.moleButton.setIcon(m9.getMole()[12]);
							}
						}
					});
				}
			});
			
			v1 = new vegetableThread(v1Location, crop1);
			v2 = new vegetableThread(v2Location, crop2);
			v3 = new vegetableThread(v3Location, crop3);
			add(v1);
			add(v2);
			add(v3);

			i1 = new itemBoxThread();
			i2 = new itemBoxThread();
			add(i1);
			add(i2);
			i1.setVisible(false);
			i2.setVisible(false);
			
			moleInHumanPerformance = new MoleInHumanPerformance(200, 225);
			add(moleInHumanPerformance);
			//add(new MoleInHumanPerformance(200, 225));
			// i2 = new itemBoxThread(2);
			// i3 = new itemBoxThread(3);

			// add(v0);
			// add(v1);
			// add(v2);

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

			itembox1 = new JLabel();
			itembox2 = new JLabel();
			itembox1.setBounds(655, 6, 36, 36);
			itembox2.setBounds(696, 6, 36, 36);
			itembox1.setVisible(false);
			add(itembox1);
			add(itembox2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public vegetableThread getV1() {
		return v1;
	}
	public vegetableThread getV2() {
		return v2;
	}
	public vegetableThread getV3() {
		return v3;
	}
	public itemBoxThread getI1() {
		return i1;
	}
	public itemBoxThread getI2() {
		return i2;
	}

	public void normalTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				second--;

				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);

				counterLabel.setText(ddMinute + ":" + ddSecond);
				//vegcountLabel.setText(15 - (v0.getvegcount() + v1.getvegcount() + v2.getvegcount()) + "");

				if (second == -1) {
					second = 59;
					minute--;

					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					counterLabel.setText(ddMinute + ":" + ddSecond);
				}
				if (counterLabel.getText().equals("00:00")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "인간 승리!!", "Result", JOptionPane.PLAIN_MESSAGE);
					ctx.writeAndFlush("[MOLELOSE]," + LoginForm.getId());
					setVisible(false);
					if (MoleClientMainHandler.roomTest.testStart.getText().equals("준비취소"))
						MoleClientMainHandler.roomTest.testStart.setText("준비");
					MoleClientMainHandler.roomTest.ready.setText("");
					MoleClientMainHandler.roomTest.setVisible(true);
				}
				if (vegcountLabel.getText().equals("0")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "두더지는 자유다 두더지 만만세", "Result", JOptionPane.PLAIN_MESSAGE);
					// MainFrame main = new MainFrame();
					// main.setVisible(true);
				}
			}
		});
	}

	class MoleThread extends Thread {
		private int x, y;
		public JButton moleButton;
		private Rectangle champion;
		public Rectangle getChampion() {
			return champion;
		}

		private int molesecond = 0;
		private int direction = 0;
	
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

		public void setStartTime(Long startTime) {
			this.startTime = startTime;
		}

		public ImageIcon[] getMole() {
			return mole;
		}

		public Timer getTimer() {
			return timer;
		}

		public Long getStartTime() {
			return startTime;
		}

		private Timer eattimer;
		private int eatsecond;
		private boolean eating = false;

		private double targetX, targetY;
		private double startX, startY;
		private double runTime;

		private boolean enhenceteeth = false;
		private ChannelHandlerContext ctx;
		private String name;

		public int getx() {
			return x;
		}

		public int gety() {
			return y;
		}

		public MoleThread(ChannelHandlerContext ctx, String name, int x, int y) {
			this.ctx = ctx;
			this.x = x;
			this.y = y;
			this.name = name;

			champion = new Rectangle(x, y, 32, 32);
			//startTime = System.currentTimeMillis();
			moleButton = new JButton(mole[12]);
			moleButton.setBorderPainted(false);
			moleButton.setFocusPainted(false);
			moleButton.setContentAreaFilled(false);
			moleButton.setBounds(x, y, 32, 32);
			add(moleButton);

			/*moleButton.addActionListener(e -> {
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
									ctx.writeAndFlush("[MOLEMOVE]," + name + "," + e.getX() + "," + e.getY());
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
			});*/
			timer = new Timer(30, e -> {
				if (eating == false) {

					/*molesecond++;
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
					}*/
					try {
						TimeMove();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} else
					timer.stop();
			});
		}
		

		public void molegetitem() {
			int itemnum = ((int) (Math.random() * 10));
			switch (itemnum) {
			case 0:
			case 9:
				System.out.println(itemnum);
				System.out.println("뱀피리 획득");
				System.out.println(itembox1.isVisible());
				if (itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemsnakepipe);
				} else {
					itembox2.setIcon(itemsnakepipe);
					System.out.println("2");
				}
				break;
			case 1:
			case 8:
				System.out.println(itemnum);
				System.out.println("사람 정지");
				System.out.println(itembox1.isVisible());
				if (itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemtrap);
				} else {
					itembox2.setIcon(itemtrap);
					System.out.println("2");
				}
				break;
			default:
				enhenceteeth = true;
				System.out.println("강철이빨 획득");
				System.out.println(itembox1.isVisible());
				if (itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemteeth);
				} else {
					itembox2.setIcon(itemteeth);
					System.out.println("2");
				}
				break;
			}
		}

		public void v1EatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
						ctx.writeAndFlush("[v1EAT]," + name);
						v1.setBounds(0, 0, 0, 0);
						v1.setVisible(false);
						moleButton.setIcon(mole[13]);
					}
				}
			});
		}
		public void v2EatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
						ctx.writeAndFlush("[v2EAT]," + name);
						v2.setBounds(0, 0, 0, 0);
						v2.setVisible(false);
						moleButton.setIcon(mole[13]);
					}
				}
			});
		}
		public void v3EatTimer() {
			eating = true;
			eattimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eatsecond++;
					if (eatsecond == 3) {
						eattimer.stop();
						eating = false;
						ctx.writeAndFlush("[v3EAT]," + name);
						v3.setBounds(0, 0, 0, 0);
						v3.setVisible(false);
						moleButton.setIcon(mole[13]);
					}
				}
			});
		}
		

		public void run() {
		}

		public void TimeMove() throws InterruptedException {
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

			if (i1.getBounds().intersects(champion) && i1.getTimerstop() == false && eating == false) {
				ctx.writeAndFlush("[MOLEITEM1EAT]," + name);
				i1.setBounds(0, 0, 0, 0);
				i1.setVisible(false);
				molegetitem();
				
			} else if (i2.getBounds().intersects(champion) && i2.getTimerstop() == false && eating == false) {
				ctx.writeAndFlush("[MOLEITEM2EAT]," + name);
				i2.setBounds(0, 0, 0, 0);
				i2.setVisible(false);
				molegetitem();
			}

			if (v1.getBounds().intersects(champion)&& v1.timerstop == false && eating == false) {
				eatsecond = 0;
				if (enhenceteeth == false) {
					v1EatTimer();
					eattimer.start();
					moleButton.setIcon(mole[12]);
				} /*else {
					enhenceteeth = false;
					if (itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					} else if (itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else
						itembox2.setIcon(null);
				}*/
				v1.plusvegcount();
			} else if (v2.getBounds().intersects(champion) && v2.timerstop == false && eating == false) {
			/*	v1.setVisible(false);
				v1.setsecond(0);
				v1.vegtimer();
				v1.vegtimer.start();*/
				eatsecond = 0;
				if (enhenceteeth == false) {
					v2EatTimer();
					eattimer.start();
					moleButton.setIcon(mole[12]);
				} else {
					enhenceteeth = false;
					if (itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					} else if (itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else
						itembox2.setIcon(null);
				}
				v2.plusvegcount();

			} else if (v3.getBounds().intersects(champion) && v3.timerstop == false && eating == false) {
				/*v2.setVisible(false);
				v2.setsecond(0);
				v2.vegtimer();
				v2.vegtimer.start();*/
				eatsecond = 0;
				if (enhenceteeth == false) {
					v3EatTimer();
					eattimer.start();
					moleButton.setIcon(mole[12]);
				} else {
					enhenceteeth = false;
					if (itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					} else if (itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else
						itembox2.setIcon(null);
				}
				v3.plusvegcount();
			}
		}

		public void calculateChampionMovement(double x, double y, Rectangle champion) {

			if (x != champion.getCenterX() || y != champion.getCenterY()) {

				targetX = x;
				targetY = y;

				System.out.println(targetX + "     " + targetY);
				startX = champion.getCenterX();
				startY = champion.getCenterY();

				
				if (targetX - startX > 0) {
                    direction = 1;
                } else if (targetX - startX < 0) {
                    direction = 2;
                }
				 

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
		//g.drawImage(humanHud, 0, 70, null);
		g.drawImage(moleHud, 715, 70, null);
		//g.drawImage(humanInv, 55, 0, null);
		g.drawImage(moleInv, 650, 0, null);
		//g.drawImage(intHuman, 0, 0, null);
		g.drawImage(intMole, 740, 0, null);
	}
}

/*class f extends JFrame {
	private MoleUI molePanel;

	public f() throws IOException, InterruptedException { // Mole UI 창
		setTitle("Mole Game");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		molePanel = new MoleUI(); // molePanel 생성

		add(molePanel);
		setVisible(true);
	}
}*/

