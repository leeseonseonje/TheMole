package MoleServer;

import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import Mole.Game.HomePanel;
import Mole.Game.HumanUI;
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
	public static RoomTest roomTest;
	private LinkedList<String> room = new LinkedList<String>();
	
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
				room.add(s[i]);
			System.out.println(room);
			testRoom = new TestRoom(ctx, room);
			mainFrame.add(testRoom);
			homePanel.setVisible(false);
			testRoom.setVisible(true);
			room.clear();
		}
		else if (s[0].equals("REFRESH")) {
			for (int i = 1; i < s.length; i ++ )
				room.add(s[i]);
			System.out.println(room);
			testRoom = new TestRoom(ctx, room);
			mainFrame.add(testRoom);
			testRoom.setVisible(false);
			testRoom.setVisible(true);
			room.clear();
			System.out.println("ok");
		}
		else if (s[0].equals("CREAT")) {
			roomTest = new RoomTest(ctx, LoginForm.getId(), "");
			mainFrame.add(roomTest);
			testRoom.setVisible(false);
			roomTest.setVisible(true);
		}
		else if (s[0].equals("JOIN")) {
			roomTest = new RoomTest(ctx, s[1], s[2]);
			roomTest.testStart.setText("준비");
			mainFrame.add(roomTest);
			testRoom.setVisible(false);
			roomTest.setVisible(true);
		}
		else if (s[0].equals("GUEST")) {
			roomTest.setVisible(false);
			roomTest = new RoomTest(ctx, s[1], s[2]);
			mainFrame.add(roomTest);
			roomTest.setVisible(false);
			roomTest.setVisible(true);
		}
		else if (s[0].equals("SENDMESSAGE")) {
			RoomTest.chatArea.append(s[1] + "\n");
		}
		else if (readMessage.equals("FULL"))
			JOptionPane.showMessageDialog(mainFrame, "풀방");
		
		else if (s[0].equals("BOOM")) {
			for (int i = 1; i < s.length; i ++ )
				room.add(s[i]);
			roomTest.setVisible(false);
			testRoom = new TestRoom(ctx, room);
			mainFrame.add(testRoom);
			testRoom.setVisible(false);
			testRoom.setVisible(true);
			room.clear();
		}
		else if (s[0].equals("GUESTOUT")) {
			roomTest.setVisible(false);
			roomTest = new RoomTest(ctx, s[1], "");
			mainFrame.add(roomTest);
			roomTest.setVisible(false);
			roomTest.setVisible(true);
		}
		else if (s[0].equals("EXITROOM")) {
			roomTest.setVisible(false);
			roomTest = new RoomTest(ctx, LoginForm.getId(), "");
			int index = Collections.binarySearch(room, s[1]);
			room.set(index, LoginForm.getId());
			mainFrame.add(roomTest);
			roomTest.setVisible(false);
			roomTest.setVisible(true);
		}
		else if (readMessage.equals("READY"))
			RoomTest.ready.setText("준비완료");
		else if (readMessage.equals("CANSLE"))
			RoomTest.ready.setText("");
		else 
			ctx.fireChannelRead(readMessage);
	}
}
