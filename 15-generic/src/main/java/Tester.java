import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemos
 * @description:
 * @author: Prvyx
 * @created: 2022/04/13 21:07
 */
class Food{}
class Meat extends Food{}
class Fruit extends Food{

}
class Apple extends Fruit{}
class Orange extends Fruit{}
class Plate <T>{
    private T item;

    public Plate() {
    }

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

}
public class Tester {
    public static void main(String[] args) {
        Plate<? extends Fruit> plate=new Plate<>(new Fruit());
        Fruit fruit=plate.getItem();
        System.out.println(fruit);
//        plate.setItem(new Food());
        Plate<? super Fruit> plate1=new Plate<Fruit>();
        Fruit fruit1=(Fruit) plate1.getItem();
//        plate1.setItem(new Food());
        List<? extends Fruit> fruits=new ArrayList<Apple>();
//        Apple fruit2=fruits.get(1);
    }
}
