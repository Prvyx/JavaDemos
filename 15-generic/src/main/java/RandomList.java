import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description:随机的List类
 * @author: Prvyx
 * @created: 2022/04/13 14:37
 */

public class RandomList<T> {
    private final List<T> list=new ArrayList<>();
    public void add(T item){
        list.add(item);
    }
    public T select(){
        return list.get(new Random().nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomList<String> randomList=new RandomList<>();
        for(String s: "prvyx dong bo".split(" ")){
            randomList.add(s);
        }
        for(int i=0;i!=5;i++){
            System.out.println(randomList.select());
        }
    }

}
