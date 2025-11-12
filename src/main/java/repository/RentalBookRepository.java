package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentalBookRepository {
    ResultSet getAllRentalBooks() throws SQLException;

    boolean addRentakBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty);

    boolean deleteRental(String deleteID);

    boolean updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty);

    ResultSet searchRental(String rentalId) throws SQLException;

    boolean returnRental(String rentalId);
}
