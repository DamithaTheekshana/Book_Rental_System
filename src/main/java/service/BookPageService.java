package service;

import javafx.collections.ObservableList;
import model.dto.Book;
import model.dto.Customer;

import java.sql.SQLException;

public interface BookPageService {
    ObservableList<Book> getAllCustomers();

    ObservableList<Book> getSearchedBook(String bookName) throws SQLException;
}
