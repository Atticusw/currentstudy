
// 让所有的对象持有唯一的一个锁对象。
public class OnlyAccount {
    // 唯一的锁对象
    private Object lock;
    private Integer balance;

    private OnlyAccount() {

    }

    // 创建 Account 时传入同一个 lock 对象
    // 创建 Account 对象时，传入相同的 lock，
    // 这样所有的 Account 对象都会共享这个 lock 了。
    public OnlyAccount (Object lock) {
        this.lock = lock;
    }

    // 转账
    void transfer(OnlyAccount target, int amt) {
        synchronized (lock) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
