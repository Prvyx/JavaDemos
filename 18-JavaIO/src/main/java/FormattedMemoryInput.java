import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @program: JavaDemos
 * @description: 格式化的内存输入
 * 在同一个默认包的BufferedInputFile的基础上
 * @author: Prvyx
 * @created: 2022/04/16 08:07
 */

public class FormattedMemoryInput {
    public static void main(String[] args) {
        try (DataInputStream dataInputStream=new DataInputStream(new ByteArrayInputStream(
                BufferedInputFile.read("C:\\Users\\呵\\Desktop\\links.txt").getBytes(StandardCharsets.UTF_8)
        ))){
            int c;
            while ((c=dataInputStream.readByte())>=0){
                System.out.print((char) c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
