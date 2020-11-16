package MoleServer;

import javax.swing.JOptionPane;

import Mole.Game.LoginForm;
import Mole.Game.MoleGame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("클라이언트 접속 완료");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		if(msg.equals("LOGIN"))
			 new MoleGame();
		if(msg.equals("LOGINFAIL")) {
			ctx.close();
			LoginForm loginForm = new LoginForm();
			JOptionPane.showMessageDialog(loginForm, "Invalid username or password");
			ctx.close();
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}	
