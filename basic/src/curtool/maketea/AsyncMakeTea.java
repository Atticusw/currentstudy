package curtool.maketea;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AsyncMakeTea {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 烧水
        // runAsync 没有返回值
        CompletableFuture<String> hotWater = CompletableFuture.supplyAsync(() -> {
            System.out.println("T1: 洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1: 烧开水...");
            sleep(15, TimeUnit.SECONDS);
            return "zheshigeceshi";
        });
        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        // supplyAsync 有返回值
        CompletableFuture<String> tea = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2: 洗茶杯...");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2: 拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return " 龙井 ";
        });
        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> thenCombine = hotWater.thenCombine(tea, (a,tf) -> {
            System.out.println("T1: 拿到茶叶:" + tf);
            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf + a;
        });
        // 等待任务 3 执行结果
        System.out.println(thenCombine.join());


    }
    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }
}
