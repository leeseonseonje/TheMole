package Mole.Game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import MoleServer.MoleClientMainHandler;
import io.netty.channel.ChannelHandlerContext;

public class RoomTest extends JPanel {
	public static JButton testStart;
	JButton testOut;
	JButton host;
	JButton guest;
	JTextField chatField;
	JTextArea chatArea;
	JScrollPane scroll;
	public RoomTest(ChannelHandlerContext ctx, String hostName, String guestName) {

		testStart = new JButton("시작");
		testOut = new JButton("나가기");
		host = new JButton(hostName);
		guest = new JButton(guestName);
		testOut.addActionListener(e -> {
			setVisible(false);
			ctx.writeAndFlush("[REFRESH]");
			if(LoginForm.getId() == hostName)
				ctx.writeAndFlush("[HOSTOUT]," + hostName);
			else
				ctx.writeAndFlush("[GUESTOUT]," + hostName);
		});	
		chatField = new JTextField(30);
		chatArea = new JTextArea(10, 30);
		chatArea.setEditable(false);
		scroll = new JScrollPane(chatArea);
		add(host);
		add(guest);
		add(testStart);
		add(testOut);
		add(chatField);
		add(scroll);
	}
}