package Mole.Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel {
	
	private double x;
	private double y;
	private int direction = 0;
	private boolean launched = false;
	
	private ImageIcon bulletR = new ImageIcon("img/bulletResource/bulletR.png");
	private ImageIcon bulletL = new ImageIcon("img/bulletResource/bulletL.png");
	
	
	public Bullet(int x, int y,int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		setBounds((int) x, (int) y, 50, 64);
		if(direction == 1)
			setIcon(bulletR);
		else if(direction == 2)
			setIcon(bulletL);
	}
	
	public void update() {
		if(launched == true && direction == 1) // ¿À¸¥ÂÊ
			x += 5;
		if(launched == true && direction == 2) // ¿ÞÂÊ
			x -= 5;
			
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
}
