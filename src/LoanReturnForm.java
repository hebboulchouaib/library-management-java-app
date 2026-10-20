import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class LoanReturnForm extends JPanel{
    private JPanel panel1;
    private JComboBox LoanJcombobox;
    private JTextField txtRetYear;
    private JTextField txtRetMonth;
    private JTextField txtRetDay;
    private JButton btnSave;
    private JButton btnBack;
    private JLabel StudentLabel;
    private JLabel BookLabel;
    private JLabel LoanDatetLabel;

    public LoanReturnForm() {
        List<Loan> activeLoans = Loan.getAllActiveLoans(); // Get active loans

        for (Loan loan : activeLoans) {
            LoanJcombobox.addItem(loan); // ComboBox will show toString()
        }

        // ComboBox listener to update labels
        LoanJcombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loan selectedLoan = (Loan) LoanJcombobox.getSelectedItem();
                if (selectedLoan != null) {
                    Student student = Student.getStudentByID(selectedLoan.getStudent_ID());
                    Book book = Book.getBookByID(selectedLoan.getBook_ID());

                    if (student != null) {
                        StudentLabel.setText(" " + student.getNom() + " " + student.getPrenom());
                    }

                    if (book != null) {
                        BookLabel.setText(" " + book.getTitle());
                    }

                    LoanDatetLabel.setText(" " + selectedLoan.getLoanDate().toString());
                }
            }
        });

        // Save button logic
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loan selectedLoan = (Loan) LoanJcombobox.getSelectedItem();
                if (selectedLoan == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un emprunt.");
                    return;
                }

                try {
                    int year = Integer.parseInt(txtRetYear.getText());
                    int month = Integer.parseInt(txtRetMonth.getText());
                    int day = Integer.parseInt(txtRetDay.getText());

                    // Validate date
                    Calendar cal = Calendar.getInstance();
                    cal.setLenient(false);
                    cal.set(year, month - 1, day); // month - 1 for 0-based
                    Date actualReturnDate = cal.getTime();

                    selectedLoan.setActualRetDate(actualReturnDate); // You need this field in your Loan class
                    selectedLoan.setStatu("returned"); // Update status

                    boolean updated = selectedLoan.updateReturn();

                    if (updated) {
                        JOptionPane.showMessageDialog(null, "Retour enregistré avec succès !");
                        App.showLandRetManage(); // Go back
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du retour.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer une date valide.");
                }
            }
        });


        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showLandRetManage();
            }
        });
    }









    public JPanel getLoanReturnPanel() {
        return panel1;
    }
}
