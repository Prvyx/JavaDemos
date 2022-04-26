package niodemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @program: JavaDemos
 * @description:ByteBuffer转换为文本
 * 注意编码、解码，一般来说，用什么编码，就用什么解码（解码字符集>=编码字符集），且编码与解码过程中均不能出错，才能正确地编解码
 * eg1：中文字符=>字节（使用UTF-8），然后字节=>中文字符（使用UTF-8或者兼容UTF-8的字符集）
 * eg2：中文字符=>字节（使用ASCII），然后.... （编码过程已经出错）
 * 编码：字符（可视化数据）=>字节；解码：字节=>字符（可视化数据）
 * @author: Prvyx
 * @created: 2022/04/16 16:54
 */

public class BufferToText {
    public static void main(String[] args) throws Exception{
        FileChannel fc1=new FileOutputStream("C:\\Users\\呵\\Desktop\\linksCopy.txt").getChannel();
        fc1.write(ByteBuffer.wrap("中文".getBytes(StandardCharsets.UTF_16BE)));
        fc1.close();
        FileChannel fc2=new FileInputStream("C:\\Users\\呵\\Desktop\\linksCopy.txt").getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        fc2.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        System.out.println("-----");
        System.out.println(Charset.forName("UTF-16BE").decode(buffer));
    }
}
