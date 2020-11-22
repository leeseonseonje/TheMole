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
	private static int roomNumber = 1;
	private static final int x = 0;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel loginUser = ctx.channel();
		onlineUsers.add(loginUser);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		System.out.println(readMessage);
		String[] s = readMessage.split(",");
		if (s[0].equals("[INFORMATION]"))
			DBConnect.informationDB(s[1], ctx);
		if (readMessage.equals("[RANKING]"))
			DBConnect.leaderBoardDB(ctx);
		if (readMessage.equals("[CREAT]")) {
			Room.roomCreat(ctx, roomNumber, x);
			roomNumber ++;
		}
		if (s[0].equals("[JOIN]"))
			Room.roomJoin(ctx, Integer.parseInt(s[1]));
		if (readMessage.equals("[LIST]"))
			Room.roomListSend(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel logoutUser = ctx.channel();
        onlineUsers.remove(logoutUser);
	}
}
