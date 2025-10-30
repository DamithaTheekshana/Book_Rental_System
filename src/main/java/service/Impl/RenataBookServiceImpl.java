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
}
