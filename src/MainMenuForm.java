import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm extends JPanel{
    private JButton btnBookManagement;
    private JPanel panel1;
    private JButton btnStudentManagement;
    private JButton btnLoanManagement;


    public MainMenuForm() {
        btnBookManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showBookmanage();
            }
        });
        btnStudentManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showStudentsManage();
            }
        });
        btnLoanManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showLandRetManage();
            }
        });
    }
    public JPanel getMainPanel() {
        return panel1;
    }
}
