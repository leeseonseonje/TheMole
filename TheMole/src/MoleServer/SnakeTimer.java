package MoleServer;

import javax.swing.Timer;

import io.netty.channel.Channel;

public class SnakeTimer {
	private Timer snakeTimer = null;
	private int moveCount = 0;
	
	public SnakeTimer(String name) {
		snakeTimer = new Timer(20, e -> {
			moveCount++;
			for (Channel channel : Room.roomManager.get(name)) {
				channel.writeAndFlush("SNAKEMOVE," + moveCount + ",");
		}
		});
	}
	public Timer getSnakeTimer() {
		return snakeTimer;
	}
}
