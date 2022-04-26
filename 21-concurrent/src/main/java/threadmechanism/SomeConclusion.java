package threadmechanism;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description:一些猜想以及代码验证demo
 * 猜想1：若无Executor创建和管理子线程，又无daemon线程，就是普通子线程，main()主线程会在所有子线程执行完之后，退出
 * 猜想2：若有Executor创建和管理子线程，若有es(即ExecutorService).shutdown()，则main()主线程会在es中的子线程完成后，会被告知要尽快结束
 *                              ；若无es.shutdown()，则main()主线程在es中的子线程完成后，不会被告知结束，过比较长的一段时间后才会被JVM给回收
 * 猜想3：若有daemon线程，则主线程直接退出后“主线程创建的子线程”不归主线程管理了，可能看不到子线程的运行结果（主线程可以休眠适当时间来观看子线程的运行结果）
 * （注：(1)可以人为地直接创建daemon线程，(2)也可以通过Executors.newCachedThreadPool(工厂参数)，让es自行创建daemon线程）
 * 注：使用了守护/后台进程（daemon），就不用使用es.shutdown了
 * @author: Prvyx
 * @created: 2022/04/20 14:36
 */

public class SomeConclusion implements Runnable{
    private static int count;
    private int id=count++;
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread()+":"+id);
            if(i==999){
                System.out.println("即将完成");
            }
            Thread.yield();
        }
    }

    // 测试
    public static void main(String[] args) throws InterruptedException {
//        // 猜想1
//        ArrayList<Thread> threads=new ArrayList<>();
//        // 子线程的创建
//        for(int i=0;i<10;i++){
//            threads.add(new Thread(new SomeConclusion()));
//        }
//        // 子线程的运行
//        for(int i=0;i<10;i++){
//            threads.get(i).start();
//        }
//        // 猜想2
//        ArrayList<Thread> threads=new ArrayList<>();
//        ExecutorService es= Executors.newCachedThreadPool();
//        for(int i=0;i<10;i++){
//            // ExecutorService进行子线程的创建和管理，不用人为地new Thread()以及run()代码的编写
//            es.submit(new SomeConclusion());
//        }
//        es.shutdown();
//        // 猜想3(1)
//        ArrayList<Thread> threads=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            Thread thread=new Thread(new SomeConclusion());
//            thread.setDaemon(true);
//            threads.add(thread);
//        }
//        for(int i=0;i<10;i++){
//            threads.get(i).start();
//        }
        // 猜想3(2)
        ExecutorService es=Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread=new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        for(int i=0;i<10;i++){
            es.submit(new SomeConclusion());
        }
        TimeUnit.SECONDS.sleep(1);
    }
}
