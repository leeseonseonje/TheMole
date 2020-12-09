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
			MoleClientMainHandler.inRoom.setVisible(false);
			gameStart = new GameStart(s[3], s[4]);
			gameStart.molePlayer.setText(s[3]);
			gameStart.humanPlayer.setText(s[4]);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			if(MoleClientMainHandler.homePanel.getPlayStatus() == true)
				MoleClientMainHandler.homePanel.getSoundToPlay().pause();	
			moleUI = new MoleUI(ctx, s[1], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]),
					Integer.parseInt(s[8]), Integer.parseInt(s[9]), Integer.parseInt(s[10]));
			MoleClientMainHandler.mainFrame.add(moleUI);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			moleUI.setVisible(true);
		}

		else if (s[0].equals("HUMANSTART")) {
			MoleClientMainHandler.inRoom.setVisible(false);
			GameStart gameStart = new GameStart(s[3], s[4]);
			gameStart.humanPlayer.setText(s[3]);
			gameStart.molePlayer.setText(s[4]);
			MoleClientMainHandler.mainFrame.add(gameStart);
			gameStart.setVisible(true);
			if(MoleClientMainHandler.homePanel.getPlayStatus() == true)
				MoleClientMainHandler.homePanel.getSoundToPlay().pause();
			humanUI = new HumanUI(ctx, s[1], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]),
					Integer.parseInt(s[8]), Integer.parseInt(s[9]), Integer.parseInt(s[10]));
			MoleClientMainHandler.mainFrame.add(humanUI);
			Thread.sleep(2000);
			gameStart.setVisible(false);
			humanUI.setVisible(true);
		}

		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("GAMECOUNT")) {
				if (moleUI == null) {
					humanUI.setSecond(humanUI.getSecond()-1);
					System.out.println(humanUI.getSecond());
					humanUI.setDdSecond(humanUI.getdFormat().format(humanUI.getSecond()));
					humanUI.setDdMinute(humanUI.getdFormat().format(humanUI.getMinute()));
					humanUI.getCounterLabel().setText(humanUI.getDdMinute() + ":" + humanUI.getDdSecond());
					if (humanUI.getSecond() == -1) {
						humanUI.setSecond(59);
						humanUI.setMinute(humanUI.getMinute() -1);

						humanUI.setDdSecond(humanUI.getdFormat().format(humanUI.getSecond()));
						humanUI.setDdMinute(humanUI.getdFormat().format(humanUI.getMinute()));
						humanUI.getCounterLabel().setText(humanUI.getDdMinute() + ":" + humanUI.getDdSecond());
					}
				} else {
					moleUI.setSecond(moleUI.getSecond()-1);
					System.out.println(moleUI.getSecond());
					moleUI.setDdSecond(moleUI.getdFormat().format(moleUI.getSecond()));
					moleUI.setDdMinute(moleUI.getdFormat().format(moleUI.getMinute()));
					moleUI.getCounterLabel().setText(moleUI.getDdMinute() + ":" + moleUI.getDdSecond());
					if (moleUI.getSecond() == -1) {
						moleUI.setSecond(59);
						moleUI.setMinute(moleUI.getMinute() -1);

						moleUI.setDdSecond(moleUI.getdFormat().format(moleUI.getSecond()));
						moleUI.setDdMinute(moleUI.getdFormat().format(moleUI.getMinute()));
						moleUI.getCounterLabel().setText(moleUI.getDdMinute() + ":" + moleUI.getDdSecond());
					}
				}
			}
			else if (s[i].equals("RIGHT")) {
				if (moleUI == null)
					humanUI.getHuman().humanMove(s[i]);
				else
					moleUI.getMoleInHumanPerformance().moleInHumanMove(s[i]);
			} else if (s[i].equals("LEFT")) {
				if (moleUI == null)
					humanUI.getHuman().humanMove(s[i]);
				else
					moleUI.getMoleInHumanPerformance().moleInHumanMove(s[i]);
			} else if (s[i].equals("LEFTSTOP")) {
				if (moleUI == null)
					humanUI.getHuman().humanMove(s[i]);
				else
					moleUI.getMoleInHumanPerformance().moleInHumanMove(s[i]);
			} else if (s[i].equals("RIGHTSTOP")) {
				if (moleUI == null)
					humanUI.getHuman().humanMove(s[i]);
				else
					moleUI.getMoleInHumanPerformance().moleInHumanMove(s[i]);
			} else if (s[i].equals("v1EAT")) {
				if (moleUI == null) {
				humanUI.getV1().setVisible(false);
				humanUI.setVegcount(humanUI.getVegcount() - 1);
				humanUI.getVegCountLabel().setText(humanUI.getVegcount() + "");
			} else {
				moleUI.getV1().setVisible(false);
				moleUI.getV1().setBounds(0, 0, 0, 0);
				moleUI.setVegcount(moleUI.getVegcount()-1);
				moleUI.getVegcountLabel().setText(moleUI.getVegcount() + "");
			}
		} else if (s[i].equals("v1")) {
			int x = Integer.parseInt(s[i+1]);
			int y = Integer.parseInt(s[i+2]);
			if (moleUI == null) {
				humanUI.getV1().setIcon(vegetableThread.vegetables[y]);
				humanUI.getV1().setBounds(x, 240, 32, 32);
				humanUI.getV1().setVisible(true);
			} else {
				moleUI.getV1().setIcon(vegetableThread.vegetables[y]);
				moleUI.getV1().setBounds(x, 240, 32, 32);
				moleUI.getV1().setVisible(true);
			}
		} else if (readMessage.equals("v2EAT")) {
			if (moleUI == null) {
				humanUI.getV2().setVisible(false);
				humanUI.getV2().setBounds(0, 0, 0, 0);
				humanUI.setVegcount(humanUI.getVegcount() - 1);
				humanUI.getVegCountLabel().setText(humanUI.getVegcount() + "");
			} else {
				moleUI.getV2().setVisible(false);
				moleUI.getV2().setBounds(0, 0, 0, 0);
				moleUI.setVegcount(moleUI.getVegcount()-1);
				moleUI.getVegcountLabel().setText(moleUI.getVegcount() + "");
			}
		} else if (s[i].equals("v2")) {
			int x = Integer.parseInt(s[i+1]);
			int y = Integer.parseInt(s[i+2]);
			if (moleUI == null) {
				humanUI.getV2().setIcon(vegetableThread.vegetables[y]);
				humanUI.getV2().setBounds(x, 240, 32, 32);
				humanUI.getV2().setVisible(true);
			} else {
				moleUI.getV2().setIcon(vegetableThread.vegetables[y]);
				moleUI.getV2().setBounds(x, 240, 32, 32);
				moleUI.getV2().setVisible(true);
			}
		} else if (readMessage.equals("v3EAT")) {
			if (moleUI == null) {
				humanUI.getV3().setVisible(false);
				humanUI.getV3().setBounds(0, 0, 0, 0);
				humanUI.setVegcount(humanUI.getVegcount() - 1);
				humanUI.getVegCountLabel().setText(humanUI.getVegcount() + "");
			} else {
				moleUI.getV3().setVisible(false);
				moleUI.getV3().setBounds(0, 0, 0, 0);
				moleUI.setVegcount(moleUI.getVegcount()-1);
				moleUI.getVegcountLabel().setText(moleUI.getVegcount() + "");
			}
		} else if (s[i].equals("v3")) {
			int x = Integer.parseInt(s[i+1]);
			int y = Integer.parseInt(s[i+2]);
			if (moleUI == null) {
				humanUI.getV3().setIcon(vegetableThread.vegetables[y]);
				humanUI.getV3().setBounds(x, 240, 32, 32);
				humanUI.getV3().setVisible(true);
			} else {
				moleUI.getV3().setIcon(vegetableThread.vegetables[y]);
				moleUI.getV3().setBounds(x, 240, 32, 32);
				moleUI.getV3().setVisible(true);
			}
		} else if (s[i].equals("ITEM")) {
			int a = Integer.parseInt(s[i+1]);
			int b = Integer.parseInt(s[i+2]);
			if (moleUI == null) {
				humanUI.getI1().setTimerstop(false);
				humanUI.getI2().setTimerstop(false);
				humanUI.getI1().setBounds(a, 270, 40, 40);
				humanUI.getI1().setVisible(true);
				humanUI.getI2().setBounds(b, 270, 40, 40);
				humanUI.getI2().setVisible(true);
			} else {
				moleUI.getI1().setBounds(a, 270, 40, 40);
				moleUI.getI1().setVisible(true);
				moleUI.getI2().setBounds(b, 270, 40, 40);
				moleUI.getI2().setVisible(true);
			}
		} else if (readMessage.equals("MOLEITEM1EAT"))
			if (moleUI == null)
				humanUI.getI1().setVisible(false);
			else {
				moleUI.getI1().setBounds(0, 0, 0, 0);
				moleUI.getI1().setVisible(false);
			}
		else if (readMessage.equals("MOLEITEM2EAT"))
			if (moleUI == null)
				humanUI.getI2().setVisible(false);
			else {
				moleUI.getI2().setBounds(0, 0, 0, 0);
				moleUI.getI2().setVisible(false);
			}
		else if (s[i].equals("HUMANITEM1EAT")) {
			if (moleUI == null) {
				humanUI.getI1().setTimerstop(true);
				humanUI.getI1().setVisible(false);
			} else {
				moleUI.getI1().setVisible(false);
				moleUI.getI1().setBounds(0, 0, 0, 0);
			}
		} else if (s[i].equals("HUMANITEM2EAT")) {
			if (moleUI == null) {
				humanUI.getI2().setTimerstop(true);
				humanUI.getI2().setVisible(false);
			} else {
				moleUI.getI2().setVisible(false);
				moleUI.getI2().setBounds(0, 0, 0, 0);
			}
		} else if (s[i].equals("HUMANSPEEDUP"))
			moleUI.getMoleInHumanPerformance().setHumanspeed(7);
		else if (s[i].equals("HUMANSPEEDDOWN"))
			moleUI.getMoleInHumanPerformance().setHumanspeed(5);
		else if (s[i].equals("MOLEMOVE")) {
			int x = Integer.parseInt(s[i + 1]);
				int y = Integer.parseInt(s[i + 2]);
				humanUI.moleMessage(s[i + 3], x, y);
			} else if (s[i].equals("MOLETRAP"))
				humanUI.getHuman().setHumanspeed(0);
			else if (s[i].equals("MOLETRAPSTOP"))
				humanUI.getHuman().setHumanspeed(5);
			else if (s[i].equals("HUMANTRAP"))
				moleUI.setMoleKill(false);
			else if (s[i].equals("HUMANTRAPSTOP"))
				moleUI.setMoleKill(true);
			else if (s[i].equals("SNAKE")) {
				int status = Integer.parseInt(s[i+1]);
				if (moleUI == null)
					humanUI.makeSnake(status);
				else
					moleUI.makeSnake(status);
			}else if (s[i].equals("SNAKEDIE")) {
				if (moleUI == null)
					humanUI.getSnake().snakeDie();
				else
					moleUI.getSnake().snakeDie();
			}else if (s[i].equals("BULLET")) {
				int direction = Integer.parseInt(s[i+1]);
				int status = Integer.parseInt(s[i+2]);
				if (humanUI == null)
					moleUI.getMoleInHumanPerformance().bullet(moleUI.getMoleInHumanPerformance().getX(), direction, status);
				else
					humanUI.getHuman().bullet(humanUI.getHuman().getX(), direction, status);
				
			} else if (s[i].equals("MOLEDIE")) {
				if(moleUI == null) {
					humanUI.moleDie(s[i+1]);
					if(humanUI.getHuman().getB() != null)
						humanUI.getHuman().getB().setVisible(false);
				}
				else {
					moleUI.moleDie(s[i+1]);
					if(moleUI.getMoleInHumanPerformance().getB() != null) 
						moleUI.getMoleInHumanPerformance().getB().setVisible(false);
				}
			} else if (s[i].equals("MINUSLIFE")) {
				if(moleUI == null) {
					humanUI.getHuman().minushumanlife();
                    humanUI.getSnake().snakeDie();
				}
				else {
					moleUI.getMoleInHumanPerformance().minushumanlife();
                    moleUI.getSnake().snakeDie();
				}
			}
		} 
	}
}
