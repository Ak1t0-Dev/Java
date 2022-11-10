package dao;

import java.util.List;
import dto.Student;

public interface StudentDao {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/student_db";
    public static final String USER = "root";
    public static final String PASSWORD = "Tr@ve1ing";

    public void insert(Student student);
    public void update(Student student);
    public void delete(int studentId);
    public Student getStudentById(int studentId);
    public List<Student> getAll();
    public int findEmail(String email);
    public int findStudentRollNumber(int studentRollNumber);
}
