package MoleServer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import Mole.Game.LeaderBoardFrame;
import Mole.Game.LoginForm;
import Mole.Game.MainFrame;
import Mole.Game.RoomTest;
import Mole.Game.TestRoom;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientMainHandler extends ChannelInboundHandlerAdapter {
	private MainFrame mainFrame;
	private LinkedList<String> roomList = new LinkedList<String>();
	
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
		else if (s[0].equals("RANKING")) 
			LeaderBoardFrame.boardcontent.append(s[1]);
		else if (s[0].equals("ROOMLIST")) {
			for (int i = 1; i < s.length; i ++ )
				roomList.add(s[i]);
			System.out.println(roomList);
			TestRoom testRoom = new TestRoom(ctx, roomList);
			mainFrame.dispose();
			roomList.clear();
		}
		else if (s[0].equals("JOIN")) {
			RoomTest roomTest = new RoomTest(ctx, s[1], s[2]);
			roomTest.guest.setText(s[2]);
			if (s[2].equals(LoginForm.getId()))
				roomTest.testStart.setText("준비");	
		}
	}
}
