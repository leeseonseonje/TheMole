package MoleServer;

import javax.swing.Timer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class VegetableTimer {
	Timer vegtimer = null;
	private int vegetableCount = 0;

	public VegetableTimer(ChannelHandlerContext ctx, String vegetableNumber, String name, int section) {
		Channel mole = ctx.channel();
		vegtimer = new Timer(1000, e -> {
			vegetableCount++;
			if (vegetableCount == 10) {
				int n = ((int) (Math.random() * 260)) + 263 * section;
				int x = ((int) (Math.random() * 10));
				for (Channel channel : Room.roomManager.get(name)) {
					if (channel == mole)
						mole.writeAndFlush("MOLE" + vegetableNumber + "," + n + "," + x);
					else
						channel.writeAndFlush("HUMAN" + vegetableNumber + "," + n + "," + x);
				}
				vegtimer.stop();
			}
		});
	}
}