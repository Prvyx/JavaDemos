package enumset;

/**
 * @program: JavaDemos
 * @description:EnumMap的demo
 * @author: Prvyx
 * @created: 2022/04/19 16:25
 */

import java.util.EnumMap;
import java.util.Map;

import static enumset.Color.*;
interface Command{void action();}
public class EnumMapDemo {
    public static void main(String[] args) {
        EnumMap<Color,Command> enumMap=new EnumMap<Color, Command>(Color.class);
        enumMap.put(RED, new Command() {
            @Override
            public void action() {
                System.out.println("红色");
            }
        });
        enumMap.put(YELLOW, new Command() {
            @Override
            public void action() {
                System.out.println("黄色");
            }
        });
        for(Map.Entry<Color,Command> entry:enumMap.entrySet()){
            System.out.println("key:"+entry.getKey());
            entry.getValue().action();
        }
    }
}
