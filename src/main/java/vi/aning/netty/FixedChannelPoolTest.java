package vi.aning.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

public class FixedChannelPoolTest {
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		final Bootstrap cb = new Bootstrap();
		InetSocketAddress addr1 = new InetSocketAddress("122.224.153.124", 8567);
		InetSocketAddress addr2 = new InetSocketAddress("120.26.40.194", 8567);

		cb.group(group).channel(NioSocketChannel.class);

		ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap = new AbstractChannelPoolMap<InetSocketAddress, SimpleChannelPool>() {
			@Override
			protected SimpleChannelPool newPool(InetSocketAddress key) {
				return new SimpleChannelPool(cb.remoteAddress(key), new TestChannelPoolHandler());
			}
		};

		// depending on when you use addr1 or addr2 you will get different
		// pools.
		final SimpleChannelPool pool = poolMap.get(addr1);
		Future<Channel> f = pool.acquire();
		f.addListener(new FutureListener<Channel>() {
			public void operationComplete(Future<Channel> f) {
				if (f.isSuccess()) {
					Channel ch = f.getNow();
					// Do somethings
					// ...
					// ...
					System.out.println("download...");
					// Release back to pool
					pool.release(ch);
				}
			}
		});
	}
}