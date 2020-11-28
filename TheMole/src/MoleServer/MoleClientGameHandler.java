package MoleServer;

import Mole.Game.GameStart;
import Mole.Game.MoleUI;
import Standby.Game;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientGameHandler extends ChannelInboundHandlerAdapter {
	public static MoleUI moleUI;
	public static Game game;
	private GameStart gameStart;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String)msg;
		String[] s = readMessage.split(",");
		
		if (s[0].equals("MOLESTART")) {
			gameStart = new GameStart(s[4], s[5]);
			gameStart.molePlayer.setText(s[4]);
			gameStart.humanPlayer.setText(s[5]);
			moleUI = new MoleUI();
			MoleClientMainHandler.roomTest.setVisible(false);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			MoleClientMainHandler.mainFrame.add(moleUI);
			moleUI.setVisible(true);
		}
		
		else if (s[0].equals("HUMANSTART")) {
			GameStart gameStart = new GameStart(s[4], s[5]);
			gameStart.humanPlayer.setText(s[4]);
			gameStart.molePlayer.setText(s[5]);
			game = new Game(ctx, s[1]);
			MoleClientMainHandler.roomTest.setVisible(false);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			MoleClientMainHandler.mainFrame.add(game);
			game.start();
		}
		else if (readMessage.equals("RIGHT"))
			moleUI.moleMove(readMessage);
	}
}
