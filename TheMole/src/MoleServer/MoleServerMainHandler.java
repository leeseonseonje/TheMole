package MoleServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MoleServerMainHandler extends ChannelInboundHandlerAdapter {
	private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel newChannel = ctx.channel();
		channelGroup.add(newChannel);
		System.out.println(channelGroup);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		System.out.println(readMessage);
		String[] s = readMessage.split(",");
		System.out.println(readMessage);
		if (s[0].equals("[INFORMATION]"))
			DBConnect.informationDB(s[1], ctx);
		if (readMessage.equals("[RANKING]"))
			DBConnect.leaderBoardDB(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel oldChannel = ctx.channel();
        channelGroup.remove(oldChannel);
	}
}
