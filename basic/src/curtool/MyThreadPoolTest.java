package curtool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolTest {

    public static void main(String[] args) {
        // 创建生产者，有界阻塞队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 创建消费者，创建线程池
        MyThreadPool pool = new MyThreadPool(10, workQueue);
        pool.execute(() -> {
            System.out.println("1223");
        });

        ThreadPoolExecutor  pools =
                new ThreadPoolExecutor(2, 4, 5, TimeUnit.MINUTES,workQueue);
    }
}
