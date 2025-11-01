package service;

import javafx.collections.ObservableList;
import model.dto.RentalBookHistory;

import java.sql.SQLException;

public interface HistoryPageService {
    ObservableList<RentalBookHistory> getAllHistory() throws SQLException;
}
