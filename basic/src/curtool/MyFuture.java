package curtool;

import java.util.concurrent.*;

public class MyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 作为任务，Runnable
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 3);
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交任务
        es.submit(futureTask);
        Integer result = futureTask.get();
        System.out.println(result);

        // 作为Future 可以获取任务的执行结果
        FutureTask<Integer> futureTaskResult = new FutureTask<>(() -> 1 + 7);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.submit(futureTaskResult);
        Integer re = futureTaskResult.get();
        System.out.println(re);

    }
}
