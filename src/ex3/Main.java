package ex3;

import ex01.StudentManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        studentManager.deleteStudentsByAge(sc);
    }
}
