package com.xuzimian.globaldemo.common.basic.io.socket.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//目标服务端
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080); // 建立 SOCKS 侦听套接字
        System.out.println("Server started on " + server.getLocalPort());
        Socket connection;
        while (true) {
            try {
                connection = server.accept();
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                DataInputStream inputStream = new DataInputStream(connection.getInputStream());
                String line;
                byte[] receiveBuff = new byte[4 * 1024];
                int readLength;
                while ((readLength = inputStream.read(receiveBuff)) > 0) {
                    line = new String(receiveBuff, 0, readLength);
                    System.out.println(String.format("从 %s:%d 读到数据 %s", connection.getLocalAddress().getHostName(),
                            connection.getPort(), line));
                    outputStream.write(String.format("server 8080 received : %s \r\n", line).getBytes());
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
