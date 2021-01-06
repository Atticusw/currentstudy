public class AllocatorAccount {
    // actr 应该为单例
    private Allocator actr;
    private int balance;

    // 转账
    void transfer(AllocatorAccount target, int amt) {
        // 一次性省钱所有资源
        while (! actr.apply(this,target));

        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }finally {
            // 释放资源
            actr.free(this,target);
        }
    }

}
