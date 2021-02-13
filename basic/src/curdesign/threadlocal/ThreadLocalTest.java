package curdesign.threadlocal;

import java.text.DateFormat;

public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            DateFormat df = SafeDateFormat.get();
            System.identityHashCode(df);
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            DateFormat df = SafeDateFormat.get();
            System.identityHashCode(df);
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            DateFormat df = SafeDateFormat.get();
            System.identityHashCode(df);
        });
        t3.start();

        /*BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(4);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 8, 10, TimeUnit.SECONDS, workQueue);
        poolExecutor.execute(() ->{

        });
        poolExecutor.execute(() ->{
            DateFormat df = SafeDateFormat.get();
            System.out.println(df);
        });
        poolExecutor.execute(() ->{
            DateFormat df = SafeDateFormat.get();
            System.out.println(df);
        });*/

    }
}
