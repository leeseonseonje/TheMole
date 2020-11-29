package MoleServer;

import Mole.Game.GameStart;
import Mole.Game.HumanUI;
import Mole.Game.MoleInHumanPerformance;
import Mole.Game.MoleUI;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientGameHandler extends ChannelInboundHandlerAdapter {
	public static MoleUI moleUI;
	public static HumanUI humanUI;
	private GameStart gameStart;
	private MoleInHumanPerformance moleInHumanPerformance;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String) msg;
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
			moleInHumanPerformance = new MoleInHumanPerformance(200, 225);
			moleUI.add(moleInHumanPerformance);
			MoleClientMainHandler.mainFrame.add(moleUI);
			moleUI.setVisible(true);
		}

		else if (s[0].equals("HUMANSTART")) {
			GameStart gameStart = new GameStart(s[4], s[5]);
			gameStart.humanPlayer.setText(s[4]);
			gameStart.molePlayer.setText(s[5]);
			humanUI = new HumanUI(ctx, s[1]);
			MoleClientMainHandler.roomTest.setVisible(false);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			MoleClientMainHandler.mainFrame.add(humanUI);
			humanUI.setVisible(true);
		} 
		else if (readMessage.equals("RIGHT"))
			moleInHumanPerformance.moleInHumanMove(readMessage);
		else if (readMessage.equals("LEFT"))
			moleInHumanPerformance.moleInHumanMove(readMessage);
		else if (readMessage.equals("STOP"))
			moleInHumanPerformance.moleInHumanMove(readMessage);
	}
}
