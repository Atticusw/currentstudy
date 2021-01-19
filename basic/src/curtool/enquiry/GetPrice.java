package curtool.enquiry;

import java.util.concurrent.*;

public class GetPrice {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> s1 = new FutureTask<>(new S1Task());
        FutureTask<Integer> s2 = new FutureTask<>(new S2Task());
        FutureTask<Integer> s3 = new FutureTask<>(new S3Task());
        long start = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.submit(s1);
        threadPool.submit(s2);
        threadPool.submit(s3);
        // 异步获取结果
        System.out.println(s1.get()+s2.get()+s3.get());
        long end = System.currentTimeMillis();
        // 耗时为10s
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(end-start));
    }
}
