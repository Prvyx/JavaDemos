package emulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: 银行出纳员仿真（一个顾客队列，根据 顾客队列人数与工作的出纳员人数的比例了来管理工作的出纳员的人数）
 * @author: Prvyx
 * @created: 2022/04/25 13:31
 */
// 顾客
class Customer {
    private static int count;
    private final int id=count++;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }
}

// 顾客队列
class CustomerQueue extends ArrayBlockingQueue<Customer> {
    public CustomerQueue(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        return super.toString()+"'s size="+size();
    }
}
// 出纳员
class Teller implements Runnable{
    private static int count;
    private final int id=count++;
    private CustomerQueue customerQueue;
    private boolean serverCustomer=true;
    private int customerServed;
    private Random random=new Random(47);

    public Teller(CustomerQueue customerQueue) {
        this.customerQueue = customerQueue;
    }

    public void setServerCustomer(boolean serverCustomer) {
        this.serverCustomer = serverCustomer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Customer customer=customerQueue.take();
                // 尽量打印 任务开始的代码  而不是打印 任务完成的代码
                System.out.println(this+"完成"+customer+"的业务ing...");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(200)+200);
                synchronized (this){
                    // 下面两行代码必须在while()的前面，原因：已经从customerQueue中take()了一个，你必须先服务他，不然customer就平白无故消失了
                    customerServed++;
                    while (!serverCustomer){
                        wait();
                    }
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Teller{" +
                "id=" + id +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es=Executors.newCachedThreadPool();
        CustomerQueue customerQueue1=new CustomerQueue(10);
        for(int i=0;i<10;i++){
            customerQueue1.add(new Customer());
        }
        es.submit(new Teller(customerQueue1));
        es.submit(new Teller(customerQueue1));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}

// 出纳员管理者
class TellerManger implements Runnable{
    private ExecutorService es;
    private Queue<Teller> workingTellers=new LinkedList<>();
    private Queue<Teller> restingTellers=new LinkedList<>();
    private CustomerQueue customerQueue;
    private Random random=new Random(47);

    public TellerManger(ExecutorService es,CustomerQueue customerQueue) {
        this.es=es;
        this.customerQueue = customerQueue;

        // 为啥不同的Teller对象可以访问到同一个customerQueue，因为customerQueue是对象而不是基本类型，customerQueue引用指向同一个堆空间
        // 并不是因为Runnable共享资源
        Teller teller=new Teller(customerQueue);
        es.submit(teller);
        workingTellers.add(teller);
    }

    private void adjustTellerNumber(){
        if(customerQueue.size()/ workingTellers.size()>2){
            Teller teller;
            if(restingTellers.size()!=0){
                System.out.println("从restingTellers中调人");
                teller=restingTellers.poll();
                synchronized (teller){
                    teller.setServerCustomer(true);
                    teller.notifyAll();
                    workingTellers.add(teller);
                    System.out.println("调整之后:-----------\nworkingTellers:"+workingTellers.toString()+
                            "\nrestingTellers:"+restingTellers.toString()+
                            "\ncustomerQueue:"+customerQueue.toString()+"\n-----");
                }
            }else {
                System.out.println("restingTeller中无人手，新建一个Teller");
                teller=new Teller(customerQueue);
                es.submit(teller);
                workingTellers.add(teller);
                System.out.println("调整之后:workingTellers:"+workingTellers.toString()+
                        "\nrestingTellers:"+restingTellers.toString()+
                        "\ncustomerQueue:"+customerQueue.toString());
            }
        }else if (customerQueue.size()/workingTellers.size()>1&& customerQueue.size()/ workingTellers.size()<2){
            Teller teller=workingTellers.poll();
            synchronized (teller){
                teller.setServerCustomer(false);
                restingTellers.add(teller);
                System.out.println("调整之后:\nworkingTellers:"+workingTellers.toString()+
                        "\nrestingTellers:"+restingTellers.toString()+
                        "\ncustomerQueue:"+customerQueue.toString());
            }
        }else if(customerQueue.size()==0){
            while (workingTellers.size()>0){
                Teller teller=workingTellers.poll();
                synchronized (teller){
                    teller.setServerCustomer(false);
                    restingTellers.add(teller);
                    System.out.println("调整之后:\nworkingTellers:"+workingTellers.toString()+
                            "\nrestingTellers:"+restingTellers.toString()+
                            "\ncustomerQueue:"+customerQueue.toString());
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                adjustTellerNumber();
                TimeUnit.MILLISECONDS.sleep(200+random.nextInt(200));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class BankTellerSimulation {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newCachedThreadPool();

        CustomerQueue customerQueue=new CustomerQueue(10);
        for(int i=0;i<10;i++){
            customerQueue.put(new Customer());
        }
        TellerManger tellerManger=new TellerManger(es,customerQueue);
        es.submit(new Thread(tellerManger));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}
