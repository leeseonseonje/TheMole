package Mole.Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	private Image image;

	// 생성자
	public Sprite(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}

	// 스프라이트의 가로 길이를 반환
	public int getWidth() {
		return image.getWidth(null);
	}

	// 스프라이트의 세로 길이를 반환
	public int getHeight() {
		return image.getHeight(null);
	}

	// 스프라이트를 화면에 그린다.
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	// 스프라이트를 움직인다.
	public void move() {
		x += dx;
		y += dy;
	}

	// dx를 설정
	public void setDx(int dx) {
		this.dx = dx;
	}

	// dy를 설정
	public void setDy(int dy) {
		this.dy = dy;
	}

	// dx를 반환
	public int getDx() {
		return dx;
	}

	// dy를 반환
	public int getDy() {
		return dy;
	}

	// x를 반환
	public int getX() {
		return x;
	}

	// y를 반환
	public int getY() {
		return y;
	}

	// 다른 스프라이트와의 충돌 여부를 계산, 충돌이면 true 반환
	public boolean checkCollision(Sprite other) {
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		myRect.setBounds(x, y, getWidth(), getHeight());
		otherRect.setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());

		return myRect.intersects(otherRect);
	}
	
	// 충돌을 처리한다.
	public void handleCollision(Sprite other) {
		
	}
}
