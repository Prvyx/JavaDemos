/**
 * @program: JavaDemos
 * @description：Java元组泛型demo
 * @author: Prvyx
 * @created: 2022/04/13 14:11
 */

class Student<A,B>{
    private A name;
    private B age;

    public Student() {
    }

    public Student(A name, B age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
public class TuplesGenericsDemo {
    static Student<String,Integer> setStudent(String name,int age){
        return new Student<>(name,age);
    }
    public static void main(String[] args) {
        System.out.println(setStudent("prvyx",12));
    }
}
