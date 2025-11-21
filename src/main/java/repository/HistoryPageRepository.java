package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HistoryPageRepository {

    ResultSet getAllHistories() throws SQLException;

    boolean addHistory(String bookId, String custId, String rentalDate, String dueDate, int qty);

    boolean updateTblHistory(String bookId, String custId, String returnDate, int overdueDays, double fineAmount);

    boolean deleteHistoryTbl(String bookId, String custId, String rentalDate, String dueDate);

    void clearData();
}
