package Standby;

import java.awt.image.BufferedImage;

public class Textures { // 다른 클래스,자바파일에서 new 키워드로 이 Textures를 생성하지 말 것.
	
	public SpriteSheet humanSpr, bulletSpr, moleSpr, snakeSpr, vegetableSpr;
	
	//public BufferedImage human, bulletR, bulletL, mole;
	
	public BufferedImage[] human = new BufferedImage[10];
	public BufferedImage[] bullet = new BufferedImage[2];
	public BufferedImage[] mole = new BufferedImage[16];
	public BufferedImage[] snake = new BufferedImage[4];
	public BufferedImage[] vegetable = new BufferedImage[10];
	
	public Textures(Game game) {
		humanSpr = new SpriteSheet(game.getHumSpriteSheet());
		bulletSpr = new SpriteSheet(game.getBulSpriteSheet());
		moleSpr = new SpriteSheet(game.getMolSpriteSheet());
		snakeSpr = new SpriteSheet(game.getSnaSpriteSheet());
		vegetableSpr = new SpriteSheet(game.getVegSpriteSheet());
		
		
		getTextures();
	}
	
	private void getTextures() {
		human[0] = humanSpr.grabHumImage(1, 1, 50, 64);
		human[1] = humanSpr.grabHumImage(2, 1, 50, 64);
		human[2] = humanSpr.grabHumImage(3, 1, 50, 64);
		human[3] = humanSpr.grabHumImage(4, 1, 50, 64);
		human[4] = humanSpr.grabHumImage(5, 1, 50, 64);
		human[5] = humanSpr.grabHumImage(1, 2, 50, 64);
		human[6] = humanSpr.grabHumImage(2, 2, 50, 64);
		human[7] = humanSpr.grabHumImage(3, 2, 50, 64);
		human[8] = humanSpr.grabHumImage(4, 2, 50, 64);
		human[9] = humanSpr.grabHumImage(5, 2, 50, 64);
		
		bullet[0] = bulletSpr.grabBulImage(1, 1, 16, 16);
		bullet[1] = bulletSpr.grabBulImage(2, 1, 16, 16);
		
		mole[0] = moleSpr.grabMolImage(1,1,32,32);
		mole[1] = moleSpr.grabMolImage(2,1,32,32);
		mole[2] = moleSpr.grabMolImage(3,1,32,32);
		mole[3] = moleSpr.grabMolImage(4,1,32,32); // 7과 동일
		mole[4] = moleSpr.grabMolImage(1,2,32,32);
		mole[5] = moleSpr.grabMolImage(2,2,32,32);
		mole[6] = moleSpr.grabMolImage(3,2,32,32);
		mole[7] = moleSpr.grabMolImage(4,2,32,32); // 3과 동일
		mole[8] = moleSpr.grabMolImage(1,3,32,32);
		mole[9] = moleSpr.grabMolImage(2,3,32,32);
		mole[10] = moleSpr.grabMolImage(3,3,32,32);
		mole[11] = moleSpr.grabMolImage(4,3,32,32);
		mole[12] = moleSpr.grabMolImage(1,4,32,32); // 15와 동일
		mole[13] = moleSpr.grabMolImage(2,4,32,32);
		mole[14] = moleSpr.grabMolImage(3,4,32,32);
		mole[15] = moleSpr.grabMolImage(4,4,32,32); // 12와 동일
		
		snake[0] = snakeSpr.grabSnaImage(1,1,32,32);
		snake[1] = snakeSpr.grabSnaImage(2,1,32,32);
		snake[2] = snakeSpr.grabSnaImage(1,2,32,32);
		snake[3] = snakeSpr.grabSnaImage(2,2,32,32);
		
		vegetable[0] = vegetableSpr.grabVegImage(1,1,32,32);
		vegetable[1] = vegetableSpr.grabVegImage(2,1,32,32);
		vegetable[2] = vegetableSpr.grabVegImage(3,1,32,32);
		vegetable[3] = vegetableSpr.grabVegImage(4,1,32,32);
		vegetable[4] = vegetableSpr.grabVegImage(5,1,32,32);
		vegetable[5] = vegetableSpr.grabVegImage(6,1,32,32);
		vegetable[6] = vegetableSpr.grabVegImage(7,1,32,32);
		vegetable[7] = vegetableSpr.grabVegImage(8,1,32,32);
		vegetable[8] = vegetableSpr.grabVegImage(9,1,32,32);
		vegetable[9] = vegetableSpr.grabVegImage(10,1,32,32);
	}
}
