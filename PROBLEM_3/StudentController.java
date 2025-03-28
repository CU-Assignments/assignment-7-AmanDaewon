import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    
    // Insert Student
    public static boolean addStudent(String name, String department, float marks) {
        String sql = "INSERT INTO students (Name, Department, Marks) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setFloat(3, marks);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read All Students
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"),
                        rs.getString("Department"), rs.getFloat("Marks")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Update Student
    public static boolean updateStudent(int id, String name, String department, float marks) {
        String sql = "UPDATE students SET Name = ?, Department = ?, Marks = ? WHERE StudentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setFloat(3, marks);
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Student
    public static boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE StudentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
