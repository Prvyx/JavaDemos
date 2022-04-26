import java.io.IOException;
import java.io.StringReader;

/**
 * @program: JavaDemos
 * @description:内存中的字符串作为输入源
 * 在同一个默认包的BufferedInputFile.java的基础上
 * @author: Prvyx
 * @created: 2022/04/16 07:44
 */

public class MemoryInput {
    public static void main(String[] args) {
        StringReader sr=new StringReader(BufferedInputFile.read("C:\\Users\\呵\\Desktop\\links.txt"));
        int c;
        try {
            while ((c=sr.read())!=-1){
                System.out.print((char)c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
