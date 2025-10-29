package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Book;
import model.dto.Customer;
import repository.BookPageRepository;
import repository.Impl.BookPageRepositoryImpl;
import service.BookPageService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPageServiceImpl implements BookPageService {

    BookPageRepository bookPageRepository = new BookPageRepositoryImpl();
    ObservableList <Book> books = FXCollections.observableArrayList();

    @Override
    public ObservableList<Book> getAllCustomers() {
        try {
            ResultSet resultSet = bookPageRepository.getAllCustomers();

            while (resultSet.next()){
                books.add(new Book(
                        resultSet.getString("Book_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Author"),
                        resultSet.getInt("Qty"),
                        resultSet.getString("Language")
                ));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         return books;
    }

    @Override
    public ObservableList<Book> getSearchedBook(String bookName) throws SQLException {
        ResultSet resultSet = bookPageRepository.getSearchedBook(bookName);
        while (resultSet.next()){
            books.add(new Book(
                    resultSet.getString("Book_ID"),
                    resultSet.getString("Title"),
                    resultSet.getString("Author"),
                    resultSet.getInt("Qty"),
                    resultSet.getString("Language")
            ));
        }
        return books;
    }

    @Override
    public void addBook(String bookId, String title, String author, int qty, String language) {
        bookPageRepository.addBook(bookId, title, author, qty, language);
    }

    @Override
    public void deleteBook(String bookId) {
        bookPageRepository.deleteBook(bookId);
    }

    @Override
    public void updateBook(String bookId, String title, String author, int qty, String language) {
        bookPageRepository.updateBook(bookId, title, author, qty, language);
    }
}
