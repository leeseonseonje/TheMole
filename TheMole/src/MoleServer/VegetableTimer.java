package MoleServer;

import javax.swing.Timer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class VegetableTimer {
	private Timer vegtimer = null;
	private int vegetableCount = 0;

	public VegetableTimer(ChannelHandlerContext ctx, String vegetableNumber, String name, int section) {
		vegtimer = new Timer(1000, e -> {
			vegetableCount++;
			if (vegetableCount == 10) {
				int n = ((int) (Math.random() * 260)) + 263 * section;
				for (Channel channel : Room.roomManager.get(name))
					channel.writeAndFlush(vegetableNumber + "," + n);
				vegtimer.stop();
			}
		});
	}
}
