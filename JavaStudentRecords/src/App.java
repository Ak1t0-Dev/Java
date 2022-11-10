import java.util.List;
import java.util.Scanner;

import dto.Student;
import service.StudentService;
import service.StudentServiceIml;

public class App {
    public static void main(String[] args) throws Exception {
        showMenu();
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentServiceIml();

        String userChoice = "";
        int studentId = 0;

        do {
            System.out.println("==================================");
            System.out.println("1. Register");
            System.out.println("2. Get all students");
            System.out.println("3. Delete a student record");
            System.out.println("4. Update a student record");
            System.out.println("5. Exit");
            System.out.println("==================================");
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    System.out.println("Enter a student name");
                    String name = scanner.nextLine();
                    System.out.println("Enter a student email");
                    String email = scanner.nextLine();
                    int emailExist = studentService.findEmail(email);
                    if (emailExist > 0) {
                        System.out.println("The email has already existed");
                        break;    
                    }
                    System.out.println("Enter a student roll number");
                    int studentRollNumber = scanner.nextInt();
                    int rollNumberExist = studentService.findStudentRollNumber(studentRollNumber);
                    if (rollNumberExist > 0) {
                        System.out.println("The student roll number has already existed");
                        break;    
                    }
                    Student studentInsert = new Student(name, email, studentRollNumber);
                    studentService.insert(studentInsert);
                    break;
                case "2":
                    System.out.println("==================================");
                    displayUsers(studentService.getAll());
                    System.out.println("==================================");
                    break;
                case "3":
                    System.out.println("==================================");
                    displayUsers(studentService.getAll());
                    System.out.println("==================================");
                    System.out.println("Enter user ID : ");
                    studentId = Integer.parseInt(scanner.nextLine());
                    studentService.delete(studentId);
                    break;
                case "4":
                    System.out.println("==================================");
                    displayUsers(studentService.getAll());
                    System.out.println("==================================");

                    System.out.println("Enter user ID : ");
                    studentId = Integer.parseInt(scanner.nextLine());
                    Student studentUpdate = studentService.getStudentById(studentId);

                    if (studentUpdate != null) {
                        System.out.println("Enter a new name");
                        String newName = scanner.nextLine();
                        System.out.println("Enter a new email");
                        String newEmail = scanner.nextLine();
                        System.out.println("Enter a new student roll number");
                        int newStudentRollNumber = scanner.nextInt();

                        studentUpdate.setName(newName);
                        studentUpdate.setEmail(newEmail);
                        studentUpdate.setStudentRollNumber(newStudentRollNumber);
                        studentService.update(studentUpdate);

                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case "5":
                    break;
                default:
                    userChoice = "5";
                    break;
            }
        } while (!userChoice.equals("5"));
        scanner.close();
    }

    public static void displayUsers(List<Student> students) {
        System.out.println("STUDENTID\tNAME\tEMAIL\tSTUDENTROLLNUMBER");
        for (Student student : students) {
            System.out.println(student.getStudentId() + "\t" + student.getName() + "\t" + "\t" + student.getEmail()
                    + "\t" + student.getStudentRollNumber());
        }
    }

}
