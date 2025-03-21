import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/EmployeeDB"; // Replace with your database name
        String user = "root";  // MySQL username
        String password = "Aman@cps45"; // MySQL password

        // SQL Query
        String query = "SELECT * FROM Employee";

        try {
            // Load MySQL JDBC Driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to the database!");

            // Create a Statement
            Statement stmt = conn.createStatement();

            // Execute Query
            ResultSet rs = stmt.executeQuery(query);

            // Process the ResultSet
            System.out.println("EmpID | Name  | Salary");
            System.out.println("-----------------------");
            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(empID + " | " + name + " | " + salary);
            }

            // Close Resources
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("✅ Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
