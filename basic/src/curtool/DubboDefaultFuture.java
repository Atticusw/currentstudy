package curtool;

import com.sun.deploy.nativesandbox.comm.Response;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 当 RPC 返回结果之前，阻塞调用线程，让调用线程等待；
// 当 RPC 返回结果后，唤醒调用线程，让调用线程重新执行。
public class DubboDefaultFuture {
    private final Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    Object get(int timeout) {
        long start = System.nanoTime();
        lock.lock();
        try{
            while (!isDone("")) {
                /*done.await();*/
                long cur = System.nanoTime();
                if (isDone("") || cur - start > timeout) {
                    break;
                }
            }

        }finally {
            lock.unlock();
        }
        if (!isDone("")) {
            /*throw new TimeoutException();*/
        }
       /* return returnFromResponse();*/
        return new Object();
    }

    // 判断 RPC 结果是否返回
    boolean isDone(String resp) {
        return resp != null;
    }

    // RPC 结果返回时调用该方法
    private void doReceived(Response res) {
        lock.lock();
        try {
            /*response = res;*/
            if (done != null) {
                done.signal();
            }
        }finally {
            lock.unlock();
        }
    }

}
