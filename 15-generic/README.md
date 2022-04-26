#### 15-generic
Java泛型对接口进行了有益的补充
1. 简单泛型
   - 仅用一次方法调用就能返回多个对象：使用元组泛型（eg：class Student<A,B>） [元组泛型demo](src/main/java/TuplesGenericsDemo.java)
   - 实践：
     - [堆栈类demo](src/main/java/StackDemo.java)
     - [RandomList类demo](src/main/java/RandomList.java)
2. 泛型接口
   - [泛型生成器接口、泛型迭代器接口的demo](src/main/java/genericinterface/GenericInterfaceDemo.java)
3. 泛型方法
   - 泛型方法与泛型类灭有关系
   - 泛型参数放在返回值前面。eg：public <T> void f(T x){}
   - 泛型方法可以使用可变参数列表
   - 通用的Generator：[通用Generator的demo](src/main/java/CommonGenerator.java)
   - Set实用工具：实现交、并、补文氏图功能（个人未实践）
4. 构建复杂类型
   - [使用泛型构造货船复杂模型](src/main/java/cargoship/CargoShip.java)
5. 擦除
   - 资料：
     - [擦除的上下界](https://cloud.tencent.com/developer/article/1649866)
     - [擦除的上下界](https://segmentfault.com/a/1190000040327946)
     - [泛型概略](https://www.cnblogs.com/wzh2010/p/15886611.html)
6. 擦除的补偿
7. 边界
   - 看5.擦除的资料
8. 通配符（**注意，List<? extends Fruit>意思是这个List持有某种Fruit，而不是持有任何类型的Fruit，List<? extends Fruit>持有的对象是唯一的**）
   - 协变：上界 
     - List<? extends Fruit>：该List持有Apple、Orange、Pear等继承自Fruit中的一种对象，虽然持有的对象不同，但都有一个上界，即都继承自Fruit，所以可以“统一读”为Fruit
     当List持有Apple时，List<? extends Fruit>=>List<Apple>,add(<? extends Fruit>)=add(Apple),<? extends Fruit> get()=>Apple get()
     当List持有Orange时，List<? extends Fruit>=>List<Orange>,add(<? extends Fruit>)=add(Orange),<? extends Fruit> get()=>Orange get()
     当List持有Pear时，List<? extends Fruit>=>List<Pear>,add(<? extends Fruit>)=add(Pear),<? extends Fruit> get()=>Pear get()
     三个add()方法取交集，为空，所以编译器不允许List<? extends Fruit>调用add()方法；而三个get()方法的返回值都是Fruit的导出类，所以向上转型为Fruit
   - 逆变：下界
     - List<? super Fruit>：该List持有Fruit、Food、Object中的一种对象。当List持有Fruit时
     （即List<? super Fruit>=>List<Fruit>， list.add(<? super Fruit>)=>list.add(Fruit)），所以可以add(Fruit以及其导出类);
      同理，当List持有Food时，可以add(Food以及其导出类)；当List持有Object时，可以add(Object以及其导出类)
      合起来取交集，List<? super Fruit>可以add(Fruit以及其导出类)；三个get()方法的返回值分别为：Fruit、Food、Object，三个返回值取并集，所以向上转型为Object
   - 无界通配符
     - eg:List<?>
9. 问题
   - Java基本类型不能作为泛型参数
   - 注意运行时对泛型的擦除
10. 自限定的类型
11. 动态类型安全
12. 混型
13. 潜在类型机制
14. 总结：关于“泛型”这一章，越到后面越迷糊，但也学习了不少东西（之前都是直接跳过的），比如上下界，以及其中的原因