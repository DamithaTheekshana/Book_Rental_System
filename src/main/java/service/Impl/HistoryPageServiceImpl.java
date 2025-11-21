package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentalBookHistory;
import repository.HistoryPageRepository;
import repository.Impl.HistoryRepositoryImpl;
import repository.Impl.RentalBookRepositoryImpl;
import repository.RentalBookRepository;
import service.HistoryPageService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryPageServiceImpl implements HistoryPageService {

    HistoryPageRepository historyPageRepository = new HistoryRepositoryImpl();
    ObservableList <RentalBookHistory> rentalBookHistories = FXCollections.observableArrayList();

    @Override
    public ObservableList<RentalBookHistory> getAllHistory() throws SQLException {
        ResultSet resultSet = historyPageRepository.getAllHistories();
        while (resultSet.next()){
            rentalBookHistories.add(new RentalBookHistory(
                    resultSet.getString("Book_ID"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("Qty"),
                    resultSet.getDate("Return_Date"),
                    resultSet.getInt("Overdue_Days"),
                    resultSet.getDouble("Fine_Amount")
            ));
        }
        return rentalBookHistories;
    }

    @Override
    public boolean addHistory(String bookId, String custId, String rentalDate, String dueDate, int qty) {
        boolean addedHistory = historyPageRepository.addHistory(bookId, custId, rentalDate, dueDate, qty);
        return addedHistory;
    }

    @Override
    public boolean updateTblHistory(String bookId, String custId, String returnDate, int overdueDays, double fineAmount) {
        boolean updateTblHistory = historyPageRepository.updateTblHistory(bookId, custId, returnDate, overdueDays, fineAmount);
        return updateTblHistory;
    }

    @Override
    public void clearData() {
        historyPageRepository.clearData();
    }

    @Override
    public boolean deleteHistoryTbl(String bookId, String custId, String rentalDate, String dueDate) {
        boolean isDeleteHistoryTbl = historyPageRepository.deleteHistoryTbl(bookId, custId, rentalDate, dueDate);
        return isDeleteHistoryTbl;
    }
}
