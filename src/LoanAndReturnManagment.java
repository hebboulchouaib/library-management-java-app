import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanAndReturnManagment {
    private JPanel LandRetPanel;
    private JButton btnNewLoan;
    private JButton btnReturnBook;
    private JButton btnBack;


    public LoanAndReturnManagment() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showMainMenu();
            }
        });
        btnNewLoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showNewLoan();}
        });
        btnReturnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showLoanRet();
            }
        });
    }

    public JPanel getLandRetManage() {
        return LandRetPanel;
    }
}
