import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditBook {
    private JPanel panel1;
    private JTextField txtTitle;
    private JTextField txtID;
    private JTextField txtAuthor;
    private JTextField txtCategory;
    private JTextField txtYear;
    private JTextField txtAvailable;
    private JButton btnUpdate;
    private JLabel lblStatus;
    private JTable tblBooks;
    private JButton btnBack;

    public EditBook() {
        setupTable();
        loadBooks();

        tblBooks.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblBooks.getSelectedRow() != -1) {
                populateFormFromSelectedRow();
            }
        });

        btnUpdate.addActionListener(e -> {
            updateBook();
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showBookmanage();
            }
        });
    }

    private void setupTable() {
        tblBooks.setModel(new DefaultTableModel(
                new Object[]{"ID", "Title", "Author", "Category", "Year", "Available"}, 0
        ));
    }

    private void loadBooks() {
        DefaultTableModel model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0);
        List<Book> books = Book.SearchBook("", "", "");

        for (Book book : books) {
            model.addRow(new Object[]{
                    book.getBookID(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getPublicationYear(),
                    book.getAvailableQuantity()
            });
        }
    }

    private void populateFormFromSelectedRow() {
        int row = tblBooks.getSelectedRow();
        txtID.setText(tblBooks.getValueAt(row, 0).toString());
        txtTitle.setText(tblBooks.getValueAt(row, 1).toString());
        txtAuthor.setText(tblBooks.getValueAt(row, 2).toString());
        txtCategory.setText(tblBooks.getValueAt(row, 3).toString());
        txtYear.setText(tblBooks.getValueAt(row, 4).toString());
        txtAvailable.setText(tblBooks.getValueAt(row, 5).toString());
    }

    private void updateBook() {
        try {
            int bookID = Integer.parseInt(txtID.getText());
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();
            String category = txtCategory.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());
            int available = Integer.parseInt(txtAvailable.getText().trim());

            Book book = new Book();
            book.setBook_ID(bookID);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setPublicationYear(year);
            book.setAvailableQuantity(available);

            boolean success = Book.UpdateBook(book);

            if (success) {
                lblStatus.setText("Book updated successfully.");
                loadBooks();
            } else {
                lblStatus.setText("Failed to update book.");
            }

        } catch (Exception ex) {
            lblStatus.setText("Invalid input.");
            ex.printStackTrace();
        }
    }

    public JPanel getUpdatePanel() {
        return panel1;
    }
}

