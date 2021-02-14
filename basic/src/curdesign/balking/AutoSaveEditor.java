package curdesign.balking;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoSaveEditor {
    // 文件是否被修改过
    boolean changed = false;
    // 定时任务线程池
    ScheduledExecutorService ses =
            Executors.newSingleThreadScheduledExecutor();

    // 定时执行自动保存
    void startAutoSave() {
        ses.scheduleWithFixedDelay(this::autoSave, 5, 5, TimeUnit.SECONDS);
    }

    // 自动存盘操作
    void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }
        // 执行存盘操作
        // 省略且实现
    }

    // 编辑操作
    void edit() {
        // 省略逻辑实现
        change();
    }

    // 改变状态
    void change() {
        synchronized (this) {
            changed = true;
        }
    }

}
