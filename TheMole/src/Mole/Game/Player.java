package Mole.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	
	private double x;
	private double y; 
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures texture;
	
	public Player(double x, double y, Textures texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
	}

	public void tick() { // 메소드를 업데이트 할때 사용
		x += velX;
		y += velY;
		
		if(x <= 0)
			x = 0;
		if(x >= 800 - 50)
			x = 800 - 50;
		/*
		if(y <= 0)
			y = 0;
		if(y >= 600 - 64)
			y = 600 - 64;
		*/
	}
	
	public void render(Graphics g) { // 이미지 그릴때 사용
		g.drawImage(texture.human, (int)x, (int)y, null);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public boolean rightStand() {
		texture.human = texture.humanSpr.grabHumImage(1, 1, 50, 64);
			return true;
	}
	
	public boolean leftStand() {
		texture.human = texture.humanSpr.grabHumImage(1, 2, 50, 64);
		return true;
}
	
	public boolean rightMove() {
		for(int i=2; i<=4; i++)
			texture.human = texture.humanSpr.grabHumImage(i, 1, 50, 64);
		return true;
	}
	
	public boolean leftMove() {
		for(int i=2; i<=4; i++)
			texture.human = texture.humanSpr.grabHumImage(i, 2, 50, 64);
		return true;
	}
}
