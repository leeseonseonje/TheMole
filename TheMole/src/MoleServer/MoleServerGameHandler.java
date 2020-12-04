package MoleServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleServerGameHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String) msg;
		String[] s = readMessage.split(",");
		Channel myChannel = ctx.channel();

		if (s[0].equals("[LEFT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("LEFT,");
			}
		} else if (s[0].equals("[RIGHT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("RIGHT,");
			}
		} else if (s[0].equals("[STOP]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("STOP,");
			}
		} else if (s[0].equals("[v1EAT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("v1EAT");
			}
			VegetableTimer vegetableTimer = new VegetableTimer(ctx, "v1", s[1], 0);
			vegetableTimer.getVegTimer().start();
		} else if (s[0].equals("[v2EAT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("v2EAT");
			}
			VegetableTimer vegetableTimer = new VegetableTimer(ctx, "v2", s[1], 1);
			vegetableTimer.getVegTimer().start();
		} else if (s[0].equals("[v3EAT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("v3EAT");
			}
			VegetableTimer vegetableTimer = new VegetableTimer(ctx, "v3", s[1], 2);
			vegetableTimer.getVegTimer().start();
		} else if (s[0].equals("[MOLEITEM1EAT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("MOLEITEM1EAT");
			}
		} else if (s[0].equals("[MOLEITEM2EAT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("MOLEITEM2EAT");
			}
		}
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("[HUMANITEM1EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANITEM1EAT,");
				}
			} else if (s[i].equals("[HUMANITEM2EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANITEM2EAT,");
				}
			} else if (s[i].equals("[HUMANSPEEDUP]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANSPEEDUP,");
				}
			} else if (s[i].equals("[HUMANSPEEDDOWN]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANSPEEDDOWN,");
				}
			} else if (s[i].equals("[MOLEMOVE]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLEMOVE," + s[i + 2] + "," + s[i + 3] + "," + s[i + 4] + ",");
				}
			}
		}
		
		if (s[0].equals("[HUMANWIN]"))
			DBConnect.humanWin(s[1]);
		else if (s[0].equals("[MOLELOSE]"))
			DBConnect.gameLose(s[1]);
		else if (s[0].equals("[MOLEWIN]"))
			DBConnect.moleWin(s[1]);
		else if (s[0].equals("[HUMANLOSE]"))
			DBConnect.gameLose(s[1]);
	}
}
