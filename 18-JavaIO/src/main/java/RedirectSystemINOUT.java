import java.io.*;

/**
 * @program: JavaDemos
 * @description:重定向默认的System.in、System.out
 * @author: Prvyx
 * @created: 2022/04/16 13:33
 */

public class RedirectSystemINOUT {
    public static void main(String[] args) {
        // 记录默认的System.in、System.out
        PrintStream out = System.out;
        InputStream in = System.in;

        try {
            // 重定向System.in System.out
            System.setIn(new BufferedInputStream(new FileInputStream("C:\\Users\\呵\\Desktop\\links.txt")));
            System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\呵\\Desktop\\linksCopy.txt")));

            // 读取links.txt并写入linksCopy.txt中
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String s;
            while((s=br.readLine())!=null){
                System.out.println(s);
            }

            // 恢复默认的System.in、System.out
            System.setIn(in);
            System.setOut(out);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
