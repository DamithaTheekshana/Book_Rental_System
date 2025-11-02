package service;

import javafx.collections.ObservableList;
import model.dto.RentalBookHistory;

import java.sql.SQLException;

public interface HistoryPageService {
    ObservableList<RentalBookHistory> getAllHistory() throws SQLException;

    boolean addHistory(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty);

    boolean updateTblHistory(String rentalId, String returnDate, int overdueDays, double fineAmount);

    void clearData();
}
