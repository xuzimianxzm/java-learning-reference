package com.xuzimian.globaldemo.common.basic.io.socket.proxy.http;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by cjl on 2015/9/8.
 */
public class HttpServerThread extends Thread {

    private Socket connection;

    public HttpServerThread(Socket _connection) {
        connection = _connection;
        start();
    }

    @Override
    public void run() { // 线程运行函数
        byte buf[] = new byte[10000];
        int creadLen = 0, sreadlen = 0;
        int i;
        String s = null, s1 = null, s2 = null;

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setPort(80);

        DataInputStream clientInput = null; //客户端输入流
        DataOutputStream clientOutput = null; //客户端输出流
        try {
            clientInput = new DataInputStream(connection.getInputStream());
            clientOutput = new DataOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            creadLen = clientInput.read(buf, 0, 10000); // 从客户端读数据
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (creadLen > 0) { // 读到数据
            s = new String(buf);
            s = getOneLine(s);

            if (s.contains("GET ")) {
                requestInfo.setMethod(HttpRequestMethod.GET);
            }

            if (s.contains("CONNECT ")) {
                // 读到 CONNECT 请求 , 返回 HTTP 应答
                s1 = s.substring(s.indexOf("CONNECT ") + 8, s.indexOf("HTTP/"));
                s2 = s1;
                s1 = s1.substring(0, s1.indexOf(":"));
                s2 = s2.substring(s2.indexOf(":") + 1);
                s2 = s2.substring(0, s2.indexOf(" "));
                try {
                    sendAck(clientOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                requestInfo.setPort(Integer.parseInt(s2));
                requestInfo.setMethod(HttpRequestMethod.CONNECT);
            }

            if (s.contains("POST "))// 如读到 POST 请求
            {
                requestInfo.setMethod(HttpRequestMethod.POST);
            }

            if (s.contains("http://") && s.contains("HTTP/")) {
                // 从所读数据中取域名和端口号
                s1 = s.substring(s.indexOf("http://") + 7, s.indexOf("HTTP/"));
                s1 = s1.substring(0, s1.indexOf("/"));
                if (s1.contains(":")) {
                    s2 = s1;
                    s1 = s1.substring(0, s1.indexOf(":"));
                    s2 = s2.substring(s2.indexOf(":") + 1);
                    // port = Integer.parseInt(s2);
                    // httpRequestMethod = 0;
                    requestInfo.setPort(Integer.parseInt(s2));
                    requestInfo.setMethod(HttpRequestMethod.GET);
                }
            }

            try {
                serverProcess(buf, creadLen, sreadlen, s1, clientInput, clientOutput, requestInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            clientInput.close();
            clientOutput.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void serverProcess(byte[] buf, int creadlen, int sreadlen, String s1, DataInputStream clientInput, DataOutputStream clientOutput, RequestInfo requestInfo) throws IOException {
        Socket server;
        if (s1 != null) {
            server = new Socket(s1, requestInfo.getPort());
            // 根据读到的域名和端口号建立套接字
            DataInputStream serverInput = new DataInputStream(server.getInputStream()); //服务端输入流
            DataOutputStream serverOutput = new DataOutputStream(server.getOutputStream());//服务端输出流

            // 向外网发送 POST 请求
            switch (requestInfo.getMethod()) {
                case GET:
                    doGet(buf, creadlen, serverInput, clientOutput, serverOutput);
                    break;
                case POST:
                    doPost(buf, creadlen, sreadlen, clientInput, serverInput, clientOutput, serverOutput);
                    break;
                case CONNECT:
                    doConnect(buf, sreadlen, clientInput, serverInput, clientOutput, serverOutput);
                    break;
                default:
                    System.out.println(String.format(" ----- 无法处理的请求类型：", requestInfo.getMethod()));
            }

            // 执行关闭操作
            serverInput.close();
            serverOutput.close();
            server.close();
        }
    }

    /**
     * 发生确认
     *
     * @param cout 输出流
     * @throws IOException
     */
    private void sendAck(DataOutputStream cout) throws IOException {
        String s2;
        byte[] buf2;
        s2 = "HTTP/1.0 200 Connection established\r\n";
        s2 = s2 + "Proxy-agent: proxy\r\n\r\n";
        buf2 = s2.getBytes();
        cout.write(buf2);
        cout.flush();
    }

    /**
     * 处理POST 请求
     *
     * @param buf      数据缓存区
     * @param creadlen 客户端读取的buf的长度
     * @param sreadlen 服务端读取的buf的长度
     * @param cin      客户端输入流
     * @param sin      服务端输入流
     * @param cout     客户端输出流
     * @param sout     服务端输出流
     * @throws IOException
     */
    private void doPost(byte[] buf, int creadlen, int sreadlen, DataInputStream cin, DataInputStream sin, DataOutputStream cout, DataOutputStream sout) throws IOException {
        write(buf, creadlen, sout);
        // 建立线程 , 用于从外网读数据 , 并返回给内网客户端
        HttpChannel thread1 = new HttpChannel(sin, cout);
        while ((sreadlen = cin.read(buf, 0, 10000)) != -1) { // 循环
            try {
                System.out.println("post>>" + new String(buf));
                if (sreadlen > 0) { // 读到数据 , 则发送给外网
                    write(buf, sreadlen, sout);
                }
            } catch (Exception e1) {
                break;
            }
        }
    }

    /**
     * 写数据
     *
     * @param buf      缓冲区
     * @param creadlen 读取的偏移量
     * @param sout     输出流
     * @throws IOException
     */
    private void write(byte[] buf, int creadlen, DataOutputStream sout) throws IOException {
        sout.write(buf, 0, creadlen);
        sout.flush();
    }

    /**
     * 处理HTTPconnect  待定
     *
     * @param buf      缓冲区
     * @param sreadlen 服务端读取的buf的长度
     * @param cin      客户端输入流
     * @param sin      服务端输入流
     * @param cout     客户端输出流
     * @param sout     服务端输出流
     * @return
     */
    private int doConnect(byte[] buf, int sreadlen, DataInputStream cin, DataInputStream sin, DataOutputStream cout, DataOutputStream sout) {
        // 建立线程 , 用于从外网读数据 , 并返回给内网客户端
        HttpChannel thread1 = new HttpChannel(sin, cout);
        try {
            while ((sreadlen = cin.read(buf, 0, 10000)) != -1) { // 循环
                // 从内网读数据
                if (sreadlen > 0) {
                    // 读到数据 , 则发送给外网
                    System.out.println("CONN>>" + new String(buf));
                    write(buf, sreadlen, sout);
                }
            }
        } catch (Exception e1) {

        }

        return sreadlen;
    }

    /**
     * 处理GET请求
     *
     * @param buf      缓冲区
     * @param creadlen 客户端读取buf的长度
     * @param sin      服务端输入流
     * @param cout     客户端输出流
     * @param sout     服务端输出流
     * @return
     * @throws IOException
     */
    private int doGet(byte[] buf, int creadlen, DataInputStream sin, DataOutputStream cout, DataOutputStream sout) throws IOException {
        int sreadlen;// 如读到 GET 请求，向外网发出 GET 请求
        System.out.println("[get] >> " + new String(buf));
        write(buf, creadlen, sout);
        while ((sreadlen = sin.read(buf, 0, 10000)) != -1) { // 循环
            try {
                if (sreadlen > 0) {
                    System.out.println("[get] << " + new String(buf));
                    write(buf, sreadlen, cout);
                }
            } catch (Exception e) {
                break;
            } // 异常则退出
        }
        return sreadlen;
    }

    private String getOneLine(String s) {
        if (s.contains("\r\n")) {
            s = s.substring(0, s.indexOf("\r\n"));
        }
        return s;
    }
}
