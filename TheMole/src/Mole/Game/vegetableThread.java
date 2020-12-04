package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class vegetableThread extends JLabel{
	private ImageIcon vegetables[] = { new ImageIcon("img/vegetableResource/vegetable0.png"),
			new ImageIcon("img/vegetableResource/vegetable1.png"),
			new ImageIcon("img/vegetableResource/vegetable2.png"),
			new ImageIcon("img/vegetableResource/vegetable3.png"),
			new ImageIcon("img/vegetableResource/vegetable4.png"),
			new ImageIcon("img/vegetableResource/vegetable5.png"),
			new ImageIcon("img/vegetableResource/vegetable6.png"),
			new ImageIcon("img/vegetableResource/vegetable7.png"),
			new ImageIcon("img/vegetableResource/vegetable8.png"),
			new ImageIcon("img/vegetableResource/vegetable9.png"), 
			};
	
	private int x, y, section;
	public Timer vegtimer;
	private int vegsecond;
	private int vegcount = 0;
	public boolean timerstop = false;

	public vegetableThread(int section) {
		this.section = section;
		x = ((int) (Math.random() * 260)) + 247 * this.section;
		y = 235;
		this.setBounds(x, y, 32, 32);
		this.setIcon(vegetables[(int)(Math.random()*10)]); 
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setsecond(int second) {
		vegsecond = second;
	}

	public int getsecond() {
		return vegsecond;
	}

	public int getvegcount() {
		return vegcount;
	}

	public int plusvegcount() {
		return vegcount++;
	}

	public void setposition() {
		x = ((int) (Math.random() * 260)) + 263 * this.section;
		y = 235;
		this.setBounds(x, y, 32, 32);
	}

	public void vegtimer() {
		timerstop = true;
		vegtimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vegsecond++;
				if (vegsecond == 10) {
					setposition();
					setVisible(true);
					vegtimer.stop();
					timerstop = false;
				}
			}
		});
	}
	public Rectangle getBounds() {
		return new Rectangle( x, y, 32, 32);
	}
}