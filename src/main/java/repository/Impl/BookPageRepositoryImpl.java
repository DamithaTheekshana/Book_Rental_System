package repository.Impl;

import db.DBConnection;
import repository.BookPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPageRepositoryImpl implements BookPageRepository {
    @Override
    public ResultSet getAllCustomers() throws SQLException {

        String SQL = "SELECT * FROM book";

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();

        return resultSet;
    }

    @Override
    public ResultSet getSearchedBook(String bookName) throws SQLException {
        String SQL = "SELECT * FROM book WHERE Title = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,bookName);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }
}
