package Mole.Game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller { // 리스트가 필요한것들의 통제 총알,아이템같은?

	private LinkedList<Bullet> b = new LinkedList<>();
	private LinkedList<Mole> m = new LinkedList<>();
	
	Bullet tempBullet;
	Mole tempMole;
	
	Game game;
	Textures texture;
	
	public Controller(Game game, Textures texture) {
		this.game = game;
		this.texture = texture;
		
		for(int i = 0; i < 3; i++)
			for(int j=0; j<3; j++)
				addMole(new Mole(150 + (j*70), 350+(i*70), false ,texture));
	}
	
	public void tick() {
		for(int i =0; i < b.size(); i++) {
			tempBullet = b.get(i);
			if(game.buldirection == true && (tempBullet.getX() - game.getPlayer().getX()) > 150) // 총알의 사정거리 150을 넘어가면 지우도록 설정
				removeBullet(tempBullet);
			else if(game.buldirection == false && Math.abs(game.getPlayer().getX() - tempBullet.getX()) > 100)
				removeBullet(tempBullet);
			tempBullet.tick();
		}
		for(int i =0; i < m.size(); i++) {
			tempMole = m.get(i);
			
			tempMole.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<b.size(); i++) {
			tempBullet = b.get(i);
			
			tempBullet.render(g);
		}
		for(int i = 0; i<m.size(); i++) {
			tempMole = m.get(i);
			
			tempMole.render(g);
		}
	}
	
	public void addBullet(Bullet block) {
		b.add(block);
	}
	public void removeBullet(Bullet block) {
		b.remove(block);
	}
	public void addMole(Mole block) {
		m.add(block);
	}
	public void removeMole(Mole block) {
		m.remove(block);
	}
}
