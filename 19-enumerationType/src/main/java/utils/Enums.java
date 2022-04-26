package utils;

import java.util.Random;

/**
 * @program: JavaDemos
 * @description:Enum的工具类:Enums
 * @author: Prvyx
 * @created: 2022/04/19 14:39
 */

public class Enums {
    private static final Random random=new Random(47);
    // 在tClass的枚举列表中随机获取一个enum实例
    public static <T extends Enum<T>> T next(Class<T> tClass){
        return random(tClass.getEnumConstants());
    }
    public static <T> T random(T[] ts){
        return ts[random.nextInt(ts.length)];
    }

    // 测试Enums工具类
    public static void main(String[] args) {
        enum Color {RED,GREEN,BLUE,WHITE};
        for(int i=0;i<5;i++){
            System.out.println(Enums.next(Color.class));
        }
    }
}
