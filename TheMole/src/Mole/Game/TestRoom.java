package Mole.Game;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.netty.channel.ChannelHandlerContext;

public class TestRoom extends JFrame{
	JPanel panel;
	JButton[] button;
	JButton testButton;
	JButton testButtonB;
	
	public TestRoom(ChannelHandlerContext ctx, String rooms) {
		setTitle("RoomTest");
		setSize(627, 401);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int r = Integer.parseInt(rooms);
		panel = new JPanel();
		button = new JButton[r];
		for(int i = 0; i < r; i++) {
			button[i] = new JButton(Integer.toString(i+1));
			panel.add(button[i]);
			button[i].addActionListener(e -> {
				String n = e.getActionCommand();
				ctx.writeAndFlush("[JOIN]" + "," + n);
			});
		}
		
		testButton = new JButton("¹æ»ý¼º");
		panel.add(testButton);
		testButton.addActionListener(e -> {
			ctx.writeAndFlush("[CREAT]");
		});

		add(panel);
		setVisible(true);
	}
}

