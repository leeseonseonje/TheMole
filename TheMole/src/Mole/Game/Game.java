package Mole.Game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = (int) (WIDTH / 12 * 9);
	public static final int SCALE = 1;
	public final String TITLE = "Mole Game";
	
	public static boolean buldirection = true;

	private boolean running = false;
	private Thread thread;

	private BufferedImage background = null;
	private BufferedImage spriteSheet = null;

	private Player humanP;
	private Controller c;

	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/humanSpr.png");
			background = loader.loadImage("/Backgrounds.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		addKeyListener(new KeyInput(this));

		humanP = new Player(200, 225, this);
		c = new Controller(this);

	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join(); // 모든 스레드가 끝날때까지 기다렸다가 다음(System.exit(1))으로 실행
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() { // Runnable의 기본으로 구현된 메소드
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) { // Game Loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now; // OldTime = CurTime,

			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps - " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		humanP.tick();
		c.tick();
	}

	private void render() { // bufferstrategy는 장면 뒤의 모든 버퍼링을 관리한다.

		BufferStrategy bs = this.getBufferStrategy(); // this는 Canvas 객체를 가리킴

		if (bs == null) {
			createBufferStrategy(3); // 3개의 버퍼를 생성한다는 뜻, 바로 필요한것(사용하는중) 뒤에 두개의 이미지를 로딩하고 있을 것이다.
										// 30개의 버퍼를 생성한다면, 그만큼 CPU의 자원을 잡아먹게 된다. 적절한 갯수로 사용,운용
			return;
		}
		Graphics g = bs.getDrawGraphics(); // graphic을 여기에 가져오고, 버퍼를 그리기 위한 graphics context를 만든다, 외부 버퍼를 그림
		/////////////// 그림 그리는 공간 으로 추정//////////////////////////

		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

		humanP.render(g); // 인간 그리기
		c.render(g);

		///////////////////////////////////////////////////////////
		g.dispose(); // 계속 루프를 하는데 dispose로 지워주지 않는다면..?
		bs.show();
	}

	public void keyPressed(KeyEvent e) { // Key와 key 주의
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			humanP.setVelX(3);
		} else if (key == KeyEvent.VK_LEFT) {
			humanP.setVelX(-3);
		} else if (key == KeyEvent.VK_A) {
			this.buldirection = false;
			c.addBullet(new Bullet(humanP.getX(),humanP.getY()+35,this));
		} else if (key == KeyEvent.VK_D) {
			this.buldirection = true;
			c.addBullet(new Bullet(humanP.getX()+50,humanP.getY()+35,this));
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			humanP.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			humanP.setVelX(0);
		}
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getSpriteSheet() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기
		return spriteSheet;
	}
}