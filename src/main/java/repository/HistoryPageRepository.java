package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HistoryPageRepository {
    ResultSet getAllHistories() throws SQLException;

    boolean addHistory(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty);

    boolean updateTblHistory(String rentalId, String returnDate, int overdueDays, double fineAmount);

    void clearData();
}
