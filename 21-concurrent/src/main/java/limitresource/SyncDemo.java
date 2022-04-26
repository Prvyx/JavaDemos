package limitresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description:使用synchronized的SyncDemo
 * 多个线程使用同一个“实现Runnable接口”的类对象，共同完成1个任务，资源共享；而多个线程使用 “继承Thread类”的相互独立，资源不共享
 * @author: Prvyx
 * @created: 2022/04/20 21:10
 */

public class SyncDemo implements Runnable{
    private int count;

    synchronized public void buy(){
        // 设置休眠的目的：当子线程1的buy()结束后，会回到run()，执行System.out.println("还有"+ticket);代码
        // 此时可能子线程2可以进入buy()，若没有休眠，直接ticket--，可能会影响子线程1的System.out.println("还有"+ticket);的打印
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    @Override
      public void run() {
        buy();
        System.out.println("count:"+count);
    }

    public static void main(String[] args) {
        ExecutorService es= Executors.newCachedThreadPool();
        SyncDemo syncDemo=new SyncDemo();
        for(int i=0;i<50;i++){
            es.execute(syncDemo);
        }
        es.shutdown();
//        List<Thread> threads=new ArrayList<>();
//        SyncDemo syncDemo=new SyncDemo();
//
//        for(int i=0;i<50;i++){
//            threads.add(new Thread(syncDemo));
//        }
//        for(int i=0;i<50;i++){
//            threads.get(i).start();
//        }
    }
}
