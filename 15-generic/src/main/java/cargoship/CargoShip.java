package cargoship;

import java.util.ArrayList;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description:使用泛型构造货船复杂模型
 * @author: Prvyx
 * @created: 2022/04/13 18:37
 */

interface Generator<T>{T next();}

// 货物
class Good{
    private int id;
    private int category;

    public Good() {
    }

    public Good(int id, int category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", category=" + category +
                '}';
    }

    // 生成器
    public static Generator<Good> generator=new Generator<Good>() {
        @Override
        public Good next() {
            Random random=new Random();
            return new Good(random.nextInt(100),random.nextInt(10));
        }
    };
}
// 货物架
class GoodShelf extends ArrayList<Good> {
    private static int goodShelfId;
    public GoodShelf(int goodNumber){
        Random random=new Random();
        for(int i=0;i<goodNumber;i++){
            add(new Good(random.nextInt(100),random.nextInt(10)));
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        if(this.size()!=0){
            stringBuilder.append("\t\tGoodShelf"+goodShelfId++ +":\n");
        }
        for(Good good:this){
            stringBuilder.append(good+" ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
// 仓库
class GoodRepository extends ArrayList<GoodShelf> {
    private static int goodRepositoryId;
    public GoodRepository(int goodNumber,int goodShelfNumber){
        for(int i=0;i<goodShelfNumber;i++){
            add(new GoodShelf(goodNumber));
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        if(this.size()!=0){
            stringBuilder.append("\tGoodRepositoryId"+goodRepositoryId++ +":\n");
        }
        for(GoodShelf goodShelf:this){
            stringBuilder.append(goodShelf+" ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
// 货船
public class CargoShip extends ArrayList<GoodRepository>{
    private static int cargoShipId;
    public CargoShip(int repositoryNumber,int goodShelfNumber,int goodNumber){
        for(int i=0;i<repositoryNumber;i++){
            add(new GoodRepository(goodNumber,goodShelfNumber));
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        if(this.size()!=0){
            stringBuilder.append("CargoShip"+cargoShipId++ +":\n");
        }
        for(GoodRepository goodRepository:this){
            stringBuilder.append(goodRepository+" ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new CargoShip(2,4,5));
    }
}
