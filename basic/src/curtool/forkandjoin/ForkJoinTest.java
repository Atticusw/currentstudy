package curtool.forkandjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {
    public static void main(String[] args) {
        // 创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(8);
        // 创建分治任务
        Fibonacci fib = new Fibonacci(30);
        // 启动分治任务，用线程池的 invoke 方法
        Integer result = fjp.invoke(fib);
        System.out.println(result);

    }
}
