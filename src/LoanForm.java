import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

public class LoanForm extends JPanel{
    private JPanel panel1;
    private JButton btnSave;
    private JButton btnBack;
    private JComboBox comboBoxStud;
    private JComboBox comboBoxBook;
    private JTextField txtYearLoan;
    private JTextField txtMonthLoan;
    private JTextField txtDayLoan;
    private JTextField txtDayRet;
    private JTextField txtMonthRet;
    private JTextField txtYearRet;
    // Fetch students and books from the database
    List<Student> students = Student.getAllStudents();
    List<Book> books = Book.getAllBooks();
    public LoanForm() {
        // Populate comboBoxStud with student names
        for (Student student : students) {
            comboBoxStud.addItem(student); // add the whole object
        }

        // Populate comboBoxBook with book titles
        for (Book book : books) {
            comboBoxBook.addItem(book); // Add the actual book object
        }

        // Button to save loan
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get selected student and book
                Student selectedStudent = (Student) comboBoxStud.getSelectedItem();
                Book selectedBook = (Book) comboBoxBook.getSelectedItem();

                if (selectedStudent == null || selectedBook == null) {
                    JOptionPane.showMessageDialog(null, "Please select a student and a book.");
                    return;
                }
                String studentID = selectedStudent.getStudent_ID(); // String
                int bookID = selectedBook.getBookID(); // Integer

                try {
                    int loanYear = Integer.parseInt(txtYearLoan.getText());
                    int loanMonth = Integer.parseInt(txtMonthLoan.getText());
                    int loanDay = Integer.parseInt(txtDayLoan.getText());

                    int returnYear = Integer.parseInt(txtYearRet.getText());
                    int returnMonth = Integer.parseInt(txtMonthRet.getText());
                    int returnDay = Integer.parseInt(txtDayRet.getText());

                    // Validate loan date
                    Calendar loanCal = Calendar.getInstance();
                    loanCal.setLenient(false); // Makes invalid dates throw exceptions
                    loanCal.set(loanYear, loanMonth - 1, loanDay); // Month is 0-based
                    Date loanDate = loanCal.getTime(); // Will throw if invalid

                    // Validate return date
                    Calendar returnCal = Calendar.getInstance();
                    returnCal.setLenient(false);
                    returnCal.set(returnYear, returnMonth - 1, returnDay);
                    Date returnDate = returnCal.getTime();

                    // Optional: check return is not before loan
                    if (returnDate.before(loanDate)) {
                        JOptionPane.showMessageDialog(null, "Return date cannot be before loan date.");
                        return;
                    }

                    // Create and save loan
                    Loan loan = new Loan();
                    loan.setStudent_ID(studentID);
                    loan.setBook_ID(bookID);
                    loan.setLoanDate(loanDate);
                    loan.setReturnDate(returnDate);
                    loan.setStatu("active");

                    if (loan.createLoan()) {
                        JOptionPane.showMessageDialog(null, "Loan created successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error creating loan.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for dates.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid calendar date.");
                }
            }
        });

        // Button to go back
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showLandRetManage();
            }
        });
    }




    public JPanel getLoanPanel() {
        return panel1;
    }
}
