import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookForm extends JPanel{
    private JPanel panel1;
    private JTextField txtBookID;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtCategory;
    private JTextField txtQuantity;
    private JTextField txtPublicationYear;
    private JButton btnSave;
    private JButton btnBack;

    public AddBookForm() {
        btnSave.addActionListener(e -> {
            try {
                Book book = new Book();
                book.setBook_ID(Integer.parseInt(txtBookID.getText()));
                book.setTitle(txtTitle.getText());
                book.setAuthor(txtAuthor.getText());
                book.setCategory(txtCategory.getText());
                book.setAvailableQuantity(Integer.parseInt(txtQuantity.getText()));
                book.setPublicationYear(Integer.parseInt(txtPublicationYear.getText()));

                if (Book.ADD_Book(book)) {
                    JOptionPane.showMessageDialog(this, "Book added!");
                    Window window = SwingUtilities.getWindowAncestor(this);
                    window.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showBookmanage();
            }
        });
    }

    public JPanel getAddBookPanel() {
        return panel1;
    }
}
