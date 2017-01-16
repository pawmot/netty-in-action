package com.pawmot.netty.plainNioServer

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        System.err.println("Usage: ${PlainNioServer::class.simpleName} <port>")
        exitProcess(1)
    }
    val port = args[0].toInt()
    PlainNioServer().serve(port)
}