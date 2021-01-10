package curtool;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 可重入锁，顾名思义，指的是线程可以重复获取同一把锁。
public class ReentrantX {
    private final Lock rtl = new ReentrantLock();
    int value;

    int get() {
        // 获取锁
        rtl.lock();
        try {
            return value;
        } finally {
            rtl.unlock();
        }
    }

    void addOne() {
        // 获取锁
        rtl.lock();
        try {
            // 访问 get 方法的时候会再次去获得这个锁
            // 加入不是 ReentrantLock , 那么它就会死锁。
            value = get() + 1;
        } finally {
            rtl.unlock();
        }
    }

}
