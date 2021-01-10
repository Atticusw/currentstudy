package curtool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 利用了 volatile 相关的 Happens-Before 规则。
 */
public class X {
    private final Lock rtl = new ReentrantLock();
    int value;

    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value += 1;
        } finally {
            // 保证释放锁
            rtl.unlock();
        }
    }


}
