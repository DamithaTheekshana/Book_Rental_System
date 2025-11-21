package service;

import javafx.collections.ObservableList;
import model.dto.RentalBook;

import java.sql.SQLException;

public interface RentalBookService {

    ObservableList<RentalBook> getAllRentalBooks() throws SQLException;

    void addRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) throws SQLException;

    void deleteRental(String deleteID, int qty, String bookId, String custId, String rentalDate, String dueDate) throws SQLException;

    void updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) throws SQLException;

    ObservableList<RentalBook> searchRental(String rentalId) throws SQLException;

    void setReturn(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty, String returnDate, int overdueDays, double fineAmount) throws SQLException;
}
