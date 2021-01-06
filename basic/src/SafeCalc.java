public class SafeCalc {

    long value = 0;
    // 因为没有加锁，根据管程中锁的规则，没有办法保证可见性。
    long get() {
        return value;
    }

    synchronized void addOne() {
        value += 1;
    }

    public static void main(String[] args) {
        SafeCalc safeCalc = new SafeCalc();

        Thread th1 = new Thread(() -> {
            safeCalc.addOne();
        });

        Thread th2 = new Thread(() -> {
            long v = safeCalc.get();
            System.out.println(v);
        });
        th1.setPriority(1);
        th2.setPriority(10);
        th1.start();
        th2.start();
    }
}
