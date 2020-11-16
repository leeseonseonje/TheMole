package Mole.Game;

import java.awt.image.BufferedImage;

public class Textures { // 다른 클래스,자바파일에서 new 키워드로 이 Textures를 생성하지 말 것.
	
	public SpriteSheet humanSpr, bulletSpr, moleSpr;
	
	public BufferedImage human, bulletR, bulletL, mole;
	
	public Textures(Game game) {
		humanSpr = new SpriteSheet(game.getHumSpriteSheet());
		bulletSpr = new SpriteSheet(game.getBulSpriteSheet());
		moleSpr = new SpriteSheet(game.getMolSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures() {
		human = humanSpr.grabHumImage(1, 1, 50, 64);
		bulletR = bulletSpr.grabBulImage(1, 1, 16, 16);
		bulletL = bulletSpr.grabBulImage(2, 1, 16, 16);
		mole = moleSpr.grabMolImage(1,1,32,32);
	}
}
