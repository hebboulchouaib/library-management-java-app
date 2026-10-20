import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;


public class App {
    protected static JFrame frame;
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Database connection successful!");
            System.out.println("Database version: " + conn.getMetaData().getDatabaseProductVersion());
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
        frame = new JFrame("Library Management");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        showMainMenu();
        frame.setVisible(true);

    }

    //main menu
    public static void showMainMenu() {
        frame.setContentPane(new MainMenuForm().getMainPanel()); // set menu panel
        frame.revalidate();
        frame.repaint();
    }
    //books managment
    public static void showBookmanage() {
        frame.setContentPane(new BookManagementForm().getBookManagePanel()); // set book managmenet panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showAddBook() {
        frame.setContentPane(new AddBookForm().getAddBookPanel()); // set add book panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showSearchBook() {
        frame.setContentPane(new SearchBook().getSearchBookPanel()); // set search book panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showDeleteBook() {
        frame.setContentPane(new DeleteBook().getDeletePanel()); // set delete book panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showUpdateBook() {
        frame.setContentPane(new EditBook().getUpdatePanel()); // set update book panel
        frame.revalidate();
        frame.repaint();
    }

    //student managment
    public static void showStudentsManage() {
        frame.setContentPane(new StudentManagment().getStudentManagmentPanel()); // set studentmanagement panel
        frame.revalidate();
        frame.repaint();
    }
    //student
    public static void showAddStudent() {
        frame.setContentPane(new AddStudent().getAddStudentPanel()); // set add student panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showEditStudent() {
        frame.setContentPane(new EditStudentForm().getEditStudentPanel()); // set Edit student panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showDeleteStudent() {
        frame.setContentPane(new DeleteStudentForm().getDeleteStdentPanel()); // set delete student panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showStudentsList() {
        frame.setContentPane(new StudentListForm().getStudentListPanel()); // set students list panel
        frame.revalidate();
        frame.repaint();
    }
    // L and Ret
    public static void showLandRetManage() {
        frame.setContentPane(new LoanAndReturnManagment().getLandRetManage()); // set Loan and loan return managment panel
        frame.revalidate();
        frame.repaint();
    }
    public static void showNewLoan() {
        frame.setContentPane(new LoanForm().getLoanPanel()); // set Loan panel
        frame.revalidate();
        frame.repaint();
    }

    public static void showLoanRet() {
        frame.setContentPane(new LoanReturnForm().getLoanReturnPanel()); // set Loan ret panel
        frame.revalidate();
        frame.repaint();
    }



}











































// exemple lwl for testing add_book
        /*
        Book newBook = new Book();
        newBook.setBook_ID(1001);
        newBook.setTitle("Java");
        newBook.setAuthor("Aymen");
        newBook.setCategory("Programming");
        newBook.setPublicationYear(2018);
        newBook.setAvailableQuantity(5);

        boolean success = Book.ADD_Book(newBook);
        if (success) {
            System.out.println(" Book inserted successfully");
        } else {
            System.out.println(" Failed to insert book");
        }
        */

// exemple lwl for testing add_Student (worked)
        /*
        Student newStudent = new Student();
        newStudent.Student_ID = 12345;
        newStudent.Nom = "AKNIOU";
        newStudent.Prenom = "Achraf";
        newStudent.Email = "AKNIOU.achraf@univ.edu";
        newStudent.Phone = 0555667788;

        boolean success = Student.ADD_Student(newStudent);
        if (success) {
            System.out.println(" Student inserted successfully");
        } else {
            System.out.println(" Failed to insert student");
        }
        */

