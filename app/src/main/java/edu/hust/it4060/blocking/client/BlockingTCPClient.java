package edu.hust.it4060.blocking.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.SocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.hust.it4060.common.client.AbstractClient;
import edu.hust.it4060.common.socket.BlockingSocketHandler;

public class BlockingTCPClient extends AbstractClient {

    private static final Logger log = LoggerFactory.getLogger(BlockingTCPClient.class);

    private Socket socket;
    private final BlockingSocketHandler socketHandler;

    public BlockingTCPClient(InetAddress serverAddress, int serverPort, BlockingSocketHandler socketHandler) {
        super(serverAddress, serverPort);
        this.socketHandler = socketHandler;
    }

    public BlockingTCPClient(InetSocketAddress socketAddress, BlockingSocketHandler socketHandler) {
        this(socketAddress.getAddress(), socketAddress.getPort(), socketHandler);
    }

    @Override
    public void connect() {
        try {
            socket = SocketFactory.getDefault().createSocket(serverAddress, serverPort);
            socketHandler.handle(socket);
        } catch (IOException e) {
            log.error("Failed to connect to {}:{}", serverAddress, serverPort);
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

}
