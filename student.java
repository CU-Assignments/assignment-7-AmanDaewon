import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "root"; // Change this if needed
    private static final String PASSWORD = "Aman@cps45"; // Set your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
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

    // Method to add a student
    private static void addStudent(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Enter marks: ");
        float marks = scanner.nextFloat();

        String sql = "INSERT INTO students (Name, Department, Marks) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setFloat(3, marks);
            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Student added successfully!" : "Failed to add student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all students
    private static void viewStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (!rs.isBeforeFirst()) {
                System.out.println("No students found.");
                return;
            }
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("StudentID") +
                        ", Name: " + rs.getString("Name") +
                        ", Dept: " + rs.getString("Department") +
                        ", Marks: " + rs.getFloat("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update student details
    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new department: ");
        String department = scanner.nextLine();
        System.out.print("Enter new marks: ");
        float marks = scanner.nextFloat();

        String sql = "UPDATE students SET Name = ?, Department = ?, Marks = ? WHERE StudentID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setFloat(3, marks);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Student updated successfully!" : "Failed to update student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM students WHERE StudentID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Student deleted successfully!" : "Failed to delete student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
