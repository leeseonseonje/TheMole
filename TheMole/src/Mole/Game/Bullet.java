package Mole.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {

	private Game game;
	private double x;
	private double y;
	// public static boolean right = true;

	BufferedImage image;
	BufferedImage image2;
	BufferedImageLoader loader = new BufferedImageLoader();

	SpriteSheet spr = null;

	public Bullet(double x, double y, Game game) {
		this.x = x;
		this.y = y;

		try {
			spr = new SpriteSheet(loader.loadImage("/bulimg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = spr.grabBulImage(1, 1, 16, 16);
		image2 = spr.grabBulImage(2, 1, 16, 16);
	}

	public void tick() {
		if (game.buldirection)
			x += 5;
		else
			x -= 5;
	}

	public void render(Graphics g) {
		if (game.buldirection)
			g.drawImage(image, (int) x, (int) y, null);
		else
			g.drawImage(image2, (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}
}
