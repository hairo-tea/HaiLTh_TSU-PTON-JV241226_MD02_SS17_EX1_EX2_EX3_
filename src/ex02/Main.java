package ex02;

import ex01.Student;
import ex01.StudentManager;

public class Main {
    public static void main(String[] args) {
        StudentManager SM = new StudentManager();
        Student student = new Student();
        SM.updateStudent(student);
    }
}
