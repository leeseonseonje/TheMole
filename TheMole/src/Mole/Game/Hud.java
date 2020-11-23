package Mole.Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Hud {

	private Game game;
	private BufferedImage humanHud, moleHud, humanInv, moleInv, intHuman, intMole;

	public Hud(Game game) {
		this.game = game;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			humanHud = loader.loadImage("/humanhud.png");
			moleHud = loader.loadImage("/molehud.png");
			humanInv = loader.loadImage("/inventory.png");
			moleInv = loader.loadImage("/inventory.png");
			intHuman = loader.loadImage("/humanint.png");
			intMole = loader.loadImage("/moleint.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void render(Graphics g) { // 이미지 그릴때 사용
		g.drawImage(humanHud, 0, 70, null);
		g.drawImage(moleHud, 720, 70, null);
		g.drawImage(humanInv, 52, 0, null);
		g.drawImage(moleInv, 660, 0, null);
		g.drawImage(intHuman, 0, 0, null);
		g.drawImage(intMole, 750, 0, null);
	}
}