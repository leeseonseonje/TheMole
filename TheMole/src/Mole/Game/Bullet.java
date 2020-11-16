package Mole.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {

	private Game game;
	private double x;
	private double y;
	
	private Textures texture;

	SpriteSheet spr = null;

	public Bullet(double x, double y, Textures texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
	}

	public void tick() {
		if (game.buldirection)
			x += 5;
		else
			x -= 5;
	}

	public void render(Graphics g) {
		if (game.buldirection)
			g.drawImage(texture.bulletR, (int) x, (int) y, null);
		else
			g.drawImage(texture.bulletL, (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}
}
