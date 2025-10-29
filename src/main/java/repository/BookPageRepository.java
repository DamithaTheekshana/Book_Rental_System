package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookPageRepository {
    ResultSet getAllCustomers() throws SQLException;

    ResultSet getSearchedBook(String bookName) throws SQLException;

    void addBook(String bookId, String title, String author, int qty, String language);

    void deleteBook(String bookId);

    void updateBook(String bookId, String title, String author, int qty, String language);
}
