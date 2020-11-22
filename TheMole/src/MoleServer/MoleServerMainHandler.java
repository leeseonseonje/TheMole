package MoleServer;

import java.util.LinkedList;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MoleServerMainHandler extends ChannelInboundHandlerAdapter {
	private static final ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel loginUser = ctx.channel();
		onlineUsers.add(loginUser);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
	//	System.out.println(readMessage);
		String[] s = readMessage.split(",");
		if (s[0].equals("[INFORMATION]"))
			DBConnect.informationDB(s[1], ctx);
		else if (readMessage.equals("[RANKING]"))
			DBConnect.leaderBoardDB(ctx);
		else if (s[0].equals("[CREAT]"))
			Room.roomCreat(ctx, s[1]);
		else if (s[0].equals("[JOIN]"))
			Room.roomJoin(ctx, s[1], s[2]);
		else if (readMessage.equals("[LIST]"))
			Room.roomListSend(ctx);
		else if (readMessage.equals("[REFRESH]"))
			Room.roomListRefresh(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel logoutUser = ctx.channel();
        onlineUsers.remove(logoutUser);
	}
}
