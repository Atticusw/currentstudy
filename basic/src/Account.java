
// 用不同的锁保护不同的资源
public class Account {
    // 锁：保护余额
    private final Object balLock = new Object();
    private Integer balance;
    // 锁：保护账户密码
    private final Object pwLock = new Object();
    private String password;

    // 取款
    void withdraw(Integer amt) {
        synchronized (balLock) {
            if (this.balance > amt) {
                this.balance -= amt;
            }
        }
    }

    // 查看余额
    Integer getBalance () {
        synchronized (balLock) {
            return this.balance;
        }
    }

    // 更改密码
    void updatePassword(String pw){
        synchronized(pwLock) {
            this.password = pw;
        }
    }
    // 查看密码
    String getPassword() {
        synchronized(pwLock) {
            return password;
        }
    }

}
