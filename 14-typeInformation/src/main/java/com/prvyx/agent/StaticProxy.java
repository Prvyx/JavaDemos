package com.prvyx.agent;

/**
 * @program: JavaDemos
 * @description:静态代理
 * @author: Prvyx
 * @created: 2022/04/09 17:02
 */
// 飞船的温度控制系统
interface TemperatureControlSystem{
    void temperatureUp();
    void temperatureDown();
}
// 飞船的温度控制系统Impl
class TemperatureControlSystemImpl implements TemperatureControlSystem{
    @Override
    public void temperatureUp() {
        System.out.println("temperatureUp 10");
    }

    @Override
    public void temperatureDown() {
        System.out.println("temperatureDown 10");
    }
}
// 飞船的动力控制系统
interface PowerControlSystem{
    void forward();
    void back();
}
// 飞船的动力控制系统Impl
class PowerControlSystemImpl implements PowerControlSystem {
    @Override
    public void forward() {
        System.out.println("forward 10m");
    }

    @Override
    public void back() {
        System.out.println("back 10m");
    }
}
// 飞船：代理了 飞船的温度控制系统、飞船的动力控制系统
class SpaceShip implements TemperatureControlSystem,PowerControlSystem{
    private final TemperatureControlSystem temperatureControlSystem=new TemperatureControlSystemImpl();
    private final PowerControlSystem powerControlSystem=new PowerControlSystemImpl();

    @Override
    public void temperatureUp() {
        temperatureControlSystem.temperatureUp();
    }

    @Override
    public void temperatureDown() {
        temperatureControlSystem.temperatureDown();
    }

    @Override
    public void forward() {
        powerControlSystem.forward();
    }

    @Override
    public void back() {
        powerControlSystem.back();
    }
}
public class StaticProxy {
    public static void main(String[] args) {
        SpaceShip spaceShip=new SpaceShip();
        spaceShip.temperatureUp();
        spaceShip.temperatureDown();
        spaceShip.forward();
        spaceShip.back();
    }
}
