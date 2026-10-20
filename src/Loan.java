import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {
    protected int Loan_ID;
    protected String Student_ID;
    protected int Book_ID;
    protected Date LoanDate;
    protected Date ReturnDate;
    protected Date ActualRetDate;
    protected String Statu;
    //getters
    public int getLoan_ID() { return Loan_ID; }
    public String getStudent_ID() { return Student_ID; }
    public int getBook_ID() { return Book_ID; }
    public Date getLoanDate() { return LoanDate; }
    public Date getReturnDate() { return ReturnDate; }
    public Date getActualRetDate() { return ActualRetDate; }
    public String getStatu() { return Statu; }
    //setters
    public void setLoan_ID (int Loan_ID){this.Loan_ID = Loan_ID;}
    public void setStudent_ID (String Student_ID){this.Student_ID = Student_ID;}
    public void setBook_ID(int Book_ID){this.Book_ID = Book_ID;}
    public void setLoanDate (Date LoanDate){this.LoanDate = LoanDate;}
    public void setReturnDate (Date ReturnDate){this.ReturnDate = ReturnDate;}
    public void setActualRetDate (Date ActualRetDate){this.ActualRetDate = ActualRetDate;}
    public void setStatu (String Statu){this.Statu = Statu;}


    public boolean createLoan() {
        String sql = "INSERT INTO Loan (Loan_ID, Student_ID, Book_ID, LoanDate, ReturnDate, Statu) VALUES (loan_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.Student_ID);
            pstmt.setInt(2, this.Book_ID);
            pstmt.setDate(3, new java.sql.Date(this.LoanDate.getTime()));
            pstmt.setDate(4, new java.sql.Date(this.ReturnDate.getTime()));
            pstmt.setString(5, this.Statu);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                boolean decremented = Book.decrementQuantity(this.Book_ID);
                if (!decremented) {
                    System.err.println("Warning: Loan created, but failed to decrement book quantity.");
                }
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error creating loan: " + e.getMessage());
            return false;
        }
    }

    public static List<Loan> getAllActiveLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT Loan_ID, Student_ID, Book_ID, LoanDate, ReturnDate, Statu FROM Loan WHERE Statu = 'active'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoan_ID(rs.getInt("Loan_ID"));
                loan.setStudent_ID(rs.getString("Student_ID"));
                loan.setBook_ID(rs.getInt("Book_ID"));
                loan.setLoanDate(rs.getDate("LoanDate"));
                loan.setReturnDate(rs.getDate("ReturnDate"));
                loan.setStatu(rs.getString("Statu"));
                loans.add(loan);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching active loans: " + e.getMessage());
            e.printStackTrace();
        }
        return loans;
    }

    public boolean updateReturn() {
        String sql = "UPDATE Loan SET ReturnDate = ?, Statu = ? WHERE Loan_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(ActualRetDate.getTime()));
            pstmt.setString(2, this.Statu);
            pstmt.setInt(3, this.Loan_ID);

            int updated = pstmt.executeUpdate();

            if (updated > 0) {
                boolean incremented = Book.incrementQuantity(this.Book_ID);
                if (!incremented) {
                    System.err.println("Warning: Loan returned, but failed to increment book quantity.");
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error updating loan return: " + e.getMessage());
        }
        return false;
    }


    @Override
    public String toString() {
        return "Loan #" + Loan_ID + " | " + Student_ID + " - Book ID: " + Book_ID;
    }
}
