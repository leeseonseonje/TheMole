package Mole.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, MouseListener {

	private boolean running = true;
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private Sprite human;
	private Sprite Mole;
	private Bullet bullet;

	private BufferedImage moleImage;
	private BufferedImage humanImage;
	private BufferedImage bulletImage;

	// private Thread thread;

	public Game() {
		new Window(800, 600, "Mole Game!", this);

		try {
			moleImage = ImageIO.read(new File("img/moleimg.png"));
			humanImage = ImageIO.read(new File("img/humanimg.png"));
			bulletImage = ImageIO.read(new File("img/bulletimg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.requestFocus();
		this.initSprites();
		addKeyListener(this);
	}

	private void initSprites() {
		human = new Human(this, humanImage, 80, 100);
		sprites.add(human);
		for (int i = 0; i < 8; i++) {
			Sprite mole = new Mole(this, moleImage, 200 + (50 * i), 400);
			sprites.add(mole);
			//sprites.add(new Mole(this, moleImage, 200 + (50 * i), 400));
		}
	}
	
	private void startGame() {
		sprites.clear();
		initSprites();
	}
	
	public void endGame() {
		// System.exit(0);
	}
	
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	public void fire() {
		Bullet shot = new Bullet(this, bulletImage, human.getX()+5, human.getY());
		sprites.add(shot); // bullet ÀÌ¹ÌÁö
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		for(int i = 0; i<sprites.size(); i++) {
			Sprite sprite = (Sprite) sprites.get(i);
			sprite.draw(g);
		}
	}
	
	public void gameLoop() {
		while(running) {
			for(int i = 0; i<sprites.size(); i++) {
				Sprite sprite = (Sprite) sprites.get(i);
				sprite.move();
			}
		}
		
		for(int p = 0; p < sprites.size(); p++) {
			for(int s = p + 1; s < sprites.size(); s++) {
				Sprite me = (Sprite) sprites.get(p);
				Sprite other = (Sprite) sprites.get(s);
				
				if(me.checkCollision(other)) {
					me.handleCollision(other);
					other.handleCollision(me);
				}
			}
		}
		
		repaint();
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			
		}
	}
	/*
	 * public synchronized void start() {
	 * 
	 * }
	 * 
	 * public void run() {
	 * 
	 * }
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			human.setDx(-3);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			human.setDx(+3);
		if(e.getKeyCode() == KeyEvent.VK_A)
			bullet.setDx(-5);
		if(e.getKeyCode() == KeyEvent.VK_D)
			bullet.setDx(+5);
		
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			human.setDx(0);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			human.setDx(0);
	}
	public void keyTyped(KeyEvent e) {	}
	
	
	public static void main(String args[]) {
		Game g = new Game();
		g.gameLoop();
	}
}
