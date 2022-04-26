package basicEnum;

/**
 * @program: JavaDemos
 * @description:enum在switch的使用
 * @author: Prvyx
 * @created: 2022/04/19 14:05
 */
enum Color1 {RED,BLUE,YELLOW,GREEN}

public class EnumInSwitch {
    public static void main(String[] args) {
        for(Color1 color:Color1.values()){
            switch (color){
                case YELLOW -> System.out.println("这是YELLOW");
                case RED -> System.out.println("这是RED");
                case BLUE -> System.out.println("这是BLUE");
                case GREEN -> System.out.println("这是GREEN");
            }
        }
    }
}
