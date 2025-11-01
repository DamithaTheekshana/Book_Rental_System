package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentalBook;
import repository.Impl.RentalBookRepositoryImpl;
import repository.RentalBookRepository;
import service.RentalBookService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RenataBookServiceImpl implements RentalBookService {

    ObservableList <RentalBook> rentalBooks = FXCollections.observableArrayList();
    RentalBookRepository rentalBookRepository = new RentalBookRepositoryImpl();

    @Override
    public ObservableList<RentalBook> getAllRentalBooks() throws SQLException {
        ResultSet resultSet = rentalBookRepository.getAllRentalBooks();

        while (resultSet.next()){
            rentalBooks.add(new RentalBook(
                    resultSet.getString("Rental_ID"),
                    resultSet.getString("Book_ID"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("Qty")
            ));
        }
        return rentalBooks;
    }

    @Override
    public void addRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) {
        rentalBookRepository.addRentakBook(rentalId, bookId, custId, rentalDate, dueDate, qty);
    }

    @Override
    public void deleteRental(String deleteID) {
        rentalBookRepository.deleteRental(deleteID);
    }

    @Override
    public void updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) {
        rentalBookRepository.updateRentalBook(rentalId, bookId, custId, rentalDate, dueDate, qty);
    }

    @Override
    public ObservableList<RentalBook> searchRental(String rentalId) throws SQLException {
        ResultSet resultSet = rentalBookRepository.searchRental(rentalId);
        while (resultSet.next()){
            rentalBooks.add(new RentalBook(
                    resultSet.getString("Rental_ID"),
                    resultSet.getString("Book_ID"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("Qty")
            ));
        }
        return rentalBooks;
    }
}
