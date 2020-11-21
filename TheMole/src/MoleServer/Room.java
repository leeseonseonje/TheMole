package MoleServer;

import java.util.LinkedList;
import java.util.Scanner;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Room {
	private static final LinkedList<ChannelGroup> roomList = new LinkedList<ChannelGroup>();

	public static void roomCreat(ChannelHandlerContext ctx, int roomNumber, int x) {
		Channel oner = ctx.channel();
		ChannelGroup room = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
		room.add(oner);
		roomList.add(room);
		System.out.println(roomList);
	}

	public static void roomJoin(ChannelHandlerContext ctx, int roomNumber) {
		Channel guest = ctx.channel();
		roomList.get(roomNumber-1).add(guest);
		System.out.println(roomList);
		/* for (Channel roomUser : roomList.get(0)) {
			 roomUser.writeAndFlush("1¹ø¹æ");
	     }*/
	}
	
	public static void roomListSend(ChannelHandlerContext ctx) {
		ctx.writeAndFlush("ROOMLIST," + roomList.size());
	}
}

