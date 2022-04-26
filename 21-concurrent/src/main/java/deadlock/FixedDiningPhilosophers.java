package deadlock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: 哲学家就餐的死锁修复版本（让最后一个哲学家先拿左手边的筷子，后拿右边的筷子）
 * @author: Prvyx
 * @created: 2022/04/24 20:19
 */
// 筷子
class Chopstick {
    private boolean taken;
    public void take() throws InterruptedException {
        synchronized (this){
            while (taken){
                wait();
            }
            taken=true;
        }
    }

    public void drop(){
        synchronized (this){
            taken=false;
            notifyAll();
        }
    }
}
// 哲学家
class Philosopher implements Runnable{
    private Chopstick left,right;
    private final int id;
    private int ponderFactor; //思考等级
    private Random random=new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }
    // 哲学家在思考/吃饭
    private void pause() throws InterruptedException {
        if(ponderFactor==0)
            return;
        TimeUnit.MILLISECONDS.sleep(random.nextInt(250*ponderFactor));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println(this+"思考...");
                pause();
                right.take();
                System.out.println(this+"已经拿起右手筷子");
                left.take();
                System.out.println(this+"已经拿起左手筷子");
                System.out.println(this+"吃饭...");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        int ponder=5;
        int size=5;
        ExecutorService es= Executors.newCachedThreadPool();
        Chopstick[] chopsticks=new Chopstick[size];
        for(int i=0;i<size;i++){
            chopsticks[i]=new Chopstick();
        }
        // 可能出现死锁
//        for(int i=0;i<size;i++){
//            es.submit(new Philosopher(chopsticks[i],chopsticks[(i+1)%size],i,ponder));
//        }
        // 通过代码设计规避死锁
        for(int i=0;i<size-1;i++){
            es.submit(new Philosopher(chopsticks[i],chopsticks[i+1],i,ponder));
        }
        es.submit(new Philosopher(chopsticks[0],chopsticks[size-1],size-1,ponder));

        TimeUnit.SECONDS.sleep(4);
        es.shutdownNow();
    }
}
