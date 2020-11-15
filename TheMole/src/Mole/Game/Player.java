package Mole.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	
	private double x;
	private double y; 
	
	private BufferedImage player;
	
	public Player(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		
		SpriteSheet spr1 = new SpriteSheet(game.getSpriteSheet());
		
		player = spr1.grabHumImage(1, 1, 50, 64);
	}
	
	public void tick() { // 메소드를 업데이트 할때 사용
		
	}
	
	public void render(Graphics g) { // 이미지 그릴때 사용
		g.drawImage(player, (int)x, (int)y, null);
	}
}
