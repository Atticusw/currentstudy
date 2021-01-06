import java.util.ArrayList;
import java.util.List;
// 给资源加锁
public class Allocator {

    private List<Object> als = new ArrayList<>();

    synchronized boolean apply (Object from, Object to) {
        // 只申请一个资源返回false
        if (als.contains(from) || als.contains(to)) {
            return false;
        }else {
            als.add(from);
            als.add(to);
        }
        return true;
    }
    // 返回资源
    synchronized void free (Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }

}
