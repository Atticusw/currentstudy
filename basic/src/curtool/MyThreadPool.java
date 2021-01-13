package curtool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

// 简化的线程池，仅用来说明工作原理
public class MyThreadPool {
    // 利用阻塞队列实现生产者 - 消费者模式
    BlockingQueue<Runnable> workQueue;
    // 保存内部工作线程
    List<WokerThread>  threads = new ArrayList<>();
    // 构造方法
    public MyThreadPool(int poolSize,
                        BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        // 创建工作线程
        for (int idx=0; idx<poolSize; idx++) {
            WokerThread work = new WokerThread();
            work.start();
            threads.add(work);
        }
    }
    // 提交任务
    void execute (Runnable command) {
        workQueue.add(command);
    }

    // 工作线程负责消费任务，并执行任务
    class  WokerThread extends Thread {
        public void run() {
            // 循环取任务并执行
            while (true) {
                Runnable task = null;
                try {
                    // 检索并且移除队列的头，如有必要，等待直到元素可用。
                    // 返回队列的头
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }
}
