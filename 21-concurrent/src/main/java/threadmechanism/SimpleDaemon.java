package threadmechanism;

import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description:daemon（守护进程、后台进程）的demo
 * Thread.setDaemon(true)
 * @author: Prvyx
 * @created: 2022/04/20 13:58
 */

public class SimpleDaemon implements Runnable{
    private static int count;
    private int id=count++;

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+":"+id);
    }

    public static void main(String[] args)throws Exception {
        for(int i=0;i<5;i++){
            Thread thread=new Thread(new SimpleDaemon());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("All daemons started");
        // 休眠1s，目的：为了看到子线程的运行结果；若无该行代码，则main()线程直接退出
        TimeUnit.SECONDS.sleep(1);
    }
}
