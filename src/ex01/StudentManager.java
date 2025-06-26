package ex01;

import database.StudentConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class StudentManager {

    //Phương thức thêm sinh viên ex1
    public static void addStudents(List<Student> student) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = StudentConnection.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false); //bắt đầu transaction thủ công

                for (Student s : student) {
                    call = conn.prepareCall("{call add_students(?,?)}");
                    call.setString(1, s.getName());
                    call.setInt(2, s.getAge());
                    call.executeUpdate();
                }
                conn.commit(); //xác nhận transaction
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {
            try {
                conn.rollback(); //quay về trạng thái cũ nếu có lỗi
                System.err.println("Error, Roll back");
            } catch (SQLException rollback) {
                rollback.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    //Phuơng thức cập nhật sinh viên ex2
    public static void updateStudent(Student student) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = StudentConnection.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false); //bắt đầu transaction thủ công

                call = conn.prepareCall("{call update_student(?,?,?)}");
                call.setInt(1, student.getId());
                call.setString(2, student.getName());
                call.setInt(3, student.getAge());
                call.executeUpdate();

                conn.commit(); //xác nhận transaction
                System.out.println("Student updated successfully!");
            }

        } catch (SQLException e) {
            try {
                conn.rollback(); //quay về trạng thái cũ nếu có lỗi
                System.err.println("Error, Roll back");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (call != null) call.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {

            }
        }
    }

    //Phuơng thức xóa sinh viên ex3
    public static void deleteStudentsByAge(Scanner scanner) {
        Connection conn = null;
        CallableStatement call = null;
        System.out.println("Enter student age to delete: ");
        int age = Integer.parseInt(scanner.nextLine());

        try {
            conn = StudentConnection.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false); //bắt đầu transaction thủ công
                call = conn.prepareCall("{call delete_students_by_age(?)}");
                call.setInt(1, age);
                int result = call.executeUpdate();
                conn.commit(); //xác nhận transaction
                if (result == 0) {
                    System.err.println("Not found any student with age smaller than " + age);
                } else {
                    System.out.println("Deleted successfully " + result + " student whose age is less than " + age);
                }
            }

        } catch (Exception e) {
            try {
                conn.rollback(); //quay về trạng thái cũ nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                if (call != null) call.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
