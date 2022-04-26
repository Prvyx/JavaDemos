package syncqueue;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemos
 * @description: PipedReader、PipedWriter测试（Piped（管道）基本是一个阻塞队列（同步队列））
 * @author: Prvyx
 * @created: 2022/04/23 20:58
 */
class Sender implements Runnable{
    private PipedWriter pipedWriter=new PipedWriter();

    public PipedWriter getPipedWriter() {
        return pipedWriter;
    }

    @Override
    public void run() {
        try {
            for(char c='a';c<='z';c++){
                pipedWriter.write(c);
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Receiver implements Runnable {
    private PipedReader pipedReader;

    public Receiver(PipedWriter pipedWriter) {
        try {
            pipedReader=new PipedReader(pipedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.print((char)pipedReader.read());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender=new Sender();
        Receiver receiver=new Receiver(sender.getPipedWriter());
        ExecutorService es= Executors.newCachedThreadPool();
        es.submit(sender);
        es.submit(receiver);
        TimeUnit.SECONDS.sleep(4);
        es.shutdown();
    }
}
