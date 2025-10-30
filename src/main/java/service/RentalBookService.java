package service;

import javafx.collections.ObservableList;
import model.dto.RentalBook;

import java.sql.SQLException;

public interface RentalBookService {
    ObservableList<RentalBook> getAllRentalBooks() throws SQLException;
}
