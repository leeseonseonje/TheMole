package MoleServer;

import javax.swing.Timer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class GameTimer {
	private Timer gameTimer = null;
	private int gameTime = 0;
	private int itemCount = 0;
	private int vegetableCount = 15, humanLife = 2, moleCount = 9;

	public GameTimer(ChannelHandlerContext ctx, Channel host, String name) {
		gameTimer = new Timer(1000, e -> {
			++itemCount;
			++gameTime;
			if (itemCount % 20 == 0) {
				int a = ((int) (Math.random() * 350)) + (400 * 0) + 50;
				int b = ((int) (Math.random() * 350)) + (400 * 1);
				for (Channel channel : Room.roomManager.get(name)) {
						channel.writeAndFlush("ITEM" + "," + a + "," + b + ",");
				}
			}
			for (Channel channel : Room.roomManager.get(name)) {
					channel.writeAndFlush("GAMECOUNT,");
			}
		});
	}

	public Timer getGameTimer() {
		return gameTimer;
	}

	public int getVegetableCount() {
		return vegetableCount;
	}

	public void setVegetableCount(int vegetableCount) {
		this.vegetableCount = vegetableCount;
	}

	public int getHumanLife() {
		return humanLife;
	}

	public void setHumanLife(int humanLife) {
		this.humanLife = humanLife;
	}

	public int getMoleCount() {
		return moleCount;
	}

	public void setMoleCount(int moleCount) {
		this.moleCount = moleCount;
	}
}
