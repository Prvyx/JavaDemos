package threadmechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description:DaemonThread的工厂。通过ExecutorService创建（创建线程使用“工厂”参数）和管理Thread
 * 使用了daemon守护进程/后台进程，就不用在最后使用es.shutdown()了
 * es.shutdown()的作用：es管理的所有子线程任务完成后尽快退出当前线程/主线程。主线程在子线程完成之前一直在运行
 * Thread.setDaemon(true)：创建的子线程变为后台/守护进程，当前线程/主线程直接退出，不等子线程完成
 * @author: Prvyx
 * @created: 2022/04/20 14:08
 */

public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread=new Thread(r);
        thread.setDaemon(true);
        return thread;
    }

    // 测试
    public static void main(String[] args)throws Exception {
        ExecutorService es= Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i=0;i<5;i++){
            es.execute(new SimplePriority(Thread.MIN_PRIORITY));
        }
        System.out.println("All daemons started");
        TimeUnit.SECONDS.sleep(1);
//        es.shutdown();
    }
}
