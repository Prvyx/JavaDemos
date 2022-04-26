package com.prvyx.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: JavaDemos
 * @description:动态代理
 * @author: Prvyx
 * @created: 2022/04/09 17:02
 */
// 飞船的温度控制系统
interface TemperatureControl{
    void temperatureUp();
    void temperatureDown();
}
// 飞船的动力控制系统
interface PowerControl{
    void forward();
    void back();
}
// 飞船
class SpaceShip2 implements TemperatureControl,PowerControl {
    @Override
    public void temperatureUp() {
        System.out.println("SpaceShip2 temperatureUp 10");
    }

    @Override
    public void temperatureDown() {
        System.out.println("SpaceShip2 temperatureDown 10");
    }

    @Override
    public void forward() {
        System.out.println("SpaceShip2 forward 10");
    }

    @Override
    public void back() {
        System.out.println("SpaceShip2 back 10");
    }
}
//动态代理的调用处理器
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied,args);
    }
}
public class DynamicProxy {
    public static void main(String[] args) {
        // 飞船的代理:proxy
        Object proxy = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),
                new Class[]{TemperatureControl.class, PowerControl.class},
                new DynamicProxyHandler(new SpaceShip2()));
        // 向下转型
        TemperatureControl temperatureControl=(TemperatureControl) proxy;
        temperatureControl.temperatureUp();
        temperatureControl.temperatureDown();
        // 向下转型
        PowerControl powerControl=(PowerControl) proxy;
        powerControl.forward();
        powerControl.back();
    }
}
