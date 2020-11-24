package Mole.Game;

import java.awt.Graphics;
import java.awt.Rectangle;

import Mole.Game.Entities.EntityD;
import Mole.Game.libs.Animation;

public class Vegetable implements EntityD {

	private double x,y;
	private boolean selected; // 해당 두더지가 선택되었을때 true로 전환, 기본 생성은 false로

	private Textures texture;
	private Game game;
	private Animation anim;
	private Controller controller;
	private Graphics g;
	
	private int random;

	public Vegetable(int x, int y, Textures texture, Controller controller, Game game) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.controller = controller;
		this.game = game;
		
		random = (int)(Math.random() * 11);
		
	}

	public void tick() {
		
		if (x <= 0)
			x = 0;
		if (x >= 800 - 50)
			x = 800 - 50;
	}

	public void render(Graphics g) {
		g.drawImage(texture.vegetable[random], (int) x, (int) y, null); // x와 y는 double이기에 (int)로 변환 해주어야 한다.
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return x;
	}
	public void getY(double y) {
		this.y = y;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}