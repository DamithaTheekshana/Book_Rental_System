package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookPageRepository {

    ResultSet getAllBooks() throws SQLException;

    ResultSet getSearchedBook(String bookName) throws SQLException;

    void addBook(String bookId, String title, String author, int qty, String language);

    void deleteBook(String bookId);

    void updateBook(String bookId, String title, String author, int qty, String language);

    boolean updateBookQty(int qty,String bookId);

    boolean addedBookQty(int qty, String bookId);

    ResultSet getOldRentalQty(String rentalId);

    boolean updateRentalQty(String bookId, int qty, int difference);
}
