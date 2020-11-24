package Mole.Game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	// default 32 * 32
	public BufferedImage grabImage(int col, int row, int width, int height) { // 디폴트? 같은 느낌

		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);

		return img;
	}

	// 16 * 16 스프라이트
	public BufferedImage grabBulImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 16) - 16, (row * 16) - 16, width, height);

		return img;
	}

	// 32 * 32 스프라이트
	public BufferedImage grabMolImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);

		return img;
	}

	public BufferedImage grabSnaImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);

		return img;
	}

	public BufferedImage grabVegImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);

		return img;
	}

	// 50 * 64 스프라이트
	public BufferedImage grabHumImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 50) - 50, (row * 64) - 64, width, height);

		return img;
	}

}
