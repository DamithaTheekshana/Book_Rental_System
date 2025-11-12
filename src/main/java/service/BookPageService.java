package service;

import javafx.collections.ObservableList;
import model.dto.Book;
import model.dto.Customer;

import java.sql.SQLException;

public interface BookPageService {
    ObservableList<Book> getAllBooks();

    ObservableList<Book> getSearchedBook(String bookName) throws SQLException;

    void addBook(String bookId, String title, String author, int qty, String language);

    void deleteBook(String bookId);

    void updateBook(String bookId, String title, String author, int qty, String language);

    boolean updateBookQty(int qty, String bookId);

    boolean addedBookQty(int qty, String bookId);
}
