package Mole.Game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class vegetableThread {
	private ImageIcon vegetables[] = { new ImageIcon("img/vegetableResource/vegetable0.png"),
			new ImageIcon("img/vegetableResource/vegetable1.png"),
			new ImageIcon("img/vegetableResource/vegetable2.png"),
			new ImageIcon("img/vegetableResource/vegetable3.png"),
			new ImageIcon("img/vegetableResource/vegetable4.png"),
			new ImageIcon("img/vegetableResource/vegetable5.png"),
			new ImageIcon("img/vegetableResource/vegetable6.png"),
			new ImageIcon("img/vegetableResource/vegetable7.png"),
			new ImageIcon("img/vegetableResource/vegetable8.png"),
			new ImageIcon("img/vegetableResource/vegetable9.png"), };
	JLabel vegetable = new JLabel(vegetables[(int)(Math.random()*10)]);
	private int x, y, section;
	public Timer vegtimer;
	private int vegsecond;
	private int vegcount = 0;
	public boolean timerstop = false;

	public vegetableThread(int section,MolePanel pan) {
		this.section = section;
		x = ((int) (Math.random() * 260)) + 263 * this.section;
		y = 260;
		vegetable.setBounds(x, y, 32, 32);
		pan.add(vegetable);
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
		y = 260;
		vegetable.setBounds(x, y, 32, 32);
	}

	public void vegtimer() {
		timerstop = true;
		vegtimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vegsecond++;
				if (vegsecond == 10) {
					setposition();
					vegetable.setVisible(true);
					vegtimer.stop();
					timerstop = false;
				}
			}
		});
	}

	public void setVisible(boolean b) {
		if (b == true)
			vegetable.setVisible(true);
		else if (b == false)
			vegetable.setVisible(false);
	}
	public Rectangle getBounds() {
		return new Rectangle( x, y, 32, 32);
	}
}