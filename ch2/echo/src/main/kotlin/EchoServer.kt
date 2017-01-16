import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress

class EchoServer(private val port: Int) {
    fun start() {
        val serverHandler = EchoServerHandler()
        val group = NioEventLoopGroup()
        try {
            println("Starting the server...")
            val bootstrap = ServerBootstrap()
            bootstrap.group(group)
                    .channel(NioServerSocketChannel::class.java)
                    .localAddress(InetSocketAddress(port))
                    .childHandler(object : ChannelInitializer<SocketChannel>() {
                        override fun initChannel(ch: SocketChannel?) {
                            ch!!.pipeline().addLast(serverHandler)
                        }
                    })
            val f = bootstrap.bind().sync()
            f.channel().closeFuture().sync()
        } finally {
            println("Shutting down...")
            group.shutdownGracefully().sync()
        }
    }
}