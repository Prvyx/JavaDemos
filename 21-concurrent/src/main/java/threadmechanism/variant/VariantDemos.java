package threadmechanism.variant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemos
 * @description: extends Thread、implements Runnable以及它们的各种内部类变体
 * implements Runnable比extends Thread的好处：实现接口可以extends其它类，而继承Thread的，则不能继承其它的类了
 * @author: Prvyx
 * @created: 2022/04/20 16:54
 */
class ExtendsThread extends Thread{
    private static int count;
    private int id=count++;

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+":"+id);
    }

    public static void main(String[] args) {
        List<Thread> threads=new ArrayList<>();
//        // 自行管理
//        // 创建子线程
//        for(int i=0;i<5;i++){
//            threads.add(new ExtendsThread());
//        }
//        // 启动子线程
//        for(int i=0;i<5;i++){
//            threads.get(i).start();
//        }
        // ExecutorService自动管理
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.submit(new ExtendsThread());
        }
        es.shutdown();
    }
}
class Variant2 {
    private class Inner extends Thread{
        private static int count;
        private int id=count++;
        @Override
        public void run() {
            System.out.println(Thread.currentThread()+":"+id);
        }
    }

    public Thread create(){
        return new Inner();
    }

    public static void main(String[] args) {
        Variant2 extendsThread2=new Variant2();
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.submit(extendsThread2.create());
        }
        es.shutdown();
    }
}
class Variant3 {
    public static Thread create(){
        return new Thread(){
            private static int count;
            private int id=count++;
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+":"+id);
            }
        };
    }

    public static void main(String[] args) {
        ExecutorService es=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.submit(Variant3.create());
        }
        es.shutdown();
    }
}
class Variant4 {
    public Runnable create(){
        return new Runnable() {
            private static int count;
            private int id=count++;
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+":"+id);
            }
        };
    }

    public static void main(String[] args) {
        ExecutorService es=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.submit(new Variant4().create());
        }
        es.shutdown();
    }
}
public class VariantDemos {

}
