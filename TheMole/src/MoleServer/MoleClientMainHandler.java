package MoleServer;

import Mole.Game.MainFrame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientMainHandler extends ChannelInboundHandlerAdapter {
	public static String serverMessage = "";
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		new MainFrame(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		String[] s = readMessage.split(",");
		System.out.println(s[1]);
		System.out.println(s[0]);
		if (s[0].equals("INFO"))
			serverMessage = s[1];	
	}
}
