package genericinterface;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description:泛型生成器接口、泛型迭代器接口的demo。其中迭代器的next()依赖生成器的create()
 * @author: Prvyx
 * @created: 2022/04/13 16:44
 */
// next方法是生成器的基本，为了获得下一个T
interface Generator<T>{T next();}
class Student{
    private String id;
    private String name;
    private static int number;

    public Student() {
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+number++;
    }
}
class GoodStudent extends Student{}
class BadStudent extends Student{}
class normalStudent extends Student{}
class StudentGenerator implements Generator<Student>,Iterable<Student>{
    // StudentGenerator的字段与方法
    private int size;

    public StudentGenerator() {
    }

    public StudentGenerator(int size) {
        this.size = size;
    }
    // 实现Generator<Student>
    private final Class<?>[] types={GoodStudent.class,BadStudent.class,normalStudent.class};

    @Override
    public Student next() {
        try {
            return (Student) types[new Random().nextInt(types.length)].getDeclaredConstructor().newInstance();
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

    // 实现内部迭代器
    private class StudentIterator implements Iterator<Student>{
        private int index;
        private int size=StudentGenerator.this.size;
        @Override
        public boolean hasNext() {
            return index<size;
        }

        @Override
        public Student next() {
            index++;
            return StudentGenerator.this.next();//使用生成器
        }
    }

    // 实现Iterable<>接口 （可以把实现”Iterable<>接口”与“内部迭代器“用“匿名内部类”实现）
    @Override
    public Iterator<Student> iterator(){
        return new StudentIterator();
    }
}
public class GenericInterfaceDemo {
    public static void main(String[] args) {
        StudentGenerator studentGenerator=new StudentGenerator();
        for(int i=0;i<5;i++){
            System.out.println(studentGenerator.next());
        }
        for(Student student:new StudentGenerator(4)){
            System.out.println(student);
        }
    }
}
