package curtool;

import java.util.concurrent.Semaphore;

// 使用信号量 实现互斥+1
public class AddOneSemphore {
    // 就是一个非公平锁
    private static final Semaphore sem = new Semaphore(1);
    private static int count = 0;

    static void addOne() throws InterruptedException {
        // 相当于加锁操作
        sem.acquire();
        try {
            count +=1;
        }finally {
            // 相当于释放锁
            sem.release();
        }
    }



}
