package cancelrunnable;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description:花园demo，并在最后取消task(即Runnable)
 * @author: Prvyx
 * @created: 2022/04/21 17:28
 */

// 用于计数
class Count{
    private int count;

    public int getCount(){
        return count;
    }
    public void addCount() {
        Thread.yield();
        count++;
        Thread.yield();

    }
}
// 花园入口   多个花园入口相当于Entrance类的多个子线程
class Entrance implements Runnable{
    private final static Count count=new Count();//所有入口进入的总人数
    private final long entranceId;//特定入口的编号
    private int number;//特定入口进入的人数
    private volatile static boolean isCanceled;//表示花园的所有入口是否开放，false为开放，true为关闭

    Random random=new Random();

    public Entrance(long entranceId) {
        this.entranceId = entranceId;
    }

    public static void cancel(){
        isCanceled=true;
    }
    public static int getTotalCount(){return count.getCount();}
    @Override
    public String toString() {
        return "entrance"+entranceId+"'s number:"+number;
    }

    @Override
    // 模拟人群的进入，每过一段时间，进入一个人
    public void run() {
        while (!isCanceled){
            synchronized (this){
                number++;
                System.out.println(this);
            }
            synchronized (count){
                count.addCount();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopping Entrance"+entranceId+" number:"+number+",totalCount:"+count.getCount());
    }
}
public class GardenDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            // 这5个子线程不是同一个对象的子线程，有自己的对象字段number
            es.submit(new Entrance(i));
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Entrance.cancel();
        es.shutdown();
        while (!es.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("some tasks were not terminated");
        }
        System.out.println("All Entrances count="+Entrance.getTotalCount());
    }
}
