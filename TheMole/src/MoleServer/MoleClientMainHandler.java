package MoleServer;

import java.util.Arrays;

import javax.swing.JOptionPane;

import Mole.Game.LeaderBoardFrame;
import Mole.Game.MainFrame;
import Mole.Game.TestRoom;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientMainHandler extends ChannelInboundHandlerAdapter {
	private MainFrame mainFrame;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		mainFrame = new MainFrame(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		String[] s = readMessage.split(",");
		if (s[0].equals("INFO")) 
			JOptionPane.showMessageDialog(mainFrame , s[1], "계정 정보", JOptionPane.PLAIN_MESSAGE);
		if (s[0].equals("RANKING")) 
				LeaderBoardFrame.boardcontent.append(s[1]);
		if (s[0].equals("ROOMLIST"))
			new TestRoom(ctx, s[1]);
	}
}
