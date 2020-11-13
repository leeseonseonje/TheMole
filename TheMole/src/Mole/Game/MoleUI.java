package Mole.Game;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MoleUII extends JFrame {
    private MolePanel molePanel;

    public MoleUII() throws IOException, InterruptedException {
        setTitle("Mole Game");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        molePanel = new MolePanel();

        add(molePanel);
        setVisible(true);
    }
}

    class MolePanel extends JPanel{
        private BufferedImage backImage;
        private ImageIcon human = new ImageIcon("img/human.png");
        private JButton humanButton;

        public MolePanel() {
            try {
                setLayout(null);
                backImage = ImageIO.read(new File("img/map.png"));

                humanButton = new JButton(human);
                humanButton.setBorderPainted(false);
                humanButton.setFocusPainted(false);
                humanButton.setContentAreaFilled(false);
                humanButton.setBounds(300, 192, 70, 70);
                add(humanButton);

                new MoleThread(50, 400);
                new MoleThread(100, 400);
                new MoleThread(150, 400);
                new MoleThread(50, 450);
                new MoleThread(100, 450);
                new MoleThread(150, 450);
                new MoleThread(50, 500);
                new MoleThread(100, 500);
                new MoleThread(150, 500);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        class MoleThread extends Thread {
            private int x, y;
            private JButton moleButton;
            private Rectangle champion;
            private ImageIcon mole = new ImageIcon("img/moleimg.png");
            private ImageIcon moleSelect = new ImageIcon("img/moleSelect.png");

            private Timer timer;
            private double speed = 0.3;
            private Long startTime;

            private double targetX, targetY;
            private double startX, startY;
            private double runTime;

            public MoleThread(int x, int y) {
                this.x = x;
                this.y = y;

                champion = new Rectangle(x, y, 10, 10);

                moleButton = new JButton(mole);
                moleButton.setBorderPainted(false);
                moleButton.setFocusPainted(false);
                moleButton.setContentAreaFilled(false);
                moleButton.setBounds(x, y, 30, 30);
                add(moleButton);

                moleButton.addActionListener( e -> {
                    if (e.getSource() == moleButton) {
                        moleButton.setIcon(moleSelect);
                        addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if (e.getButton() == MouseEvent.BUTTON3) {
                                    if(moleButton.getIcon().equals(moleSelect)) {
                                        timer.stop();
                                        calculateChampionMovement(e.getX(), e.getY(), champion);

                                        startTime = System.currentTimeMillis();
                                        timer.start();
                                    }
                                }
                                if (e.getButton() == MouseEvent.BUTTON1) {
                                    if(moleButton.getIcon().equals(moleSelect))
                                        moleButton.setIcon(mole);
                                }
                            }	
                        });
                    }
                });
                timer = new Timer(10, e -> {
                    TimeMove();
                });
               }
              public void run() {

            }
            public void TimeMove() {
                long duration = System.currentTimeMillis() - startTime;
                double progress = duration / runTime;

                if (progress >= 1.0) {
                    progress = 1.0;
                    timer.stop();
                }

                double x = (int) (startX + ((targetX - startX) * progress));
                double y = (int) (startY + ((targetY - startY) * progress));

                repaint();
                if(y >= 296 && x >= 12 && x <= 770) {
                    moleButton.setBounds((int) x - 15, (int) y - 15, 30, 30);
                    champion.setRect(x - 5, y - 5, 10, 10);
                }
            }

            public void calculateChampionMovement(double x, double y, Rectangle champion) {

                if (x != champion.getCenterX() || y != champion.getCenterY() ) {

                    targetX = x;
                    targetY = y;

                    startX = champion.getCenterX();
                    startY = champion.getCenterY();
                    double distance = Math.sqrt(
                            (startX - targetX) * (startX - targetX)
                                    + (startY - targetY) * (startY - targetY));

                    runTime = distance / (double)speed;
                }
            }
        }
        public void paintComponent (Graphics g){// 그리는 함수
            super.paintComponent(g);
            g.drawImage(backImage, 0, 0, null);
        }
    }

public class MoleUI {
    public static void main(String[] args) throws IOException, InterruptedException {
        new MoleUII();
    }
}
