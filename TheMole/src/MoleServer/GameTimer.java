package MoleServer;

import javax.swing.Timer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class GameTimer {
	private Timer gameTimer = null;
	private int gameTime = 0;
	private int itemCount = 0;
	
	public GameTimer(String name) {
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
}
