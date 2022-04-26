package syncqueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: 吐司制作以及加工流程（该示例没有任何显式的同步（即Lock对象/sync...关键字的同步），同步是由队列管理，也没有显式的wait()、notifyAll()）
 * @author: Prvyx
 * @created: 2022/04/23 20:09
 */
// 吐司
class Toast {
    enum Status {DRY,BUTTERED,JAMMED}
    private Status status=Status.DRY;
    private final int id;

    public Toast(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "status=" + status +
                ", id=" + id +
                '}';
    }

    public void butter(){status=Status.BUTTERED;}
    public void jam(){status=Status.JAMMED;}
}
// 吐司同步队列
class ToastQueue extends LinkedBlockingQueue<Toast>{}
// 吐司制作器
class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count;
    private Random random=new Random(47);

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println("制作吐司中...");
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                toastQueue.put(new Toast(count++));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
// 黄油添加器
class Butterer implements Runnable{
    private ToastQueue dryQueue,butterQueue;

    public Butterer(ToastQueue dryQueue, ToastQueue butterQueue) {
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast toast=dryQueue.take();
                System.out.println("添加黄油中...");
                TimeUnit.MILLISECONDS.sleep(200);
                toast.butter();
                butterQueue.put(toast);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
// 果酱添加器
class Jammer implements Runnable{
    private ToastQueue butterQueue,jamQueue;

    public Jammer(ToastQueue butterQueue, ToastQueue jamQueue) {
        this.butterQueue = butterQueue;
        this.jamQueue = jamQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast toast= butterQueue.take();
                System.out.println("添加果酱中...");
                TimeUnit.MILLISECONDS.sleep(200);
                toast.jam();
                jamQueue.put(toast);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    private ToastQueue jamQueue;
    private int count;

    public Consumer(ToastQueue jamQueue) {
        this.jamQueue = jamQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast toast = jamQueue.take();
                if(toast.getId()!=count++||toast.getStatus()!= Toast.Status.JAMMED){
                    System.out.println("制作流程出现错误");
                    System.out.println("出现错误："+toast);
                    System.exit(1);
                }
                System.out.println("吃掉"+toast);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue=new ToastQueue(),
                butterQueue=new ToastQueue(),
                jamQueue=new ToastQueue();
        ExecutorService es= Executors.newCachedThreadPool();
        // 操作dryQueue的子线程
        es.submit(new Toaster(dryQueue));
        // 操作dryQueue,butterQueue的子线程
        es.submit(new Butterer(dryQueue,butterQueue));
        // 操作butterQueue,jamQueue的子线程
        es.submit(new Jammer(butterQueue,jamQueue));
        // 操作jamQueue的子线程
        es.submit(new Consumer(jamQueue));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}
