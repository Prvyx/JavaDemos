package com.prvyx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: JavaDemos
 * @description：注册工厂
 * @author: Prvyx
 * @created: 2022/04/09 15:20
 */
interface People{}
interface PeopleFactory<T>{ List<T> create(int n);}
class SchoolPeople implements People{}
class OtherPeople implements People{}
class Student extends SchoolPeople{
    private String studentId;
    private String studentName;
    public Student() {
    }
    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }
    private class StudentFactory implements PeopleFactory<Student>{
        private final String[] studentIds={"2019","2020","2021"};
        private final String[] studentNames={"dong","prvyx","bo"};
        private List<Student> students=new ArrayList<>();
        @Override
        public List<Student> create(int n) {
            for(int i=0;i<n;i++){
                students.add(new Student(studentIds[new Random().nextInt(3)],studentNames[new Random().nextInt(3)]));
            }
            return students;
        }
    }
    public List<Student> getStudents(int number){
        return new Student.StudentFactory().create(number);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
public class PeopleFactories {
    public static void main(String[] args) {
        Student student=new Student();
        System.out.println(student.getStudents(4));
    }
}
