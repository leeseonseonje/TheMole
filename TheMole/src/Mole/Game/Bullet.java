package Mole.Game;

import java.awt.Image;

public class Bullet extends Sprite { // ShotSprite

	private Game game;

	public Bullet(Game game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		// dy = -3;
	}

	@Override
	public void move() {
		if(((dx<0) && (x<10)) || ((dx>0) && (x>800))) {
			dx = -dx;
		}
		if (x > +100 || x < -100) {
			game.removeSprite(this);
		}
	}

	@Override
	public void handleCollision(Sprite other) {

		if (other instanceof Mole) {
			game.removeSprite(this);
			game.removeSprite(other);
		}
	}

}
