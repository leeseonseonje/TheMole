package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Bullet extends JLabel {

	private int x;
	private int y = 255;
	private int direction = 0;

	private Human hum;
	private Timer shoottimer;
	private Timer dshoottimer;
	private int shootsec = 0;
	private int dshootsec = 0;
	MolePanel mainpanel;

	private ImageIcon bulletR = new ImageIcon("img/bulletR.png");
	private ImageIcon bulletL = new ImageIcon("img/bulletL.png");
	private ImageIcon bulletD = new ImageIcon("img/bulletD.png");

	public Bullet(int x, int direction, int status, MolePanel pan) {
		mainpanel = pan;
		if (direction == 1) {
			this.x = x + 50;
		} else if(direction == 2){
			this.x = x;
		} else
			this.x = x+15;

		this.direction = direction;

		if (direction == 1 && status == 1) { // 坷弗率
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletR);
			pan.add(this);
			this.setVisible(true);
			shooting(this);
			shoottimer.start();
		} else if (direction == 2 && status == 2) { // 哭率
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletL);
			pan.add(this);
			this.setVisible(true);
			shooting(this);
			shoottimer.start();
		} else if (direction == 3) {
			setBounds((int) x , (int) y, 16, 16);
			setIcon(bulletD);
			pan.add(this);
			this.setVisible(true);
			dshooting();
			dshoottimer.start();
		}
	}

	public void dshooting() {
		dshoottimer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dshootsec++;
				System.out.println(x + "     " + y);
				for (int i = 0; i < 5; i++) {
					bulMoveD(1);
					setBounds(x,y, 16, 16);
					if (mainpanel.m1.getx() >= getX() - 20 && mainpanel.m1.getx() <= getX() + 20
							&& mainpanel.m1.gety() == y && mainpanel.m1.getlife() == true) {
						mainpanel.m1.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m2.getx() >= getX() - 20 && mainpanel.m2.getx() <= getX() + 20
							&& mainpanel.m2.gety() == y && mainpanel.m2.getlife() == true) {
						mainpanel.m2.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m3.getx() >= getX() - 20 && mainpanel.m3.getx() <= getX() + 20
							&& mainpanel.m3.gety() == y && mainpanel.m3.getlife() == true) {
						mainpanel.m3.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m4.getx() >= getX() - 20 && mainpanel.m4.getx() <= getX() + 20
							&& mainpanel.m4.gety() == y && mainpanel.m4.getlife() == true) {
						mainpanel.m4.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m5.getx() >= getX() - 20 && mainpanel.m5.getx() <= getX() + 20
							&& mainpanel.m5.gety() == y && mainpanel.m5.getlife() == true) {
						mainpanel.m5.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m6.getx() >= getX() - 20 && mainpanel.m6.getx() <= getX() + 20
							&& mainpanel.m6.gety() == y && mainpanel.m6.getlife() == true) {
						mainpanel.m6.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m7.getx() >= getX() - 20 && mainpanel.m7.getx() <= getX() + 20
							&& mainpanel.m7.gety() == y && mainpanel.m7.getlife() == true) {
						mainpanel.m7.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m8.getx() >= getX() - 20 && mainpanel.m8.getx() <= getX() + 20
							&& mainpanel.m8.gety() == y && mainpanel.m8.getlife() == true) {
						mainpanel.m8.moledie();
						setVisible(false);
						dshoottimer.stop();
					} else if (mainpanel.m9.getx() >= getX() - 20 && mainpanel.m9.getx() <= getX() + 20
							&& mainpanel.m9.gety() == y && mainpanel.m9.getlife() == true) {
						mainpanel.m9.moledie();
						setVisible(false);
						dshoottimer.stop();
					}
				}if (dshootsec == 30) {
					setVisible(false);
					dshoottimer.stop();
				}
				

			}

		});
	}

	public void shooting(JLabel bul) {
		shoottimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shootsec++;
				for (int i = 0; i < 5; i++) {
					if (direction == 1) { // 坷弗率
						bulMove(1);
						setBounds(((int) bul.getX()), (int) y, 16, 16);
						if (shootsec == 30) {
							bul.setVisible(false);
							shoottimer.stop();
						}
					} else if (direction == 2) { // 哭率
						bulMove(-1);
						setBounds(((int) bul.getX()), (int) y, 16, 16);
						if (shootsec == 30) {
							bul.setVisible(false);
							shoottimer.stop();
						}
					}
					if (mainpanel.m1.getx() == bul.getX() && mainpanel.m1.gety() <= 275
							&& mainpanel.m1.getlife() == true) {
						mainpanel.m1.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m2.getx() == bul.getX() && mainpanel.m2.gety() <= 275
							&& mainpanel.m2.getlife() == true) {
						mainpanel.m2.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m3.getx() == bul.getX() && mainpanel.m3.gety() <= 275
							&& mainpanel.m3.getlife() == true) {
						mainpanel.m3.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m4.getx() == bul.getX() && mainpanel.m4.gety() <= 275
							&& mainpanel.m4.getlife() == true) {
						mainpanel.m4.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m5.getx() == bul.getX() && mainpanel.m5.gety() <= 275
							&& mainpanel.m5.getlife() == true) {
						mainpanel.m5.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m6.getx() == bul.getX() && mainpanel.m6.gety() <= 275
							&& mainpanel.m6.getlife() == true) {
						mainpanel.m6.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m7.getx() == bul.getX() && mainpanel.m7.gety() <= 275
							&& mainpanel.m7.getlife() == true) {
						mainpanel.m7.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m8.getx() == bul.getX() && mainpanel.m8.gety() <= 275
							&& mainpanel.m8.getlife() == true) {
						mainpanel.m8.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.m9.getx() == bul.getX() && mainpanel.m9.gety() <= 275
							&& mainpanel.m9.getlife() == true) {
						mainpanel.m9.moledie();
						bul.setVisible(false);
						shoottimer.stop();
					} else if (mainpanel.snake.getX() == bul.getX() && mainpanel.snake.getmoving() == true) {
						mainpanel.snake.snakedie();
						bul.setVisible(false);
						shoottimer.stop();
					}
				}
			}

		});
	}

	public void bulMove(int x) {
		this.x = this.x + x;
	}

	public void bulMoveD(int y) {
		this.y = this.y + y;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}
}
