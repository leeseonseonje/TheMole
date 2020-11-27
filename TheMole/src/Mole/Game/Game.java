package Mole.Game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Game extends Canvas implements Runnable { // 다른 클래스,자바파일에서 new 키워드로 Game을 생성하지 말 것.

	private static final long serialVersionUID = 1L;
	// 프레임 설정 정보
	public static final int WIDTH = 800;
	public static final int HEIGHT = (int) (WIDTH / 12 * 9);
	public static final int SCALE = 1;
	public final String TITLE = "Mole Game";

	public static int BULLETCOUNT = 5;
	private boolean is_shooting = false; // 총알 발사버튼을 꾹눌러서 줄줄이 나오는거 방지
	public static boolean buldirection = true; // 총알방향, true는 오른쪽, false는 왼쪽
	
	private static String countdown = "";
	
	private static int vegCount = 0;
	
	// 3분의 카운트다운-> Frame에 들어감.
	private static JLabel count;
	private static Font fonts = new Font("Arial", Font.BOLD, 30);
	private static Timer timer;
	private static int second, minute;
	private static String ddSecond, ddMinute;
	private static DecimalFormat dFormat = new DecimalFormat("00");

	private boolean running = false; // 게임의 실행여부
	private Thread thread;

	private BufferedImage background = null; // 배경출력하는 버퍼이미지
	private BufferedImage humSpriteSheet = null; // 인간출력하는 버퍼이미지
	private BufferedImage bulSpriteSheet = null; // 총알출력하는 버퍼이미지
	private BufferedImage molSpriteSheet = null; // 두더지출력하는 버퍼이미지
	private BufferedImage snaSpriteSheet = null; // 두더지출력하는 버퍼이미지
	private BufferedImage vegSpriteSheet = null; // 두더지출력하는 버퍼이미지

	// 캐릭터 생성
	private Human human;	
	private Mole mole;
	
	// 기타요소 
	private Controller c; // 컨트롤러
	private Snake snake;
	private Vegetable vegetable;
	
	private Textures texture;
	private Hud hud;
	
	public LinkedList<Bullet> b;
	public LinkedList<Mole> m;
	public LinkedList<Vegetable> v;

	private enum STATE {
		MENU,
		GAME
	};
	
	private STATE State = STATE.MENU;
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			humSpriteSheet = loader.loadImage("/humanSpr.png");
			bulSpriteSheet = loader.loadImage("/bulimg.png");
			molSpriteSheet = loader.loadImage("/moleSpr.png");
			snaSpriteSheet = loader.loadImage("/snakeSpr.png");
			vegSpriteSheet = loader.loadImage("/vegetables.png");
			background = loader.loadImage("/Backgrounds.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		texture = new Textures(this); // 생성전에 텍스처를 생성
		human = new Human(200, 225, texture,this);
		snake = new Snake(texture,this);
		
		c = new Controller(this, texture);
		hud = new Hud(this);
		
		
		b = c.getBullet(); // 메소드를 부르기전에 Controller를 초기화 해주어야한다.
		m = c.getMole();
		v = c.getVegetable();
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		c.addMole(mole.mole_count);
		c.addVegetable(3);
	}

	public synchronized void start() { // 시작하는 함수 - start, 게임이 돌아가고있는지 확인하고 안돌아가고있으면, 스레드를 생성과 동시에 스레드.start로 run함수를 실행한다.
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
		
		init(); // 초기설정 하는 메소드; 객체 생성요소가 다 들어있음
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; // 델타타임, 게임에서의 시간 계산
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

	private void tick() { // 진드기? 시간측정단위로 구체적으로 게임에서 반복되는 동작의 단일 인스턴스 또는 동작이 소비하는 기간을 나타냅니다.
		human.tick();
		c.tick();
		snake.tick();
	}

	private void render() { // render - 실제 게임의 화면을 그려주는 함수, bufferstrategy는 장면 뒤의 모든 버퍼링을 관리한다.

		BufferStrategy bs = this.getBufferStrategy(); // this는 Canvas 객체를 가리킴

		if (bs == null) {
			createBufferStrategy(3); // 3개의 버퍼를 생성한다는 뜻, 바로 필요한것(사용하는중) 뒤에 두개의 이미지를 로딩하고 있을 것이다.
										// 30개의 버퍼를 생성한다면, 그만큼 CPU의 자원을 잡아먹게 된다. 적절한 갯수로 사용,운용
			return;
		}
		Graphics g = bs.getDrawGraphics(); // graphic을 여기에 가져오고, 버퍼를 그리기 위한 graphics context를 만든다, 외부 버퍼를 그림
		/////////////// 그림 그리는 공간 으로 추정//////////////////////////
		
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		countDownRender(g, countdown);
		lifCountRender(g,String.format("%d",human.getLife()));
		bulCountRender(g,String.format("%d",BULLETCOUNT));
		vegCountRender(g,String.format("%d",vegCount));
		molCountRender(g,String.format("%d",mole.mole_count));
		
		c.render(g); // c.render에 mole, bullet, vegetable이 들어있음
		human.render(g); // 인간 그리기
		
		// moleP.render(g);
		snake.render(g);
		hud.render(g);

		///////////////////////////////////////////////////////////
		g.dispose(); // 계속 루프를 하는데 dispose로 지워주지 않는다면..?
		bs.show();
	}

	public void keyPressed(KeyEvent e) { // Key와 key 주의
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			 // 오른쪽 방향키
			human.setVelX(3);
			human.rightMove();
		} else if (key == KeyEvent.VK_LEFT) {
			// 왼쪽 방향키
			human.setVelX(-3);
			human.leftMove();
		} else if (key == KeyEvent.VK_A && (human.getStatus() == 1 || human.getStatus() == 3) && (BULLETCOUNT > 0) && !is_shooting) {
			// 왼쪽을 보고있는 상태에서 A키
			this.buldirection = false;
			is_shooting = true;
			c.addEntity(new Bullet(human.getX(), human.getY() + 35, texture, this));
			--BULLETCOUNT;
		} else if (key == KeyEvent.VK_D && (human.getStatus() == 0 || human.getStatus() == 2) && (BULLETCOUNT > 0) && !is_shooting) { 
			// 오른쪽을 보고있는 상태에서 D키
			this.buldirection = true;
			is_shooting = true;
			c.addEntity(new Bullet(human.getX() + 50, human.getY() + 35, texture, this));
			--BULLETCOUNT;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			human.setVelX(0);
			human.rightStand();
		} else if (key == KeyEvent.VK_LEFT) {
			human.setVelX(0);
			human.leftStand();
		} else if (key == KeyEvent.VK_A) {
			is_shooting = false;
		} else if (key == KeyEvent.VK_D) {
			is_shooting = false;
		}
	}
	
	
	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.setLayout(new BorderLayout());
		
		count = new JLabel("");
		count.setText("03:00"); // 시간 설정 : 3:00
		second = 0;
		minute = 3;
		normalTimer(game,count);
		timer.start();

		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		game.start();
	}
	public static void normalTimer(Game game, JLabel label) {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				second--;

				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);

				label.setText(ddMinute + ":" + ddSecond);

				if (second == -1) {
					second = 59;
					minute--;

					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					label.setText(ddMinute + ":" + ddSecond);
				}
				countdown = label.getText();
				if (minute == 0 && second == 0) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "인간 승리!!", "Result", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
	}
	public void countDownRender(Graphics g,String countdown) {
		g.setFont(fonts);
		g.drawString(countdown, 380, 25);
		g.setFont(getFont()); // 기본값 설정
	}
	public void lifCountRender(Graphics g,String value) {
		g.drawString(value, 28, 85);
	}
	public void bulCountRender(Graphics g,String value) {
		g.drawString(value, 28, 105);
	}
	public void vegCountRender(Graphics g,String value) {
		g.drawString(value, 768, 105);
	}
	public void molCountRender(Graphics g,String value) {
		g.drawString(value, 768, 85);
	}
	
	
	public BufferedImage getHumSpriteSheet() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기
		return humSpriteSheet;
	}

	public BufferedImage getBulSpriteSheet() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기
		return bulSpriteSheet;
	}

	public BufferedImage getMolSpriteSheet() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기
		return molSpriteSheet;
	}
	public BufferedImage getSnaSpriteSheet() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기
		return snaSpriteSheet;
	}
	public BufferedImage getVegSpriteSheet() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기
		return vegSpriteSheet;
	}

	public Human getPlayer() { // Game 클래스의 내부 메소드 - spriteSheet를 가져오기, Controller 클래스에서 사용
		return human;
	}
}