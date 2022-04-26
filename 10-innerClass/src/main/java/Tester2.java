/**
 * @program: JavaDemos
 * @description: 通过内部类实现Java多重继承的效果 的demo
 * @author: Prvyx
 * @created: 2022/04/06 16:50
 */

class A {}
class C {
    void c(){}
}
class B extends A{
    private class D extends C{
        @Override
        void c() {
            System.out.println("D中的c()");
        }
    }
    public C getC(){return new D();}//new D()向上转型为C
}
public class Tester2 {
    public static void main(String[] args) {
        C c=new B().getC();//啦啦啦，看似B没有extends C，但却能获得C的引用，你还看不出来，气不气
        c.c();
    }
}
