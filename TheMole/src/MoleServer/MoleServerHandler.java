package MoleServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MoleServerHandler extends ChannelInboundHandlerAdapter{
	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("ÇÏ¿°");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		   String[] s = readMessage.split(",");
		   System.out.println(readMessage);
		   System.out.println(MoleServerMainHandler.onlineId);
		   if (s[0].equals("[CONNECT]"))
			   MoleServerMainHandler.onlineId.put(ctx.channel(), s[1]);
	       if (s[2].equals("[LOGIN]")) 
	    	   LoginServer.login(s[3], s[4], ctx);
	       else if (s[0].equals("[DUPLICATE]"))
	    	   LoginServer.duplicateCheck(s[1], ctx);
	       else if (s[0].equals("[SIGNUP]"))
	    	   LoginServer.signUpComplete(s[1], s[2], ctx);
	       else
	    	   ctx.fireChannelRead(readMessage);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
