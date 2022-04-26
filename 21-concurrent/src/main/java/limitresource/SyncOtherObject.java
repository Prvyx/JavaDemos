package limitresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemos
 * @description:同步其他对象的demo
 * 场景：假设银行只有一个账户Account，有两个人/多个人想去不同的银行几乎同一时间取钱
 * 可以把 场景中不同的银行 视为 Bank类的不同子线程（任务）
 * @author: Prvyx
 * @created: 2022/04/21 14:47
 */

class Account{
    public int remain;

    public Account(int remain) {
        this.remain = remain;
    }

    // 汇款
    public void saveMoney(int money){
        remain+=money;
    }
    // 提款
    synchronized public void withdraw(int money){
        if(remain-money>=0){
            remain-=money;
            System.out.println("取走了"+money+"，余额："+getRemain());
        }else {
            System.out.println("余额不足："+getRemain());
        }
    }
    // 查看余额
    synchronized public int getRemain(){
        return remain;
    }
}

class Bank implements Runnable{
    private final Account account=new Account(1000);

    @Override
    public void run() {
        synchronized (account){
            account.withdraw(100);
            // 要想保证下一行正确打印，需要给run()里面所有代码套上sync..(account)。
            // 原因：eg：子线程1刚取完钱准备进入getRemain()，而子线程2准备进入withdraw()，错误场景：子线程2先进入withdraw()
            System.out.println("余额："+account.getRemain());
        }
    }
}
public class SyncOtherObject {
    public static void main(String[] args) {
        Bank bank=new Bank();
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<20;i++){
            es.submit(bank);
        }
        es.shutdown();
    }
}
