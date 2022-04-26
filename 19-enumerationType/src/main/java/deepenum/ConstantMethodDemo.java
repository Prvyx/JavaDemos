package deepenum;

import java.util.EnumSet;

/**
 * @program: JavaDemos
 * @description:枚举常量的方法重写
 * @author: Prvyx
 * @created: 2022/04/19 18:28
 */

public class ConstantMethodDemo {
    private static enum Color{
        RED{
            @Override
            void printColor() {
                System.out.println("打印红色");
            }
        },
        BLUE{
            @Override
            void printColor() {
                System.out.println("打印蓝色");
            }
        },
        WHITE{
            @Override
            void printColor() {
                System.out.println("打印白色");
            }
        },
        BLACK{
            @Override
            void printColor() {
                System.out.println("打印黑色");
            }
        }
        ;

        abstract void printColor();
    }

    public static void main(String[] args) {
        for(Color color:Color.values()){
            color.printColor();
        }

        System.out.println("-----");
        EnumSet<Color> enumSet=EnumSet.allOf(Color.class);
        System.out.println(enumSet);
        for(Color color:enumSet){
            color.printColor();
        }
    }
}
/*Output:
打印红色
打印蓝色
打印白色
打印黑色
 */