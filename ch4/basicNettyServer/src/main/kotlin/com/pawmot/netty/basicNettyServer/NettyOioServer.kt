package com.pawmot.netty.basicNettyServer

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener.CLOSE
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.util.CharsetUtil.UTF_8
import java.net.InetSocketAddress

class NettyOioServer {
    fun serve(port: Int, transportSpecifier: TransportSpecifier) {
        val buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi\r\n", UTF_8))
        val evLoopGroup = transportSpecifier.eventLoopGroup()
        try {
            val svcBootstrap = ServerBootstrap()
            svcBootstrap.group(evLoopGroup)
                    .channel(transportSpecifier.channelClass())
                    .localAddress(InetSocketAddress(port))
                    .childHandler(object : ChannelInitializer<SocketChannel>() {
                        override fun initChannel(ch: SocketChannel) {
                            ch.pipeline().addLast(object : ChannelInboundHandlerAdapter() {
                                override fun channelActive(ctx: ChannelHandlerContext) {
                                    println("Accepted connection: ${ctx.channel().remoteAddress()}")
                                    ctx.writeAndFlush(buf.duplicate()).addListener(CLOSE)
                                }
                            })
                        }
                    })
            val channelFuture = svcBootstrap.bind().sync()
            channelFuture.channel().closeFuture().sync()
        } finally {
            evLoopGroup.shutdownGracefully().sync()
        }
    }
}