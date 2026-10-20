import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    protected int Book_ID;
    protected String Title;
    protected String Author;
    protected String Category;
    protected int PublicationYear;
    protected int AvailableQuantity;
    // getters
    public int getBookID() { return Book_ID; }
    public String getTitle() { return Title; }
    public String getAuthor() { return Author; }
    public String getCategory() { return Category; }
    public int getPublicationYear() { return PublicationYear; }
    public int getAvailableQuantity() { return AvailableQuantity; }
    //setters
    public void setBook_ID(int Book_ID){this.Book_ID = Book_ID;}
    public void setTitle(String Title){this.Title = Title;}
    public void setAuthor(String Author){this.Author = Author;}
    public void setCategory(String Category){this.Category = Category;}
    public void setPublicationYear(int PublicationYear){this.PublicationYear = PublicationYear;}
    public void setAvailableQuantity(int AvailableQuantity){this.AvailableQuantity = AvailableQuantity;}

    //add a book
    public static boolean ADD_Book(Book book) {
        String sql = "INSERT INTO Book (Book_ID, Title, Author, Category, "
                + "PublicationYear, AvailableQuantity) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, book.getBookID());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getCategory());
            pstmt.setInt(5, book.getPublicationYear());
            pstmt.setInt(6, book.getAvailableQuantity());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting book:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }

    // search a book
    public static List<Book> SearchBook(String title, String author, String category) {
        List<Book> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Book WHERE 1=1");

        // Build query dynamically based on input parameters
        if (!title.isEmpty()) sql.append(" AND LOWER(Title) LIKE ?");
        if (!author.isEmpty()) sql.append(" AND LOWER(Author) LIKE ?");
        if (!category.isEmpty()) sql.append(" AND LOWER(Category) LIKE ?");

        // Add order by clause
        sql.append(" ORDER BY Book_ID ASC");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (!title.isEmpty()) pstmt.setString(paramIndex++, "%" + title.toLowerCase() + "%");
            if (!author.isEmpty()) pstmt.setString(paramIndex++, "%" + author.toLowerCase() + "%");
            if (!category.isEmpty()) pstmt.setString(paramIndex, "%" + category.toLowerCase() + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setBook_ID(rs.getInt("Book_ID"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setCategory(rs.getString("Category"));
                    book.setPublicationYear(rs.getInt("PublicationYear"));
                    book.setAvailableQuantity(rs.getInt("AvailableQuantity"));
                    results.add(book);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching books:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
        return results;
    }
    public static boolean DeleteBook(int bookID) {
        String sql = "DELETE FROM Book WHERE Book_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting book:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }
    //update a book
    public static boolean UpdateBook(Book book) {
        String sql = "UPDATE Book SET Title = ?, Author = ?, Category = ?, "
                + "PublicationYear = ?, AvailableQuantity = ? WHERE Book_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setInt(5, book.getAvailableQuantity());
            pstmt.setInt(6, book.getBookID());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
            return false;
        }
    }
    // get the list of all books
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book ORDER BY Title ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBook_ID(rs.getInt("BOOK_ID"));
                book.setTitle(rs.getString("TITLE"));
                book.setAuthor(rs.getString("AUTHOR"));
                book.setCategory(rs.getString("CATEGORY"));
                book.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
                book.setAvailableQuantity(rs.getInt("AVAILABLEQUANTITY"));
                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching book:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
        return books;
    }
    // get the book id
    public static Book getBookByID(int id) {
        String sql = "SELECT * FROM Book WHERE Book_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setBook_ID(rs.getInt("Book_ID"));
                book.setTitle(rs.getString("Title"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // decrement the book quantity when a loan is saved
    public static boolean decrementQuantity(int bookId) {
        String sql = "UPDATE Book SET AvailableQuantity = AvailableQuantity - 1 WHERE Book_ID = ? AND AvailableQuantity > 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error decrementing book quantity: " + e.getMessage());
            return false;
        }
    }


    //for increamenting the AvailableQuantity of a book after a book return
    public static boolean incrementQuantity(int bookId) {
        String sql = "UPDATE Book SET AvailableQuantity = AvailableQuantity + 1 WHERE Book_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error incrementing book quantity: " + e.getMessage());
            return false;
        }
    }
    @Override
    public String toString() {
        return Title; // or title + " by " + author;
    }
}

