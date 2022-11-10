package dto;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private int studentRollNumber;
    
    public Student() {
    }

    public Student(int studentId, String name, String email, int studentRollNumber) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.studentRollNumber = studentRollNumber;
    }

    public Student(String name, String email, int studentRollNumber) {
        this.name = name;
        this.email = email;
        this.studentRollNumber = studentRollNumber;
    }
    
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getStudentRollNumber() {
        return studentRollNumber;
    }
    public void setStudentRollNumber(int studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }
}
