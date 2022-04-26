package com.initialCleanUp;

/**
 * @program: JavaDemos
 * @description: 测试Enum的使用
 * @author: Prvyx
 * @created: 2022/04/05 14:07
 */

enum Color{
    red,orange,yellow,green,blue,purple
}
public class EnumTester {
    public static void main(String[] args) {
        for(Color color:Color.values()){
            System.out.println(color+"ordinal:"+color.ordinal());
        }
    }
}
