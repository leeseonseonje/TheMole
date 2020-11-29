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

	private double x;
	private double y;
	private boolean timerstop = false;
	private static int status = 0; // 1은 오른쪽, 2는 왼쪽,
	

	private boolean shooting = false;

	private Timer mover;
	private boolean moving = false;
	public int humsecond = 0;
	
	private ImageIcon human[] = {  new ImageIcon("img/humanResource/human1.png"),
			new ImageIcon("img/humanResource/human2.png"), new ImageIcon("img/humanResource/human3.png"),
			new ImageIcon("img/humanResource/human4.png"), new ImageIcon("img/humanResource/human5.png"),
			new ImageIcon("img/humanResource/human6.png"), new ImageIcon("img/humanResource/human7.png"),
			new ImageIcon("img/humanResource/human8.png"), new ImageIcon("img/humanResource/human9.png"),
			new ImageIcon("img/humanResource/human10.png") };

	public Human(MolePanel pan,double x, double y) {
		this.x = (double)x;
		this.y = (double)y;
		setBounds((int) x, (int) y, 50, 64);
		setIcon(human[0]);
		pan.setFocusable(true);
		pan.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 왼쪽 방향키
					moving = true;
					status = 2;
					setX(-5);
					if(timerstop==false) {
						humsecond = 0;
						timerstop=true;
						timerstart();
					}
					setBounds(getX(),(int) y,50,64);		
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 오른쪽 방향키
					moving = true;
					status = 1;
					setX(5);
					if(timerstop==false) {
						humsecond = 0;
						timerstop=true;
						timerstart();
					}
					setBounds(getX(),(int) y,50,64);
				}
				if (e.getKeyCode() == KeyEvent.VK_A && shooting == false) {
					shooting = true;
					status = 2;
					pan.add(new Bullet(getX(),getY(),status,pan));
				}
				if (e.getKeyCode() == KeyEvent.VK_D && shooting == false) {
					shooting = true;
					status = 1;
					pan.add(new Bullet(getX(),getY(),status,pan));
				}
			}
			public void timerstart() {
				mover();
				mover.start();
			}

			public void keyReleased(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 왼쪽 방향키
					moving = false;
					setIcon(human[5]);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 오른쪽 방향키
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

	public void mover() {
		mover = new Timer(100,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				humsecond++;
				humsecond = humsecond%5;
				System.out.println(humsecond);
				System.out.println(moving+"  "+ status);
				if(moving == true && status == 1 ) { // 오른쪽방향으로 움직일때 -누름
					System.out.println("오른쪽이동중");
					if(humsecond == 1)
						setIcon(human[1]);
					else if (humsecond == 2)
						setIcon(human[2]);
					else if (humsecond == 3)
						setIcon(human[3]);
					else if (humsecond == 4)
						setIcon(human[4]);
				}
				if(moving == true && status == 2) { // 왼쪽방향으로 움직일때 -누름
					if(humsecond == 1)
						setIcon(human[6]);
					else if (humsecond == 2)
						setIcon(human[7]);
					else if (humsecond == 3)
						setIcon(human[8]);
					else if (humsecond == 4)
						setIcon(human[9]);
				}
				if(moving == false) {
					mover.stop();
					timerstop = false;
				}
			}
		});
	}
	
	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public void setX(double x) {
		this.x = this.x + x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public int getStatus() {
		return status;
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 50, 64);
	}
}