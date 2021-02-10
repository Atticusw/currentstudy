package curtool.maketea;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ApplyToEitherTest {

    public static void main(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random(10);
            int t = random.nextInt();
            sleep(t, TimeUnit.SECONDS);
            return String.valueOf(t);
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random(10);
            int t = random.nextInt();
            sleep(t, TimeUnit.SECONDS);
            return String.valueOf(t);
        });
        CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);
        System.out.println(f3.join());
    }

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }
}
