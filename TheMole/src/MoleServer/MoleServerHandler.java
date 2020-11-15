package MoleServer;

import java.util.Arrays;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MoleServerHandler extends ChannelInboundHandlerAdapter{
	private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel newChannel = ctx.channel();
        System.out.println("클라이언트 접속");

        channelGroup.add(newChannel);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		System.out.println(readMessage);
		   String[] s = readMessage.split(",");
		   String command = s[0];
		   String id = s[1];
		   String pw = s[2];
		   System.out.println(s[0]);
		   System.out.println(s[1]);
		   System.out.println(s[2]);
		   System.out.println(command);
		   System.out.println(id);
		   System.out.println(pw);
	       if (command.equals("[LOGIN]")) {
	    	   new LoginServer(id, pw, ctx);
	       }
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel oldChannel = ctx.channel();
        channelGroup.remove(oldChannel);
	}
}
