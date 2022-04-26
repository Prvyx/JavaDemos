package threadmechanism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: JavaDemos
 * @description:实现Callable<>接口（call()方法比Runnable的run()有返回值）创建线程任务
 * Runnable对象的run()没返回值，而Callable对象的call()有返回值
 * @author: Prvyx
 * @created: 2022/04/20 10:14
 */
//有Rs（返回值）的任务
class TaskWithRs implements Callable<String>{
    private static int count;
    private int id=count++;

    @Override
    public String call() throws Exception {
        return "TaskWithRs"+id;
    }
}
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService es= Executors.newCachedThreadPool();
        List<Future<String>> futures=new ArrayList<>();
        int size=5;
        for(int i=0;i<size;i++){
            futures.add(es.submit(new TaskWithRs()));
        }
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                //必须有，否则程序不能正常退出
                es.shutdown();
            }

        }
    }
}
