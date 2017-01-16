package com.pawmot.netty.basicNettyServer

import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel
import io.netty.channel.oio.OioEventLoopGroup
import io.netty.channel.socket.oio.OioServerSocketChannel

class OioTransportSpecifier : TransportSpecifier {
    override fun eventLoopGroup(): EventLoopGroup {
        return OioEventLoopGroup()
    }

    override fun channelClass(): Class<out ServerChannel> {
        return OioServerSocketChannel::class.java
    }
}