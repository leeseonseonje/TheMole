package MoleServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MoleServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//	pipeline.addLast(new LineBasedFrameDecoder(1024));
			pipeline.addLast(new StringDecoder());
			pipeline.addLast(new StringEncoder());
	/*	pipeline.addLast(new StringEncoder(), new ObjectDecoder(ClassResolvers
                .weakCachingConcurrentResolver(this
                        .getClass()
                        .getClassLoader())),new ObjectEncoder());*/
			pipeline.addLast(new MoleServerHandler());
			pipeline.addLast(new MoleServerMainHandler());
	}
}
