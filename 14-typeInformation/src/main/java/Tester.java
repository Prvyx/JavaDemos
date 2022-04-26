/**
 * @program: JavaDemos
 * @description: 测试“泛化的Class”的demo
 * @author: Prvyx
 * @created: 2022/04/09 14:15
 */
class People {

}
class Student extends People{
    private String studentId;
    private String studentName;



    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
public class Tester {
    public static void main(String[] args) {
        Class<? extends People> studentClass=Student.class;
        Class<? super Student> peopleClass=People.class;
        System.out.println(studentClass.getName());
        System.out.println(peopleClass.getName());
        People people=new People();
        System.out.println(people instanceof Student);
    }
}
/*Output
Student
People
 */