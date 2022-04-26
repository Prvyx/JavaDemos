package threadmechanism;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemos
 * @description:Executor的demo
 * java.util.concurrent包中的Executor将为你管理Thread线程，简化了并发编程。
 * 其中，ExecutorService（具有服务声明周期的Executor）知道如何构建恰当的上下文来执行Runnable对象
 * @author: Prvyx
 * @created: 2022/04/20 09:52
 */

enum ExecutorPoolKind{
    CACHED{
        @Override
        ExecutorService create() {
            return Executors.newCachedThreadPool();
        }
    },
    FIXED{
        @Override
        ExecutorService create() {
            return Executors.newFixedThreadPool(5);
        }
    },
    SINGLE{
        @Override
        ExecutorService create() {
            return Executors.newSingleThreadExecutor();
        }
    }
    ;
    abstract ExecutorService create();
}
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorPoolKind executorPoolKind=ExecutorPoolKind.values()[new Random().nextInt(3)];
        System.out.println(executorPoolKind);
        ExecutorService executorService=executorPoolKind.create();
        for(int i=0;i<5;i++){
            executorService.submit(new LiftOff());
//也行：            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
