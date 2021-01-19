package curtool.maketea;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T1Task implements Callable<String> {
    // 因为泡茶需要等待T2 拿出茶叶，所以需要 T2 任务的 FutureTask
    FutureTask<String> f2;

    T1Task(FutureTask<String> f2) {
        this.f2 = f2;
    }
    @Override
    public String call() throws Exception {
        System.out.println("T1：洗水壶..");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2：烧开水");
        TimeUnit.SECONDS.sleep(15);

        // 获取线程 T2 的茶叶
        String tf = f2.get();
        System.out.println("T1：拿到茶叶：" + tf);

        System.out.println("T1：开始泡茶....");

        return "上茶：" + tf;
    }
}
