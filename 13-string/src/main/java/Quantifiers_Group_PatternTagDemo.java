import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: JavaDemos
 * @description: 关于正则的量词、元组、Pattern标记的demo
 * @author: Prvyx
 * @created: 2022/04/08 13:51
 */

public class Quantifiers_Group_PatternTagDemo {
    private final static String s="a<tr>aava </tr>abb\n"+
                                    "<tr>bbv </tr>\n"+
                                    "a<tr>ccv </tr>\n"+
                                    "<tr>ddv </tr>";
    // Java正则若没有量词，默认为贪婪型(Greediness)，每次匹配（指的是正则的每个控制元素与s的匹配）的都是最大匹配范围，在失败后，还会回溯尝试匹配更小的范围，直至找到
    // 量词为?：勉强型(Laziness)，只要全部控制元素都匹配成功，就不再继续尝试匹配更大范围的内容
    // 量词为+：占有型，正则的每个控制元素的匹配都是最大匹配范围，但与Greediness不同的是，没有回溯记录，失败后，无法回退尝试匹配更小的范围
    // 查找的资料：https://daimajiaoliu.com/daima/8c7967e4344d406

    // 正则中元组的出现，是为了更方便地在匹配成功的字符串中获得相应子串
    // Pattern标记(?m)开启了Pattern.MultiLine模式，将原本^、$作为整个字符串的开始、结束=>每行字符串的开始、结束
    private final static Pattern pattern=Pattern.compile("(?m)^<(.+)>");

    public static void main(String[] args) {
        Matcher m=pattern.matcher(s);
        System.out.println(m.groupCount());//元组的个数
        while (m.find()){
            for(int j=0;j<=m.groupCount();j++){
                // 官方文档：the expression m.group(0) is equivalent to m.group(). 组0就是匹配成功的整个字符串
                System.out.print(m.group(j)+" | start:"+m.start(j)+" | end:"+(m.end(j)-1)+" // ");
            }
            System.out.println();
        }
    }

}
