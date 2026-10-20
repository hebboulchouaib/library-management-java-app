import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentListForm extends JPanel {
    private JButton btnBack;
    private JTable tblStudent;
    private JPanel panel1;

    public StudentListForm() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showStudentsManage();
            }
        });

        setupTable();
        loadStudents(); // Load the students on form creation
    }

    private void setupTable() {
        tblStudent.setModel(new DefaultTableModel(
                new Object[]{"Student ID", "Nom", "Prenom", "Email", "Phone"}, 0
        ));
    }

    private void loadStudents() {
        DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
        model.setRowCount(0); // Clear existing rows

        List<Student> students = Student.getAllStudents();
        for (Student s : students) {
            model.addRow(new Object[]{
                    s.getStudent_ID(),
                    s.getNom(),
                    s.getPrenom(),
                    s.getEmail(),
                    s.getPhone()
            });
        }
    }





    public JPanel getStudentListPanel() {return panel1;}
}
