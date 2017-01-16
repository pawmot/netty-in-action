import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled.EMPTY_BUFFER
import io.netty.channel.ChannelFutureListener.CLOSE
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.CharsetUtil.UTF_8

@Sharable
class EchoServerHandler : ChannelInboundHandlerAdapter() {
    override fun channelActive(ctx: ChannelHandlerContext?) {
        println("Client connected ${ctx!!.channel().remoteAddress()}")
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        println("Client disconnected ${ctx!!.channel().remoteAddress()}")
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        val input = msg as ByteBuf
        println("Server received: ${input.toString(UTF_8)}")
        ctx!!.write(input)
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx!!.writeAndFlush(EMPTY_BUFFER).addListener { CLOSE }
    }

    // TODO: find out how it should be done
    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause!!.printStackTrace()
        ctx!!.close()
    }
}