package com.pawmot.netty.plainOioServer

import java.io.IOException
import java.net.ServerSocket

class PlainOioServer {
    fun serve(port: Int) {
        val socket = ServerSocket(port)
        try {
            while(true) {
                val clientSocket = socket.accept()
                println("Accepted connection from $clientSocket")
                Thread {
                    try {
                        val out = clientSocket.outputStream
                        out.write("Hi!\r\n".toByteArray(Charsets.UTF_8))
                        out.flush()
                        clientSocket.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        try {
                            clientSocket.close()
                        } catch (e: IOException) {}
                    }
                }.start()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}