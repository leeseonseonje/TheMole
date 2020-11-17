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
	       if (s[0].equals("[LOGIN]")) 
	    	   new LoginServer(s[1], s[2], ctx);
	       else if (s[0].equals("[DUPLICATE]"))
	    	   SignUpServer.duplicateCheck(s[1], ctx);
	       else if (s[0].equals("[SIGNUP]"))
	    	   SignUpServer.signUpComplete(s[1], s[2], ctx);
	       
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel oldChannel = ctx.channel();
        channelGroup.remove(oldChannel);
        System.out.println("eee");
	}
}
