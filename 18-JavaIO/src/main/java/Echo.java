import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: JavaDemos
 * @description:Java版的Unix的echo实现
 * @author: Prvyx
 * @created: 2022/04/16 13:17
 */

public class Echo {
    public static void main(String[] args) {
        try (BufferedReader br=new BufferedReader(new InputStreamReader(System.in));){
            String s;
            while ((s=br.readLine())!=null&&s.length()!=0){
                System.out.println(s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
