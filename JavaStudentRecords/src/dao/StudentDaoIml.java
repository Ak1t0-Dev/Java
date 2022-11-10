package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import dto.Student;

public class StudentDaoIml implements StudentDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final String INSERT = "INSERT INTO student_table (name, email, student_roll_number) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE student_table SET name = ?, email = ?, student_roll_number = ? WHERE student_id = ?";
    private static final String DELETE = "DELETE FROM student_table WHERE student_id = ?";
    private static final String GET_BY_STUDENTID = "SELECT * FROM student_table WHERE student_id = ?";
    private static final String GET_ALL = "SELECT * FROM student_table";
    private static final String FIND_EMAIL = "SELECT COUNT(*) AS count FROM student_table WHERE email = ?";
    private static final String FIND_STUDENT_ROLL_NUMBER = "SELECT COUNT(*) AS count FROM student_table WHERE student_roll_number = ?";

    public StudentDaoIml() {
        try {
            connection = DriverManager.getConnection(StudentDao.URL, StudentDao.USER, StudentDao.PASSWORD);
            System.out.println("Connected to DB");
        } catch (SQLException e) {
            System.out.println(" Unable to Connect");
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Student student) {
        int rowAffected = 0;

        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getStudentRollNumber());

            rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected + " row(s) affected");

        } catch (SQLException e) {
            System.out.println("Unable to insert data");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Student student) {
        int rowAffected = 0;

        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getStudentRollNumber());
            preparedStatement.setInt(4, student.getStudentId());

            rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected + " row(s) affected");

        } catch (SQLException e) {
            System.out.println("Unable to update data");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(int studentId) {
        int rowAffected = 0;

        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, studentId);

            rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected + " row(s) affected");

        } catch (SQLException e) {
            System.out.println("Unable to delete data");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = null;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_STUDENTID);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            student = new Student();
            student.setStudentId(resultSet.getInt("student_id"));
            student.setName(resultSet.getString("name"));
            student.setEmail(resultSet.getString("email"));
            student.setStudentRollNumber(resultSet.getInt("student_roll_number"));

        } catch (SQLException e) {
            System.out.println("Unable to find list of all users");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> studentList = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setStudentRollNumber(resultSet.getInt("student_roll_number"));
                studentList.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Unable to find list of all users");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }

        return studentList;

    }

    @Override
    public int findEmail(String email) {
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(FIND_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException e) {
            System.out.println("Unable to find list of all users");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }
        return count;

    }

    @Override
    public int findStudentRollNumber(int studentRollNumber) {
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(FIND_STUDENT_ROLL_NUMBER);
            preparedStatement.setInt(1, studentRollNumber);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException e) {
            System.out.println("Unable to find list of all users");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the statement");
                e.printStackTrace();
            }
        }
        return count;
    }

}
