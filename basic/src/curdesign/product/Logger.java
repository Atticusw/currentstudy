package curdesign.product;

import java.util.concurrent.BlockingQueue;

public class Logger {

    // 任务队列
    /*final BlockingQueue<LogMesg> bq
            = new BlockingQueue<>();*/

    enum LEVEL {
        INFO, ERROR
    }

    class LogMesg {
        LEVEL level;
        String msg;

        LogMesg(LEVEL level, String msg) {
            this.level = level;
            this.msg = msg;
        }
        // 省略 toString 实现
    }
}
