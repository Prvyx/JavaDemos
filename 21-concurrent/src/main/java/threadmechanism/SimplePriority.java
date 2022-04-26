package threadmechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemos
 * @description:线程优先级demo
 * 优先级在构造器设置没有什么好处，因为Executor在此刻还没有开始执行任务
 * 唯一可移植的方法是-当调整优先级时，只使用MAX_PRIORITY、NORM_PRIORITY、MIN_PRIORITY
 * @author: Prvyx
 * @created: 2022/04/20 13:24
 */

public class SimplePriority implements Runnable{
    private int id=count++;
    private int priority;
    private static int count;

    public SimplePriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread()+": "+id;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        for(int i=0;i<10000;i++){
            if(i%1000==0){
                Thread.yield();
            }
        }
        System.out.println(this);
    }

    public static void main(String[] args) {
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.execute(new SimplePriority(Thread.MIN_PRIORITY));
        }
        es.execute(new SimplePriority(Thread.MAX_PRIORITY));
        es.shutdown();
    }
}
