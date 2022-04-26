import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: JavaDemos
 * @description:appendReplacement()、appendTail()相对于replaceAll()|replaceFirst()的灵活之处，以及测试reset()方法
 * @author: Prvyx
 * @created: 2022/04/08 15:25
 */

public class Replace_ResetDemo {
    private static final String s="dongACa";
    private static final Pattern pattern=Pattern.compile("[aeiou]");
    public static void main(String[] args) {
        Matcher m=pattern.matcher(s);
        StringBuffer sb=new StringBuffer();
        while (m.find()){
            // 根据Matcher类的源码可知，存在字符串s的copy副本（CharSequence text : The original string being matched.）
            m.appendReplacement(sb,m.group().toUpperCase());//m中的text被替换后，将text的(begin->end)子串的copy副本拼接到sb上
            System.out.println(sb);
        }
        // 执行一次或多次appendReplacement()后，可能输入字符串还余下一部分，将输入字符串余下部分的copy副本拼接到sb中
        m.appendTail(sb);

        System.out.println("-----");
        // 测试Matcher的reset()方法
        m.reset();
        while (m.find()){
            System.out.println(m.group());
        }
        System.out.println("====");
        m.reset("dangBo");
        while (m.find()){
            System.out.println(m.group());
        }
    }
}
/*Output:
dO
dOngACA
-----
o
a
====
a
o
*/