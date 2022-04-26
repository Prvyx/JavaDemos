package niodemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: JavaDemos
 * @description:通过nio进行文件的复制
 * @author: Prvyx
 * @created: 2022/04/16 14:54
 */

public class FileCopyByNio {
    public static void main(String[] args) {
        try {
            FileChannel fc1=new FileInputStream("C:\\Users\\呵\\Desktop\\linksCopy.txt").getChannel();
            FileChannel fc2=new FileOutputStream("C:\\Users\\呵\\Desktop\\links.txt").getChannel();
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            int len;
            while((len=fc1.read(buffer))!=-1){
                // flip()：改变位置：将buffer的limit置为position（当前位置），且将position置为0，方便后续对byteBuffer的使用（使用position(此时为0)~limit）
                // 这样fc2.write(buffer)就不用判断要读多长的字节了
                buffer.flip();
                fc2.write(buffer);
                // 清除buffer，便于下次的向buffer的写入
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
