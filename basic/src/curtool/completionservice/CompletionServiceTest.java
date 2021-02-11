package curtool.completionservice;

import java.util.concurrent.*;

public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建 completionService
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        //  异步向电商 S1 询价， submit的返回值为 Future， 将返回结果的对象 Future 加入到阻塞队列中
        completionService.submit(() -> getOne());
        completionService.submit(() -> getTwo());
        completionService.submit(() -> getThree());
        // 将询价结果保存起来
        for (int i=0; i<3; i++) {
            Integer r = completionService.take().get();
            executor.execute(() -> save(r));
        }

    }


    static Integer getOne() {
        System.out.println("查询报价One");
        return 1;
    }
    static Integer getTwo() {
        System.out.println("查询报价Two");
        return 2;
    }
    static Integer getThree() {
        System.out.println("查询报价Three");
        return 3;
    }
    static void save(Integer price) {
        System.out.println("保存报价" + price);
    }
}
