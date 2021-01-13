
// 创建线程的第一种方法，自定义线程对象,覆写 run 方法
public class ThreadMethodOne extends Thread{
    @Override
    public void run() {
        System.out.println("线程需要执行的代码");
    }
}
