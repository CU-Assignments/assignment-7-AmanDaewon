public class Student {
    private int studentID;
    private String name;
    private String department;
    private float marks;

    public Student(int studentID, String name, String department, float marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public float getMarks() { return marks; }

    @Override
    public String toString() {
        return "ID: " + studentID + ", Name: " + name + ", Dept: " + department + ", Marks: " + marks;
    }
}
