package ex01;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentManager stManager = new StudentManager();
        List<Student> students = Arrays.asList(
                new Student(0, "Nguyễn Văn A", 20),
                new Student(0, "Nguyễn Thị B", 21),
                new Student(0, "Nguyễn Văn C", 22)
        );
        stManager.addStudents(students);
    }
}