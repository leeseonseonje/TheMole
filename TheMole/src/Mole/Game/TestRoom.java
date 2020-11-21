package Mole.Game;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.netty.channel.ChannelHandlerContext;

public class TestRoom extends JFrame{
	JPanel panel;
	JButton[] button;
	JButton testButton, refreshButton;
	// JButton testButtonB;
	
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
		
		testButton = new JButton("规积己");
		panel.add(testButton);
		testButton.addActionListener(e -> {
			ctx.writeAndFlush("[CREAT]");
			this.repaint();
		});
		refreshButton = new JButton("货肺绊魔");
		panel.add(refreshButton);
		refreshButton.addActionListener(e -> {
			panel.repaint();
			this.repaint();
		});

		add(panel);
		setVisible(true);
	}
}

