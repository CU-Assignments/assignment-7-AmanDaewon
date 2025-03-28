import java.util.List;
import java.util.Scanner;

public class StudentView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addStudentUI();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    updateStudentUI();
                    break;
                case 4:
                    deleteStudentUI();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addStudentUI() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Enter marks: ");
        float marks = scanner.nextFloat();

        if (StudentController.addStudent(name, department, marks)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Error adding student.");
        }
    }

    private static void displayStudents() {
        List<Student> students = StudentController.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void updateStudentUI() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new department: ");
        String department = scanner.nextLine();
        System.out.print("Enter new marks: ");
        float marks = scanner.nextFloat();

        if (StudentController.updateStudent(id, name, department, marks)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Error updating student.");
        }
    }

    private static void deleteStudentUI() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        if (StudentController.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Error deleting student.");
        }
    }
}
