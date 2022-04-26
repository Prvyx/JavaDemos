package com.prvyx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: JavaDemos
 * @description:通过反射调用private方法demo
 * @author: Prvyx
 * @created: 2022/04/09 17:53
 */
class A{
    private void a(int m,int n){System.out.println("A a()"+m+"-"+n);}
}
public class InvokePrivateMethodByReflect {
    public static void main(String[] args) {
        try {
            // getMethods()返回该类以及其基类的所有public方法；getDeclaredMethods()返回该类所有方法（包括private）
            Method method=A.class.getDeclaredMethod("a", int.class, int.class);
            // 设置该方法的accessible为true
            method.setAccessible(true);
            method.invoke(new A(),1,2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
