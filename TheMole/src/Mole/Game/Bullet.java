package Mole.Game;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel {
	
	private double x;
	private double y;
	private int direction = 0;

	private Human hum;
	
	private ImageIcon bulletR = new ImageIcon("img/bulletResource/bulletR.png");
	private ImageIcon bulletL = new ImageIcon("img/bulletResource/bulletL.png");
	
	
	public Bullet(double x, double y,int direction,MolePanel pan) {
		if(direction == 1) {
			this.x = x + 50;
			this.y = y + 35;
		}
		else {
			this.x = x;
			this.y += 35;
		}
		this.direction = direction;
		
		
		if(direction == 1) { // 坷弗率
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletR);
		} else if(direction == 2) { // 哭率
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletL);
		}
		class bulThread extends Thread {
			public void run() {
				while(true) {
					update();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		Thread t = new bulThread();
		t.start();
	}
	
	public void update() {
		if(direction == 1) { // 坷弗率
			bulMove(5);
			setBounds(getX(),(int) y,50,64);
			if( this.getX() - hum.getX() > 150) {
				this.setVisible(false);
			}
		}
		if(direction == 2) { // 哭率
			bulMove(-5);
			setBounds(getX(),(int) y,50,64);
		}
		if( hum.getX() - this.getX() > 100) {
			this.setVisible(false);
		}	
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
