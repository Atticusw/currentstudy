import sun.invoke.empty.Empty;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();
    // 条件变量 队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量 队列不空
    final Condition notEmpty = lock.newCondition();

    // 入队
    void enq(T x) {
        // 先加锁
        lock.lock();
        try {
            // true 只是个符号，表示队列已经满了
            while (true) {
                // 等待队列不满
                notFull.await();
            }
            // 省略入队操作...
            // 入队后, 通知可出队
           /* notEmpty.signal();*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 出队
    void deq() {
        lock.lock();

        // 等待队列不空
        try {

            // true 只是个符号，表示队列已经空了
            while (true) {
                notEmpty.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }



}
