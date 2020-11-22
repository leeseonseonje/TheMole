package Mole.Game;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.netty.channel.ChannelHandlerContext;

public class TestRoom extends JFrame {
	public TestRoom(ChannelHandlerContext ctx, LinkedList<String> roomList) {
		setTitle("RoomTest");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RoomListPanel roomListPanel = new RoomListPanel(ctx, roomList, this);
		RoomTest roomTest = new RoomTest(ctx, LoginForm.getId(), "");
		add(roomListPanel);
		roomListPanel.testButton.addActionListener(e -> {
			ctx.writeAndFlush("[CREAT]," + LoginForm.getId());
			add(roomTest);
			roomListPanel.setVisible(false);
			roomTest.setVisible(true);
		});
	//	roomListPanel.setVisible(true);
		setVisible(true);
	}
}
class RoomListPanel extends JPanel{
	JPanel panel;
	JButton[] button;
	JButton testButton;
	JButton testButtonB;
	public RoomListPanel(ChannelHandlerContext ctx, LinkedList<String> roomList, JFrame frame) {
		button = new JButton[roomList.size()];
		for(int i = 0; i < roomList.size(); i++) {
			button[i] = new JButton(roomList.get(i) + "丛狼 规");
			add(button[i]);
			button[i].addActionListener(e -> {
				String n = e.getActionCommand();
				String s[] = n.split("丛");
				ctx.writeAndFlush("[JOIN]" + "," + s[0] + "," + LoginForm.getId());
				frame.dispose();
			});
		}
		testButton = new JButton("规积己");
		add(testButton);
		/*testButton.addActionListener(e -> {
			ctx.writeAndFlush("[CREAT]," + LoginForm.getId());
			frame.dispose();
			RoomTest roomTest = new RoomTest(ctx, LoginForm.getId(), "");
		});*/
		testButtonB = new JButton("货肺绊魔");
		add(testButtonB);
		testButtonB.addActionListener(e -> {
			ctx.writeAndFlush("[LIST]");
			setVisible(false);
			setVisible(true);
		});
	}
}


