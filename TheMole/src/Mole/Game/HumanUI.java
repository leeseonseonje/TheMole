package Mole.Game;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private boolean humtrap = false;
	public HumanUI(ChannelHandlerContext ctx, String name, int v1Location, int v2Location, int v3Location, int crop1, int crop2, int crop3) throws IOException {
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
	

	public void paintComponent(Graphics g) {// 그리는 함수
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, null);
		g.drawImage(humanHud, 0, 70, null);
		g.drawImage(humanInv, 55, 0, null);
		g.drawImage(intHuman, 0, 0, null);
		
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
