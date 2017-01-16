package com.pawmot.netty.basicNettyServer

import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

class NioTransportSpecifier : TransportSpecifier {
    override fun eventLoopGroup(): EventLoopGroup {
        return NioEventLoopGroup()
    }

    override fun channelClass(): Class<out ServerChannel> {
        return NioServerSocketChannel::class.java
    }
}