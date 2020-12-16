package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import io.netty.channel.ChannelHandlerContext;

public class Bullet extends JLabel {
	
	private int x;
	private int y = 255;
	private int direction = 0;

	private Human hum;
	private Timer shoottimer;
	private Timer dshoottimer;
	private int shootsec = 0;
	private int dshootsec = 0;
	private HumanUI mainpanel;
	
	private String name;
	private ChannelHandlerContext ctx;
	
	private ImageIcon bulletR = new ImageIcon("img/bulletR.png");
	private ImageIcon bulletL = new ImageIcon("img/bulletL.png");
	private ImageIcon bulletD = new ImageIcon("img/bulletD.png");
	
	public Bullet(int x, int direction, int status, HumanUI pan, ChannelHandlerContext ctx, String name) {
		mainpanel = pan;
		this.name = name;
		this.ctx = ctx;
		if (direction == 1) {
			this.x = x + 50;
		} else if(direction == 2){
			this.x = x;
		} else
			this.x = x+15;
		System.out.println(y);
		this.direction = direction;
		
		
		if (direction == 1 && status == 1) {
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletR);
			pan.add(this);
			this.setVisible(true);
			shooting(this);
			shoottimer.start();
		} else if (direction == 2 && status == 2) { 
			setBounds((int) x, (int) y, 16, 16);
			setIcon(bulletL);
			pan.add(this);
			this.setVisible(true);
			shooting(this);
			shoottimer.start();
		} else if (direction == 3) {
			setBounds((int) x , (int) y, 16, 16);
			setIcon(bulletD);
			pan.add(this);
			this.setVisible(true);
			dshooting();
			dshoottimer.start();
		}
	}
	public void dshooting() {
		dshoottimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dshootsec++;
				System.out.println(x + "     " + y);
				for (int i = 0; i < 5; i++) {
					bulMoveD(1);
					setBounds(x,y, 16, 16);
					if (mainpanel.getM1().getX() >= getX() - 20 && mainpanel.getM1().getX() <= getX() + 20
							&& mainpanel.getM1().getY() == y && mainpanel.getM1().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 1 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM2().getX() >= getX() - 20 && mainpanel.getM2().getX() <= getX() + 20
							&& mainpanel.getM2().getY() == y && mainpanel.getM2().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 2 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM3().getX() >= getX() - 20 && mainpanel.getM3().getX() <= getX() + 20
							&& mainpanel.getM3().getY() == y && mainpanel.getM3().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 3 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM4().getX() >= getX() - 20 && mainpanel.getM4().getX() <= getX() + 20
							&& mainpanel.getM4().getY() == y && mainpanel.getM4().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 4 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM5().getX() >= getX() - 20 && mainpanel.getM5().getX() <= getX() + 20
							&& mainpanel.getM5().getY() == y && mainpanel.getM5().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 5 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM6().getX() >= getX() - 20 && mainpanel.getM6().getX() <= getX() + 20
							&& mainpanel.getM6().getY() == y && mainpanel.getM6().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 6 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM7().getX() >= getX() - 20 && mainpanel.getM7().getX() <= getX() + 20
							&& mainpanel.getM7().getY() == y && mainpanel.getM7().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 7 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM8().getX() >= getX() - 20 && mainpanel.getM8().getX() <= getX() + 20
							&& mainpanel.getM8().getY() == y && mainpanel.getM8().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 8 + ",");
						dshoottimer.stop();
					} else if (mainpanel.getM9().getX() >= getX() - 20 && mainpanel.getM9().getX() <= getX() + 20
							&& mainpanel.getM9().getY() == y && mainpanel.getM9().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 9 + ",");
						dshoottimer.stop();
					}
				}if (dshootsec == 30) {
					setVisible(false);
					mainpanel.remove(mainpanel.getHuman().getB());
					dshoottimer.stop();
				}
			}
		});
	}
	
	
	public void shooting(JLabel bul) {
		shoottimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shootsec++;
				for (int i = 0; i < 5; i++) {
					if (direction == 1) { 
						bulMove(1);
						setBounds(((int) bul.getX()), (int) y, 16, 16);
						if (shootsec == 30) {
							bul.setVisible(false);
							mainpanel.remove(bul);
							shoottimer.stop();
						}
					} else if (direction == 2) { 
						bulMove(-1);
						setBounds(((int) bul.getX()), (int) y, 16, 16);
						if (shootsec == 30) {
							bul.setVisible(false);
							mainpanel.remove(bul);
							shoottimer.stop();
						}
					}
					if (mainpanel.getM1().getX() == bul.getX() && mainpanel.getM1().getY() <= 275
							&& mainpanel.getM1().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 1 + ",");
						shoottimer.stop();
					} else if (mainpanel.getM2().getX() == bul.getX() && mainpanel.getM2().getY() <= 275
							&& mainpanel.getM2().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 2 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM3().getX() == bul.getX() && mainpanel.getM3().getY() <= 275
							&& mainpanel.getM3().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 3 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM4().getX() == bul.getX() && mainpanel.getM4().getY() <= 275
							&& mainpanel.getM4().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 4 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM5().getX() == bul.getX() && mainpanel.getM5().getY() <= 275
							&& mainpanel.getM5().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 5 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM6().getX() == bul.getX() && mainpanel.getM6().getY() <= 275
							&& mainpanel.getM6().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 6 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM7().getX() == bul.getX() && mainpanel.getM7().getY() <= 275
							&& mainpanel.getM7().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 7 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM8().getX() == bul.getX() && mainpanel.getM8().getY() <= 275
							&& mainpanel.getM8().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 8 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getM9().getX() == bul.getX() && mainpanel.getM9().getY() <= 275
							&& mainpanel.getM9().getLife() == true) {
						ctx.writeAndFlush("[MOLEDIE]," + name + "," + 9 + ",");
						shoottimer.stop();
						
					} else if (mainpanel.getIsSnake() == true){
						if (mainpanel.getSnake().getX() == bul.getX() && mainpanel.getSnake().getMoving() == true) {
							ctx.writeAndFlush("[SNAKEDIE]," + name + ",");
							shoottimer.stop();
							
						}
					}
				}
			}

		});
	}
	public void bulMove(int x) {
		this.x = this.x + x;
	}
	
	public void bulMoveD(int y) {
		this.y = this.y + y;
	}
	
	public int getX() {
		return (int)x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}
}