package MoleServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Room {
	public static final LinkedList<ChannelGroup> roomList = new LinkedList<ChannelGroup>();
	private static final LinkedList<String> roomHostUser = new LinkedList<String>();
	public static final HashMap<String, ChannelGroup> roomManager = new HashMap<String, ChannelGroup>();

	public static void roomCreat(ChannelHandlerContext ctx, String roomHost) {
		Channel host = ctx.channel();
		ChannelGroup room = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
		room.add(host);
		roomManager.put(roomHost, room);
		ctx.writeAndFlush("CREAT," + roomHost);
	}

	public static void roomJoin(ChannelHandlerContext ctx, String roomHostName, String myName) {
		Channel guest = ctx.channel();
		
		if (roomManager.get(roomHostName).size() > 1)
			ctx.writeAndFlush("FULL");
		else {
			roomManager.get(roomHostName).add(guest);
			for (Channel channel : roomManager.get(roomHostName)) {
				if(channel == guest)
					guest.writeAndFlush("JOIN," + roomHostName + "," + myName);
				else
					channel.writeAndFlush("GUEST," + roomHostName + "," + myName);
			}
		}
	}
	
	public static void roomListSend(ChannelHandlerContext ctx) {
		ctx.write("ROOMLIST,");
		for (Entry<String, ChannelGroup> entry : roomManager.entrySet())
			ctx.write(entry.getKey() + ",");
		ctx.flush();
	}
	public static void roomListRefresh(ChannelHandlerContext ctx) {		
		ctx.write("REFRESH,");
		for (Entry<String, ChannelGroup> entry : roomManager.entrySet())
			ctx.write(entry.getKey() + ",");
		ctx.flush();
	}
	public static void roomDelete(ChannelHandlerContext ctx, String hostName) {
		Channel me = ctx.channel();
		
		for (Channel channel : roomManager.get(hostName)) {
			if(channel == me) {
				me.write("REFRESH,");
				for (Entry<String, ChannelGroup> entry : roomManager.entrySet())
					me.write(entry.getKey() + ",");
				me.flush();
			} else {
				channel.write("BOOM,");
				for (Entry<String, ChannelGroup> entry : roomManager.entrySet())
					me.write(entry.getKey() + ",");
				me.flush();
			}
		}
		roomManager.remove(hostName);
	}
	public static void roomOut(ChannelHandlerContext ctx, String hostName) {
		Channel guest = ctx.channel();

		roomManager.get(hostName).remove(guest);
		ctx.write("REFRESH,");
		for (Entry<String, ChannelGroup> entry : roomManager.entrySet())
			ctx.write(entry.getKey() + ",");
		ctx.flush();
	}
	public static void readyState(ChannelHandlerContext ctx, String m, String hostName) {
		Channel guest = ctx.channel();
		
		if (m.equals("[READY]")) {
			for (Channel channel : roomManager.get(hostName)) {
				if(channel != guest)
					channel.writeAndFlush("READY");
			}
		} else {
			for (Channel channel : roomManager.get(hostName)) {
				if(channel != guest)
					channel.writeAndFlush("CANSLE");
			}
		}
	}
	public static void roomChatting(ChannelHandlerContext ctx, String message, String name, String hostName) {
		Channel host = ctx.channel();
	
		for (Channel channel : roomManager.get(hostName))
			channel.writeAndFlush("SENDMESSAGE," + name + ": " + message);
	}
	public static void startGame(ChannelHandlerContext ctx, String hostName) {
		Channel host = ctx.channel();
		int index = Collections.binarySearch(roomHostUser, hostName);
		int r = (int)(Math.random()*2);
		
		for (Channel channel : roomManager.get(hostName)) {
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
}

