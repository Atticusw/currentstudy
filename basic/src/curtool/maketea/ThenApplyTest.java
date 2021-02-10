package curtool.maketea;

import java.util.concurrent.CompletableFuture;

public class ThenApplyTest {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> s + " QQ")
                .thenApply(String::toUpperCase);
        String join = completableFuture.join();
        System.out.println(join);
    }
}
