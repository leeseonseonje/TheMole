package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Bullet extends JLabel {

	private double x;
	private double y = 235;
	private int direction = 0;

	private Human hum;
	private Timer shoottimer;
	private int shootsec = 0;
	MolePanel mainpanel;

	private ImageIcon bulletR = new ImageIcon("img/bulletR.png");
	private ImageIcon bulletL = new ImageIcon("img/bulletL.png");

	public Bullet(double x, int direction, MolePanel pan) {
		mainpanel = pan;
		if (direction == 1) {
			this.x = x + 50;
		} else {
			this.x = x;
		}
		System.out.println(y);
		this.direction = direction;

		if (direction == 1) { // 坷弗率
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletR);
		} else if (direction == 2) { // 哭率
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletL);
		}
		pan.add(this);
		this.setVisible(true);
		shooting(this);
		shoottimer.start();
		/*
		 * class bulThread extends Thread { public void run() { while(true) { try {
		 * Thread.sleep(50); } catch (Exception e) { e.printStackTrace(); } } } } Thread
		 * t = new bulThread(); t.start();
		 */
	}

	public void shooting(JLabel bul) {
		shoottimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shootsec++;
				if (direction == 1) { // 坷弗率
					bulMove(5);
					setBounds(((int)bul.getX()), (int)y, 50, 64);
					if (shootsec == 40) {
						bul.setVisible(false);
					}
				}
				else if (direction == 2) { // 哭率
						bulMove(-5);
						setBounds(((int)bul.getX()), (int)y, 50, 64);
						if (shootsec == 40) {
							bul.setVisible(false);
						}
					}
				
				if(mainpanel.m1.getx() == bul.getX()) {
					
				}
			}
			
		});
	}

	public void bulMove(double x) {
		this.x = this.x + x;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}
}
