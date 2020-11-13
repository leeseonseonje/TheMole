package Mole.Game;

import java.awt.Image;

public class Mole extends Sprite {

	private Game game;

	public Mole(Game game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = 0;
		dy = 0;
	}

	@Override
	public void move() {
		if ((dx < 0) && (x < 10)) {
			return;
		}
		if ((dx > 0) && (x > 800)) {
			return;
		}
		super.move();
	}

	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Human)
			game.endGame();
	}

}
