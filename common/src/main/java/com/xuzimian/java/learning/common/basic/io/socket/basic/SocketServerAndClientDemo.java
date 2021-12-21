package com.xuzimian.globaldemo.common.basic.io.socket.basic;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: global-demo
 * @description: SocketServer
 * @author: xzm
 * @create: 2019-05-23 16:26
 **/
public class SocketServerAndClientDemo {

    @Before
    public void ServerStart() {
        try {
            //创建一个ServerSocket监听8080端口
            ServerSocket server = new ServerSocket(8080);
            //等待请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    try {
                        socket = server.accept();
                        //接收到请求后使用socket进行通信，创建BufferedReader用于读取数据，
                        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String line = is.readLine();
                        System.out.println("received from client: " + line);
                        //创建PrintWriter，用于发送数据
                        PrintWriter pw = new PrintWriter(socket.getOutputStream());
                        pw.println("received data: " + line);
                        pw.flush();
                        //关闭资源
                        pw.close();
                        is.close();
                        socket.close();
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clientRequest() {
        String msg = "Client Data";
        try {
            //创建一个Socket，跟本机的8080端口连接
            Socket socket = new Socket("127.0.0.1", 8080);
            //使用Socket创建PrintWriter和BufferedReader进行读写数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
            pw.println(msg);
            pw.flush();
            //接收数据
            String line = is.readLine();
            System.out.println("received from server: " + line);
            //关闭资源
            pw.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
