package curtool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 class Cache<K, V> {
    final Map<K,V> m = new HashMap<>();
    // 可重入锁，当前线程当持有这把锁的时候，这个线程还可以再一次获取这把锁。
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    // 读缓存
    V get(K key) {
        r.lock();
        try {
          return m.get(key);
        }finally {
            r.unlock();
        }
    }

    // 写缓存
    void Put(K key, V value) {
        w.lock();
        try {
            m.put(key, value);
        }finally {
            w.unlock();
        }
    }

}
