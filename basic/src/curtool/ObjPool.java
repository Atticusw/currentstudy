package curtool;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

// 使用信号量，实现一个限流器
public class ObjPool<T, R> {
    // 对象池
    final List<T> pool;
    final Semaphore se ;

    public ObjPool(int size, T t) {
        this.pool = new Vector<T>();
        for (int i=0; i<size; i++) {
            pool.add(t);
        }
        this.se = new Semaphore(size);
    }

    // 利用对象池中的对象，调用func
    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        se.acquire();
        try {
            // 取出最前面元素
            t = pool.remove(0);
            return func.apply(t);
        }finally {
            // 将使用后的元素添加到对象池中
            pool.add(t);
            se.release();
        }
    }

    public static void main(String[] args)  {
        ObjPool<Long, String> objPool = new ObjPool<>(10, 2L);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                try {
                    objPool.exec(t -> {
                        System.out.println(t);
                        return t.toString();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
