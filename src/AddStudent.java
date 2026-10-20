import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddStudent extends JPanel{
    private JPanel panel1;
    private JTextField txtStudentID;
    private JTextField txtStudentNom;
    private JTextField txtStudentPrenom;
    private JTextField txtStudentEmail;
    private JTextField txtStudentPhone;
    private JButton btnBack;
    private JButton btnSave;

    public AddStudent() {
        btnSave.addActionListener(e -> {
            try {
                String studentID = txtStudentID.getText().trim();
                String phone = txtStudentPhone.getText().trim();
                String nom = txtStudentNom.getText().trim();
                String prenom = txtStudentPrenom.getText().trim();

                if (studentID.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
                    throw new IllegalArgumentException("Student ID, Nom, and Prenom are required.");
                }

                if (!studentID.matches("\\d+")) {
                    throw new IllegalArgumentException("Student ID must contain only digits.");
                }

                if (!phone.isEmpty() && !phone.matches("\\d{10}")) {
                    throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
                }

                Student student = new Student();
                student.setStudent_ID(studentID);
                student.setNom(nom);
                student.setPrenom(prenom);
                student.setEmail(txtStudentEmail.getText().trim());
                student.setPhone(phone);

                if (Student.ADD_Student(student)) {
                    JOptionPane.showMessageDialog(this, "Student added!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add student.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1) {
                    JOptionPane.showMessageDialog(this, "Student ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                } else if (ex.getErrorCode() == 2290) {
                    JOptionPane.showMessageDialog(this, "Email is not valid.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showStudentsManage();}
        });
    }

    public JPanel getAddStudentPanel() {
        return panel1;
    }
}
