package com.pawmot.netty.basicNettyServer

import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel

class EpollTransportSpecifier : TransportSpecifier {
    override fun eventLoopGroup(): EventLoopGroup {
        return EpollEventLoopGroup()
    }

    override fun channelClass(): Class<out ServerChannel> {
        return EpollServerSocketChannel::class.java
    }
}