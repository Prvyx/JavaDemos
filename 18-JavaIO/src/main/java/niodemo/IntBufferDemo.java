package niodemo;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @program: JavaDemos
 * @description:IntBuffer的demo
 * 向inputBuffer存入数据 或 从inputBuffer相对get()数据，会使position向后移动
 * @author: Prvyx
 * @created: 2022/04/16 22:18
 */

public class IntBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        IntBuffer intBuffer=buffer.asIntBuffer();
        // 每次向intBuffer存入一个数据，position就自动向后移动一位
        intBuffer.put(new int[]{1,2,3,4,5,6});
        // 官方文档：get(int index) Description：Absolute get method.绝对的get(int index)不会使position向后移动
        System.out.println(intBuffer.get(4));
        System.out.println("position:"+intBuffer.position()+",limit:"+intBuffer.limit()+",capacity:"+intBuffer.capacity());
        intBuffer.flip();
        System.out.println("position:"+intBuffer.position()+",limit:"+intBuffer.limit()+",capacity:"+intBuffer.capacity());

        // 读取intBuffer position~limit-1 之间的数据
        while (intBuffer.hasRemaining()){
            System.out.println("position:"+intBuffer.position()+",limit:"+intBuffer.limit()+",capacity:"+intBuffer.capacity());

            // 官方文档：get() Description：Relative get method. 相对的get()会使position向后移动
            System.out.println(intBuffer.get());
        }

    }
}
