package vi.aning.netty;

import io.netty.channel.Channel;
import io.netty.channel.pool.ChannelPoolHandler;

public class TestChannelPoolHandler implements ChannelPoolHandler {

	public void channelAcquired(Channel arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelAcquired");
	}

	public void channelCreated(Channel arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelCreated");
	}

	public void channelReleased(Channel arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelReleased");
	}

}
