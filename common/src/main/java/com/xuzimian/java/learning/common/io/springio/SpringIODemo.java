package com.xuzimian.globaldemo.common.io.springio;

import io.netty.buffer.PooledByteBufAllocator;
import org.junit.Test;
import org.springframework.core.io.buffer.*;


import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-04-17 17:03
 **/
public class SpringIODemo {

    @Test
    public void writeAndRead() throws Exception {
        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
        DataBuffer buffer = dataBufferFactory.allocateBuffer(2);
        buffer.write(new byte[]{'a', 'b', 'c'});
        System.out.println("capacity-->" + buffer.capacity());
        System.out.println("readPosition-->" + buffer.readPosition());
        System.out.println("writePosition-->" + buffer.writePosition());
        System.out.println("writableByteCount-->" + buffer.writableByteCount());
        System.out.println("readableByteCount-->" + buffer.readableByteCount());

        int index = buffer.indexOf(p -> p == 'c', 1);
        System.out.println("index-->" + index);

        int ch = buffer.read();
        assertEquals('a', ch);

        buffer.readPosition(2);
        ch = buffer.read();
        assertEquals('c', ch);

        buffer.write((byte) 'd');
        buffer.write((byte) 'e');

        byte[] result = new byte[2];
        buffer.read(result);

        assertArrayEquals(new byte[]{'d', 'e'}, result);

        ByteBuffer buffer2 = ByteBuffer.allocate(4);
        buffer2.put((byte) '1');
        buffer2.put((byte) '2');
        buffer2.put((byte) '3');
        buffer2.put((byte) '4');
        buffer2.position(2);
        System.out.println("write before writePosition-->" + buffer.writePosition());
        buffer.write(buffer2);
        System.out.println("write after writePosition-->" + buffer.writePosition());

        ByteBuffer buffer3 = buffer.asByteBuffer(4, 5);
        ch = buffer3.get();
        assertEquals('e', ch);
        assertEquals(5, buffer3.limit());

        InputStream in = buffer.asInputStream();
        in.read(result);
        assertArrayEquals(new byte[]{'3', '4'}, result);

        DataBufferUtils.release(buffer);
    }

    @Test
    public void retainAndRelease() {
        NettyDataBufferFactory dataBufferFactory = new NettyDataBufferFactory(new PooledByteBufAllocator());
        PooledDataBuffer buffer = dataBufferFactory.allocateBuffer(4);
//		buffer.write((byte) 'a');

        buffer.retain();
        boolean result = buffer.release();
        assertFalse(result);
        result = buffer.release();
        assertTrue(result);

    }
}
