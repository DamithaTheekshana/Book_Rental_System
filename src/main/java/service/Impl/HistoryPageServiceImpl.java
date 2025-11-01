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
                    resultSet.getString("Rental_ID"),
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
}
