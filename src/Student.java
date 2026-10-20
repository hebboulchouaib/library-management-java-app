import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String Student_ID;
    protected String Nom;
    protected String Prenom;
    protected String Email;
    private String Phone;
    //getters
    public String getStudent_ID() { return Student_ID; }
    public String getNom() { return Nom; }
    public String getPrenom() { return Prenom; }
    public String getEmail() { return Email; }
    public String getPhone() {return Phone;}
    //setters
    public void setStudent_ID(String student_ID) { Student_ID = student_ID; }
    public void setNom(String Nom){this.Nom = Nom;}
    public void setPrenom(String Prenom){this.Prenom = Prenom;}
    public void setEmail(String Email){this.Email = Email;}
    public void setPhone(String phone) {Phone = phone;}


    public static boolean ADD_Student(Student student) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO Student (Student_ID, Nom, Prenom, Email, Phone) VALUES (?, ?, ?, ?, ?)"
        );

        pstmt.setString(1, student.getStudent_ID());
        pstmt.setString(2, student.getNom());
        pstmt.setString(3, student.getPrenom());
        pstmt.setString(4, student.getEmail());
        pstmt.setString(5, student.getPhone());

        int rows = pstmt.executeUpdate();
        return rows > 0;
    }

    public static boolean UpdateStudent(Student student) {
        String sql = "UPDATE Student SET Nom = ?, Prenom = ?, Email = ?, Phone = ? WHERE Student_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getNom());
            pstmt.setString(2, student.getPrenom());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getStudent_ID());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student ORDER BY Nom ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudent_ID(rs.getString("Student_ID"));
                student.setNom(rs.getString("Nom"));
                student.setPrenom(rs.getString("Prenom"));
                student.setEmail(rs.getString("Email"));
                student.setPhone(rs.getString("Phone"));
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching students:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }

        return students;
    }

    public static boolean deleteStudent(String studentID) {
        String sql = "DELETE FROM Student WHERE Student_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }

    public static Student getStudentByID(String id) {
        String sql = "SELECT * FROM Student WHERE Student_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setStudent_ID(rs.getString("Student_ID"));
                student.setNom(rs.getString("Nom"));
                student.setPrenom(rs.getString("Prenom"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return Nom + " " + Prenom;
    }

}
