package Mole.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class HumanTest extends JFrame {
    private HumanPanel humanPanel;

    public HumanTest() throws IOException {
        setTitle("Mole Game");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        humanPanel = new HumanPanel();
        add(humanPanel);

        setVisible(true);
    }

    class HumanPanel extends JPanel {
        private BufferedImage backImage;
        private ImageIcon humanStop = new ImageIcon("img/human3.png");
        private ImageIcon humanLeft = new ImageIcon("img/human.png");
        private ImageIcon humanRight = new ImageIcon("img/human2.png");
        private JButton humanCharacter;
        public HumanPanel() throws IOException {
            setLayout(null);
            backImage = ImageIO.read(new File("img/map.png"));

            humanCharacter = new JButton(humanStop);
            humanCharacter.setBorderPainted(false);
            humanCharacter.setFocusPainted(false);
            humanCharacter.setContentAreaFilled(false);
            humanCharacter.setBounds(300,192, 70, 70);
            add(humanCharacter);

            humanCharacter.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_A && humanCharacter.getX() >= -10) {
                    	humanCharacter.setIcon(humanLeft);	
                        humanCharacter.setBounds(humanCharacter.getX()-10, 192, 70, 70);
                    }
                    if(e.getKeyCode() == KeyEvent.VK_D && humanCharacter.getX() <= 720) {
                    	humanCharacter.setIcon(humanRight);
                    	humanCharacter.setBounds(humanCharacter.getX()+10, 192, 70, 70);
                    }
                }
                public void keyReleased(KeyEvent e) {
                	humanCharacter.setIcon(humanStop);
                }
            });
        }

        public void paintComponent(Graphics g) {// 그리는 함수
            super.paintComponent(g);
            g.drawImage(backImage, 0, 0, null);
        }
    }
}

public class HumanUI {
    public static void main(String[] args) throws IOException {
        new HumanTest();
    }
}
