package Mole.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {
	
	private Game game;
	private double x;
	private double y;
	//public static boolean right = true;

	BufferedImage image;
	BufferedImageLoader loader = new BufferedImageLoader();

	SpriteSheet spr = null;
	
	public Bullet(double x, double y, Game game) {
		this.x = x;
		this.y = y;

		
		try {
			spr = new SpriteSheet(loader.loadImage("/bulimg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = spr.grabBulImage(1, 1, 16, 16);
	}

	public void tick() {
		if(game.buldirection)
			x += 5;
		else
			x -= 5;
	}

	public void render(Graphics g) {
		g.drawImage(image, (int) x, (int) y, null);
	}
}
