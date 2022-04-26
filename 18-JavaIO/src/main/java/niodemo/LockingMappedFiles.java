package niodemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @program: JavaDemos
 * @description:对映射文件进行部分加锁
 * 注：对通道加锁，不能对缓冲器加锁
 * @author: Prvyx
 * @created: 2022/04/17 11:40
 */

public class LockingMappedFiles {
    static final int LENGTH=(128*1024)*20;
    static FileChannel fc;
    public static void main(String[] args) throws IOException {
        fc=new RandomAccessFile("C:\\Users\\呵\\Desktop\\linksTemp.txt","rw").getChannel();
        MappedByteBuffer buffer=fc.map(FileChannel.MapMode.READ_WRITE,0,LENGTH);
        for(int i=0;i<LENGTH;i++){
            buffer.put((byte) i);
        }
        for(int i=0;i<LENGTH/(128*1024);i++){
            System.out.print(buffer.get(i));
        }
        new LockingMappedFile(buffer,0,LENGTH/(128*1024));
//        new LockingMappedFile(buffer,4,LENGTH/(128*1024));
    }

    private static class LockingMappedFile extends Thread{
        private ByteBuffer slice;
        private int start;
        private int end;

        public LockingMappedFile(ByteBuffer buffer, int start, int end) {
            this.start = start;
            this.end = end;

            // 计算slice
            buffer.position(start);
            buffer.limit(end);
            slice = buffer.slice();

            // 启动线程
            start();
        }

        @Override
        public void run(){
            try {
                // 对通道加锁，不能对缓冲器加锁
                FileLock fl=fc.tryLock(start,end-start,false);
                System.out.println("\nslice.position()="+slice.position());
                while (slice.position()<slice.limit()-1){
                    slice.put((byte) (slice.get()+2));
                }
                fl.release();
                slice.rewind();
                System.out.println("\n---------");
                while (slice.hasRemaining()){
                    System.out.print(slice.get());
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
