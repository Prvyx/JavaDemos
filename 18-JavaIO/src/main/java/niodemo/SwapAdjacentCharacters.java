package niodemo;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @program: JavaDemos
 * @description:交换ByteBuffer相邻字符
 * @author: Prvyx
 * @created: 2022/04/16 23:12
 */

public class SwapAdjacentCharacters {
    private static void swapAdjacentChar(CharBuffer cb){
        while (cb.hasRemaining()){
            cb.mark();
            char c1=cb.get();
            char c2=cb.get();
            // 官方文档：Reset() this buffer's position to the previously-marked position.
            cb.reset();
            cb.put(c2).put(c1);
        }
    }
    public static void main(String[] args) {
        char[] data="dongshuaibo2".toCharArray();
        CharBuffer cb= ByteBuffer.allocate(data.length*2).asCharBuffer();
        cb.put(data);
        cb.rewind();
        swapAdjacentChar(cb);
        cb.rewind();
        System.out.println(cb);
        swapAdjacentChar(cb);
        System.out.println(cb.rewind());
    }
}
