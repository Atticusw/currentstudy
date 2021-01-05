public class DoubleCheckedSingle {
    static volatile DoubleCheckedSingle instance;

    static DoubleCheckedSingle getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingle.class) {
                if (instance == null) {
                    // 在 new 的过程中，会出现指令重排的问题；
                    // 本来应该是先分配内存，然后初始化对象，最后进行赋值操作；
                    // 但是指令重排后，可能会造成 初始化和赋值操作 先后顺序调换；
                    // 这个时候就会出现问题，当线程 A 执行到赋值操作后，还没有初始化，线程 B 判断 instance == null 为false，
                    // 就会直接返回 instance。但是呢，这个时候 instance 并没有初始化，就会造成空指针问题。
                    instance = new DoubleCheckedSingle();
                    return instance;
                }
            }
        }
        return instance;
    }
}
