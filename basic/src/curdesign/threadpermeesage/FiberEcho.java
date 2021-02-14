package curdesign.threadpermeesage;


import com.sun.xml.internal.ws.api.pipe.Fiber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class FiberEcho {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        // 处理请求
        try{
            while (true) {
                // 接收请求
                final SocketChannel sc =
                        ssc.accept();
                /*Fiber fiber = new Fiber();
                fiber.sch*/
            }
        }finally {
            ssc.close();
        }
    }
}
