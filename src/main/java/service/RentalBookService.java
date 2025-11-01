package service;

import javafx.collections.ObservableList;
import model.dto.RentalBook;

import java.sql.SQLException;

public interface RentalBookService {
    ObservableList<RentalBook> getAllRentalBooks() throws SQLException;

    void addRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty);

    void deleteRental(String deleteID);

    void updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty);

    ObservableList<RentalBook> searchRental(String rentalId) throws SQLException;
}
