package enumset;
import java.util.EnumSet;

import static enumset.Color.*;
import static java.lang.System.out;
/**
 * @program: JavaDemos
 * @description:EnumSet的demo
 * @author: Prvyx
 * @created: 2022/04/19 16:10
 */
public class EnumSetDemo {
    public static void main(String[] args) {
        EnumSet<Color> enumSet=EnumSet.noneOf(Color.class);
        System.out.println(enumSet);
        enumSet.add(YELLOW);
        out.println(enumSet);
        enumSet.addAll(EnumSet.of(WHITE,RED));
        out.println(enumSet);
        enumSet.remove(WHITE);
        out.println(enumSet);
        enumSet.removeAll(EnumSet.allOf(Color.class));
        out.println(enumSet);
        enumSet=EnumSet.allOf(Color.class);
        out.println(enumSet);
        // 删除enumSet中的RED,YELLOW（complement的意思：补充）
        enumSet=EnumSet.complementOf(EnumSet.of(RED,YELLOW));
        out.println(enumSet);
    }
}
