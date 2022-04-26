#### 09-interface
- 抽象类与接口：
  - 抽象类：有一个或多个abstract方法
  - 接口：完全抽象的类，方法全为abstract方法。接口中的方法都是默认public的，接口中的字段都是默认static、final的
- 一个类实现一个接口，重写的方法必须是public，因为接口的方法是public，Java继承不允许降低访问权限
- Java中的多重继承：class Student extends People implements CanRun,CanSleep{}，Student类能向上转型为其中的任何一个