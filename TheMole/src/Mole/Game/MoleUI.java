package Mole.Game;

import java.awt.Color;
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
	public JLabel counterLabel;
	private JLabel vegcountLabel;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	private Font font2 = new Font("Arial", Font.BOLD, 15);
	

	public MoleUI frame;

	Timer timer;
	
	Snake snake;
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
	
	private ImageIcon itemteeth = new ImageIcon("img/strongteeth.png");
	private ImageIcon itemtrap = new ImageIcon("img/trapM.png");
	private ImageIcon itemsnakepipe = new ImageIcon("img/Snakepipe.png");
	private JLabel itembox1;
	private JLabel itembox2;
	private JLabel humitembox1;
	private JLabel humitembox2;
	itemBoxThread i0;
	itemBoxThread i1;
	itemBoxThread i2;
	itemBoxThread i3;
	Human hum;
	private boolean humtrap = false;
	private Timer humstoptimer;
	private int humstun = 3;
	public boolean humstop = false;
	//itemBoxThread i2;
	//itemBoxThread i3;
	
	
	int second, minute;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");

	public MolePanel() {
		try {
			setLayout(null);
			backImage = ImageIO.read(new File("img/Back4.png"));
			humanHud = ImageIO.read(new File("img/humanHud.png"));
			moleHud = ImageIO.read(new File("img/moleHud.png"));
			humanInv = ImageIO.read(new File("img/inventory.png"));
			moleInv = ImageIO.read(new File("img/inventory.png"));
			intHuman = ImageIO.read(new File("img/humanint.png"));
			intMole = ImageIO.read(new File("img/moleint.png"));

			m1 = new MoleThread(50, 275);
			m2 = new MoleThread(100, 265);
			m3 = new MoleThread(150, 265);
			m4 = new MoleThread(50, 350);
			m5 = new MoleThread(100, 450);
			m6 = new MoleThread(150, 450);
			m7 = new MoleThread(50, 300);
			m8 = new MoleThread(100, 500);
			m9 = new MoleThread(150, 500);

			v0 = new vegetableThread(0);
			v1 = new vegetableThread(1);
			v2 = new vegetableThread(2);

			i0 = new itemBoxThread(0);
			i1 = new itemBoxThread(1);
			snake = new Snake(this);

			
			add(i0);
			add(i1);
			add(v0);
			add(v1);
			add(v2);
			
			
			hum = new Human(this,225);
			add(hum);
			add(snake);
			
			//i2 = new itemBoxThread(2);
			//i3 = new itemBoxThread(3);
			
			//add(v0);
			//add(v1);
			//add(v2);


			counterLabel = new JLabel("");
			counterLabel.setBounds(345, 0, 100, 50);
			counterLabel.setHorizontalAlignment(JLabel.CENTER);
			counterLabel.setFont(font1);

			add(counterLabel);
			

			counterLabel.setText("03:00");

			second = 0;
			minute = 1;

			normalTimer();
			timer.start();

			vegcountLabel = new JLabel("15");
			vegcountLabel.setBounds(758, 90, 20, 20);
			vegcountLabel.setHorizontalAlignment(JLabel.CENTER);
			vegcountLabel.setFont(font2);

			add(vegcountLabel);
			
			itembox1 = new JLabel();
			itembox2 = new JLabel();
			humitembox1 = new JLabel();
			humitembox2 = new JLabel();
			itembox1.setBounds(655,6,36,36);
			itembox2.setBounds(696,6,36,36);
			humitembox1.setBounds(655,6,36,36);
			humitembox2.setBounds(696,6,36,36);
			itembox1.setVisible(false);
			add(itembox1);
			add(itembox2);
			add(humitembox1);
			add(humitembox2);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sethumtrap(boolean a) {
		humtrap = a;
	}
	public boolean gethumtrap() {
		return humtrap;
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
				if (counterLabel.getText().equals("00:00")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "인간 승리!!", "Result", JOptionPane.PLAIN_MESSAGE);
					MainFrame main = new MainFrame();
					main.setVisible(true);
				}
				if (vegcountLabel.getText().equals("0")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "두더지는 자유다 두더지 만만세", "Result", JOptionPane.PLAIN_MESSAGE);
					MainFrame main = new MainFrame();
					main.setVisible(true);
				}
			}
		});
	}

	class MoleThread  {
		private int x, y;
		private int direction = 0;
		public int molesecond = 0;
		private JButton moleButton;
		private Rectangle champion;
		private boolean life = true;
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
		
		private boolean enhenceteeth = false;
		
		

		public int getx() {
			return x;
		}

		public int gety() {
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
			moleButton.setBounds(x, y, 32, 32);
			add(moleButton);

			moleButton.addActionListener(e -> {
				if (e.getSource() == moleButton && eating == false) {
					moleButton.setIcon(mole[13]);
					addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								if (eating == true)
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
			
			timer = new Timer(10, e -> {
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
		
		
		public void moledie() {
			moleButton.setVisible(false);
			champion.setBounds(0,0,0,0);
			life = false;
		}
		public boolean getlife() {
			return life;
		}
		
		public void molegetitem() {
			int itemnum = ((int)(Math.random()*10));
			switch (itemnum) {
			case 0:
			case 9:
				System.out.println(itemnum);
				System.out.println("뱀피리 획득");
				System.out.println(itembox1.isVisible());
				if(itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemsnakepipe);
				}
				else {
					itembox2.setIcon(itemsnakepipe);
					System.out.println("2");
				}
				break;
			case 1:
			case 8:
				System.out.println(itemnum);
				System.out.println("사람 정지");
				if(itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemtrap);
					humstop = true;
					humstop();
					humstoptimer.start();
				}
				else if (itembox1.getIcon() == itemtrap) {
					humstun+= 3;
				}
				else {
					itembox2.setIcon(itemtrap);
					humstop();
					humstoptimer.start();
				}
				break;
			default: 
				enhenceteeth = true;
				System.out.println("강철이빨 획득");
				System.out.println(itembox1.isVisible());
				if(itembox1.isVisible() == false) {
					itembox1.setVisible(true);
					itembox1.setIcon(itemteeth);
				}
				else {
					itembox2.setIcon(itemteeth);
				}
				break;
			}
		}
		
		public void humstop() {
			humstoptimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					humstun--;
					if (itembox1.getIcon() == itemtrap) {
						itembox1.setFont(font1);
						itembox1.setText(humstun + "");
						itembox1.setVerticalTextPosition(JLabel.CENTER);
						itembox1.setHorizontalTextPosition(JLabel.CENTER);
						itembox1.setForeground(Color.cyan);
					} else {
						itembox2.setText(humstun + "");
						itembox2.setFont(font1);
						itembox2.setVerticalTextPosition(JLabel.CENTER);
						itembox2.setHorizontalTextPosition(JLabel.CENTER);
						itembox2.setForeground(Color.cyan);
					}
					if(humstun == 0) {
						if(itembox1.getIcon() == itemtrap) {
							itembox1.setIcon(null);
							itembox1.setText(null);
						}
						else {
							itembox1.setIcon(null);
							itembox1.setText(null);
						}
						humstop = false;
						humstun = 3;
						humstoptimer.stop();
					}
				}
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
			
			//System.out.println(targetX + " "+targetY);
			double x = (int) (startX + ((targetX - startX) * progress));
			double y = (int) (startY + ((targetY - startY) * progress));

			repaint();
			
			if (y >= 270) {
                moleButton.setBounds((int) x - 15, (int) y - 15, 32, 32);
                champion.setRect(x - 15, y - 15, 32, 32);
            }
			
			if(y < 290 && humtrap == true) {
				moledie();
				humtrap = false;
			}

			if (i0.getBounds().intersects(champion) && i0.timerstop == false) {
				i0.setVisible(false);
				i0.setsecond(0);
				i0.itemtimer();
				i0.itemtimer.start();
				molegetitem();
			} else if (i1.getBounds().intersects(champion) && i1.timerstop == false) {
				i1.setVisible(false);
				i1.setsecond(0);
				i1.itemtimer();
				i1.itemtimer.start();
				molegetitem();
				}
			
			if (v0.getBounds().intersects(champion) && v0.timerstop == false && eating == false) {
				v0.setVisible(false);
				v0.setsecond(0);
				v0.vegtimer();
				v0.vegtimer.start();
				eatsecond = 0;
				if(enhenceteeth == false) {	
				eatTimer();
				eattimer.start();
				} else {
					enhenceteeth = false ;
					if(itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
						itembox1.setIcon(null);
						enhenceteeth = true;
					}
					else if(itembox1.getIcon() == itemteeth)
						itembox1.setIcon(null);
					else 
						itembox2.setIcon(null);
				}
				v0.plusvegcount();
			} else if (v1.getBounds().intersects(champion) && v1.timerstop == false && eating == false) {
				v1.setVisible(false);
				v1.setsecond(0);
				v1.vegtimer();
				v1.vegtimer.start();
				eatsecond = 0;
				if(enhenceteeth == false) {	
					eatTimer();
					eattimer.start();
					} else {
						enhenceteeth = false ;
						if(itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
							itembox1.setIcon(null);
							enhenceteeth = true;
						}
						else if(itembox1.getIcon() == itemteeth)
							itembox1.setIcon(null);
						else 
							itembox2.setIcon(null);
						}
				v1.plusvegcount();
				
			} else if (v2.getBounds().intersects(champion) && v2.timerstop == false && eating == false) {
				v2.setVisible(false);
				v2.setsecond(0);
				v2.vegtimer();
				v2.vegtimer.start();
				eatsecond = 0;
				if(enhenceteeth == false) {	
					eatTimer();
					eattimer.start();
					} else {
						enhenceteeth = false ;
						if(itembox1.getIcon() == itemteeth && itembox2.getIcon() == itemteeth) {
							itembox1.setIcon(null);
							enhenceteeth = true;
						}
						else if(itembox1.getIcon() == itemteeth)
							itembox1.setIcon(null);
						else 
							itembox2.setIcon(null);
					}
				v2.plusvegcount();
			}
		}

		public void calculateChampionMovement(double x, double y, Rectangle champion) {

			if (x != champion.getCenterX() || y != champion.getCenterY()) {

				targetX = x;
				targetY = y;
				
				//System.out.println(targetX+"     "+targetY);
				startX = champion.getCenterX();
				startY = champion.getCenterY();
				
				if (targetX - startX > 0) // 방향체크, 사분면
					direction = 1;
				else
					direction = 2;
				
				/*
				 * if(targetX - startX > 0) { if(targetY - startY > 0) {
				 * System.out.println("4사분면"); }else System.out.println("1사분면"); } else if
				 * (targetX-startX < 0) { if(targetY - startY > 0) { System.out.println("3사분면");
				 * }else System.out.println("2사분면"); }
				 */

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

	public void paintComponent(Graphics g) { // 그리는 함수
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, null);
		g.drawImage(humanHud, 0, 70, null);
		g.drawImage(moleHud, 715, 70, null);
		g.drawImage(humanInv, 55, 0, null);
		g.drawImage(moleInv, 650, 0, null);
		g.drawImage(intHuman, 0, 0, null);
		g.drawImage(intMole, 740, 0, null);
	}
}