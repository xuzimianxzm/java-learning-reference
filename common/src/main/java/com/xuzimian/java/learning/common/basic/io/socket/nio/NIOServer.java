package com.xuzimian.globaldemo.common.basic.io.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @program: global-demo
 * @description: NioSocket
 * @author: xzm
 * @create: 2019-05-23 16:45
 **/
public class NIOServer {

    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel，监听8080端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        //设置为非阻塞模式
        ssc.configureBlocking(false);

        //为ssc注册选择器
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //创建处理器
        ServerHandler handler = new ServerHandler(1024);

        while (true) {
            // 等待请求，每次等待阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传参数将一直阻塞
            if (selector.select(3000) == 0) {
                System.out.println("等待请求超时。。。");
                continue;
            }
            System.out.println("处理请求。。。");
            // 获取待处理的SelectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                try {
                    // 接收到连接请求时
                    if (key.isAcceptable()) {
                        handler.handleAccept(key);
                    }
                    // 读数据
                    if (key.isReadable()) {
                        handler.handleRead(key);
                    }
                } catch (IOException ex) {
                    keyIter.remove();
                    continue;
                }
                // 处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
                keyIter.remove();
            }
        }
    }

}
