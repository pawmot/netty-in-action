import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if(args.size != 2) {
        System.err.println("Usage : ${EchoClient::class.simpleName} <host> <port>")
        exitProcess(1)
    }

    val host = args[0]
    val port = args[1].toInt()
    EchoClient(host, port).start()
}