package com.xuzimian.globaldemo.common.basic.io.socket.proxy.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketProxy extends Thread {
    private ServerSocket server;

    public SocketProxy(ServerSocket _server) {
        server = _server;
        start();
    }


    @Override
    public void run() { // 线程运行函数
        Socket connection;
        while (true) {
            try {
                connection = server.accept();
                SocketServerThread handler = new SocketServerThread(connection);
                handler.run();
            } catch (Exception e) {
            }
        }
    }
}