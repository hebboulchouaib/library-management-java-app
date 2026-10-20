import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchBook {
    private JPanel SearchBookPanel;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtCategory;
    private JButton btnSearchButton;
    private JTable tblResults;
    private JButton btnBack;

    public SearchBook() {
        btnSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        // Initialize table with empty model
        tblResults.setModel(new DefaultTableModel(
                new Object[]{"ID", "Title", "Author", "Category", "Year", "Available"},
                0 // rows
        ));

        performSearch();

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.showBookmanage();
            }
        });
    }
    private void performSearch() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = txtCategory.getText().trim();

        List<Book> books = Book.SearchBook(title, author, category);

        DefaultTableModel model = (DefaultTableModel) tblResults.getModel();
        model.setRowCount(0); // Clear old results

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

        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(SearchBookPanel, "No books found.", "Search", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    public JPanel getSearchBookPanel() {
        return SearchBookPanel;
    }
}
