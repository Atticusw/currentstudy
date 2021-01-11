package curtool;

import java.util.concurrent.locks.StampedLock;

public class Point {
    private int x, y;
    final StampedLock sl = new StampedLock();

    // 计算到原点的距离
    int distanceFromOrigin() {
        // 获取乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量
        // 读的过程中可能数据被修改
        int curX = x, curY = y;
        // 判断执行读操作期间，
        // 是否存在写操作，如果存在，
        // 则 sl.validate 返回 false
        if (!sl.validate(stamp)) {
            // 有写操作，将乐观读，升级为悲观读
            stamp = sl.readLock();
            try{
                curX = x;
                curX = y;
            }finally {
                // 释放悲观读锁
                sl.unlockRead(stamp);
            }
        }

        return (int) Math.sqrt(curX * curX + curY * curY);
    }
}
