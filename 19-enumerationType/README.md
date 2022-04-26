#### 19-enumerationType
1. 基本enum:[基本enum特性](src/main/java/basicEnum/EnumClass.java)
2. 深入理解enum
   - [enum的深入理解](src/main/java/deepenum/DeepUnderstandEnum.java)
   - 资料：[都有常量了，为啥还要用枚举？](https://www.bilibili.com/video/BV1A34y1v7aL)
3. switch中的enum:[enum在switch的使用](src/main/java/basicEnum/EnumInSwitch.java)
4. values()的神秘之处
5. 实现，而非继承
   - [enum实现Generator<>接口](src/main/java/deepenum/EnumImplementation.java)
6. 随机选取
   - [Enum的工具类:Enums](src/main/java/utils/Enums.java)。若Class为enum，则有getEnumConstants()方法：返回枚举常量列表
7. 使用接口组织枚举
   - [随机点餐。使用接口组织枚举。](src/main/java/deepenum/Meal.java) 将枚举元素通过接口进行分组
8. EnumSet [EnumSet的demo](src/main/java/enumset/EnumSetDemo.java)
9. EnumMap [EnumMap的demo](src/main/java/enumset/EnumMapDemo.java)
10. 常量相关的方法
    - [枚举常量的方法重写](src/main/java/deepenum/ConstantMethodDemo.java)，实现了类似多态的行为
    - 一些使用场景（skip）：
      - 使用enum的职责链
      - 使用enum的状态机
11. 多路分发（skip）
    - 多路分发是个什么东东。。
    - 猜拳比赛，书中P613