package curtool.immutability;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

// 路由表信息
public class RouterTable {
    // key：接口名
    // value：路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();
    // 根据接口名获取路由表
    public Set<Router> get(String iface) {
        return rt.get(iface);
    }
    // 删除路由
    public void remove(Router router) {
        Set<Router> set = rt.get(router.getIface());
        if (set != null) {
            set.remove(router);
        }
    }
    // 添加路由
    public void add(Router router) {
        // 就是判断这个 key 是否在其中，如果不在就执行将
        // r -> new new CopyOnWriteArraySet<>()，以这个key 存入进去。
        // 否者就返回 这个key 的value。
        Set<Router> set = rt.computeIfAbsent(router.getIface(),
                r -> new CopyOnWriteArraySet<>());
        set.add(router);
    }
}
