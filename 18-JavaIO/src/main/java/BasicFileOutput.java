import java.io.*;

/**
 * @program: JavaDemos
 * @description:面向字符的文件输出
 * 在同包的BufferedInputFile.java的基础上
 * @author: Prvyx
 * @created: 2022/04/16 08:19
 */

public class BasicFileOutput {
    public static void main(String[] args) {
        // 若C:\Users\呵\Desktop\linksCopy.txt的文件不存在，会自动创建一个；但目录只能自行创建
        try (BufferedReader br=new BufferedReader(new StringReader(BufferedInputFile.read("C:\\Users\\呵\\Desktop\\links.txt")));
//             PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\呵\\Desktop\\linksCopy.txt")))
             PrintWriter pw=new PrintWriter("C:\\Users\\呵\\Desktop\\linksCopy.txt")//上一行可以简化为该行
        ){
            int lineCount=1;
            String s;
            while ((s=br.readLine())!=null){
                pw.println(lineCount++ +":"+s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
