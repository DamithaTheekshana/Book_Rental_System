package repository.Impl;

import db.DBConnection;
import repository.RentalBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalBookRepositoryImpl implements RentalBookRepository {
    @Override
    public ResultSet getAllRentalBooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbook");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }
}
