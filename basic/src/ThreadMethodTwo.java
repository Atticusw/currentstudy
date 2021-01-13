

// 创建线程的第二种方法，实现 Runnable 的run方法
public class ThreadMethodTwo implements Runnable{
    @Override
    public void run() {
        System.out.println("需要执行的代码");
    }
}
