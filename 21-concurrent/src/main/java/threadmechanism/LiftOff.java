package threadmechanism;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemos
 * @description:通过实现Runnable接口创建线程（10s倒计时）
 * 注：从Runnable导出一个类时，必须具有run()方法--但它不会产生任何内在的线程能力。要想实现线程行为，必须显式地将一个Runnable任务附着到线程上
 * 在main()的线程中，main()创建其它线程来执行LiftOff.run()，创建后回到main()，继续执行...
 * 在LiftOff.run()执行完之前，垃圾回收器无法清除它
 * @author: Prvyx
 * @created: 2022/04/20 09:19
 */

public class LiftOff implements Runnable{
    private int countDown=10;
    private static int taskCount=0;
    private int id=taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown, int id) {
        this.countDown = countDown;
        this.id = id;
    }

    @Override
    public String toString() {
        return "LiftOff{" +
                "countDown=" + countDown +
                ", id=" + id +
                '}';
    }

    @Override
    public void run() {
        while (countDown-->0){
            System.out.println(this);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        int capacity=5;
        List<LiftOff> liftOffs=new ArrayList<>(capacity);
        for(int i=0;i<capacity;i++){
            liftOffs.add(new LiftOff());
        }
        for(int i=0;i<capacity;i++){
            new Thread(liftOffs.get(i),"线程"+(i+1)).start();
        }
    }
}
