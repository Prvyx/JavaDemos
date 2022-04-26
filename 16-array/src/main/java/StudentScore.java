import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description:比较器：内部比较器（通过继承Comparable<>）、外部比较器（通过内部类实现Comparator<>接口）
 * 内部比较器优点：实现简单，但维护性较差；而外部比较器：实现相对复杂，但维护性较好（因为不用使用继承，直接用内部类实现接口）
 * 注：查看源码知：Comparable接口与Comparator接口没有关系，而Iterable接口使用了Iterator接口
 * 该demo目的：StudentScore[]根据score进行排序
 * 注：Arrays.sort()没有第二个参数时，使用内部比较器；若有第二个参数，则使用的是外部比较器
 * @author: Prvyx
 * @created: 2022/04/14 15:49
 */
interface Generator<T> {T next();}
public class StudentScore implements Comparable<StudentScore>{
    private String studentName;
    private double score;

    // 自带构造器、get、set方法
    public StudentScore() {
    }

    public StudentScore(String studentName, double score) {
        this.studentName = studentName;
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "studentName='" + studentName + '\'' +
                ", score=" + score +
                '}';
    }

    // 生成器的实现
    public static Generator<StudentScore> generator(){
        return new Generator<StudentScore>() {
            private final String[] studentNames=new String[]{"prvyx","dong","bo"};
            private Random random=new Random();
            @Override
            public StudentScore next() {
                return new StudentScore(studentNames[random.nextInt(3)],random.nextDouble()*100);
            }
        };
    }
    // 内部比较器
    @Override
    public int compareTo(StudentScore o) {
        return Double.compare(score,o.score);
    }

    // 外部比较器，只不过使用内部类放在StudentScore类的内部
    public static Comparator<StudentScore> comparator(){
        return new Comparator<StudentScore>() {
            @Override
            public int compare(StudentScore o1, StudentScore o2) {
                return Double.compare(o1.score,o2.score);
            }
        };
    }

    public static void main(String[] args) {
        StudentScore[] studentScores=new StudentScore[5];
        for(int i=0;i<5;i++){
            studentScores[i]=StudentScore.generator().next();
        }
        Arrays.sort(studentScores);//使用内部比较器
        for(int i=0;i<5;i++){
            System.out.println(studentScores[i]);
        }
        System.out.println();
        Arrays.sort(studentScores,StudentScore.comparator());//使用外部比较器
        for(int i=0;i<5;i++){
            System.out.println(studentScores[i]);
        }
    }
}
