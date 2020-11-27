package Mole.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	Game game;

	MouseInput(Game game) {
		this.game = game;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(mx > game.getPlayer().getX() && mx < game.getPlayer().getX()+50)
			System.out.println("ÀÏ´Ü µÊ");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
