package curdesign.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SafeDateFormat {
    // 定义 ThreadLocal 变量
    static final ThreadLocal<DateFormat> t1 =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    static DateFormat get() {
        return t1.get();
    }
}
