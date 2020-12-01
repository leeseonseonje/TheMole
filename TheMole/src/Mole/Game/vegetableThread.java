package Mole.Game;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class vegetableThread extends JLabel{
	public static ImageIcon[] vegetables = { new ImageIcon("img/vegetableResource/vegetable0.png"),
			new ImageIcon("img/vegetableResource/vegetable1.png"), new ImageIcon("img/vegetableResource/vegetable2.png"),
			new ImageIcon("img/vegetableResource/vegetable3.png"), new ImageIcon("img/vegetableResource/vegetable4.png"),
			new ImageIcon("img/vegetableResource/vegetable5.png"), new ImageIcon("img/vegetableResource/vegetable6.png"),
			new ImageIcon("img/vegetableResource/vegetable7.png"), new ImageIcon("img/vegetableResource/vegetable8.png"),
			new ImageIcon("img/vegetableResource/vegetable9.png") };
	private int section;
	public Timer vegtimer;
	public int vegsecond;
	private int vegcount = 0;
	public boolean timerstop = false;
	

	public vegetableThread(int section, int crop)  {
		//this.section = section;
		//x = section;
		//y = 255;
		setBounds(section, 255, 32, 32);
		setIcon(vegetables[crop]);
		//System.out.println(section + "!!!!");
		// vegetable.setIcon(veget);
		
		// System.out.println("작물위치 " + x + " " + y);
	}
	public ImageIcon[] getVegetables() {
		return vegetables;
	}
	public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), 32, 32);
    }

	/*public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}*/

/*	public void setsecond(int second) {
		vegsecond = second;
	}

	public int getsecond() {
		return vegsecond;
	}

	public int getvegcount() {
		return vegcount;
	}*/

	public int plusvegcount() {
		return vegcount++;
	}

/*	public void setposition() {
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
	}*/

	
}