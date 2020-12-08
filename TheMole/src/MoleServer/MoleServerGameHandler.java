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
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("[LEFT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("LEFT,");
				}
			} else if (s[i].equals("[RIGHT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("RIGHT,");
				}
			} else if (s[i].equals("[LEFTSTOP]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("LEFTSTOP,");
				}
			}  else if (s[i].equals("[RIGHTSTOP]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("RIGHTSTOP,");
				}
			}else if (s[i].equals("[v1EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("v1EAT");
				}
				VegetableTimer vegetableTimer = new VegetableTimer(ctx, "v1", s[i+1], 0);
				vegetableTimer.getVegTimer().start();
			} else if (s[i].equals("[v2EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("v2EAT");
				}
				VegetableTimer vegetableTimer = new VegetableTimer(ctx, "v2", s[i+1], 1);
				vegetableTimer.getVegTimer().start();
			} else if (s[i].equals("[v3EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("v3EAT");
				}
				VegetableTimer vegetableTimer = new VegetableTimer(ctx, "v3", s[i+1], 2);
				vegetableTimer.getVegTimer().start();
			} else if (s[i].equals("[MOLEITEM1EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLEITEM1EAT");
				}
			} else if (s[i].equals("[MOLEITEM2EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i+1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLEITEM2EAT");
				}
			}

			else if (s[i].equals("[HUMANITEM1EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANITEM1EAT,");
				}
			} else if (s[i].equals("[HUMANITEM2EAT]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANITEM2EAT,");
				}
			} else if (s[i].equals("[HUMANSPEEDUP]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANSPEEDUP,");
				}
			} else if (s[i].equals("[HUMANSPEEDDOWN]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANSPEEDDOWN,");
				}
			} else if (s[i].equals("[MOLEMOVE]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLEMOVE," + s[i + 2] + "," + s[i + 3] + "," + s[i + 4] + ",");
				}
			} else if (s[i].equals("[MOLETRAP]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLETRAP,");
				}
			} else if (s[i].equals("[MOLETRAPSTOP]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLETRAPSTOP,");
				}
			} else if (s[i].equals("[HUMANTRAP]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANTRAP,");
				}
			} else if (s[i].equals("[HUMANTRAPSTOP]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("HUMANTRAPSTOP,");
				}
			}else if (s[i].equals("[MOLEDIE]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("MOLEDIE," + s[i + 2] + ",");
				}
			} else if (s[i].equals("[SNAKE]")) {
				int status = (int) (Math.random() * 2);
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel == myChannel)
						channel.writeAndFlush("MOLESNAKE," + status + ",");
					else
						channel.writeAndFlush("HUMANSNAKE," + status + ",");
				}
			} else if (s[i].equals("[BULLET]")) {
				for (Channel channel : Room.roomManager.get(s[i + 1])) {
					if (channel != myChannel)
						channel.writeAndFlush("BULLET," + s[i + 2] + "," + s[i + 3] + ",");
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
