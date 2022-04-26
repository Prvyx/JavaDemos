import java.lang.reflect.Method;

/**
 * @program: JavaDemos
 * @description:@Test测试类
 * @author: Prvyx
 * @created: 2022/04/19 21:45
 */

public class Tester {
    public static void main(String[] args) {
        Class<Student> studentClass=Student.class;
        Method[] methods = studentClass.getDeclaredMethods();
        for (Method method : methods) {
            Test annotation = method.getDeclaredAnnotation(Test.class);
            if(annotation!=null){
                System.out.println(annotation.id()+" "+annotation.name());
            }
        }
    }
}
