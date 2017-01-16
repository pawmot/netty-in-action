
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.CharsetUtil.UTF_8

@Sharable
class EchoClientHandler : SimpleChannelInboundHandler<ByteBuf>() {
    override fun channelActive(ctx: ChannelHandlerContext?) {
        ctx!!.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", UTF_8))
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ByteBuf?) {
        println("Client received: ${msg!!.toString(UTF_8)}")
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause!!.printStackTrace()
        ctx!!.close()
    }
}