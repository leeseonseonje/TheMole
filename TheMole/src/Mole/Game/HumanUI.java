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
	private ImageIcon human = new ImageIcon("img/human.png");
	private JButton humanCharacter;

	public HumanUI(ChannelHandlerContext ctx, String name) throws IOException {
		setLayout(null);
		backImage = ImageIO.read(new File("img/Back4.png"));
		add(new Human(this, 200, 225, ctx, name));
	}

	

	public void paintComponent(Graphics g) {// 그리는 함수
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, null);
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
