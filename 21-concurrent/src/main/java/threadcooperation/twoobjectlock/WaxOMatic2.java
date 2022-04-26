package threadcooperation.twoobjectlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: 打蜡、擦拭 Car的demo，有2个对象锁-car1、car2
 * @author: Prvyx
 * @created: 2022/04/22 15:09
 */

class Car {
    private boolean waxOn=false;
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }

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
                System.out.println(car+" WaxOn,let's go");
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
                System.out.println(car+" WaxOff,let's go");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffer();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        Car car1=new Car("car1");
        ExecutorService es= Executors.newCachedThreadPool();
        es.submit(new WaxOff(car1));
        es.submit(new WaxOn(car1));
        Car car2=new Car("car2");
        es.submit(new WaxOff(car2));
        es.submit(new WaxOn(car2));
        TimeUnit.MILLISECONDS.sleep(5000);
        es.shutdownNow();
    }
}
