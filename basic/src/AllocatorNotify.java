import java.util.List;

public class AllocatorNotify {

    private List<Object> als;

    // 一次性申请所有资源
    synchronized void apply(Object from, Object to) {
        // 因为 synchronized 锁的资源是als，所以要么全部拿到，要么全部拿不到
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        als.add(from);
        als.add(to);
    }

    // 归还资源
    synchronized  void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

}
