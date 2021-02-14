package curdesign.balking;

import curdesign.immutability.Router;

import java.util.Set;
import java.util.concurrent.*;

// 当路由表发生改变时，本地保存路由表信息
public class RouterTableBalking {
    // key: 接口名
    // value: 路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>>
            rt = new ConcurrentHashMap<>();
    // 路由表是否发生变化
    volatile boolean changed;
    // 将路由表写入本地本件的线程池
    ScheduledExecutorService ses =
            Executors.newSingleThreadScheduledExecutor();

    // 启动定时任务
    // 将变更后的路由表写入本地文件
    public void startLocalSaver() {
        ses.scheduleWithFixedDelay(this::autoSave, 1, 1, TimeUnit.MINUTES);
    }

    // 保存路由表到本地文件
    void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        // 将路由表写入本地文件
        // 省略其方法实现
        // this.save2Local();
    }

    // 删除路由
    public void remove(Router router) {
        Set<Router> set = rt.get(router.getIface());
        if (set != null) {
            set.remove(router);
            // 路由表已发生变化
            changed = true;
        }
    }

    // 增加路由
    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(
                router.getIface(), r ->
                        new CopyOnWriteArraySet<>());
        set.add(router);
        // 路由表已发生变化
        changed = true;
    }
}
