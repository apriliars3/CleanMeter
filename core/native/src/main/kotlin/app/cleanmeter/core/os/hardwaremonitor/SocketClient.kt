package app.cleanmeter.core.os.hardwaremonitor

import app.cleanmeter.core.os.util.getByteBuffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketException
import java.nio.ByteBuffer
import java.nio.ByteOrder

private const val COMMAND_SIZE = 2
private const val LENGTH_SIZE = 4

sealed class Packet {
    data class Data(val data: ByteArray) : Packet()
    data class PresentMonApps(val data: ByteArray) : Packet()
    data class SelectPresentMonApp(val name: String) : Packet()
}

object SocketClient {

    private var socket = Socket()

    private val packetChannel = Channel<Packet>(Channel.CONFLATED)
    val packetFlow: Flow<Packet> = packetChannel.receiveAsFlow()

    init {
        connect()
    }

    private fun connect() = CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            // try open a connection with HardwareMonitor
            if (!socket.isConnected) {
                try {
                    println("Trying to connect")
                    socket = Socket()
                    socket.connect(InetSocketAddress("0.0.0.0", 31337))
                    println("Connected ${socket.isConnected}")
                } catch (ex: Exception) {
                    println("Couldn't connect ${ex.message}")
//                    if (ex !is SocketException) {
                        ex.printStackTrace()
//                    }
                } finally {
                    delay(500)
                    continue
                }
            }

            val inputStream = socket.inputStream
            while(socket.isConnected) {
                try {
                    val command = getCommand(inputStream)
                    val size = getSize(inputStream)
                    when (command) {
                        Command.Data -> packetChannel.trySend(Packet.Data(inputStream.readNBytes(size)))
                        Command.PresentMonApps -> packetChannel.trySend(Packet.PresentMonApps(inputStream.readNBytes(size)))
                        Command.RefreshPresentMonApps -> Unit
                        Command.SelectPresentMonApp -> Unit
                    }
                } catch (e: SocketException) {
                    socket.close()
                    socket = Socket()
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getCommand(inputStream: InputStream): Command {
        val buffer = getByteBuffer(inputStream, COMMAND_SIZE)
        return Command.fromValue(buffer.short)
    }

    private fun getSize(inputStream: InputStream): Int {
        val buffer = getByteBuffer(inputStream, LENGTH_SIZE)
        return buffer.int
    }

    fun sendPacket(selectPresentMonApp: Packet.SelectPresentMonApp) {
        if (socket.isConnected) {
            val nameBytes = selectPresentMonApp.name.toByteArray()
            val buffer = ByteBuffer.allocate(2 + 2 + nameBytes.count()).order(ByteOrder.LITTLE_ENDIAN).apply {
                putShort(Command.SelectPresentMonApp.value)
                putShort(nameBytes.size.toShort())
                put(nameBytes)
            }.array()
            println("Sending ${buffer.count()} bytes")
            socket.outputStream.apply {
                write(buffer)
                flush()
            }
        }
    }
}
