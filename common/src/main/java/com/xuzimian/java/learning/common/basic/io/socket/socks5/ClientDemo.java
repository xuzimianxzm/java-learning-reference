package com.xuzimian.globaldemo.common.basic.io.socket.socks5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

//socks5 客户端
public class ClientDemo {
    static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {

        Socket client = getSocket();
        System.out.println("client ready,Please enter below :");

        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream inputStream = new DataInputStream(client.getInputStream());

        String line;
        byte[] receiveBuff = new byte[4 * 1024];
        while ((line = consoleReader.readLine()) != null) {
            outputStream.write(line.getBytes());
            outputStream.flush();
            if ("quit".equals(line)) {
                break;
            }
            int readLength = inputStream.read(receiveBuff);
            System.out.print(new String(receiveBuff, 0, readLength));
        }
    }


    private static Socket getSocket() throws IOException {
        System.out.println("Direct link please enter 1");
        String line = consoleReader.readLine();
        String host = "127.0.0.1";
        if ("1".equals(line)) {
            return new Socket(host, 888);
        }

        SocketAddress socketAddress = new InetSocketAddress(host, 888);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socketAddress);
        Socket client = new Socket(proxy);

        client.connect(new InetSocketAddress(host, 8080));//服务器的ip及地址
        return client;
    }
}
