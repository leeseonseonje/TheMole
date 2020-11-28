package Mole.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class vegetableThread extends JLabel{
	private ImageIcon veget = new ImageIcon("img/vegetables.png");
	private int x, y, section;
	public Timer vegtimer;
	public int vegsecond;
	private int vegcount = 0;
	public boolean timerstop = false;
	

	public vegetableThread(int section)  {
		this.section = section;
		x = ((int) (Math.random() * 260)) + 263 * this.section;
		y = 260;
		setBounds(x, y, 16, 16);
		setIcon(veget);
		// vegetable.setIcon(veget);
		
		// System.out.println("작물위치 " + x + " " + y);
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
		setBounds(x, y, 16, 16);
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

	
}