package curtool.completionservice;

import java.util.ArrayList;
import java.util.concurrent.*;

public class DubboForking {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        ArrayList<Future> futures = new ArrayList<>(3);
        futures.add(cs.submit(() -> getOne()));
        futures.add(cs.submit(() -> getTwo()));
        futures.add(cs.submit(() -> getThree()));
        // 获取最快返回的任务执行结果
        Integer r = 0;
        try{
            // 只要有一个成功返回，则break
            for (int i=0; i<3; i++) {
                r = cs.take().get();
                if (r != null) {
                    break;
                }
            }
        }finally {
            // 取消所有任务
            for(Future<Integer> f : futures) {
                f.cancel(true);
            }
        }
        System.out.println(r);
    }

    static Integer getOne() {
        System.out.println("查询地址One");
        return 1;
    }
    static Integer getTwo() {
        System.out.println("查询地址Two");
        return 2;
    }
    static Integer getThree() {
        System.out.println("查询地址Three");
        return 3;
    }
}
