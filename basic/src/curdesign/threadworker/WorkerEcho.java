package curdesign.threadworker;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

public class WorkerEcho {
    public static void main(String[] args) throws IOException {
        ExecutorService es = new ThreadPoolExecutor(50,500,
                60L, TimeUnit.SECONDS,
                // 创建有界队列
                new LinkedBlockingDeque<>(2000),
                // 根绝业务需求实现 ThreadFactory
                r -> {
                    return new Thread(r,"echo-" + r.hashCode());
                },
                // 根据业务需求实现拒绝策略， RejectedExecutionHandler
                new ThreadPoolExecutor.CallerRunsPolicy());
        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(new InetSocketAddress(808));

        // 处理请求
        try {
            // 接收请求
            SocketChannel sc = ssc.accept();
            // 将请求处理任务提交给线程池
            es.execute(() -> {
                try {
                    // 读 socket
                    ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                    sc.read(rb);
                    // 模拟处理请求
                    Thread.sleep(2000);
                    // 写 socket
                    ByteBuffer wb = (ByteBuffer) rb.flip();
                    sc.write(wb);
                    // 关闭 Socket
                    sc.close();
                }catch (Exception e) {
                    throw new UncheckedIOException((IOException) e);
                }

            });
        }finally {
            ssc.close();
            // 关闭线程池
            es.shutdown();
        }
    }
}
