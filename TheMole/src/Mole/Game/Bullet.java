package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Bullet extends JLabel {
	
	private double x;
	private double y = 255;
	private int direction = 0;

	private Human hum;
	private Timer shoottimer;
	private int shootsec = 0;
	MolePanel mainpanel;
	
	private ImageIcon bulletR = new ImageIcon("img/bulletResource/bulletR.png");
	private ImageIcon bulletL = new ImageIcon("img/bulletResource/bulletL.png");
	
	
	public Bullet(double x, int direction, MolePanel pan) {
		mainpanel = pan;
		if(direction == 1) {
			this.x = x + 50;
		}
		else {
			this.x = x;
		}
		System.out.println(y);
		this.direction = direction;
		
		
		if(direction == 1) { 
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletR);
		} else if(direction == 2) { 
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletL);
		}
		pan.add(this);
		this.setVisible(true);
		shooting(this);
		shoottimer.start();
	}
	
	public void shooting(JLabel bul) {
		shoottimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shootsec++;
				for (int i = 0; i < 5; i++) {
					if (direction == 1) { 
						bulMove(1);
						setBounds(((int) bul.getX()), (int) y, 16, 16);
						if (shootsec == 30) {
							bul.setVisible(false);
							shoottimer.stop();
						}
					} else if (direction == 2) { 
						bulMove(-1);
						setBounds(((int) bul.getX()), (int) y, 16, 16);
						if (shootsec == 30) {
							bul.setVisible(false);
							shoottimer.stop();
						}
					}
					if (mainpanel.m1.getx() == bul.getX() && mainpanel.m1.gety() <= 275
							&& mainpanel.m1.getlife() == true) {
						System.out.println("죽음");
						bul.setVisible(false);
						shoottimer.stop();
						mainpanel.m1.moledie();
					} else if (mainpanel.m2.getx() == bul.getX() && mainpanel.m2.gety() <= 275
							&& mainpanel.m2.getlife() == true) {
						System.out.println("죽음");
						bul.setVisible(false);
						shoottimer.stop();
						mainpanel.m2.moledie();
					} else if (mainpanel.m3.getx() == bul.getX() && mainpanel.m3.gety() <= 275
							&& mainpanel.m3.getlife() == true) {
						System.out.println("죽음");
						bul.setVisible(false);
						shoottimer.stop();
						mainpanel.m3.moledie();
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
					} else if (mainpanel.isSnake == true){
						if (mainpanel.snake.getX() == bul.getX() && mainpanel.snake.getmoving() == true) {
							mainpanel.snake.snakedie();
							bul.setVisible(false);
							shoottimer.stop();
						}
					}
				}
			}

		});
	}
	public void bulMove(double x) {
		this.x = this.x + x;
	}
	
	public int getX() {
		return (int)x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}
}