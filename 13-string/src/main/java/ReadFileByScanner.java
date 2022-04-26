import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @program: JavaDemos
 * @description:测试Scanner构造器的BufferedReader、File输入对象
 * @author: Prvyx
 * @created: 2022/04/08 16:19
 */

public class ReadFileByScanner {
    public static void main(String[] args) {
        String path="C:\\Users\\呵\\Desktop\\Java Web.txt";
        File file=new File(path);
        // BufferedReader作为Scanner构造器的参数
        try (BufferedReader br=new BufferedReader(new FileReader(file))){
            Scanner scanner=new Scanner(br);
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("====");
        // File作为Scanner构造器的参数
        try {
            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
