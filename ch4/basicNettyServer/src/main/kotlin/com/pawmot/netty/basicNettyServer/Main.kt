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
                    else -> {
                        throw IllegalArgumentException()
                    }
                }

        // start
        val port = args[1].toInt()
        NettyOioServer().serve(port, transportSpecifier)
    } catch (ex: IllegalArgumentException) {
        printUsageAndExit()
    }
}

private fun printUsageAndExit() {
    println("Usage: please specify transport and port number ([oio|nio] <port>).")
    exitProcess(1)
}