package threadmechanism;

import java.util.ArrayList;

/**
 * @program: JavaDemos
 * @description:Thread的join()方法的demo
 * 该demo不能使用Executors，需要人为自行创建和管理才能使join()有效
 * @author: Prvyx
 * @created: 2022/04/20 17:32
 */

public class ThreadJoinDemo {
    private Thread create(){
        return new Thread() {
            private static int count;
            private int id=count++;
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+":"+id);
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads=new ArrayList<>();

        for(int i=0;i<5;i++){
            threads.add(new ThreadJoinDemo().create());
        }
        for(int i=0;i<5;i++){
            threads.get(i).start();
            // 在main()主线程调用thread.join()，则main()线程被挂起，直到目标线程thread结束才恢复
            threads.get(i).join();
        }
    }
}
