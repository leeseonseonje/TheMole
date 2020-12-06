package MoleServer;

import javax.swing.Timer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class ItemTimer {
	private Timer itemTimer = null;
	private int itemCount = 0;
	private int cnt = 1;
	public ItemTimer(ChannelHandlerContext ctx, Channel host, String name, String hostRole, String guestRole) {
		itemTimer = new Timer(1000, e -> {
		++itemCount;
			if (itemCount % 10 == 0) {
				int a = ((int) (Math.random() * 380)) + (400 * 0) + 20;
				int b = ((int) (Math.random() * 380)) + (400 * 1) + 20;
				for (Channel channel : Room.roomManager.get(name)) {
					if (channel == host)
						host.writeAndFlush(hostRole + "," + a + "," + b);
					else
						channel.writeAndFlush(guestRole + "," + a + "," + b);
				}
				if (itemCount == 150) {
					itemTimer.stop();
				}
			}
		});
	}
	public Timer getItemTimer() {
		return itemTimer;
	}
}
	

