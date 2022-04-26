package deepenum;

import utils.Enums;

/**
 * @program: JavaDemos
 * @description:随机点餐。使用接口组织枚举。将枚举元素通过接口进行分组
 * @author: Prvyx
 * @created: 2022/04/19 15:06
 */

public enum Meal {
    MEAT(Food.Meat.class),
    VEGETABLE(Food.Vegetable.class),
    DRINK(Food.Drink.class);

    //使用接口组织枚举，将枚举元素通过接口进行分组start----------------
    interface Food {
        enum Meat implements Food{
            CHICKEN,PORK,LAMB
        }
        enum Vegetable implements Food{
            TOMATO,ONION,RADISH
        }
        enum Drink implements Food{
            TEA,COFFEE
        }
    }
    //end------------------

    private Food[] simpleFoods;//单一Food的所有枚举元素，eg：Meat的所有枚举元素；Drink的所有枚举元素

    Meal(Class<? extends Food> kind) {
        simpleFoods=kind.getEnumConstants();
    }

    public Food randomSelection(){return Enums.random(simpleFoods);}

    public static void main(String[] args) {
        System.out.println("随机点5份餐");
        for(int i=0;i<5;i++){
            System.out.println("第"+(i+1)+"份餐");
            for(Meal kind:Meal.values()){
                System.out.println(kind.randomSelection());
            }
        }
    }
}
