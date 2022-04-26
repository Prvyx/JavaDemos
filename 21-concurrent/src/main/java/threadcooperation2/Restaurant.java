package threadcooperation2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaDemos
 * @description: 一个餐馆，只有一个厨师和一个服务员，且餐馆的窗口只能放下一个Meal (用Lock和Condition替代sync同步锁)
 * 总结：“谁”等待/通知“谁”，就给“谁”上锁
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
    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void createFood(){
        try {
            restaurant.waitPerson.lock.lock();
            restaurant.meal=new Meal(count++);
            System.out.println("已经生产一份食物至窗口");
            restaurant.mealIsExist=true;
            restaurant.waitPerson.condition.signalAll();
        }finally {
            restaurant.waitPerson.lock.unlock();
        }
    }

    public void waitForConsume() throws InterruptedException {
        try {
            lock.lock();
            while (restaurant.mealIsExist){
                condition.await();
            }
        }finally {
            lock.unlock();
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
    Lock lock=new ReentrantLock();
    Condition condition= lock.newCondition();

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void consumeFood(){
        try {
            restaurant.chef.lock.lock();
            restaurant.meal=null;
            System.out.println("已经消费窗口的食物");
            restaurant.mealIsExist=false;
            restaurant.chef.condition.signalAll();
        }finally {
            restaurant.chef.lock.unlock();
        }
    }
    public void waitForCreate()throws InterruptedException{
        try {
            lock.lock();
            if(!restaurant.mealIsExist){
                condition.await();
            }
        }finally {
            lock.unlock();
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
    boolean mealIsExist;
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
