package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HistoryPageRepository {
    ResultSet getAllHistories() throws SQLException;
}
