package limitresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaDemos
 * @description: 临界区的sync、Lock的demo
 * 注意：
 * 子线程的运行过程分析：this.run()中的跑addCount()、getCount()（即this.count）代码。
 * 若给addCount()、getCount()设置sync..，不给run()设置sync...，所有子线程都跑到run()里面，然后在任意时刻，只有一个进入addCount()（eg：子线程1），
 * 子线程1会把this给锁住，其它子线程阻塞到addCount()门前，没问题。
 * 但问题出现在后面，子线程1从addCount()出来，this锁释放，（问题在后面）
 * 然后子线程2准备进入addCount()，需要锁住this，但子线程1出来后，准备进入getCount()，需要锁住this，这时就会出现冲突，出现错误
 * 理想情况是-子线程1先进入getCount()，锁住this，释放this，然后子线程2进入addCount()...
 * 出错情况是-子线程2先进入addCount()，锁住this，释放this，然后子线程1进入getCount()，这时返回的count就不对了
 * 错误示范：CriticalSection1，正确示范：CriticalSection2，将run()进行同步控制
 * @author: Prvyx
 * @created: 2022/04/21 12:37
 */

class CriticalSection1 implements Runnable{
    private int count;

    synchronized int getCount(){
        return this.count;
    }
    synchronized void addCount(){
        this.count++;
    }

    @Override
    public void run() {
        addCount();
        System.out.println("count:"+getCount());
    }

    public static void main(String[] args) {
        CriticalSection1 criticalSection1=new CriticalSection1();
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<20;i++){
            es.submit(criticalSection1);
        }
        es.shutdown();
    }
}
class CriticalSection2 implements Runnable{
    private int count;
    private Lock lock=new ReentrantLock();

    int getCount(){
        return count;
    }
    void addCount(){
        count++;
    }
    @Override
    public void run() {
        try {
            lock.lock();
            addCount();
            System.out.println("count:"+count);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CriticalSection2 criticalSection2=new CriticalSection2();
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<20;i++){
            es.submit(criticalSection2);
        }
        es.shutdown();
    }
}
public class CriticalSectionDemo {
}
