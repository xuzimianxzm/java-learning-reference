package com.xuzimian.globaldemo.common.basic.io.socket.proxy.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * curl -X GET "http://www.baidu.com"
 * <p>
 * curl -H "Content-Type: application/json"  -X POST  -d '{"user_id": "123", "coin":100, "success":1,  "msg":"OK!"}'"http://www.baidu.com"
 * <p>
 * Http CONNECT request format:
 * CONNECT www.baidu.com:443 HTTP/1.1
 * Host: www.baidu.com:443
 * Proxy-Connection: keep-alive
 * User-Agent: Mozilla/5.0 (X11; Linux x86_64; MuMu 6.0.1 Build/V417IR) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.34 Safari/534.24
 */
public class HttpProxy {
    private ServerSocket server;
    private Charset charset = StandardCharsets.UTF_8;
    private int timeout = 1000 * 2;
    private int port;

    public HttpProxy(Charset charset, int port) {
        this.charset = charset;
        this.port = port;
    }

    public HttpProxy(int port) {
        this.port = port;
    }

    public HttpProxy() {
        this.port = 8080;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public void setPort(int port) {
        this.port = port;
    }


    private void init() throws IOException {
        server = new ServerSocket(port);
        System.out.println("HTTP Proxy started on " + port);
    }

    public void syncStart() throws IOException {
        init();
        process();
    }

    public void aysnStart() throws IOException {
        init();
        new Thread(this::process);
    }


    public void process() {
        // 线程运行函数
        while (true) {
            try (Socket connection = server.accept()) {
                connection.setSoTimeout(timeout);
                //接受到请求，就新建一个处理请求的服务线程，将当前请求传递到线程里面
                new HttpServerThread(connection).run();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void testReadLines(Socket connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
        System.out.println("------- start    --------");

        int postDataI = -1;
        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = reader.readLine()) != null && (line.length() != 0)) {
            stringBuffer.append(line);
            stringBuffer.append("\r\n");
            if (line.indexOf("Content-Length:") > -1) {
                postDataI = new Integer(
                        line.substring(
                                line.indexOf("Content-Length:") + 16,
                                line.length()));
            }
        }
        String postData = "";
        if (postDataI > 0) {
            char[] charArray = new char[postDataI];
            reader.read(charArray, 0, postDataI);
            postData = new String(charArray);
            stringBuffer.append("\r\n");
            stringBuffer.append(postData);
        }

        System.out.println(stringBuffer.toString());
        System.out.println("-------   end  --------");
        System.out.println(stringBuffer.toString().getBytes().length);
        System.out.println();
    }

}