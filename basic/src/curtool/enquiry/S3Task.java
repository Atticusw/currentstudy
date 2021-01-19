package curtool.enquiry;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class S3Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return 3;
    }
}
