package resourcesharingtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: Runnable、Thread的资源共享的真正含义
 * 对于implements Runnable或extends Thread的类，在多线程中/非多线程时，对象字段都是共享资源，而基本类型字段则不是（原因：对象字段是引用值，指向 堆）
 * 和implements Runnable 还是 extends Thread，没关系！！！
 * @author: Prvyx
 * @created: 2022/04/25 19:20
 */
class Student{
    String name;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
// 测试Runnable的资源共享
class RunnableDemo implements Runnable{
    private int id;
    private Student student;

    public RunnableDemo(int id, Student student) {
        this.id = id;
        this.student = student;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!Thread.interrupted()){
            synchronized (this){
                if(id==2){
                    id=100;
                    student.setName("dongshuaibo");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "RunnableDemo{" +
                "id=" + id +
                ", student=" + student +
                '}';
    }
}
class RunnableTester {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newCachedThreadPool();
        List<RunnableDemo> list=new ArrayList<>();
        Student student=new Student("prvyx");
        for(int i=0;i<5;i++){
            list.add(new RunnableDemo(i,student));
        }
        // es.submit(Runnable)
//        for(int i=0;i<5;i++){
//            es.submit(list.get(i));
//        }
        // es.submit(Thread)
        for(int i=0;i<5;i++){
            es.submit(new Thread(list.get(i)));
        }

        TimeUnit.MILLISECONDS.sleep(300);
        es.shutdownNow();
        System.out.println(list);
    }
}

// 测试Thread的资源共享
class ThreadDemo extends Thread{
    private int id;
    private Student student;

    public ThreadDemo(int id, Student student) {
        this.id = id;
        this.student = student;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            synchronized (this){
                if(id==2){
                    id=100;
                    student.setName("hello");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ThreadDemo{" +
                "id=" + id +
                ", student=" + student +
                '}';
    }
}
class ThreadDemoTester{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newCachedThreadPool();
        List<ThreadDemo> list=new ArrayList<>();
        Student student=new Student("prvyx");
        for(int i=0;i<5;i++){
            list.add(new ThreadDemo(i,student));
        }
//        for(int i=0;i<5;i++){
//            es.submit(list.get(i));
//        }
        // 再套一层Thread
        for(int i=0;i<5;i++){
            es.submit(new Thread(list.get(i)));
        }
        TimeUnit.MILLISECONDS.sleep(300);
        es.shutdownNow();
        for (ThreadDemo threadDemo : list) {
            System.out.println(threadDemo);
        }
    }
}
public class ResourceSharingTester {
}
