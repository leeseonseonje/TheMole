package MoleServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MoleClient {
	private static final int PORT = 10002;
	
	private Channel serverChannel;
	private ChannelFuture future;
	
	public void clientConnect() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
				.channel(NioSocketChannel.class)
				.handler(new MoleClientInitializer());
				serverChannel = bootstrap.connect().sync().channel();
		} finally {
			group.shutdownGracefully();
		}
	}
}