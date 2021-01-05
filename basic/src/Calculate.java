public class Calculate {

    private long count = 0;
    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final Calculate calculate = new Calculate();
        // 创建两个线程
        Thread th1 = new Thread(() -> {
            calculate.add10K();
        });
        Thread th2 = new Thread(() -> {
            calculate.add10K();
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();
        System.out.println(calculate.count);
    }


}
