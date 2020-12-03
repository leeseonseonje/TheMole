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

import MoleServer.MoleClientMainHandler;
import io.netty.channel.ChannelHandlerContext;

public class HumanUI extends JPanel {
	/*public static void main(String[] args) throws IOException, InterruptedException {
		new H();
	}*/

	private BufferedImage backImage;
	private BufferedImage humanHud;
	private BufferedImage humanInv;
	private BufferedImage intHuman;
	private Human human;
	
	private vegetableThread v1;
	private vegetableThread v2;
	private vegetableThread v3;
	
	private itemBoxThread i1;
	private itemBoxThread i2;
	
	public static HumanInMole m1, m2, m3, m4, m5, m6, m7, m8, m9;
	
	private boolean humtrap = false;
	private boolean timerstop = false;
	
	public JLabel counterLabel;
	private String ddSecond, ddMinute;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private int second, minute;
	private Timer timer;
	private Font font1 = new Font("Arial", Font.BOLD, 30);
	private Font font2 = new Font("Arial", Font.BOLD, 15);
	private ChannelHandlerContext ctx;
	
	public HumanUI(ChannelHandlerContext ctx, String name, int v1Location, int v2Location, int v3Location, int crop1, int crop2, int crop3) throws IOException {
		this.ctx = ctx;
		setLayout(null);
		backImage = ImageIO.read(new File("img/Back4.png"));
		humanHud = ImageIO.read(new File("img/humanHud.png"));
		humanInv = ImageIO.read(new File("img/inventory.png"));
		intHuman = ImageIO.read(new File("img/humanint.png"));
		human = new Human(this, 200, 225, ctx, name);
		add(human);
		
		v1 = new vegetableThread(v1Location, crop1);
		v2 = new vegetableThread(v2Location, crop2);
		v3 = new vegetableThread(v3Location, crop3);
		add(v1);
		add(v2);
		add(v3);
		
		i1 = new itemBoxThread();
		i2 = new itemBoxThread();
		add(i1);
		add(i2);
		i1.setVisible(false);
		i2.setVisible(false);
		
		m1 = new HumanInMole(50, 400);
		m2 = new HumanInMole(100, 400);
		m3 = new HumanInMole(150, 400);
		m4 = new HumanInMole(50, 450);
		m5 = new HumanInMole(100, 450);
		m6 = new HumanInMole(150, 450);
		m7 = new HumanInMole(50, 500);
		m8 = new HumanInMole(100, 500);
		m9 = new HumanInMole(150, 500);
		add(m1);
		add(m2);
		add(m3);
		add(m4);
		add(m5);
		add(m6);
		add(m7);
		add(m8);
		add(m9);
		
		counterLabel = new JLabel("");
		counterLabel.setBounds(345, 0, 100, 50);
		counterLabel.setHorizontalAlignment(JLabel.CENTER);
		counterLabel.setFont(font1);

		add(counterLabel);

		counterLabel.setText("03:00");

		second = 0;
		minute = 3;
		
		normalTimer();
		timer.start();
		
	}
	public vegetableThread getV1() {
		return v1;
	}
	public vegetableThread getV2() {
		return v2;
	}
	public vegetableThread getV3() {
		return v3;
	}
	public itemBoxThread getI1() {
		return i1;
	}
	public itemBoxThread getI2() {
		return i2;
	}

	public void sethumtrap(boolean a) {
        humtrap = a;
    }
    public boolean gethumtrap() {
        return humtrap;
    }
	public boolean getTimerstop() {
		return timerstop;
	}
	public void setTimerstop(boolean timerstop) {
		this.timerstop = timerstop;
	}

	public void paintComponent(Graphics g) {// 그리는 함수
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, null);
		g.drawImage(humanHud, 0, 70, null);
		g.drawImage(humanInv, 55, 0, null);
		g.drawImage(intHuman, 0, 0, null);
		
	}
	
	public void moleMessage(String message, int x, int y) {
		if (message.equals("1"))
			m1.humanInMoleMove(message, x, y);
		else if (message.equals("2"))
			m2.humanInMoleMove(message, x, y);
		else if (message.equals("3"))
			m3.humanInMoleMove(message, x, y);
		else if (message.equals("4"))
			m4.humanInMoleMove(message, x, y);
		else if (message.equals("5"))
			m5.humanInMoleMove(message, x, y);
		else if (message.equals("6"))
			m6.humanInMoleMove(message, x, y);
		else if (message.equals("7"))
			m7.humanInMoleMove(message, x, y);
		else if (message.equals("8"))
			m8.humanInMoleMove(message, x, y);
		else if (message.equals("9"))
			m9.humanInMoleMove(message, x, y);
	}
	public void normalTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				second--;

				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);

				counterLabel.setText(ddMinute + ":" + ddSecond);
				//vegcountLabel.setText(15 - (v0.getvegcount() + v1.getvegcount() + v2.getvegcount()) + "");

				if (second == -1) {
					second = 59;
					minute--;

					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					counterLabel.setText(ddMinute + ":" + ddSecond);
				}
				if (counterLabel.getText().equals("00:00")) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "인간 승리!!", "Result", JOptionPane.PLAIN_MESSAGE);
					ctx.writeAndFlush("[HUMANWIN]," + LoginForm.getId());
					setVisible(false);
					if (MoleClientMainHandler.roomTest.testStart.getText().equals("준비취소"))
						MoleClientMainHandler.roomTest.testStart.setText("준비");
					MoleClientMainHandler.roomTest.ready.setText("");
					MoleClientMainHandler.roomTest.setVisible(true);
				}
			}
		});
	}
}

/*class H extends JFrame {
    private HumanUI humanPanel;

    public H() throws IOException {
        setTitle("Mole Game");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        humanPanel = new HumanUI();
        add(humanPanel);

        setVisible(true);
    }
}*/


