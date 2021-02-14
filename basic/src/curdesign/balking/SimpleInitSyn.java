package curdesign.balking;

// 使用 Balking 模式的线程安全单例模式

public class SimpleInitSyn {
    boolean inited = false;
    synchronized void init() {
        if (inited) {
            return;
        }
        // 省略 doInit 的实现
        inited = true;
    }
}
