import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * @program: JavaDemos
 * @description: Scanner与正则配合：scanner.hasNext(Pattern/String)方法
 * @author: Prvyx
 * @created: 2022/04/08 16:39
 */

public class Scanner_Regex_Demo {
    private static String threatData=
            "58.27.82.161@02/10/2005\n"+ "58.27.82.161@02/10/2006\n"+"58.27.82.161@02/10/2007\n";
    public static void main(String[] args) {
        Scanner scanner=new Scanner(threatData);
        String pattern="(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        // scanner.hasNext(pattern) --- scanner.next(pattern) 一一对应，包括参数
        while (scanner.hasNext(pattern)){
            scanner.next(pattern);
            // scanner.match()返回最后一次扫描操作的匹配结果
            MatchResult matchResult=scanner.match();
            String ip=matchResult.group(1);
            String date=matchResult.group(2);
            System.out.println("ip:"+ip+" date:"+date);
        }

    }
}
/*Output:
ip:58.27.82.161 date:02/10/2005
ip:58.27.82.161 date:02/10/2006
ip:58.27.82.161 date:02/10/2007
 */