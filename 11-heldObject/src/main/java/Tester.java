import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description: 本来：编写一个实现Collection接口的新类，该类实现了iterator()方法。但发现，要重写好多方法。
 *               So，使用extends AbstractCollection<>,重写size()、iterator()方法即可
 *               自定义序列容器StudentSequence demo
 * @author: Prvyx
 * @created: 2022/04/07 12:42
 */
class Student {
    private String studentId;
    private String studentName;
    private int score= new Random().nextInt(100);

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", score=" + score +
                '}';
    }
}

class Students {//产生Student的工厂
    private static String[] mockStudentIds=new String[]{"2019","2020","2021","2021"};
    private static String[] mockStudentNames=new String[]{"dong","prvyx","bo"};
    public static Student[] createStudentArray(int number){
        Student[] students=new Student[number];
        for(int i=0;i<number;i++){
            students[i]=new Student(mockStudentIds[new Random().nextInt(4)],
                    mockStudentNames[new Random().nextInt(3)]);
        }

        return students;
    }
}
class StudentSequence extends AbstractCollection<Student> {//Student的序列容器
    private Student[] students=Students.createStudentArray(5);

    @Override
    public int size() {
        return students.length;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<Student>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index<students.length;
            }

            @Override
            public Student next() {
                return students[index++];
            }
        };
    }
}
public class Tester {
    public static void main(String[] args) {
        StudentSequence studentSequence=new StudentSequence();
        Iterator<Student> iterator=studentSequence.iterator();
        while (iterator.hasNext()){
            Student student=iterator.next();
            System.out.println(student);
        }
        System.out.println("----");
        //foreach用法，因为StudentSequence extends AbstractCollection<>，而AbstractCollection实现了Iterable<>接口
        for(Student student:studentSequence){
            System.out.println(student);
        }
    }
}
