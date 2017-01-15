package com.pawmot.netty.plainNioServer

import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.SelectionKey.OP_READ
import java.nio.channels.SelectionKey.OP_WRITE
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import kotlin.text.Charsets.UTF_8

class PlainNioServer {
    fun serve(port: Int) {
        val serverChannel = ServerSocketChannel.open()
        serverChannel.configureBlocking(false)
        val socket = serverChannel.socket()
        val addr = InetSocketAddress(port)
        socket.bind(addr)
        val selector = Selector.open()
        serverChannel.register(selector, SelectionKey.OP_ACCEPT)
        val msg = ByteBuffer.wrap("Hi!\r\n".toByteArray(UTF_8))
        while(true) {
            try {
                selector.select()
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }
            val selectedKeys = selector.selectedKeys()
            val it = selectedKeys.iterator()
            while (it.hasNext()) {
                val key = it.next()
                it.remove()
                try {
                    if (key.isAcceptable) {
                        val server = key.channel() as ServerSocketChannel
                        val client = server.accept()
                        client.configureBlocking(false)
                        client.register(selector, OP_WRITE or OP_READ, msg.duplicate())
                        println("Accepted connection from $client")
                    }

                    if (key.isWritable) {
                        val client = key.channel() as SocketChannel
                        val buffer = key.attachment() as ByteBuffer
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break
                            }
                        }
                        client.close()
                    }
                } catch (e: IOException) {
                    key.cancel()
                    try {
                        key.channel().close()
                    } catch (e: IOException) {}
                }
            }
        }
    }
}