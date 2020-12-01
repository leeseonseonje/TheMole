package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Human extends JLabel{

	private int x;
	private int y;
	private int velX = 5;
	private double velY = 0;
	private static int status = 0; // 1은 오른쪽, 2는 왼쪽,

	private boolean shooting = false;

	private Timer mover;
	private boolean moving = false;

	private ImageIcon human[] = { new ImageIcon("img/humanResource/human1.png"),
			new ImageIcon("img/humanResource/human2.png"), new ImageIcon("img/humanResource/human3.png"),
			new ImageIcon("img/humanResource/human4.png"), new ImageIcon("img/humanResource/human5.png"),
			new ImageIcon("img/humanResource/human6.png"), new ImageIcon("img/humanResource/human7.png"),
			new ImageIcon("img/humanResource/human8.png"), new ImageIcon("img/humanResource/human9.png"),
			new ImageIcon("img/humanResource/human10.png"), };

	public Human(MolePanel pan,int x, int y) {
		this.x = x;
		System.out.println(x);	
		this.y = y;
		setBounds((int) x, (int) y, 50, 64);
		setIcon(human[0]);
		pan.setFocusable(true);
		pan.addKeyListener(new KeyListener() {

			//private int x = this.x;

			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 왼쪽 방향키
					moving = true;
					status = 2;
					//mover.start();
					setX(x-getVelX());				
					setBounds(getX(),y,50,64);					
					System.out.println(x);

				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 오른쪽 방향키
					moving = true;
					status = 1;
					//mover.start();
					setBounds(x+getVelX(),y,50,64);
				}
				if (e.getKeyCode() == KeyEvent.VK_A && shooting == false) {
					shooting = true;
					System.out.println("왼쪽 총알");
				}
				if (e.getKeyCode() == KeyEvent.VK_D && shooting == false) {
					shooting = true;
					System.out.println("오른쪽 총알");
				}
			}

			public void keyReleased(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 왼쪽 방향키
					setVelX(0);
					moving = false;
					setIcon(human[5]);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 오른쪽 방향키
					setVelX(0);
					moving = false;
					setIcon(human[0]);
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					shooting = false;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					shooting = false;
				}
			}

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

		});
	}
/*
	public void timer() {
		mover = new Timer(3000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(moving == true && status == 1) { // 오른쪽방향으로 움직일때 -누름
					for(int i= 1; i<=4; i++)
						setIcon(human[i]);
				}
				if(moving == true && status == 2) { // 왼쪽방향으로 움직일때 -누름
					for(int i= 6; i<=9; i++)
						setIcon(human[i]);
				}
				
				mover.stop();
			}
		});
	}
	*/
	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public int getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public int getStatus() {
		return status;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 64);
	}
}