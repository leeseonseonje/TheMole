package MoleServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleServerGameHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		String[] s = readMessage.split(",");
		Channel myChannel = ctx.channel();

		if (s[0].equals("[RIGHT]")) {
			for (Channel channel : Room.roomManager.get(s[1])) {
				if (channel != myChannel)
					channel.writeAndFlush("RIGHT");
			}
		}
	}
}
