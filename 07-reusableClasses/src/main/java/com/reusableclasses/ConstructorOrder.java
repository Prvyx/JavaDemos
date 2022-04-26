package com.reusableclasses;

/**
 * @program: JavaDemos
 * @description:验证基类、导出类的构造器调用顺序，以及导出类构造器中的关于基类构造器的默认调用
 * @author: Prvyx
 * @created: 2022/04/05 16:13
 */
class BasicClass {
    BasicClass(){
        System.out.println("BasicClass无参构造器");
    }
    BasicClass(int i){
        System.out.println("BasicClass有参构造器");
    }

}
class DerivedClass extends BasicClass{
    DerivedClass(){
        System.out.println("DerivedClass无参构造器");
    }
    DerivedClass(int i){
        // 此处默认调用super()，即BasicClass()而不是BasicClass(int i)
        // 若想调用BasicClass(int i)，添加一行super(i)即可
        System.out.println("DerivedClass有参构造器");
    }
}
public class ConstructorOrder {
    public static void main(String[] args) {
        new DerivedClass(1);
    }
}
/*Output:
BasicClass无参构造器
DerivedClass有参构造器
 */