package com.pawmot.netty.basicNettyServer

import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel

interface TransportSpecifier {
    fun eventLoopGroup(): EventLoopGroup

    fun channelClass(): Class<out ServerChannel>
}