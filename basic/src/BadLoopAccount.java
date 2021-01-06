// 破坏循环等待条件，按次序申请资源
public class BadLoopAccount {
    private int id;
    private int balance;

    // 转账
    void transfer (BadLoopAccount target, int amt) {
        BadLoopAccount left = this;
        BadLoopAccount right = target;

        if (this.id < target.id) {
            left = target;
            right = this;
        }
        // 按顺序去申请锁，就不会出现闭环。如A--B--A，因为不会出现 B--A 的申请顺序；
        synchronized (left) {
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
