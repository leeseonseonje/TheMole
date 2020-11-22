package MoleServer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import Mole.Game.HomePanel;
import Mole.Game.LeaderBoardFrame;
import Mole.Game.LoginForm;
import Mole.Game.MainFrame;
import Mole.Game.RoomTest;
import Mole.Game.TestRoom;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientMainHandler extends ChannelInboundHandlerAdapter {
	public static MainFrame mainFrame;
	public static HomePanel homePanel;
	public static TestRoom testRoom;
	private LinkedList<String> roomList = new LinkedList<String>();
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		mainFrame = new MainFrame(ctx);
		homePanel = new HomePanel(ctx);
		mainFrame.add(homePanel);
		mainFrame.setVisible(true);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		String[] s = readMessage.split(",");
		
		if (s[0].equals("INFO")) 
			JOptionPane.showMessageDialog(mainFrame , s[1], "계정 정보", JOptionPane.PLAIN_MESSAGE);
		else if (s[0].equals("RANKING")) {
			LeaderBoardFrame leaderBoardFrame = new LeaderBoardFrame(homePanel);
			mainFrame.add(leaderBoardFrame);
			homePanel.setVisible(false);
			leaderBoardFrame.setVisible(true);
			leaderBoardFrame.boardcontent.append(s[1]);
		}
		else if (s[0].equals("ROOMLIST")) {
			for (int i = 1; i < s.length; i ++ )
				roomList.add(s[i]);
			System.out.println(roomList);
			testRoom = new TestRoom(ctx, roomList);
			mainFrame.add(testRoom);
			homePanel.setVisible(false);
			testRoom.setVisible(true);
			roomList.clear();
		}
		else if (s[0].equals("REFRESH")) {
			for (int i = 1; i < s.length; i ++ )
				roomList.add(s[i]);
			System.out.println(roomList);
			testRoom = new TestRoom(ctx, roomList);
			System.out.println("2");
			mainFrame.add(testRoom);
			System.out.println("3");
			testRoom.setVisible(false);
			testRoom.setVisible(true);
			System.out.println("4");
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
