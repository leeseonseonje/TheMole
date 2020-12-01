package MoleServer;

import Mole.Game.GameStart;
import Mole.Game.HumanUI;
import Mole.Game.MoleInHumanPerformance;
import Mole.Game.MoleUI;
import Mole.Game.vegetableThread;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MoleClientGameHandler extends ChannelInboundHandlerAdapter {
	public static MoleUI moleUI;
	public static HumanUI humanUI;
	private GameStart gameStart;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String readMessage = (String) msg;
		String[] s = readMessage.split(",");

		if (s[0].equals("MOLESTART")) {
			gameStart = new GameStart(s[3], s[4]);
			gameStart.molePlayer.setText(s[3]);
			gameStart.humanPlayer.setText(s[4]);
			moleUI = new MoleUI(ctx, s[1], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]), Integer.parseInt(s[9]), Integer.parseInt(s[10]));
			System.out.println(s[5]);
			System.out.println(s[6]);
			System.out.println(s[7]);
			MoleClientMainHandler.roomTest.setVisible(false);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			MoleClientMainHandler.mainFrame.add(moleUI);
			moleUI.setVisible(true);
		}

		else if (s[0].equals("HUMANSTART")) {
			GameStart gameStart = new GameStart(s[3], s[4]);
			gameStart.humanPlayer.setText(s[3]);
			gameStart.molePlayer.setText(s[4]);
			humanUI = new HumanUI(ctx, s[1], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]), Integer.parseInt(s[9]), Integer.parseInt(s[10]));
			MoleClientMainHandler.roomTest.setVisible(false);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			MoleClientMainHandler.mainFrame.add(humanUI);
			humanUI.setVisible(true);
		} 
		else if (readMessage.equals("RIGHT"))
			moleUI.moleInHumanPerformance.moleInHumanMove(readMessage);
		else if (readMessage.equals("LEFT"))
			moleUI.moleInHumanPerformance.moleInHumanMove(readMessage);
		else if (readMessage.equals("STOP"))
			moleUI.moleInHumanPerformance.moleInHumanMove(readMessage);
		else if (readMessage.equals("v1EAT"))
			humanUI.getV1().setVisible(false);
		else if (s[0].equals("MOLEv1")) {
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			moleUI.getV1().setIcon(vegetableThread.vegetables[y]);
			moleUI.getV1().setBounds(x, 255, 32, 32);
			moleUI.getV1().setVisible(true);
		}
		else if (s[0].equals("HUMANv1")) {
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			humanUI.getV1().setIcon(vegetableThread.vegetables[y]);
			humanUI.getV1().setBounds(x, 255, 32, 32);
			humanUI.getV1().setVisible(true);
		}
		else if (readMessage.equals("v2EAT"))
			humanUI.getV2().setVisible(false);
		else if (s[0].equals("MOLEv2")) {
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			moleUI.getV2().setIcon(vegetableThread.vegetables[y]);
			moleUI.getV2().setBounds(x, 255, 32, 32);
			moleUI.getV2().setVisible(true);
		}
		else if (s[0].equals("HUMANv2")) {
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			humanUI.getV2().setIcon(vegetableThread.vegetables[y]);
			humanUI.getV2().setBounds(x, 255, 32, 32);
			humanUI.getV2().setVisible(true);
		}
		else if (readMessage.equals("v3EAT"))
			humanUI.getV3().setVisible(false);
		else if (s[0].equals("MOLEv3")) {
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			moleUI.getV3().setIcon(vegetableThread.vegetables[y]);
			moleUI.getV3().setBounds(x, 255, 32, 32);
			moleUI.getV3().setVisible(true);
		}
		else if (s[0].equals("HUMANv3")) {
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			humanUI.getV3().setIcon(vegetableThread.vegetables[y]);
			humanUI.getV3().setBounds(x, 255, 32, 32);
			humanUI.getV3().setVisible(true);
		}
	}
}