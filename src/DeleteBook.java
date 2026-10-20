import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteBook {
    private JPanel panel1;
    private JButton btnDelete;
    private JTable tblBooks;
    private JLabel lblStatus;
    private JButton btnBack;

    public DeleteBook() {
        loadBooks();

        btnDelete.addActionListener(e -> {
            int selectedRow = tblBooks.getSelectedRow();
            if (selectedRow != -1) {
                int bookID = (int) tblBooks.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(panel1,
                        "Are you sure you want to delete Book ID " + bookID + "?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = Book.DeleteBook(bookID);
                    if (success) {
                        ((DefaultTableModel) tblBooks.getModel()).removeRow(selectedRow);
                        lblStatus.setText("Book deleted successfully.");
                    } else {
                        lblStatus.setText("Failed to delete book.");
                    }
                }
            } else {
                lblStatus.setText("Please select a book to delete.");
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showBookmanage();
            }
        });
    }

    private void loadBooks() {
        List<Book> books = Book.SearchBook("", "", "");
        String[] columns = {"Book ID", "Title", "Author", "Category", "Year", "Available"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Book book : books) {
            Object[] row = {
                    book.getBookID(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getPublicationYear(),
                    book.getAvailableQuantity()
            };
            model.addRow(row);
        }

        tblBooks.setModel(model);
    }

    public JPanel getDeletePanel() {
        return panel1;
    }
}

