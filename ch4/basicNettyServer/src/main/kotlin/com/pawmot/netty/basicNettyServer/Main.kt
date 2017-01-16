package com.pawmot.netty.basicNettyServer

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 2) {
        printUsageAndExit()
    }

    try {
        val transportName = args[0]
        val transportSpecifier: TransportSpecifier =
                when (transportName) {
                    "oio" -> OioTransportSpecifier()
                    "nio" -> NioTransportSpecifier()
                    "epoll" -> EpollTransportSpecifier()
                    else -> {
                        throw IllegalArgumentException()
                    }
                }

        // start
        val port = args[1].toInt()
        NettyServer().serve(port, transportSpecifier)
    } catch (ex: IllegalArgumentException) {
        printUsageAndExit()
    }
}

private fun printUsageAndExit() {
    println("Usage: please specify transport and port number ([oio|nio|epoll] <port>).")
    exitProcess(1)
}