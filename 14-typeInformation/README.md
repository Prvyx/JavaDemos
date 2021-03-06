#### 14-typeInformation
- Class类
  - Class对象的获取
  - Class.forName(全限定名)
  - 对象名.getClass()
  - Student.class
  - 简单、安全，而且在编译期就会受到检查（因此不需要置于try语句块中）
  - 加载、链接、初始化（Student.class没有“初始化”）
  - 通过Class对象创建实例
  - class.newInstance()（使用默认构造器来创建）
  - 泛化的Class
  - Class<?>优于Class
  - Class<? extends People>
  - Class<? super Student>
  - [测试“泛化的Class”的demo](C:\Users\呵\Desktop\getWork\JavaDemos\14-typeInformation\src\main\java\Tester.java)
- RTTI与反射
  - RTTI：在编译时知道类型信息
    - RTTI形式：
      - 传统的类型转换（eg:(Shape)）
      - Class对象，Java执行类型检查，类型安全的向下转型
      - instanceof，返回布尔值：表示对象是不是某个特定类型的实例
        - (eg：People、Student类,people=new Student();people instanceof Student/People为true;people=new People();people instanceof Student为false)
      - class.isInstance(对象名)：<=> 动态instanceof
    - 注册工厂
    - (instanceof、isInstance())与Class的等价性
      - instanceof保持了继承的概念，而用==比较Class对象，则不考虑继承的概念
  - 反射：在运行时发现和使用类的信息
    - Class类和java.lang.reflect类库（包含Field、Method、Constructor类等）一起对”反射”提供支持
  - 代理：
    - 静态代理：[静态代理demo](C:\Users\呵\Desktop\getWork\JavaDemos\14-typeInformation\src\main\java\com\prvyx\agent\StaticProxy.java)
    - 动态代理：静态代理Proxy调用的所有方法在动态代理中都被重定向到“单一的调用处理器”上(**使用Java反射实现**)
      - [动态代理demo](C:\Users\呵\Desktop\getWork\JavaDemos\14-typeInformation\src\main\java\com\prvyx\agent\DynamicProxy.java)
  - RTTI与反射的区别只在于：RTTI在编译期需要.class文件（换句话说，我们可以用”普通“方式调用对象的所有方法），而反射不需要，在运行时打开和检查.class文件
  - 反射允许更加动态的编程风格，开创了编程的新世界。反射是一个后门，通过反射可以访问private方法:[通过反射调用private方法demo](C:\Users\呵\Desktop\getWork\JavaDemos\14-typeInformation\src\main\java\com\prvyx\InvokePrivateMethodByReflect.java)