import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteStudentForm extends JPanel {
    private JPanel panel1;
    private JButton btnDelete;
    private JTable tblStudent;
    private JLabel lblStatus;
    private JButton btnBack;

    public DeleteStudentForm() {
        setupTable();
        loadStudents();

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showStudentsManage();
            }
        });

        tblStudent.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblStudent.getSelectedRow() != -1) {
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
        List<Student> students = Student.getAllStudents(); // Assuming you have this method

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

    private void deleteStudent() {
        int row = tblStudent.getSelectedRow();
        if (row != -1) {
            String studentID = tblStudent.getValueAt(row, 0).toString();

            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the student with ID " + studentID + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                boolean success = Student.deleteStudent(studentID);

                if (success) {
                    lblStatus.setText("Student deleted successfully.");
                    loadStudents();
                } else {
                    lblStatus.setText("Failed to delete student.");
                }
            }
        } else {
            lblStatus.setText("Please select a student to delete.");
        }
    }


    public JPanel getDeleteStdentPanel() {
        return panel1;
    }
}
