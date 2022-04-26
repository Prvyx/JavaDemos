/**
 * @program: JavaDemos
 * @description: 匿名内部类（带构造器）demo
 * @author: Prvyx
 * @created: 2022/04/06 16:23
 */

interface SpaceShipControl{
    void forward();
}
class SpaceShip {
    public SpaceShipControl getSpaceShipControl(){
        return new SpaceShipControl() {//啦啦啦，你根本不知道我是怎么实现的，我的forward默认前进100哦，你也不知道，你只能跳到接口
            private int i;
            {i=100;}//构造器
            @Override
            public void forward() {
                System.out.println("前进"+i+"米");
            }
        };
    }
}
public class Tester {
    public static void main(String[] args) {
        SpaceShip spaceShip=new SpaceShip();
        spaceShip.getSpaceShipControl().forward();
    }
}
