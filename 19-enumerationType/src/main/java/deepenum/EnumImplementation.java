package deepenum;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description:enum实现Generator<>接口
 * @author: Prvyx
 * @created: 2022/04/19 14:20
 */
interface Generator<T> {T next();}
enum Color3 implements Generator<Color3>{
    RED,BLUE,GREEN,WHITE;

    @Override
    public Color3 next() {
        return Color3.values()[new Random().nextInt(4)];
    }
}
public class EnumImplementation {
    public static void main(String[] args) {
        // 对象才能向上转型，而根据enum的深入理解，RED,BLUE,GREEN,WHITE均是Color3类的对象
        Generator<Color3> generator=Color3.BLUE;
        for(int i=0;i<10;i++){
            System.out.println(generator.next());
        }
    }
}
