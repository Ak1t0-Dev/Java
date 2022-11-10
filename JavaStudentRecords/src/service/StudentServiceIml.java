package service;

import java.util.List;
import dao.StudentDao;
import dao.StudentDaoIml;
import dto.Student;

public class StudentServiceIml implements StudentService {
    private StudentDao studentDao = new StudentDaoIml();

    @Override
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(int studentId) {
        studentDao.delete(studentId);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public int findEmail(String email) {
        return studentDao.findEmail(email);
    }
    
    @Override
    public int findStudentRollNumber(int studentRollNumber) {
        return studentDao.findStudentRollNumber(studentRollNumber);
    }
}
