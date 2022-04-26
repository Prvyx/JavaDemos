package threadcooperation.oneobjectlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: 打蜡、擦拭 Car的demo
 * 强调第26行、31行代码 while循环包围wait()的重要性：其本质就是要检查所感兴趣的特定条件，并在条件不满足的情况下返回到wait()中。
 * 惯用的方法就是使用while来编写这种代码
 * @author: Prvyx
 * @created: 2022/04/22 15:09
 */

class Car {
    private boolean waxOn=false;
    synchronized public void waxed(){
        waxOn=true;
        notifyAll();
    }
    synchronized public void buffer(){
        waxOn=false;
        notifyAll();
    }
    synchronized public void waitForWaxing() throws InterruptedException {
        while (!waxOn){
            wait();
        }
    }
    synchronized public void waitForBuffing() throws InterruptedException {
        while (waxOn){
            wait();
        }
    }
}
class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                car.waitForBuffing();
                System.out.println("WaxOn,let's go");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                car.waitForWaxing();
                System.out.println("WaxOff,let's go");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffer();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class WaxOMatic1 {
    public static void main(String[] args) throws InterruptedException {
        Car car=new Car();
        ExecutorService es= Executors.newCachedThreadPool();
        es.submit(new WaxOff(car));
        es.submit(new WaxOn(car));
        Car car1=new Car();
        TimeUnit.MILLISECONDS.sleep(5000);
        es.shutdownNow();
    }
}
