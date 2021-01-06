// 两个转账操作可以并行执行
public class Parallel {

    private int balance;
    // 转账
    void transfer(Parallel target, int amt) {
        // 锁定转出账户
        synchronized (this) {
            synchronized (target) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
