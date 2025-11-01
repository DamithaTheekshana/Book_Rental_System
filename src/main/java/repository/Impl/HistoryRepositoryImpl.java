package repository.Impl;

import db.DBConnection;
import repository.HistoryPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRepositoryImpl implements HistoryPageRepository {
    @Override
    public ResultSet getAllHistories() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbookhistory");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }
}
