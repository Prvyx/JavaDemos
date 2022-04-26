import java.lang.reflect.InvocationTargetException;

/**
 * @program: JavaDemos
 * @description:
 * @author: Prvyx
 * @created: 2022/04/13 18:07
 */
// next()方法：生成器为了获得下一个T
interface Generator<T>{T next();}
class People{
    private static int id;

    @Override
    public String toString() {
        return "People"+id++;
    }
}
public class CommonGenerator<T> implements Generator<T> {
    // 重写接口方法
    @Override
    public T next() {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Generator导出类的扩展
    private Class<T> type;
    private CommonGenerator(Class<T> type) {
        this.type=type;
    }

        // 创建一个特定的生成器对象，因为构造器对外不可见
    public Generator<T> create(Class<T> type){return new CommonGenerator<>(type);}

    public static void main(String[] args) {
        Generator<People> peopleGenerator=new CommonGenerator<>(People.class);
        for(int i=0;i<5;i++){
            System.out.println(peopleGenerator.next());
        }
    }
}
