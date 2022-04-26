import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: JavaDemos
 * @description:JGrep：灵感来于Unix的grep(两个参数：文件名、要匹配的正则表达式)
 * @author: Prvyx
 * @created: 2022/04/08 15:50
 */

public class JGrep {
    private static List<String> getFileString(String filePath){
        List<String> stringList=new ArrayList<>();
        File file=new File(filePath);
        try (BufferedReader br=new BufferedReader(new FileReader(file))){
            String str="";
            while ((str=br.readLine())!=null){
                stringList.add(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return stringList;
    }

    public static void main(String[] args) {
        String filePath="C:\\Users\\呵\\Desktop\\Java Web.txt";
        String regex="(?i)(?u)你好[，]?我是prvyx";
        List<String> fileStrings=getFileString(filePath);
        Pattern pattern=Pattern.compile(regex);
        Matcher m=pattern.matcher("");
        int index=0;
        for(String s:fileStrings){
            m.reset(s);
            while (m.find()){
                System.out.println(index++ +": "+m.group()+": "+m.start());
            }
        }
    }
}
