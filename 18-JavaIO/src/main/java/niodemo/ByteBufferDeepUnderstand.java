package niodemo;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @program: JavaDemos
 * @description:ByteBuffered的深入理解：flip()、clear()、rewind()的区别
 * 总结：ByteBuffer有position、limit、capacity
 * 1. flip():limit=position;position=0; 作用：方便后续的写入操作（读取ByteBuffer（position~limit-1），写至输出地），不用人为地编写fileChannel.write(bytes,0,len)的代码，而是直接fileChannel.write(bytes)即可
 * 2. rewind():position=0; 作用：方便后续的”再次读取ByteBuffer，且无写入“的操作（读取position~limit-1）
 * 3. clear():position=0;limit=capacity; 作用：将ByteBuffer写入文件等输出地后，需要使用clear()，重置position、limit为初始状态
 * 4. 将ByteBuffer写入文件后，position停在与limit相等的位置
 * 在Java中，不讲字符集，说一个字符占多少字节都是耍流氓
 * @author: Prvyx
 * @created: 2022/04/16 18:09
 */

public class ByteBufferDeepUnderstand {
    static ByteBuffer read(ByteBuffer buffer){
        char c;
        while ((c=buffer.getChar())!=0){
            System.out.print(c);
        }
        return buffer;
    }
    public static void main(String[] args) throws Exception{
        // 创建一个capacity（容量）为20的ByteBuffer（在不讨论字符集的情况下，1个英文字符为8bit，也就是1个字节，但在Java的UTF_16BE字符集中，英文字符默认2个字节）
        System.out.println("dong".getBytes(StandardCharsets.UTF_16BE).length);
        // 所以容量为20的buffer只能存10个英文字符
        ByteBuffer buffer=ByteBuffer.allocate(20);
        // buffer.asCharBuffer().put()输入，不会乱码。注：”buffer.asCharBuffer()读取、asCharBuffer().put()写入“默认是UTF_16BE字符集，所以一个英文占2个字符
        // 你看下C:\Users\呵\Desktop\linksCopy.txt文件就知道了
        buffer.asCharBuffer().put("helloF");
        System.out.println("position:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        System.out.println(buffer.asCharBuffer());
        System.out.println("position:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        buffer=read(buffer);
        System.out.println("\nposition:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        System.out.println("\nflip()之后");
        buffer.flip();
        System.out.println("position:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        FileChannel fc=new FileOutputStream("C:\\Users\\呵\\Desktop\\linksCopy.txt").getChannel();
        fc.write(buffer);
        System.out.println("将ByteBuffer写入文件后");
        System.out.println("position:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());

        System.out.println(buffer.asCharBuffer());

        System.out.println("\nclear()之后");
        buffer.clear();
        System.out.println("position:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        buffer=read(buffer);
        System.out.println("\nposition:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        System.out.println(buffer.asCharBuffer());
        System.out.println("\nrewind()之后");
        buffer.rewind();
        System.out.println("position:"+buffer.position()+",limit: "+buffer.limit()+",capacity: "+buffer.capacity());
        System.out.println(buffer.asCharBuffer());
    }
}
