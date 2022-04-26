package threadcooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: 一个餐馆，只有一个厨师和一个服务员，且餐馆的窗口只能放下一个Meal
 * 注意：wait()、notify()、notifyAll()必须加sync...
 * 可以这样理解：
 * 一个餐馆，只有一个厨师和一个服务员，且餐馆的窗口只能放下一个Meal。
 * 厨师等待（this.wait()），直到窗口没餐（被服务员notifyAll()），然后去做饭，通知服务员拿餐（即restaurant.waitPerson.notifyAll()）
 * 服务员等待（this.wait()），直到窗口有餐（被厨师notifyAll()），然后去窗口拿餐，通知厨师做饭（即restaurant.chef.notifyAll()）
 * restaurant.waitPerson.notifyAll()的同步块：sync...(restaurant.waitPerson)
 * restaurant.chef.notifyAll()的同步块：sync...(restaurant.chef)
 * this.wait()的同步块：sync...(this)
 *
 * 总结：“谁”等待/通知“谁”，sync...(“谁”)（即 “谁”等待/通知“谁”，就给“谁”上锁）
 * @author: Prvyx
 * @created: 2022/04/22 18:00
 */

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "orderNum=" + orderNum +
                '}';
    }
}
class Chef implements Runnable {
    private final Restaurant restaurant;
    private static int count;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void createFood(){
        synchronized (restaurant.waitPerson){
            restaurant.meal=new Meal(count++);
            System.out.println("已经生产一份食物至窗口");
            restaurant.waitPerson.notifyAll();
        }
    }

    public void waitForConsume() throws InterruptedException {
        synchronized (this){
            while (restaurant.meal!=null){
                wait();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                waitForConsume();
                TimeUnit.MILLISECONDS.sleep(200);
                createFood();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class WaitPerson implements Runnable {
    private final Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void consumeFood(){
        synchronized (restaurant.chef){
            restaurant.meal=null;
            System.out.println("已经消费窗口的食物");
            restaurant.chef.notifyAll();
        }
    }
    public void waitForCreate()throws InterruptedException{
        synchronized (this){
            while (restaurant.meal==null){
                wait();
            }
        }
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                waitForCreate();
                TimeUnit.MILLISECONDS.sleep(200);
                consumeFood();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class Restaurant {
    Meal meal;
    final Chef chef=new Chef(this);
    final WaitPerson waitPerson=new WaitPerson(this);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newCachedThreadPool();
        Restaurant restaurant=new Restaurant();
        es.submit(restaurant.chef);
        es.submit(restaurant.waitPerson);

        TimeUnit.MILLISECONDS.sleep(4000);
        es.shutdownNow();
    }
}
