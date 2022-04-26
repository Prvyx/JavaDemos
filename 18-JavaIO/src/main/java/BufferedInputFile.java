import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @program: JavaDemos
 * @description:面向字符的缓冲输入文件（将文件作为输入源，且用缓冲进行封装）
 * @author: Prvyx
 * @created: 2022/04/16 07:33
 */

public class BufferedInputFile {
    public static String read(File file){
        StringBuilder sb=new StringBuilder();
        String str;
        try (BufferedReader br=new BufferedReader(new FileReader(file))){
            // br.readLine()会删掉换行符
            while ((str=br.readLine())!=null){
                sb.append(str+"\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return sb.toString();
    }
    public static String read(String path){
        return BufferedInputFile.read(new File(path));
    }

    public static void main(String[] args) {
        System.out.println(BufferedInputFile.read("C:\\Users\\呵\\Desktop\\links.txt"));
    }
}
