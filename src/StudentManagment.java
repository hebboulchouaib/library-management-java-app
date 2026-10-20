import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagment extends JPanel{
    private JPanel StudentManagmentPanel;
    private JButton btnAddStudent;
    private JButton btnEditStudent;
    private JButton btnDeleteStudent;
    private JButton btnSearchStudent;
    private JButton btnBack;


    public StudentManagment() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showMainMenu();}
        });


        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showAddStudent();}
        });
        btnEditStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnSearchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnEditStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showEditStudent();}
        });
        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showDeleteStudent();}
        });
        btnSearchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showStudentsList();}
        });
    }

    public JPanel getStudentManagmentPanel() {return StudentManagmentPanel;}
}
