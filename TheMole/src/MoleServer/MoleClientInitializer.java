package MoleServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MoleClientInitializer extends ChannelInitializer {

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//	pipeline.addLast(new LineBasedFrameDecoder(1024));
			pipeline.addLast(new StringDecoder());
			pipeline.addLast(new StringEncoder());
			pipeline.addLast(new MoleClientHandler());
			pipeline.addLast(new MoleClientMainHandler());
	}
	
}
