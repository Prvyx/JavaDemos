package niodemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @program: JavaDemos
 * @description: ByteBuffer的字节存放次序：big endian(高位优先)、little endian(低位优先)
 * ByteBuffer默认以高位优先存储数据，数据在网上传送时常常使用高位优先
 * @author: Prvyx
 * @created: 2022/04/16 22:47
 */

public class Endians {
    public static void main(String[] args) {
        ByteBuffer bb=ByteBuffer.allocate(12);
        bb.asCharBuffer().put("abcdef");
        // bb.array()返回byte[]
        System.out.println(Arrays.toString(bb.array()));

        // 调整ByteBuffer的字节存放次序为高位次序
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        // 调整ByteBuffer的字节存放次序为低位次序
        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
    }
}
/*Output:
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102, 0]
 */