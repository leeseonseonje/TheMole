package MoleServer;

import javax.swing.JOptionPane;

import Mole.Game.LoginForm;
import Mole.Game.MoleGame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientHandler extends ChannelInboundHandlerAdapter{
	public static String serverMessage;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("클라이언트 접속 완료");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		System.out.println("zzz");
		if(msg.equals("LOGIN"))
			 serverMessage = "LOGIN";
		if(msg.equals("LOGINFAIL")) {
			ctx.close();
			LoginForm loginForm = new LoginForm();
			JOptionPane.showMessageDialog(loginForm, "Invalid username or password");
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}	
