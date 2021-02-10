package curtool.maketea;

import java.util.concurrent.CompletableFuture;

public class ExceptionallyTest {
    public static void main(String[] args) {
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> 7 / 0)
                .thenApply(r -> r * 10)
                .exceptionally(e -> 0);
        System.out.println(exceptionally.join());

    }
}
