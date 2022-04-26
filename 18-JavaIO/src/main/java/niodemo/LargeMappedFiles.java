package niodemo;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: JavaDemos
 * @description:内存映射文件demo
 * @author: Prvyx
 * @created: 2022/04/16 23:28
 */

public class LargeMappedFiles {
    static int length=0x8FFFFFF;//128MB
    public static void main(String[] args)throws Exception {
        // position- 文件中映射区域开始的位置 size- 要映射的区域的大小
        MappedByteBuffer buffer= new RandomAccessFile("C:\\Users\\呵\\Desktop\\linksCopy.txt","rw")
                .getChannel().map(FileChannel.MapMode.READ_WRITE,0,length);
        for(int i=0;i<length;i++){
            buffer.put((byte) 'x');
        }

        System.out.println("finish put");
//        for(int i=length/2;i<length/2+10;i++){
//            System.out.println((char) buffer.get(i));
//        }
        buffer.rewind();
        while (buffer.hasRemaining()){
            System.out.print((char) buffer.get());
        }
    }
}
