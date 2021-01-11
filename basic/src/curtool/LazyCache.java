package curtool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class LazyCache<K, V> {
    final Map<K, V> m = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    // 读缓存
    V get(K key) {
        V v = null;
        r.lock();
        try {
            v = m.get(key);
        }finally {
            r.unlock();
        }

        if (v != null) {
            return v;
        }
        // 缓存数据不存在，查询数据库
        w.lock();
        try {
            // 再次验证
            // 其他的线程可能已经查询过数据库
            // 再次验证的方式，能够避免高并发场景下重复查询数据的问题。
            if (v == null) {
                // 查询数据库，查出这个值
                v = (V) new Object();
                m.put(key, v);
            }
        }finally {
            w.unlock();
        }
        return v;
    }

    //写不变
}
