package basicEnum;

/**
 * @program: JavaDemos
 * @description:基本enum特性
 * @author: Prvyx
 * @created: 2022/04/19 13:19
 */

enum Color {RED,BLUE,YELLOW,GREEN}
public class EnumClass {
    public static void main(String[] args) {
        for(Color color:Color.values()){
            System.out.println("-----");
            System.out.println("color.ordinal()="+color.ordinal());
            System.out.println(color.compareTo(Color.YELLOW));
            System.out.println(color.equals(Color.YELLOW));
            System.out.println(color.getDeclaringClass());
            System.out.println(color);
        }

        for(String s:"RED BLUE YELLOW GREEN".split(" ")){
            Color color = Enum.valueOf(Color.class, s);
            System.out.println(color);
        }

    }
}
