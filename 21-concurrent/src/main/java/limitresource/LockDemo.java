package limitresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaDemos
 * @description:使用Lock的LockDemo
 * lock.lock()：阻塞式；lock.tryLock()：非阻塞式，这两者需求场景不同
 * 我原本认为tryLock()应该执行50次，但事实只执行一次。这便是tryLock()与lock()的不同了，这两个的需求场景不同
 * 资料：https://segmentfault.com/q/1010000005602326
 * @author: Prvyx
 * @created: 2022/04/20 21:50
 */

public class LockDemo implements Runnable{
    private int count;
    private final Lock lock=new ReentrantLock();

    void addCount(){
        // 使用lock.lock()执行50次且共享资源不冲突
        try {
            lock.lock();
            count++;
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println("count:"+count);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

//        // 使用lock.tryLock()执行1次且共享资源不重复
//        if(lock.tryLock()){
//            try {
//                count++;
//                TimeUnit.MILLISECONDS.sleep(200);
//                System.out.println("count:"+count);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {//只有在获得锁后，才会选择释放掉
//                lock.unlock();
//            }
//        }else {
//            System.out.println("请勿重复操作");
//        }
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        ExecutorService es=Executors.newCachedThreadPool();
        LockDemo lockDemo=new LockDemo();

        for(int i=0;i<50;i++){
            es.submit(new Thread(lockDemo));
        }
        es.shutdown();
    }
}
