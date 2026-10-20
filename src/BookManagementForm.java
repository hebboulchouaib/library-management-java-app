import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookManagementForm extends JPanel{
    private JButton btnAddBook;
    private JPanel panel1;
    private JButton btnEditBook;
    private JButton btnDeleteBook;
    private JButton btnSearchBook;
    private JButton btnBack;

    public BookManagementForm() {
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showAddBook();
            }
        });

        btnSearchBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showSearchBook();
            }
        });

        btnDeleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showDeleteBook();
            }
        });

        btnEditBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {App.showUpdateBook();
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showMainMenu();
            }
        });
    }
    public JPanel getBookManagePanel() {
        return panel1;
    }
}
