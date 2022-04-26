import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @program: JavaDemos
 * @description:用PrintWriter封装System.out
 * @author: Prvyx
 * @created: 2022/04/16 13:22
 */

public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter pw=new PrintWriter(new OutputStreamWriter(System.out),true);
//        PrintWriter pw=new PrintWriter(System.out,true);
        pw.println("Hello World");
    }
}
