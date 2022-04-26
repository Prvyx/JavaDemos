package syncqueue;

import java.util.concurrent.*;

/**
 * @program: JavaDemos
 * @description: 测试BlockingQueue
 * @author: Prvyx
 * @created: 2022/04/23 19:09
 */
class LiftOff implements Runnable {
    private int count=10;

    public LiftOff(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (count-->0){
            System.out.println("剩余时间："+count);
        }
    }
}
class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> blockingQueue;

    public LiftOffRunner(BlockingQueue<LiftOff> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void add(LiftOff liftOff) throws InterruptedException {
        blockingQueue.put(liftOff);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                // 从消费者线程的队列中，每次拉出一个LiftOff，进行运行
                LiftOff liftOff=(LiftOff) blockingQueue.take();
                liftOff.run();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class TestBlockingQueues {
    public static void test(String queueName,BlockingQueue<LiftOff> blockingQueue) throws InterruptedException {
        System.out.println("\nqueueName:"+queueName);
        LiftOffRunner liftOffRunner=new LiftOffRunner(blockingQueue);
        // 创建一个liftOffRunner消费者线程
        Thread thread=new Thread(liftOffRunner);
        thread.start();
        // 向liftOffRunner的同步队列中 插入5个LiftOff
        for(int i=0;i<5;i++){
            liftOffRunner.add(new LiftOff(5));
        }
        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
    }

    public static void main(String[] args) {
        try {
            test("ArrayBlockingQueue",new ArrayBlockingQueue<>(3));
            test("LinkedBlockingQueue",new LinkedBlockingQueue<>());
            test("SynchronousQueue",new SynchronousQueue<>());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
