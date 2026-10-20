import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditStudentForm extends JPanel{
    private JPanel panel1;
    private JTable tblStudent;
    private JTextField txtNom;
    private JTextField txtStudentID;
    private JTextField txtPrenom;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JButton btnUpdate;
    private JLabel lblStatus;
    private JButton btnBack;


    public EditStudentForm() {
        setupTable();
        loadStudents();

        tblStudent.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblStudent.getSelectedRow() != -1) {
                populateFormFromSelectedRow();
            }
        });

        btnUpdate.addActionListener(e -> {
            updateStudent();
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showStudentsManage();
            }
        });
    }

    private void setupTable() {
        tblStudent.setModel(new DefaultTableModel(
                new Object[]{"Student ID", "Nom", "Prenom", "Email", "Phone"}, 0
        ));
    }

    private void loadStudents() {
        DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
        model.setRowCount(0);
        List<Student> students = Student.getAllStudents(); // You need to define this

        for (Student student : students) {
            model.addRow(new Object[]{
                    student.getStudent_ID(),
                    student.getNom(),
                    student.getPrenom(),
                    student.getEmail(),
                    student.getPhone()
            });
        }
    }

    private void populateFormFromSelectedRow() {
        int row = tblStudent.getSelectedRow();
        txtStudentID.setText(tblStudent.getValueAt(row, 0).toString());
        txtNom.setText(tblStudent.getValueAt(row, 1).toString());
        txtPrenom.setText(tblStudent.getValueAt(row, 2).toString());
        txtEmail.setText(tblStudent.getValueAt(row, 3).toString());
        txtPhone.setText(tblStudent.getValueAt(row, 4).toString());
    }

    private void updateStudent() {
        try {
            String studentID = txtStudentID.getText().trim();
            String nom = txtNom.getText().trim();
            String prenom = txtPrenom.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();

            // Validate phone number if it is not empty
            if (!phone.isEmpty() && !phone.matches("\\d{10}")) {
                lblStatus.setText("Phone number must be exactly 10 digits.");
                return;
            }

            // Create the student object and set the fields
            Student student = new Student();
            student.setStudent_ID(studentID);
            student.setNom(nom);
            student.setPrenom(prenom);
            student.setEmail(email);
            student.setPhone(phone);

            // Call the method to update the student
            boolean success = Student.UpdateStudent(student); // You need to define this

            if (success) {
                lblStatus.setText("Student updated successfully.");
                loadStudents();
            } else {
                lblStatus.setText("Failed to update student.");
            }

        } catch (Exception ex) {
            lblStatus.setText("Invalid input.");
            ex.printStackTrace();
        }
    }

    public JPanel getEditStudentPanel() {
        return panel1;
    }
}

