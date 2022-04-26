package niodemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @program: JavaDemos
 * @description:GetChannel的demo
 * @author: Prvyx
 * @created: 2022/04/16 14:26
 */

public class GetChannel {
    public static void main(String[] args) {
        try {
            // 通过缓冲器向FileOutputStream通道 写入
            FileChannel fc=new FileOutputStream("C:\\Users\\呵\\Desktop\\links.txt").getChannel();
            fc.write(ByteBuffer.wrap("some text\n".getBytes(StandardCharsets.UTF_8)));
            fc.close();

            // 通过缓冲器向RandomAccessFile通道 写入
            fc=new RandomAccessFile("C:\\Users\\呵\\Desktop\\links.txt","rw").getChannel();
            //文件指针置于末尾
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap("另外一些内容\n".getBytes(StandardCharsets.UTF_8)));
            fc.close();

            //通过缓冲器对FileInputStream通道 读取
            fc=new FileInputStream("C:\\Users\\呵\\Desktop\\links.txt").getChannel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            fc.read(byteBuffer);
            // flip()：将byteBuffer的当前位置调换为0，方便后续对byteBuffer的使用
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char) byteBuffer.get());
            }
            fc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
