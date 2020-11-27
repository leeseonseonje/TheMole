package Standby;

import java.awt.Graphics;
import java.awt.Rectangle;

import Mole.Game.Entities.EntityA;
import Mole.Game.Entities.EntityB;

public class Bullet implements EntityA {

	private Game game;
	private Mole mole;
	private double x;
	private double y;
	
	private Textures texture;

	public Bullet(double x, double y, Textures texture, Game game) {
		this.x = x;
		this.y = y;
		this.texture = texture; 
		this.game = game;
	}

	public void tick() {
		if (game.buldirection)
			x += 10;
		else
			x -= 10;
		
		if(Physics.Collision(this, game.m))
		{
			mole.mole_count--;
			System.out.println("COLLISION DETECTED");
		}
	}

	public void render(Graphics g) {
		if (game.buldirection)
			g.drawImage(texture.bullet[0], (int) x, (int) y, null);
		else
			g.drawImage(texture.bullet[1], (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}
}