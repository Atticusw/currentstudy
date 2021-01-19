package curtool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDiff {


    void diff () throws Exception{
        // 创建 2 个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 存在未对账订单
        while (true) {
            // 计数器初始值为 2
            CountDownLatch latch = new CountDownLatch(2);
            //  查询未对账订单
            executor.execute(() -> {
                // pos = getPOrders(); 业务代码，获取未对账订单
                latch.countDown();
            });

            // 查询派送订单
            executor.execute(() -> {
                //  dos = getDOrders(); 业务代码
                latch.countDown();
            });
            // 等待两个查询操作结束
            latch.wait();

            // 执行对账操作
            // ** diff = check(pos, dos);
            // 差异写入差异库
            // ** save(diff);
        }
    }
}
