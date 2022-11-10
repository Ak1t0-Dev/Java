package service;

import java.util.List;
import dto.Student;

public interface StudentService {
    public void insert(Student student);
    public void update(Student student);
    public void delete(int studentId);
    public Student getStudentById(int studentId);
    public List<Student> getAll();
    public int findEmail(String email);
    public int findStudentRollNumber(int studentRollNumber);
}
