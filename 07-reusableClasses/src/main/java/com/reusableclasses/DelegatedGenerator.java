package com.reusableclasses;

/**
 * @program: JavaDemos
 * @description: Intellij IDEA提供代理支持：alt+insert组合键，选择Delegated methods
 * @author: Prvyx
 * @created: 2022/04/05 16:38
 */
class SpaceShipControl {
    public void forward(){}
    public void back(){}
    public void left(){}
    public void right(){}
    public void up(){}
    public void down(){}
}
class SpaceShip{
    final private SpaceShipControl spaceShipControl=new SpaceShipControl();

    public void forward() {
        spaceShipControl.forward();
    }

    public void back() {
        spaceShipControl.back();
    }

    public void left() {
        spaceShipControl.left();
    }

    public void right() {
        spaceShipControl.right();
    }

    public void up() {
        spaceShipControl.up();
    }

    public void down() {
        spaceShipControl.down();
    }
}
public class DelegatedGenerator {
    public static void main(String[] args) {
        new SpaceShip();
    }
}
