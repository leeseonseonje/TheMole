package MoleServer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Room {
	private static final LinkedList<ChannelGroup> roomList = new LinkedList<ChannelGroup>();
	private static final LinkedList<String> roomHostUser = new LinkedList<String>();

	public static void roomCreat(ChannelHandlerContext ctx, String roomHost) {
		Channel Host = ctx.channel();
		ChannelGroup room = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
		room.add(Host);
		roomList.add(room);
		roomHostUser.add(roomHost);
		ctx.writeAndFlush("CREAT," + roomHost);
	}

	public static void roomJoin(ChannelHandlerContext ctx, String roomHostName, String myName) {
		Channel guest = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, roomHostName);
		if (roomList.get(index).size() > 1) 
			ctx.writeAndFlush("FULL");
		else {
		roomList.get(index).add(guest);
		System.out.println(roomList.get(index).size());
		for (Channel channel : roomList.get(index)) {
			if(channel == guest)
				guest.writeAndFlush("JOIN," + roomHostName + "," + myName);
			else
				channel.writeAndFlush("GUEST," + roomHostName + "," + myName);
			}
		}
	}
	
	public static void roomListSend(ChannelHandlerContext ctx) {
	//	System.out.println(roomHostUser);
		ctx.write("ROOMLIST,");
		for(int i = 0; i < roomHostUser.size(); i++) 
			ctx.write(roomHostUser.get(i) + ",");
		ctx.flush();
	}
	public static void roomListRefresh(ChannelHandlerContext ctx) {
	//	System.out.println(roomHostUser);
		ctx.write("REFRESH,");
		for(int i = 0; i < roomHostUser.size(); i++) 
			ctx.write(roomHostUser.get(i) + ",");
		ctx.flush();
	}
	public static void roomDelete(ChannelHandlerContext ctx, String hostName) {
		Channel me = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, hostName);
		roomHostUser.remove(hostName);
		for (Channel channel : roomList.get(index)) {
			if(channel == me) {
				me.write("REFRESH,");
				for(int i = 0; i < roomHostUser.size(); i++) 
					me.write(roomHostUser.get(i) + ",");
				me.flush();
			} else {
				channel.write("BOOM,");
				for(int i = 0; i < roomHostUser.size(); i++) 
					channel.write(roomHostUser.get(i) + ",");
				channel.flush();
			}
		}
		roomList.remove(index);
	}
	public static void roomOut(ChannelHandlerContext ctx, String hostName) {
		Channel guest = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, hostName);
		roomList.get(index).remove(guest);
		ctx.write("REFRESH,");
		for(int i = 0; i < roomHostUser.size(); i++) 
			ctx.write(roomHostUser.get(i) + ",");
		ctx.flush();
	}
	public static void readyState(ChannelHandlerContext ctx, String m, String hostName) {
		Channel guest = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, hostName);
		if (m.equals("[READY]")) { 
			for (Channel channel : roomList.get(index)) {
				if(channel != guest)
					channel.writeAndFlush("READY");
			}
		} else { 
			for (Channel channel : roomList.get(index)) {
				if(channel != guest)
					channel.writeAndFlush("CANSLE");
			}
		}
	}
	public static void startGame(ChannelHandlerContext ctx, String hostName) {
		Channel host = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, hostName);
		int r = (int)(Math.random()*2);
		for (Channel channel : roomList.get(index)) {
			if (r == 0 && channel != host) {
				host.writeAndFlush("MOLESTART");
				channel.writeAndFlush("HUMANSTART");
			} 
			else if (r == 1 && channel != host) {
				host.writeAndFlush("HUMANSTART");
				channel.writeAndFlush("MOLESTART");
			}
		}
	}
	public static void roomChatting(ChannelHandlerContext ctx, String message, String name, String hostName) {
		Channel host = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, hostName);
		for (Channel channel : roomList.get(index)) {
			channel.writeAndFlush("SENDMESSAGE," + name + ": " + message);
		}
	}
}

