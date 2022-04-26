import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemos
 * @description:
 * @author: Prvyx
 * @created: 2022/04/05 17:46
 */

public class Test {
    public static void main(String[] args) {
        Map<Character,Integer> map=new HashMap<>();
        String s="abcabc";
        map.put('a',0);
        map.put('b',1);
        map.put('c',2);
        System.out.println(map.containsKey(s.charAt(3)));
    }
}
